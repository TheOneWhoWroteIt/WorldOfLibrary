package by.laniakea.repository;

import by.laniakea.model.users.Employee;
import by.laniakea.model.users.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    @Query("select u from Employee u where u.login =?1")
    Employee findByLogin(String login);

    Page<Employee> findAll(Pageable pageable);

    @Query("select a.password, a.role.roleName from Employee a where a.login=?1")
    String findEmployeeSecurityByLogin(String login);

}
