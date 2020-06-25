package com.example.springSecurity.security;

import com.example.springSecurity.auth.ApplicationUserService;
import com.example.springSecurity.jwt.JwtTokenVarifier;
import com.example.springSecurity.jwt.JwtUsernameAndPasswordAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ApplicationUserService applicationUserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //.csrf(it -> it.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // jwt jest bezstanowe więc musimy to zaznaczyć
                .and()
                .addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager()))//dodajemy filter do jwt - filter stworzony przez nas
                .addFilterAfter(new JwtTokenVarifier(), JwtUsernameAndPasswordAuthenticationFilter.class)
                .authorizeRequests() // zapytania muszą myć autoryzowane
                .antMatchers("/", "/index", "/login")
                .permitAll()
                .antMatchers("/student/**")
                .hasRole(ApplicationUserRole.STUDENT.name())
                .antMatchers(HttpMethod.DELETE,"/management/**")
                .hasAuthority(ApplicationUserPremission.COURSE_WRITE.getPermission())
                .antMatchers(HttpMethod.PUT,"/management/**")
                .hasAuthority(ApplicationUserPremission.COURSE_WRITE.getPermission())
                .antMatchers(HttpMethod.POST,"/management/**")
                .hasAuthority(ApplicationUserPremission.COURSE_WRITE.getPermission())
                .antMatchers(HttpMethod.GET,"/management/**")
                .hasAnyRole(ApplicationUserRole.ADMIN.name(), ApplicationUserRole.TRAINEE.name())
                .anyRequest() // każde zapytanie
                .authenticated(); // musi być autoryzowane
                /*.and() // i
                //.httpBasic(); // uzywany basic auth
                .formLogin()// używać form based authentication
                //.loginPage("/login")//gdybym chciała inna stronę logowania
                //.permitAll()
                .defaultSuccessUrl("/courses", true)
                //.passwordParameter("pass").usernameParameter("user") // takie wartosci beda wykorzystywane w pliku html
                .and().rememberMe()
                //.rememberMeParameter("forgot") // taka wartosc bedzie wykorzystywana jako parametr w pliku html
                .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(30)) // zapamiętuje do 2 tyg
                .and().logout().logoutUrl("/logout")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID", "remember-me")
                .logoutSuccessUrl("/login");*/
                //Powyższy kod dotyczył form based authentication, chyba, że zostało to zaznaczone inaczej

    }

//    @Override
//    @Bean
//    protected UserDetailsService userDetailsService() {
//        UserDetails student = User.builder()
//                .username("User")
//                .password(passwordEncoder.encode("User"))
//                //.roles(ApplicationUserRole.STUDENT.name())//ROLE_STUDENT
//                .authorities(ApplicationUserRole.STUDENT.getGrantedAuthority())
//                .build();
//        UserDetails admin = User.builder()
//                .username("Admin")
//                .password(passwordEncoder.encode("Admin"))
//                //.roles(ApplicationUserRole.ADMIN.name())//ROLE_ADMIN
//                .authorities(ApplicationUserRole.ADMIN.getGrantedAuthority())
//                .build();
//        UserDetails trainee = User.builder()
//                .username("Trainee")
//                .password(passwordEncoder.encode("Trainee"))
//                //.roles(ApplicationUserRole.TRAINEE.name())//ROLE_TRAINEE
//                .authorities(ApplicationUserRole.TRAINEE.getGrantedAuthority())
//                .build();
//        return new InMemoryUserDetailsManager(student, admin, trainee);
//    }
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider  provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder); // umożliwia dekodowanie hasła
        provider.setUserDetailsService(applicationUserService);
        return provider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }
}
