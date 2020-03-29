package by.laniakea.controllers;

import by.laniakea.dto.AuthorDTO;
import by.laniakea.model.books.Author;
import by.laniakea.model.books.Book;
import by.laniakea.repository.AuthorServiceRepository;
import by.laniakea.repository.BookServiceRepository;
import by.laniakea.util.UtilPagination;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
@SessionAttributes(names = {"author"})
public class LibraAuthorsController {

    private final AuthorServiceRepository authorServiceRepository;
    private final UtilPagination<Author> utilPaginationAuthor;
    private final BookServiceRepository bookServiceRepository;
    private final UtilPagination<Book> utilPaginationBook;

    private static final int QUANTITY_OBJECT_ON_PAGE = 8;

    @GetMapping("/libra/add_author")
    public String showAddAuthor(Model model){

        model.addAttribute("authorDTO", new Author());
        return "libra_add_author";
    }

    @PostMapping("/libra/add_author")
    public String actionAddAuthor(@Valid @ModelAttribute(name = "authorDTO")AuthorDTO authorDTO,
                                  BindingResult result/*, Model model*/){

        if(result.hasErrors()){
            return "libra_add_author";
        }
        //model.addAttribute("author", authorDTO);
        authorServiceRepository.save(authorDTO);
        return "redirect:/libra/authors_list";
    }

    @GetMapping("/libra/author_info")
    public String showAuthorInfo(){
        return "libra_author_info";
    }

    @PostMapping("/libra/author_info")
    public String actionAuthorInfo(HttpServletRequest request, Model model){
        String[] index = request.getParameterValues("authorId");
        Author author = authorServiceRepository.findByID(Long.valueOf(index[0]));
        model.addAttribute("author", author);
        return "libra_author_info";
    }

    @GetMapping("/libra/edit_author")
    public String showEditAuthor(HttpServletRequest request, Model model){
        String[] index = request.getParameterValues("authorId");
        Author author = authorServiceRepository.findByID(Long.valueOf(index[0]));
        model.addAttribute("author", author);
        model.addAttribute("authorDTO", new AuthorDTO());
        return "libra_edit_author";
    }

    @PostMapping("/libra/edit_author")
    public String actionEditAuthor(@ModelAttribute(name = "author") Author author,
                                   @ModelAttribute(name = "authorDTO")AuthorDTO authorDTO){

        Author authorUpdate = authorServiceRepository.findByID(author.getId());
        authorServiceRepository.update(authorDTO, authorUpdate);


        return "redirect:/libra/authors_list";
    }

    @GetMapping("/libra/authors_list")
    public String showAuthorsList(Model model, HttpServletRequest request){

        String url = "/libra/authors_list?page=";
        int pageNumber = utilPaginationAuthor.paginationNumber(request.getParameter("page"));
        Page<Author> authors = authorServiceRepository.findAll(utilPaginationAuthor.pageableForm(QUANTITY_OBJECT_ON_PAGE, pageNumber, "id"));
        List<Author> authorsList = authors.getContent();
        utilPaginationAuthor.paginationNextAndPrevious(authors, pageNumber);
        model.addAttribute("authors", authors);
        model.addAttribute("authorsList", authorsList);
        model.addAttribute("numberPage", pageNumber);
        model.addAttribute("pageUrl", url);

        return "libra_authors_list";
    }

    @PostMapping("/libra/authors_list")
    public String actionAuthorsList(){
        return "libra_authors_list";
    }

    @PostMapping("/libra/find_author")
    public String actionFindAuthor(HttpServletRequest request, Model model){

        String url = "/libra/authors_list?page=";
        int pageNumber = utilPaginationAuthor.paginationNumber(request.getParameter("page"));
        Page<Author> authors = authorServiceRepository.findByLastName(request.getParameter("search"),
                utilPaginationAuthor.pageableForm(8, pageNumber, "id"));
        List<Author> authorsList = authors.getContent();
        utilPaginationAuthor.paginationNextAndPrevious(authors, pageNumber);
        model.addAttribute("authors", authors);
        model.addAttribute("authorsList", authorsList);
        model.addAttribute("numberPage", pageNumber);
        model.addAttribute("pageUrl", url);

        return "libra_authors_list";
    }

}
