package be.vdab.firma.security;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String GEBRUIKER = "gebruiker";

    private final DataSource dataSource;

    public SecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery(
                        //maak obv emailAdres een username+password
                        """
                                select emailAdres as username, paswoord as password, 1 as enabled
                                from werknemers
                                where emailadres = ?;     
                                """
                )
                .authoritiesByUsernameQuery(
                        //er zitten geen authorities in de tafel
                        """
                                select emailAdres as username, 'gebruiker' as authorities
                                from werknemers
                                where emailAdres = ?
                                """
                );
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .mvcMatchers("/images/**")
                .mvcMatchers("/css/**")
                .mvcMatchers("/js/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin(login -> login.loginPage("/login"));
        http.authorizeRequests(requests -> requests
                .mvcMatchers("/", "/login").permitAll()
//                .mvcMatchers("/geluksgetal/**").authenticated()
//                .mvcMatchers("/geluksgetal/**").hasAuthority(GEBRUIKER)
        );
        http.logout(logout -> logout.logoutSuccessUrl("/"));
    }
}
