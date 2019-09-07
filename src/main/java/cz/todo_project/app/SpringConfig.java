package cz.todo_project.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import cz.todo_project.app.entity.Task;
import cz.todo_project.app.entity.User;
import cz.todo_project.app.entity.UserProperties;


@Configuration
@ComponentScan(basePackages = { "cz.todo_project.app.*" })
@EnableTransactionManagement
@EnableWebMvc
public class SpringConfig implements WebMvcConfigurer {
	
	@Autowired
	ApplicationContext applicationContext;
	
	
		@Bean
	    public LocalSessionFactoryBean getSessionFactory() {
			LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
			factoryBean.setConfigLocation(applicationContext.getResource("classpath:hibernate-config.cfg.xml"));
			factoryBean.setAnnotatedClasses(new Class[] {Task.class, User.class, UserProperties.class});
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
	    

}
