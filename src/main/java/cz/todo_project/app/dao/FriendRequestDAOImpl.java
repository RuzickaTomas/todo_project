package cz.todo_project.app.dao;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cz.todo_project.app.entity.FriendRequest;

@Repository
public class FriendRequestDAOImpl extends BaseDAOImpl<Long, FriendRequest> {

	
	@Transactional(readOnly = true)
	public List<FriendRequest> getRequestsById(Long id) {
		TypedQuery<FriendRequest> query = getCurrentSession().createQuery("select rq from "+ FriendRequest.class.getSimpleName() + " rq where (rq.accepted is null)", FriendRequest.class);
		List<FriendRequest> requests = query.getResultList();
		requests = requests.stream().filter(p -> id.equals(p.getRequestedUserId())).collect(Collectors.toList());
		return requests;
	}

}
