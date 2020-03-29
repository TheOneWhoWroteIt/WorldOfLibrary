package by.laniakea.security;

import by.laniakea.model.users.Employee;
import by.laniakea.model.users.Reader;
import by.laniakea.model.users.Role;
import by.laniakea.model.users.User;
import by.laniakea.repository.EmployeeServiceRepository;
import by.laniakea.repository.ReaderServiceRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
//@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class AutharizationProviderImpl implements UserDetailsService {

   private  ReaderServiceRepository readerServiceRepository;
   private  EmployeeServiceRepository employeeServiceRepository;


    @Autowired
   public void setReaderServiceRepository(ReaderServiceRepository readerServiceRepository) {
        this.readerServiceRepository = readerServiceRepository;
    }

    @Autowired
    public void setEmployeeServiceRepository(EmployeeServiceRepository employeeServiceRepository) {
        this.employeeServiceRepository = employeeServiceRepository;
    }



    public String findByUserName(String username){

        String user;

        user = employeeServiceRepository.findEmployeeSecurityByLogin(username);

        if(user == null){
            user = readerServiceRepository.findReaderSecurityByLogin(username);
        }


        return user;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDetails userDetails = null;
        String user = findByUserName(username);


        if( user != null){
            String[] userSecurity = user.split(",");


            List<GrantedAuthority> roles = new ArrayList<>();
            roles.add(new SimpleGrantedAuthority(userSecurity[1]));

            userDetails = org.springframework.security.core.userdetails.User.builder()
                    .username(username)
                    .password(userSecurity[0])
                    .authorities(roles)
                    .build();
        }

        return userDetails;
    }
}
