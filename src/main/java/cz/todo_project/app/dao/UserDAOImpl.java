package cz.todo_project.app.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cz.todo_project.app.entity.User;

@Repository
public class UserDAOImpl extends BaseDAOImpl<Long, User> {

	@Transactional
	public List<User> getAllByIdIn(List<Long> ids) {
		TypedQuery<User> query = getCurrentSession().createQuery("select u from "+ User.class.getSimpleName() + " u where u.id in (:ids)", User.class);
		query.setParameter("ids", ids);
		List<User> user = query.getResultList();
		return user;
	}
	
	@Transactional
	public User getByName(String name) {
		TypedQuery<User> query = getCurrentSession().createQuery("select u from "+ User.class.getSimpleName() + " u where u.name = :name ", User.class);
		query.setParameter("name", name);
		User user = query.getSingleResult();
		return user;
	}
	
	@Transactional(readOnly = true)
	public User getByUsername(String email) {
		if (email == null) {
			return null;
		}
		TypedQuery<User> query = getCurrentSession().createQuery("select u from "+ User.class.getSimpleName() + " u join u.properties where u.email = :email ", User.class);
		query.setParameter("email", email);
		User user = query.getSingleResult();
		return user;
	}

}
