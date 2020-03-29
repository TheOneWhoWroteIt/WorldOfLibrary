package by.laniakea.controllers;

import by.laniakea.model.books.Book;
import by.laniakea.model.list.OrderReader;
import by.laniakea.model.list.RequestFromReader;
import by.laniakea.model.users.Employee;
import by.laniakea.model.users.Reader;


import by.laniakea.repository.*;
import by.laniakea.util.UtilAuthorization;
import by.laniakea.util.UtilPagination;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

import java.util.List;


@Controller
@SessionAttributes(value = {"user"})
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class AuthorizationController {

    private final ReaderServiceRepository readerServiceRepository;
    private final RequestFromReaderServiceRepository requestFromReaderServiceRepository;
    private final BookServiceRepository bookServiceRepository;
    private final UtilPagination<Book> utilPaginationBook;
    private final UtilPagination<OrderReader> utilPaginationOrderReader;
    private final UtilPagination<RequestFromReader> utilPaginationRequest;
    private final EmployeeServiceRepository employeeServiceRepository;
    private final UtilAuthorization utilAuthorization;
    private final OrderReaderServiceRepository orderReaderServiceRepository;
    private static final int QUANTITY_OBJECT_ON_PAGE = 8;


    @GetMapping("/libra/room")
    public String libraRoom(Model model, HttpServletRequest request) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        Employee libra = employeeServiceRepository.findByLogin(userName);

        String url = "/libra/room?page=";
        int pageNumber = utilPaginationBook.paginationNumber(request.getParameter("page"));
        Page<RequestFromReader> requestFromReaders = requestFromReaderServiceRepository.findAllRequest(utilPaginationRequest.pageableForm(QUANTITY_OBJECT_ON_PAGE, pageNumber, "id"));
        List<RequestFromReader> requestFromReadersList = requestFromReaders.getContent();


        utilPaginationRequest.paginationNextAndPrevious(requestFromReaders, pageNumber);

        model.addAttribute("user", libra);
        model.addAttribute("requestFromReaders", requestFromReaders);
        model.addAttribute("requestFromReadersList", requestFromReadersList);
        model.addAttribute("numberPage", pageNumber);
        model.addAttribute("pageUrl", url);

        return "libra_room";
    }

    @GetMapping("/admin/room")
    public String adminRoom(Model model, HttpServletRequest request) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        Employee admin = employeeServiceRepository.findByLogin(userName);

        String url = "/admin/room?page=";
        int pageNumber = utilPaginationOrderReader.paginationNumber(request.getParameter("page"));
        Page<OrderReader> orderReaders = orderReaderServiceRepository.findOverdueOrderReader(utilPaginationOrderReader.pageableForm(QUANTITY_OBJECT_ON_PAGE, pageNumber, "id"));
        List<OrderReader> orderReaderList = orderReaders.getContent();

        utilPaginationOrderReader.paginationNextAndPrevious(orderReaders, pageNumber);

        model.addAttribute("user", admin);
        model.addAttribute("orderReaders", orderReaders);
        model.addAttribute("orderReaderList", orderReaderList);
        model.addAttribute("numberPage", pageNumber);
        model.addAttribute("pageUrl", url);
        return "admin_room";
    }

    @GetMapping("/reader/room")
    public String readerRoom(Model model, HttpServletRequest request) {

        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        Reader reader = readerServiceRepository.findByLogin(userName);

        //RequestFromReader requestFromReader = requestFromReaderServiceRepository.findByReaderId(reader.getTicketNumber());
        RequestFromReader requestFromReader = reader.getRequestFromReader();
       if( requestFromReader != null
               && !requestFromReader.getValidUntilDateRequest().isAfter(LocalDate.now())
               && requestFromReader.getOrder() == null){
           requestFromReaderServiceRepository.delete(requestFromReader);
       }

        //String requestId = requestFromReaderServiceRepository.findRequestByReaderId(reader.getTicketNumber());

        String url = "/reader/room?page=";
        int pageNumber = utilPaginationBook.paginationNumber(request.getParameter("page"));
        Page<Book> books = bookServiceRepository.findAll(utilPaginationBook.pageableForm(QUANTITY_OBJECT_ON_PAGE, pageNumber, "id"));
        List<Book> booksList = books.getContent();

        utilPaginationBook.paginationNextAndPrevious(books, pageNumber);


        model.addAttribute("user", reader);
        //model.addAttribute("requestFromReader", /*requestId*/ requestFromReader.getId());
        model.addAttribute("books", books);
        model.addAttribute("booksList", booksList);
        model.addAttribute("numberPage", pageNumber);
        model.addAttribute("pageUrl", url);

        return "reader_room";

    }

    @PostMapping("/authorization")
    public String authorizationUser() {

        SimpleGrantedAuthority roles = (SimpleGrantedAuthority) SecurityContextHolder.getContext()
                .getAuthentication().getAuthorities()
                .stream().findFirst().get();

        return utilAuthorization.urlPage(roles);
    }


}
