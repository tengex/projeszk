package hu.elteik.projecttools.libmgmt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

/**
 * Created by Bázis on 2017. 05. 20..
 */

/**
 * Spring Security configuration class.<br />
 * Overrides default view permissions and security behavior.
 *
 * @see WebSecurityConfig#configure(HttpSecurity)
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("userDetailsService")
    private UserDetailsService userDetailsService;

    /**
     * Create a BCryptPasswordEncoder Bean. <br />
     * The returned instance is accessible via the @Autowired annotation in other classes.
     *
     * @return BCrypt Password Encoder instance
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /**
     * Sets the default password encoder.
     *
     * @param auth AuthenticationManagerBuilder
     * @throws Exception when the password encoder is null or cannot be set.
     */
    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    /**
     * Overrides default Spring Security HTTP access and authority configuration.<br />
     * Current configuration: <br />
     * - public: all resources, index, registration, login, book_list <br />
     * - needs authentication: all other pages <br />
     * - needs admin privilege: user_list
     *
     * @param http HttpSecurity
     * @throws Exception when the builder fails
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/user_list").hasAnyAuthority("ROLE_ADMIN", "ROLE_LIBRARIAN")
                .antMatchers("/css/**", "/js/**", "/img/**", "/font/**", "/", "/index", "/registration", "/book_list").permitAll()
                .anyRequest().authenticated()
                .and()

                .formLogin()
                .loginPage("/login").permitAll()
                .and()

                .logout().logoutSuccessUrl("/index").permitAll()
                .and().csrf().csrfTokenRepository(csrfTokenRepository());
    }

    /**
     * Sets default csrf token repository and determines attribute name in forms (_csrf")
     *
     * @return CsrfTokenRepository
     */
    private CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setSessionAttributeName("_csrf");
        return repository;
    }

    /**
     * Overrides WebSecurity configuration. We are currently using the default configuration.
     *
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }
}
