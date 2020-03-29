package by.laniakea.config;

import by.laniakea.security.AutharizationProviderImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.sql.DataSource;
import java.nio.charset.StandardCharsets;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "by.laniakea")

public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private  AutharizationProviderImpl autharizationProviderImpl;
    private  PasswordEncoder passwordEncoder;

    @Autowired
    public void setAutharizationProviderImpl(AutharizationProviderImpl autharizationProviderImpl) {
        this.autharizationProviderImpl = autharizationProviderImpl;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.addFilterBefore(new CharacterEncodingFilter(StandardCharsets.UTF_8.name()), CsrfFilter.class);

        http.authorizeRequests()
                .antMatchers("/reader/*").hasAnyAuthority("READER")
                .antMatchers("/admin/*").hasAnyAuthority("ADMIN")
                .antMatchers("/libra/*").hasAnyAuthority("LIBRA")
                .antMatchers("/welcome", "/about", "/contact", "/rules", "/registration").permitAll()
                .and().formLogin()
                .loginPage("/login")
                .permitAll()
                .failureUrl("/login?error=true")
                /*.usernameParameter("username")
                .passwordParameter("password")*/
                .successForwardUrl("/authorization")
                .and().logout()
                .and().csrf().disable();

    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(autharizationProviderImpl).passwordEncoder(passwordEncoder);

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(8);
    }
}
