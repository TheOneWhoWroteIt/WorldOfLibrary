package by.laniakea.controllers;

import by.laniakea.dto.EmployeeDTO;
import by.laniakea.model.users.Employee;
import by.laniakea.model.users.Reader;
import by.laniakea.model.users.User;
import by.laniakea.repository.EmployeeServiceRepository;
import by.laniakea.repository.ReaderServiceRepository;
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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class AdminMenuController {

    private final UtilPagination<Employee> utilPaginationEmployee;
    private final EmployeeServiceRepository employeeServiceRepository;
    private final UtilPagination<Reader> utilPaginationBlackList;
    private final ReaderServiceRepository readerServiceRepository;
    private static final int QUANTITY_OBJECT_ON_PAGE = 8;

    @GetMapping("/admin/add_employee")
    public String showAddEmployee(Model model) {
        model.addAttribute("employeeDTO", new EmployeeDTO());
        return "admin_add_employee";
    }

    @PostMapping("/admin/add_employee")
    public String actionAddEmployee(@Valid @ModelAttribute(name = "employeeDTO") EmployeeDTO employeeDTO,
                                    BindingResult result) {

        if (result.hasErrors()) {
            return "admin_add_employee";
        }

        employeeServiceRepository.save(employeeDTO);

        return "redirect:/admin/employee_list";
    }

    @GetMapping("/admin/employee_info")
    public String showEmployeeInfo(HttpServletRequest request, Model model) {
        String[] index = request.getParameterValues("userId");
        Employee employee = (Employee) employeeServiceRepository.findById(Long.valueOf(index[0]));
        model.addAttribute("user", employee);
        return "admin_employee_info";
    }

    @PostMapping("/admin/employee_info")
    public String actionEmployeeInfo() {
        return "admin_employee_info";
    }

    @GetMapping("/admin/edit_employee")
    public String showEditEmployee(Model model, HttpServletRequest request) {

        String[] index = request.getParameterValues("userId");
        Employee employee = employeeServiceRepository.findById(Long.valueOf(index[0]));
        model.addAttribute("employeeDTO", new EmployeeDTO());
        model.addAttribute("user", employee);
        return "admin_edit_employee";
    }

    @PostMapping("/admin/edit_employee")
    public String actionEditEmployee(@ModelAttribute(name = "employeeDTO") EmployeeDTO employeeDTO,
                                     @ModelAttribute(name = "user") Employee employee) {
        Employee employeeUpdate = (Employee) employeeServiceRepository.findById(employee.getPersonnelNumber());
        employeeServiceRepository.update(employeeDTO, employeeUpdate);
        return "redirect:/admin/edit_employee";
    }

    @GetMapping("/admin/employees_list")
    public String showEmployeesList(HttpServletRequest request, Model model) {

        String url = "/admin/employee_list?page=";
        int pageNumber = utilPaginationEmployee.paginationNumber(request.getParameter("page"));
        Page<Employee> employees = employeeServiceRepository.findAll(utilPaginationEmployee.pageableForm(QUANTITY_OBJECT_ON_PAGE, pageNumber, "personnelNumber"));
        List<Employee> employeesList = employees.getContent();

        utilPaginationEmployee.paginationNextAndPrevious(employees, pageNumber);

        model.addAttribute("employees", employees);
        model.addAttribute("employeesList", employeesList);
        model.addAttribute("numberPage", pageNumber);
        model.addAttribute("pageUrl", url);

        return "admin_employees_list";
    }

    @PostMapping("/admin/employees_list")
    public String actionEmployeesList() {
        return "admin_employees_list";
    }

    @GetMapping("/admin/black_list")
    public String showBlackList(HttpServletRequest request, Model model) {

        String url = "/admin/black_list?page=";
        int pageNumber = utilPaginationBlackList.paginationNumber(request.getParameter("page"));

        Page<Reader> blackLists = readerServiceRepository.findAllReaderFromBlackList(utilPaginationEmployee.pageableForm(QUANTITY_OBJECT_ON_PAGE, pageNumber, "ticketNumber"));
        List<Reader> blackListsContent = blackLists.getContent();

        utilPaginationBlackList.paginationNextAndPrevious(blackLists, pageNumber);

        model.addAttribute("blackLists", blackLists);
        model.addAttribute("blackListsContent", blackListsContent);
        model.addAttribute("numberPage", pageNumber);
        model.addAttribute("pageUrl", url);
        return "admin_black_list";
    }

    @PostMapping("/admin/black_list")
    public String actionBlackList(HttpServletRequest request) {
        String[] index = request.getParameterValues("readerId");
        Reader reader = readerServiceRepository.findByID(Long.valueOf(index[0]));
        reader.setBlackList(false);
        readerServiceRepository.saveReader(reader);
        return "redirect:/admin/black_list";

    }

    @PostMapping("/admin/room")
    public String actionOverdueOrder(HttpServletRequest request) {
        String[] index = request.getParameterValues("readerId");
        Reader reader = readerServiceRepository.findByID(Long.valueOf(index[0]));
        reader.setBlackList(true);
        readerServiceRepository.saveReader(reader);
        return "redirect:/admin/room";
    }
}
