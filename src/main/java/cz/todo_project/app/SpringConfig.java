package cz.todo_project.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import cz.todo_project.app.entity.Friend;
import cz.todo_project.app.entity.Task;
import cz.todo_project.app.entity.User;
import cz.todo_project.app.entity.UserProperties;
import cz.todo_project.app.enums.UserRoleEnum;
import cz.todo_project.app.service.CustomUserDetailsService;
import cz.todo_project.app.service.LogoutHandler;


@Configuration
@ComponentScan(basePackages = { "cz.todo_project.app.*" })
@EnableTransactionManagement
@EnableWebMvc
@EnableWebSecurity
public class SpringConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {
	
		@Autowired
		ApplicationContext applicationContext;
	
	    @Autowired
		CustomUserDetailsService userdetailsService;
	    
	    @Autowired
	    LogoutHandler logoutHandler;
		
	
		@Bean
	    public LocalSessionFactoryBean getSessionFactory() {
 			LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
			factoryBean.setConfigLocation(applicationContext.getResource("classpath:hibernate-config.cfg.xml"));
			factoryBean.setAnnotatedClasses(new Class[] {Task.class, User.class, Friend.class, UserProperties.class});
			return factoryBean;
		}
		
	    @Bean
	    public HibernateTransactionManager getTransactionManager() {
	        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
	        transactionManager.setSessionFactory(getSessionFactory().getObject());
	        return transactionManager;

	    }
	    
		    
	    @Bean
	    public BCryptPasswordEncoder getBEncoder() {
	    	return new BCryptPasswordEncoder();
	    }
	    
	    @Override
	    public void configureDefaultServletHandling(
	      DefaultServletHandlerConfigurer configurer) {
	        configurer.enable();
	    }
	 
	    @Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	        registry.addResourceHandler("/resources/**")
	          .addResourceLocations("/resources/").setCachePeriod(3600)
	          .resourceChain(true).addResolver(new PathResourceResolver());
	    }
	    
	    


		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		    auth.userDetailsService(userdetailsService).passwordEncoder(encoder());
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
			    .and()
			    .authorizeRequests()
			    .antMatchers("/javax.faces.resource/**").permitAll()
			    //.antMatchers("/rest", "/pages/main.xhtml").authenticated()
			    //.antMatchers("/rest/task/**").hasRole(UserRoleEnum.ADMIN.name())
			    //hasRole comes with ROLE_ prefix, to aviod additional configuration i used authority
			    .antMatchers("/pages/main.xhtml").hasAuthority(UserRoleEnum.USER.name())
			    .anyRequest().authenticated()
			    .and()
			    .formLogin().loginPage("/pages/login.xhtml")
			    			.usernameParameter("username")
			    			.passwordParameter("password")
			    			.loginProcessingUrl("/doLogin")
			    			.successForwardUrl("/pages/main.xhtml")
			    	.failureUrl("/pages/login.xhtml?error=true")
			    	.permitAll()
			    .and()
			    .logout().logoutUrl("/logout")
			    		 .logoutSuccessHandler(logoutHandler).permitAll();

		}

}
