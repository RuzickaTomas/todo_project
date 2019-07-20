package cz.todo_project.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.todo_project.app.dao.UserDAOImpl;
import cz.todo_project.app.dto.UserDTO;
import cz.todo_project.app.entity.User;

@Service
public class UserService {

	@Autowired
	private UserDAOImpl userDao;

	
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
		UserDTO to = new UserDTO();
		to.setId(from.getId());
		to.setName(from.getName());
		to.setSurname(from.getSurname());
		to.setValid_to(from.getValid_to());
		return to;
	}

	public User transform(UserDTO from) {
		User to = new User();
		to.setId(from.getId());
		to.setName(from.getName());
		to.setSurname(from.getSurname());
		to.setValid_to(from.getValid_to());
		return to;
	}


}
