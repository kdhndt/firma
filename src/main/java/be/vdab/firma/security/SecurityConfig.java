package be.vdab.firma.security;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String GEBRUIKER = "gebruiker";

    private final DataSource dataSource;

    public SecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //loginpagina wordt automatisch getoond als de gebruiker toegang wil tot een pagina met bepaalde rechten
        http.formLogin();
        http.authorizeRequests(requests -> requests
                .mvcMatchers("/geluksgetal/**").hasAuthority(GEBRUIKER)
        );
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                //eerste vak v/d loginform is de parameter, in dit geval emailAdres, hiermee duiden we onze username+password aan, Spring heeft het nl. in die vorm nodig
                .usersByUsernameQuery(
                        //maak obv emailAdres een username+password
                        """
                                select emailAdres as username, paswoord as password, 1 as enabled
                                from werknemers
                                where emailadres = ?;     
                                """
                )
                .authoritiesByUsernameQuery(
                        //er zitten geen authorities in de tafel,dus maak ze via SQL
                        /*"""
                                select emailAdres as username, 'gebruiker' as authorities
                                from werknemers
                                where emailAdres = ?
                                """*/
                        //kan korter:
                        "select ?, 'gebruiker'"
                );
    }
}
