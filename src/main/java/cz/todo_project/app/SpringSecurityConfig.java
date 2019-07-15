package cz.todo_project.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import cz.todo_project.app.handler.RestSuccessHandler;

@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter  {
	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	    auth.inMemoryAuthentication()
	        .withUser("admin").password(encoder().encode("adminPass")).roles("ADMIN")
	        .and()
	        .withUser("user").password(encoder().encode("userPass")).roles("USER");
	}
	 
	
	@Bean
	public PasswordEncoder  encoder() {
	    return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		  http
		    .csrf().disable()
		    .exceptionHandling()
		    .authenticationEntryPoint(new RestEntryPoint())
		    .and()
		    .authorizeRequests()
		    .antMatchers("/rest").authenticated()
		    .antMatchers("/rest/task/**").hasRole("ADMIN")
		    .and()
		    .formLogin()
		    .successHandler(new RestSuccessHandler())
		    .failureHandler(new SimpleUrlAuthenticationFailureHandler())
		    .and()
		    .logout();

	}
	
}
