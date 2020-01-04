package cz.todo_project.app.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

public class LogoutHandler implements LogoutSuccessHandler {

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		if (authentication != null && authentication.getDetails() != null) {
			HttpSession session = request.getSession(false);
			if (session != null) {
				session.invalidate();
			}
		}
        response.setStatus(HttpServletResponse.SC_OK);
		response.sendRedirect("./pages/login.xhtml");	
	}

}
