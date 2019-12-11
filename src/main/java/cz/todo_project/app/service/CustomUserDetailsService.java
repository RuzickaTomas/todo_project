package cz.todo_project.app.service;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cz.todo_project.app.dao.UserDAOImpl;
import cz.todo_project.app.dto.UserDetailsImpl;
import cz.todo_project.app.entity.User;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserDAOImpl service;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = null;
		try {
		 user = service.getByUsername(username);
	
		} catch (NoResultException | InternalAuthenticationServiceException e) {
				throw new UsernameNotFoundException("Username or password might be incorrect!");
		}
			
		return new UserDetailsImpl(user);
	}

}
