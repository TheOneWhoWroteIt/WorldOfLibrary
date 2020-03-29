package by.laniakea.controllers;

import by.laniakea.model.users.Reader;
import by.laniakea.repository.ReaderServiceRepository;
import by.laniakea.util.UtilPagination;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class LibraReadersController {

    private final ReaderServiceRepository readerServiceRepository;
    private final UtilPagination<Reader> utilPaginationReader;
    private static final int QUANTITY_OBJECT_ON_PAGE = 8;

    @GetMapping("/libra/reader_info")
    public String showReaderInfo(){
        return "libra_reader_info";
    }

    @PostMapping("/libra/reader_info")
    public String actionReaderInfo(HttpServletRequest request, Model model){
        String[] index = request.getParameterValues("readerId");
        Reader reader = readerServiceRepository.findByID(Long.valueOf(index[0]));
        model.addAttribute("reader", reader);
        return "libra_reader_info";
    }

    @GetMapping("/libra/readers_list")
    public String showReadersList(Model model, HttpServletRequest request){

        String url = "/libra/readers_list?page=";
        int pageNumber = utilPaginationReader.paginationNumber(request.getParameter("page"));
        Page<Reader> readers = readerServiceRepository.findAll(utilPaginationReader.pageableForm(QUANTITY_OBJECT_ON_PAGE, pageNumber, "ticketNumber"));
        List<Reader> readersList = readers.getContent();

        utilPaginationReader.paginationNextAndPrevious(readers, pageNumber);

        model.addAttribute("readers", readers);
        model.addAttribute("readersList", readersList);
        model.addAttribute("numberPage", pageNumber);
        model.addAttribute("pageUrl", url);

        return "libra_readers_list";
    }

    @PostMapping("/libra/readers_list")
    public String actionReadersList(){
        return "libra_readers_list";
    }

    @PostMapping("/libra/search_reader")
    public String actionSearchReader(HttpServletRequest request, Model model){

        Long readerId = Long.valueOf(request.getParameter("search"));
        Reader reader = readerServiceRepository.findByID(readerId);
        List<Reader> readersList = new ArrayList<>();
        readersList.add(reader);
        Page<Reader> readers = new PageImpl(readersList, utilPaginationReader.pageableForm(1, 0, "ticketNumber"), 1 );
        String url = "/libra/readers_list?page=";
        int pageNumber = 0;

        model.addAttribute("readers", readers);
        model.addAttribute("readersList", readersList);
        model.addAttribute("numberPage", pageNumber);
        model.addAttribute("pageUrl", url);

        return "libra_readers_list";
    }
}
