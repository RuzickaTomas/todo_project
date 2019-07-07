package cz.todolist.app.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public abstract class BaseDAOImpl<PK, T> {

	private final Class<T> clazz;

	public BaseDAOImpl() {
		this.clazz = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[1];
	}

	@Autowired
	SessionFactory sessionFactory;

	public T get(PK id) {
		return (T) getCurrentSession().get(clazz, (Number) id);
	}

	public List getAll() {
		return getCurrentSession().createQuery("from " + clazz.getName()).list();
	}

	public T create(T entity) {
		getCurrentSession().persist(entity);
		return entity;
	}

	public T update(T entity) {
		return (T) getCurrentSession().merge(entity);
	}

	public void delete(T entity) {
		getCurrentSession().delete(entity);
	}

	public void deleteById(PK entityId) {
		T entity = get(entityId);
		delete(entity);
	}

	protected synchronized Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

}
