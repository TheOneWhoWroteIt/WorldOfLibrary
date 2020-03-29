package by.laniakea.controllers;

import by.laniakea.dto.ReaderDTO;
import by.laniakea.model.books.Author;
import by.laniakea.model.books.Book;
import by.laniakea.model.list.RequestFromReader;
import by.laniakea.model.users.Reader;
import by.laniakea.repository.AuthorServiceRepository;
import by.laniakea.repository.BookServiceRepository;
import by.laniakea.repository.ReaderServiceRepository;
import by.laniakea.repository.RequestFromReaderServiceRepository;
import by.laniakea.util.UtilPagination;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
@SessionAttributes(names = {"booksRequest", "user"})
public class ReaderMenuController {

    private final BookServiceRepository bookServiceRepository;
    private final AuthorServiceRepository authorServiceRepository;
    private final UtilPagination<Book> utilPaginationBook;
    private final ReaderServiceRepository readerServiceRepository;
    private final RequestFromReaderServiceRepository requestFromReaderServiceRepository;

    private static final int QUANTITY_OBJECT_ON_PAGE = 8;

    @GetMapping("/reader/edit_profile")
    public String showEditProfile(Model model){

        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        Reader reader = readerServiceRepository.findByLogin(userName);

        model.addAttribute("readerDTO", new ReaderDTO());
        model.addAttribute("user", reader);
        return "reader_edit_profile";
    }

    @PostMapping("/reader/edit_profile")
    public String actionEditProfile(@ModelAttribute(name = "readerDTO") ReaderDTO readerDTO,
                                    @ModelAttribute(name = "user") Reader reader){
        Reader readerUpdate = readerServiceRepository.findByID(reader.getTicketNumber());
        readerServiceRepository.update(readerDTO, readerUpdate);
        return "redirect:/reader/edit_profile";
    }

    @GetMapping("/reader/create_order")
    public String showCreateOrder(Model model){
        Set<Book> books = (Set<Book>) model.getAttribute("booksRequest");
        model.addAttribute("booksRequest", books);
        return "reader_create_order";
    }

    @PostMapping("/reader/create_order")
    public String actionCreateOrder(Model model){
        Set<Book> books = (Set<Book>) model.getAttribute("booksRequest");
        readerServiceRepository.createRequestBooks(books);
        return "redirect:/reader/room";
    }

    @PostMapping("/reader/request_add_book")
    public String actionRequestAddBook(Model model, HttpServletRequest request){
        Set<Book> booksRequest = (Set<Book>) model.getAttribute("booksRequest");
        String[] index = request.getParameterValues("bookId");
        Book book = bookServiceRepository.findByID(Long.valueOf(index[0]));
        Set<Book> books = bookServiceRepository.addBookInRequest(booksRequest, book);
        model.addAttribute("booksRequest", books);
        model.addAttribute("book", book);
        return "reader_book_info";


    }

    @PostMapping("/reader/book_delete")
    public String actionBookDelete(Model model, HttpServletRequest request){
        Set<Book> books = (Set<Book>) model.getAttribute("booksRequest");
        String[] index = request.getParameterValues("bookId");
        Book book = bookServiceRepository.findByID(Long.valueOf(index[0]));
        books.remove(book);
        model.addAttribute("booksRequest", books);
        return "reader_create_order";
    }

    @GetMapping("/reader/current_order")
    public String showCurrentOrder(Model model, HttpServletRequest request){


        Long readerId = Long.valueOf(request.getParameter("readerId"));

        RequestFromReader requestFromReader = requestFromReaderServiceRepository.findByReaderId(readerId);

        if((requestFromReader != null && requestFromReader.getOrder() == null) || (requestFromReader != null && requestFromReader.getOrder() != null && !requestFromReader.getOrder().isOrderExpired())) {
            Set<Book> books = requestFromReader.getBooks();

            model.addAttribute("requestOrder", requestFromReader);
            model.addAttribute("orderReader", requestFromReader.getOrder());
            model.addAttribute("books", books);

        }
        /*else if(requestFromReader != null && requestFromReader.getOrder() != null && !requestFromReader.getOrder().isOrderExpired()){
            Set<Book> books = requestFromReader.getBooks();
            model.addAttribute("orderReader", requestFromReader.getOrder());
            model.addAttribute("books", books);
        }*/

        return "reader_current_order";
    }

