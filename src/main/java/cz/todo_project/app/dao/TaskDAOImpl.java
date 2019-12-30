package cz.todo_project.app.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cz.todo_project.app.entity.Task;

@Repository
public class TaskDAOImpl extends BaseDAOImpl<Long, Task> {
	
	
	@Transactional(readOnly = true)
	public List<Task> getByUsername(String email) {
		TypedQuery<Task> query = getCurrentSession().createQuery("select t from "+ Task.class.getSimpleName() + " t join t.user u where u.email = :email ", Task.class);
		query.setParameter("email", email);
		List<Task> tasks = query.getResultList();
		return tasks;
	}

}
