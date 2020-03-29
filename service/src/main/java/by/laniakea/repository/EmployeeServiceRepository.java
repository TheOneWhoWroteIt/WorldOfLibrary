package by.laniakea.repository;

import by.laniakea.dto.EmployeeDTO;
import by.laniakea.model.users.Address;
import by.laniakea.model.users.Employee;
import by.laniakea.model.users.Role;
import by.laniakea.model.users.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Repository
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class EmployeeServiceRepository {

    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;


    public void save(EmployeeDTO object) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate birthday = LocalDate.parse(object.getBirthday(), formatter);
        LocalDate startWork = LocalDate.parse(object.getStartWork(), formatter);

        Address address = new Address(object.getCity(), object.getStreet(),
                Integer.valueOf(object.getHomeNumber()),
                Integer.valueOf(object.getRoomNumber()));

        String password = passwordEncoder.encode(object.getPassword());
        String roleEmployee = object.getRole();
        Role role = roleRepository.findByRoleName(roleEmployee);
        String photoPath = "/resources/img/app_img/no foto.jpg";


        Employee employee = new Employee(object.getFirstName(), object.getLastName(), birthday, object.getEmail(),
                                            object.getPhoneNumber(), role, object.getLogin(), password,
                                            address, object.getPosition(), startWork, photoPath);

        employeeRepository.save(employee);


    }

    public void update(EmployeeDTO employeeDTO, Employee employee){

        if(employeeDTO.getFirstName() != null && !employeeDTO.getFirstName().isEmpty()){
            employee.setFirstName(employeeDTO.getFirstName());
        }
        if(employeeDTO.getLastName() != null && !employeeDTO.getLastName().isEmpty()){
            employee.setLastName(employeeDTO.getLastName());
        }
        if(employeeDTO.getBirthday() != null && !employeeDTO.getBirthday().isEmpty()){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate birthday = LocalDate.parse(employeeDTO.getBirthday(), formatter);
            employee.setBirthday(birthday);
        }
        if(employeeDTO.getEmail() != null && !employeeDTO.getEmail().isEmpty()){
            employee.setEmail(employeeDTO.getEmail());
        }
        if(employeeDTO.getPhoneNumber() != null && !employeeDTO.getPhoneNumber().isEmpty()){
            employee.setPhoneNumber(employeeDTO.getPhoneNumber());
        }
        if(employeeDTO.getCity() != null && !employeeDTO.getCity().isEmpty()){
            employee.getAddress().setCity(employeeDTO.getCity());
        }
        if(employeeDTO.getStreet() != null && !employeeDTO.getStreet().isEmpty()){
            employee.getAddress().setStreet(employeeDTO.getStreet());
        }
        if(employeeDTO.getHomeNumber() != null && !employeeDTO.getHomeNumber().isEmpty()){
            employee.getAddress().setHomeNumber(Integer.parseInt(employeeDTO.getHomeNumber()));
        }
        if(employeeDTO.getRoomNumber() != null && !employeeDTO.getRoomNumber().isEmpty()){
            employee.getAddress().setRoomNumber(Integer.parseInt(employeeDTO.getRoomNumber()));
        }
        if(employeeDTO.getLogin() != null && !employeeDTO.getLogin().isEmpty()){
            employee.setLogin(employeeDTO.getLogin());
        }
        if(employeeDTO.getPassword() != null && !employeeDTO.getPassword().isEmpty()){
            String password = passwordEncoder.encode(employeeDTO.getPassword());
            employee.setPassword(password);
        }
        if(employeeDTO.getPosition() != null && !employeeDTO.getPosition().isEmpty()){
            employee.setPosition(employeeDTO.getPosition());
        }
        if(employeeDTO.getEndWork() != null && !employeeDTO.getEndWork().isEmpty()){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate endWork = LocalDate.parse(employeeDTO.getEndWork(), formatter);
            employee.setEndWork(endWork);
        }
        if(employeeDTO.getStartWork() != null && !employeeDTO.getStartWork().isEmpty()){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate startWork = LocalDate.parse(employeeDTO.getStartWork(), formatter);
            employee.setStartWork(startWork);
        }

        employeeRepository.save(employee);

    }

    public Iterable<Employee> findAll(){
        Iterable<Employee> iterable = employeeRepository.findAll();
        return iterable;
    }

    public Page<Employee> findAll(Pageable pageable){
        return employeeRepository.findAll(pageable);
    }

    public Employee findByLogin(String login){
        return employeeRepository.findByLogin(login);
    }

    public Employee findById(Long id){
        return employeeRepository.findById(id).get();
    }

    public String findEmployeeSecurityByLogin(String login) {
        return employeeRepository.findEmployeeSecurityByLogin(login);
    }

   /* public User findUser(String login){
        return employeeRepository.findUserFromReaderOrEmployee(login);
    }*/
}
