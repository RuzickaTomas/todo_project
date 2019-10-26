package cz.todo_project.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.ProviderManagerBuilder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import cz.todo_project.app.enums.UserRoleEnum;
import cz.todo_project.app.handler.RestSuccessHandler;
import cz.todo_project.app.service.UserDetailService;

@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter  {
	
	@Autowired
	private UserDetailService userDetailService;
	//TODO enable OAuth
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	    auth.userDetailsService(userDetailService).passwordEncoder(encoder());
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
		    .authorizeRequests().anyRequest().hasAnyRole(UserRoleEnum.ADMIN.name(), UserRoleEnum.USER.name(), UserRoleEnum.DEV.name())
		    .antMatchers("/rest").authenticated()
		    .antMatchers("/rest/task/**").hasRole("ADMIN")
		    .and()
		    .formLogin().loginPage("/pages/login.xhtml").loginProcessingUrl("/pages/main.xhtml").permitAll()
		    .and()
		    .formLogin().successHandler(new RestSuccessHandler()).and().
		     formLogin().failureHandler(new SimpleUrlAuthenticationFailureHandler())
		    .and()
		    .logout().logoutSuccessUrl("/login").permitAll();

	}
	
}
