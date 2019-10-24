package cz.todo_project.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.todo_project.app.dao.UserDAOImpl;
import cz.todo_project.app.dto.UserDTO;
import cz.todo_project.app.dto.UserPropertiesDTO;
import cz.todo_project.app.entity.User;
import cz.todo_project.app.entity.UserProperties;
import cz.todo_project.app.enums.UserRoleEnum;

@Service
public class UserService {

	@Autowired
	private UserDAOImpl userDao;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	
	@Transactional(readOnly = true)
	public UserDTO getByName(String name) {
		User user = userDao.getByName(name);
		UserDTO res = transform(user);
		return res;
	}
	
	@Transactional
	public void create(UserDTO entity) {
	 User domain = transform(entity);
	 userDao.create(domain);
	}

	@Transactional
	public void update(UserDTO entity) {
		 User domain = transform(entity);
		 userDao.update(domain);
	}

	@Transactional
	public void delete(Long id) {
		 userDao.deleteById(id);
	}

	@Transactional(readOnly = true)
	public List<UserDTO> getAll() {
	  	List<User> Users = userDao.getAll();
	  	List<UserDTO> UserRes = new ArrayList<>();
	  	for (var User  : Users) {
	  		UserDTO tsk = transform(User);
	  		UserRes.add(tsk);
	  	}
	  	return UserRes;
	}

	@Transactional(readOnly = true)
	public UserDTO get(Long id) {
		 User domain = userDao.get(id);
		 return transform(domain);
	}

	
	
	public UserDTO transform(User from) {
		if (from == null) {
			return null;
		}
		UserDTO to = new UserDTO();
		to.setId(from.getId());
		to.setName(from.getName());
		to.setSurname(from.getSurname());
		to.setEmail(from.getEmail());
		to.setValid_to(from.getValid_to());
		if (from.getProperties() != null) {
		to.setProperties(transform(from.getProperties()));
		}
		return to;
	}

	public User transform(UserDTO from) {
		if (from == null) {
			return null;
		}
		User to = new User();
		to.setId(from.getId());
		to.setName(from.getName());
		to.setSurname(from.getSurname());
		to.setEmail(from.getEmail());
		to.setValid_to(from.getValid_to());
		if (from.getProperties() != null) {
		to.setProperties(transform(from.getProperties()));
		}
		return to;
	}
	
	private UserPropertiesDTO transform(UserProperties from) {
		 UserPropertiesDTO to = new UserPropertiesDTO();
		 to.setId(from.getId());
		 to.setPassword(from.getPassword());
		 to.setRole(from.getRole());
		 return to;
	}

	private UserProperties transform(UserPropertiesDTO from) {
		 UserProperties to = new UserProperties();
		 to.setId(from.getId());
		 to.setPassword(from.getPassword());
		 to.setRole(from.getRole());
		 return to;
	}
	
	public String hashPassword(String password) {
		return passwordEncoder.encode(password);
	} 
	

}
