package cz.todo_project.app.dao;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import cz.todo_project.app.entity.User;

@Repository
public class UserDAOImpl extends BaseDAOImpl<Long, User> {

	public User getByName(String name) {
		TypedQuery<User> query = getCurrentSession().createQuery("select u from "+ User.class.getSimpleName() + "u where u.name = :name ", User.class);
		query.setParameter("name", name);
		User user = query.getSingleResult();
		return user;
	}
	
	public User getByUsername(String email) {
		TypedQuery<User> query = getCurrentSession().createQuery("select u from "+ User.class.getSimpleName() + "u join u.properties where u.email = :email ", User.class);
		query.setParameter("email", email);
		User user = query.getSingleResult();
		return user;
	}

}
