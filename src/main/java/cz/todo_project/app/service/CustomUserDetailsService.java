package cz.todo_project.app.service;

import org.springframework.beans.factory.annotation.Autowired;
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
		// TODO Auto-generated method stub
		User user  = null;
		try {
		user = service.getByUsername(username);
		} catch (Exception e) {
			System.out.println("Username not found due to: " + e);
		}
		return new UserDetailsImpl(user);
	}

}
