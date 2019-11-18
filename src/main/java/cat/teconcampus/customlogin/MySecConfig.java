package cat.teconcampus.customlogin;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class MySecConfig extends WebSecurityConfigurerAdapter {
    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/hello").authenticated()
                .and()
                .formLogin()
                .loginPage("/myLogin").permitAll()//to use forms (web)
                .loginProcessingUrl("/login")
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")); //needed only when csrf is enabled (as by default is post)
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("roure").password("{noop}roure").roles("ADMIN").and() //not encoded
                .withUser("alvarez").password(passwordEncoder().encode("alvarez")).roles("USER").and() // default encoder (bcrypt)
                .withUser("lecina").password(passwordEncoder().encode("lecina")).roles("USER,ADMIN");
    }
}
