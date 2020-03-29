package cz.todo_project.app.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cz.todo_project.app.entity.FriendRequest;

@Repository
public class FriendRequestDAOImpl extends BaseDAOImpl<Long, FriendRequest> {

	
	@Transactional(readOnly = true)
	public List<FriendRequest> getRequestsById(Long id) {
		TypedQuery<FriendRequest> query = getCurrentSession().createQuery("select rq from "+ FriendRequest.class.getSimpleName() + " rq where rq.requestedUserId = :id and (rq.accepted is null) or (rq.denied is null)", FriendRequest.class);
		query.setParameter("id", id);
		List<FriendRequest> requests = query.getResultList();
		return requests;
	}

}
