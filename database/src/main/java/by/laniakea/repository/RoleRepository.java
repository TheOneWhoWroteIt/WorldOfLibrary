package by.laniakea.repository;

import by.laniakea.model.users.Reader;
import by.laniakea.model.users.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {

    @Query("select u from Role u where u.roleName =?1")
    Role findByRoleName(String roleName);


}
