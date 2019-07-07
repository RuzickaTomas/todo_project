package cz.todolist.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import cz.todolist.app.entity.Task;


@Configuration
@ComponentScan(basePackages = { "cz.todolist.app.*" })
@EnableTransactionManagement
@EnableWebMvc
public class SpringConfig implements WebMvcConfigurer {
	
	@Autowired
	ApplicationContext applicationContext;
	
	
		@Bean
	    public LocalSessionFactoryBean getSessionFactory() {
			LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
			factoryBean.setConfigLocation(applicationContext.getResource("classpath:hibernate-config.cfg.xml"));
			factoryBean.setAnnotatedClasses(new Class[] {Task.class});
	        return factoryBean;
		}
		
	    @Bean
	    public HibernateTransactionManager getTransactionManager() {
	        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
	        transactionManager.setSessionFactory(getSessionFactory().getObject());
	        return transactionManager;

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
