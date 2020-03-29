package by.laniakea.controllers;

import by.laniakea.model.books.Book;
import by.laniakea.model.list.OrderReader;
import by.laniakea.model.list.RequestFromReader;
import by.laniakea.repository.*;
import by.laniakea.util.UtilPagination;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.tags.form.CheckboxesTag;

import javax.servlet.http.HttpServletRequest;

import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

@Controller
@RequiredArgsConstructor(onConstructor = @_(@Autowired))

public class LibraMenuController {

    private final RequestFromReaderServiceRepository requestFromReaderServiceRepository;
    private final OrderReaderServiceRepository orderReaderServiceRepository;
    private final UtilPagination<OrderReader> utilPaginationOrderReader;
    private final UtilPagination<RequestFromReader> utilPaginationRequest;
    private final BookServiceRepository bookServiceRepository;
    private static final int QUANTITY_OBJECT_ON_PAGE = 8;

    @GetMapping("/libra/open_orders")
    public String showOpenOrders(Model model, HttpServletRequest request){

        String url = "/libra/open_orders?page=";
        int pageNumber = utilPaginationOrderReader.paginationNumber(request.getParameter("page"));
        Page<OrderReader> orders = orderReaderServiceRepository.findOpenOrder(utilPaginationOrderReader.pageableForm(QUANTITY_OBJECT_ON_PAGE, pageNumber, "orderNumber"));
        List<OrderReader> ordersList = orders.getContent();

        utilPaginationOrderReader.paginationNextAndPrevious(orders, pageNumber);

        model.addAttribute("orders", orders);
        model.addAttribute("ordersList", ordersList);
        model.addAttribute("numberPage", pageNumber);
        model.addAttribute("pageUrl", url);

        return "libra_open_orders";
    }

    @PostMapping("/libra/open_orders")
    public String actionOpenOrders(){
        return "libra_open_orders";
    }

    @GetMapping("/libra/request_from_reader")
    public String showRequestFromReader(HttpServletRequest request, Model model){
        String[] index = request.getParameterValues("requestId");
        RequestFromReader requestFromReader = requestFromReaderServiceRepository.findByID(Long.valueOf(index[0]));
        model.addAttribute("requestFromReader", requestFromReader);

        return "libra_request_from_reader";
    }

    @PostMapping("/libra/request_from_reader")
    public String actionRequestFromReader(HttpServletRequest request){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startOrder = LocalDate.parse(request.getParameter("startDate"), formatter);
        LocalDate validOrderDate = LocalDate.parse(request.getParameter("validDate"), formatter);
        String[] index = request.getParameterValues("requestId");
        String[] bookId = request.getParameterValues("check");
        RequestFromReader requestFromReader = requestFromReaderServiceRepository.findByID(Long.valueOf(index[0]));
        Set<Book> books = requestFromReaderServiceRepository.bookListForOpenOrder(requestFromReader.getBooks(), bookId);
        /*Set<Book> books = new HashSet<>();
        Iterator<Book> iterator = requestFromReader.getBooks().iterator();
        Book book;



        while (iterator.hasNext()){
            book = iterator.next();
            for (int i = 0; i < bookId.length; i++) {
                if (book.getId().toString().equals(bookId[i])){
                    book.setPrintEditionCount(book.getPrintEditionCount()-1);
                    books.add(book);
                }
            }
        }*/

        requestFromReader.setBooks(books);
        bookServiceRepository.saveAll(books);
        requestFromReaderServiceRepository.save(requestFromReader);

        OrderReader order = new OrderReader(requestFromReader, startOrder, validOrderDate);
        orderReaderServiceRepository.save(order);

        return "redirect:/libra/open_orders";
    }

    @GetMapping("/libra/order_info_and_close")
    public String showOrderInfo(HttpServletRequest request, Model model){
        String[] index = request.getParameterValues("orderId");
        OrderReader orderReader = orderReaderServiceRepository.findByID(Long.valueOf(index[0]));
        model.addAttribute("orderReader", orderReader);
        return "libra_order_info_and_action";
    }

    @PostMapping("/libra/order_info_and_close")
    public String actionOrderInfo(Model model, HttpServletRequest request){
        String[] index = request.getParameterValues("orderId");
        OrderReader orderReader = orderReaderServiceRepository.findByID(Long.valueOf(index[0]));
        String[] bookId = request.getParameterValues("check");
        Set<Book> books = requestFromReaderServiceRepository.bookListForCloseOrder(orderReader.getRequestFromReader().getBooks(), bookId);
        bookServiceRepository.saveAll(books);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate endOrder = LocalDate.parse(request.getParameter("endDate"), formatter);

        if(books.size() == orderReader.getRequestFromReader().getBooks().size()){
            orderReader.setEndOrder(endOrder);
            orderReader.setOrderExpired(true);
            orderReaderServiceRepository.save(orderReader);
        }else{
            // TODO: 24.02.2020 сделать обработку случая когда сдаются не все книги сразу
        }

        return "redirect:/libra/open_orders";
    }

    @PostMapping("/libra/search_request")
    public String actionSearchRequest(HttpServletRequest request, Model model){

        Long requestId = Long.valueOf(request.getParameter("search"));
        RequestFromReader requestFromReader = requestFromReaderServiceRepository.findByID(requestId);
        List<RequestFromReader> requestFromReaderList = new ArrayList<>();
        requestFromReaderList.add(requestFromReader);
        Page<RequestFromReader> requestFromReaders = new PageImpl(requestFromReaderList, utilPaginationRequest.pageableForm(1, 0, "id"), 1 );
        String url = "/libra/room?page=";
        int pageNumber = 0;

        model.addAttribute("requestFromReaders", requestFromReaders);
        model.addAttribute("requestFromReadersList", requestFromReaderList);
        model.addAttribute("numberPage", pageNumber);
        model.addAttribute("pageUrl", url);

        return "libra_room";
    }

    @PostMapping("/libra/search_order")
    public String actionSearchOrder(HttpServletRequest request, Model model){

        Long orderId = Long.valueOf(request.getParameter("search"));
        OrderReader orderReader = orderReaderServiceRepository.findByID(orderId);
        List<OrderReader> orderReaderList = new ArrayList<>();
        orderReaderList.add(orderReader);
        Page<OrderReader> orderReaders = new PageImpl(orderReaderList, utilPaginationOrderReader.pageableForm(1, 0, "id"), 1 );
        String url = "/libra/open_orders?page=";
        int pageNumber = 0;

        //utilPaginationOrderReader.paginationNextAndPrevious(orderReaders, pageNumber);

        model.addAttribute("orders", orderReaders);
        model.addAttribute("ordersList", orderReaderList);
        model.addAttribute("numberPage", pageNumber);
        model.addAttribute("pageUrl", url);

        return "libra_room";
    }



}
