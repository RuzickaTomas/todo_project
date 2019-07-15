package cz.todo_project.app;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.sun.faces.config.FacesInitializer;

public class AppInitializer extends FacesInitializer implements WebApplicationInitializer  {
	
	public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(SpringConfig.class, SpringSecurityConfig.class);
        //context.refresh();
        context.setServletContext(servletContext);
        servletContext.setInitParameter("primefaces.THEME", "luna-blue");
        servletContext.addListener(new ContextLoaderListener(context));
                
        ServletRegistration.Dynamic servlet = servletContext.addServlet(
        "dispatcherExample", new DispatcherServlet(context));
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/");
	}


}