    @PostMapping("/reader/current_order")
    public String actionCurrentOrder(HttpServletRequest request){

        Long readerId = Long.valueOf(request.getParameter("readerId"));

        RequestFromReader requestFromReader = requestFromReaderServiceRepository.findByReaderId(readerId);

        if( requestFromReader != null && requestFromReader.getOrder() == null){
            requestFromReaderServiceRepository.delete(requestFromReader);
        }
        return "redirect:/reader/room";
    }

    @GetMapping("/reader/author_info")
    public String showAuthorInfo(HttpServletRequest request, Model model){
        String[] index = request.getParameterValues("authorId");
        Author author = authorServiceRepository.findByID(Long.valueOf(index[0]));
        List<Book> books = bookServiceRepository.findByAuthorId(author.getId());
        model.addAttribute("author", author);
        model.addAttribute("books", books);
        return "reader_author_info";
    }

    @PostMapping("/reader/author_info")
    public String actionAuthorInfo(){
        return "reader_author_info";
    }

    @GetMapping("/reader/book_info")
    public String showBookInfo(HttpServletRequest request, Model model){
        String[] index = request.getParameterValues("bookId");
        Book book = bookServiceRepository.findByID(Long.valueOf(index[0]));
        model.addAttribute("book", book);
        return "reader_book_info";
    }

    @PostMapping("/reader/book_info")
    public String actionBookInfo(){
        return "reader_book_info";
    }

    @GetMapping("/reader/find_by_genre")
    public String showFindByGenre(HttpServletRequest request, Model model){

        Page<Book> books;
        String url = "/reader/room?page=";
        int pageNumber = utilPaginationBook.paginationNumber(request.getParameter("page"));

        if(request.getParameter("genreName").equals("ALL")){
           books = bookServiceRepository.findAll(utilPaginationBook.pageableForm(QUANTITY_OBJECT_ON_PAGE, pageNumber, "id"));
        }
        else{
            books = bookServiceRepository.findByGenre(request.getParameter("genreName"), utilPaginationBook.pageableForm(QUANTITY_OBJECT_ON_PAGE, pageNumber, "id"));
        }

        List<Book> booksList = books.getContent();
        utilPaginationBook.paginationNextAndPrevious(books, pageNumber);

        model.addAttribute("books", books);
        model.addAttribute("booksList", booksList);
        model.addAttribute("numberPage", pageNumber);
        model.addAttribute("pageUrl", url);

        return "reader_room";
    }

    @GetMapping("/reader/find_by_letter")
    public String showBookByAlfavit(HttpServletRequest request, Model model){

        String url = "/reader/room?page=";
        int pageNumber = utilPaginationBook.paginationNumber(request.getParameter("page"));
        Page<Book> books = bookServiceRepository.findByAlfavit(request.getParameter("letter"),
                                                            utilPaginationBook.pageableForm(QUANTITY_OBJECT_ON_PAGE, pageNumber, "id"));

        List<Book> booksList = books.getContent();
        utilPaginationBook.paginationNextAndPrevious(books, pageNumber);

        model.addAttribute("books", books);
        model.addAttribute("booksList", booksList);
        model.addAttribute("numberPage", pageNumber);
        model.addAttribute("pageUrl", url);

        return "reader_room";

    }

    @GetMapping("/reader/find_by_title_or_name")
    public String showBookByTitleOrAuthorName(HttpServletRequest request, Model model){

        String url = "/reader/room?page=";
        int pageNumber = utilPaginationBook.paginationNumber(request.getParameter("page"));
        Page<Book> books;

        if(request.getParameter("searchradio").equals("bookSearch")){
            books = bookServiceRepository.findByTitle(request.getParameter("search"),
                    utilPaginationBook.pageableForm(8, pageNumber, "id"));
        }
        else{
            books = bookServiceRepository.findByAuthor(request.getParameter("search"),
                    utilPaginationBook.pageableForm(8, pageNumber, "id"));
        }

        List<Book> booksList = books.getContent();
        utilPaginationBook.paginationNextAndPrevious(books, pageNumber);

        model.addAttribute("books", books);
        model.addAttribute("booksList", booksList);
        model.addAttribute("numberPage", pageNumber);
        model.addAttribute("pageUrl", url);

        return "reader_room";
    }



}
