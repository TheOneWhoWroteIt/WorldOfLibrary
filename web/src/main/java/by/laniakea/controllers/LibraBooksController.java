package by.laniakea.controllers;

import by.laniakea.dto.BookDTO;
import by.laniakea.model.books.Book;

import by.laniakea.repository.BookServiceRepository;
import by.laniakea.util.UtilPagination;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
@SessionAttributes(names = {"book"})
public class LibraBooksController {


    private final BookServiceRepository bookServiceRepository;
    private final UtilPagination<Book> utilPaginationBook;

    private static final int QUANTITY_OBJECT_ON_PAGE = 8;

    @GetMapping("/libra/add_book")
    public String showAddBook(Model model){

        model.addAttribute("bookDTO", new BookDTO());
        return "libra_add_book";
    }

    @PostMapping("/libra/add_book")
    public String actionAddBook(@Valid @ModelAttribute(name = "bookDTO") BookDTO bookDTO,
                                BindingResult result, HttpServletRequest request){

        if(result.hasErrors()){
            return "libra_add_book";
        }
        bookDTO.setGenre(request.getParameter("genre"));
        bookServiceRepository.save(bookDTO);
        return "redirect:/libra/books_list";
    }

    @GetMapping("/libra/book_info")
    public String showBookInfo(HttpServletRequest request, Model model){
        String[] index = request.getParameterValues("bookId");
        Book book = bookServiceRepository.findByID(Long.valueOf(index[0]));
        model.addAttribute("book", book);
        return "libra_book_info";
    }

    @PostMapping("/libra/book_info")
    public String actionBookInfo(){
        return "libra_book_info";
    }

    @GetMapping("/libra/edit_book")
    public String showEditBook(HttpServletRequest request, Model model){
        String[] index = request.getParameterValues("bookId");
        Book book = bookServiceRepository.findByID(Long.valueOf(index[0]));
        model.addAttribute("bookDTO", new BookDTO());
        model.addAttribute("book", book);
        return "libra_book_edit";
    }

    @PostMapping("/libra/edit_book")
    public String actionEditBook(@ModelAttribute(name = "book")Book book,
                                 @ModelAttribute(name = "bookDTO")BookDTO bookDTO,
                                 HttpServletRequest request){


        Book bookUpdate = bookServiceRepository.findByID(Long.valueOf(book.getId()));
        bookDTO.setGenre(request.getParameter("genre"));
        bookServiceRepository.update(bookDTO, bookUpdate);
        return "redirect:/libra/books_list";
    }

    @GetMapping("/libra/books_list")
    public String showBooksList(Model model, HttpServletRequest request){

        String url = "/libra/books_list?page=";
        int pageNumber = utilPaginationBook.paginationNumber(request.getParameter("page"));
        Page<Book> books = bookServiceRepository.findAll(utilPaginationBook.pageableForm(QUANTITY_OBJECT_ON_PAGE, pageNumber, "id"));
        List<Book> booksList = books.getContent();

        utilPaginationBook.paginationNextAndPrevious(books, pageNumber);

        model.addAttribute("books", books);
        model.addAttribute("booksList", booksList);
        model.addAttribute("numberPage", pageNumber);
        model.addAttribute("pageUrl", url);

        return "libra_books_list";
    }

    @PostMapping("/libra/books_list")
    public String actionBooksList(){
        return "libra_books_list";
    }

    @PostMapping("/libra/find_book_by_title")
    public String actionSearchBook(HttpServletRequest request, Model model){

        String url = "/libra/books_list?page=";
        int pageNumber = utilPaginationBook.paginationNumber(request.getParameter("page"));

        Page<Book> books = bookServiceRepository.findByTitle(request.getParameter("search"),
                utilPaginationBook.pageableForm(8, pageNumber, "id"));
        List<Book> booksList = books.getContent();


        utilPaginationBook.paginationNextAndPrevious(books, pageNumber);

        model.addAttribute("books", books);
        model.addAttribute("booksList", booksList);
        model.addAttribute("numberPage", pageNumber);
        model.addAttribute("pageUrl", url);

        return "libra_books_list";
    }
}
