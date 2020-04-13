package cz.todo_project.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.todo_project.app.dao.FriendRequestDAOImpl;
import cz.todo_project.app.dto.FriendRequestDTO;
import cz.todo_project.app.entity.FriendRequest;
import cz.todo_project.app.view.converter.CollectionsTransformUtil;

@Service
public class FriendRequestService {

	@Autowired
	FriendRequestDAOImpl friendRequestDao;
	
	@Autowired
	UserService userService;
	
	@Transactional
	public void create(FriendRequestDTO entity) {
	 FriendRequest domain = transform(entity);
	 friendRequestDao.create(domain);
	}

	@Transactional
	public FriendRequest update(FriendRequestDTO entity) {
		 FriendRequest domain = transform(entity);
		 return friendRequestDao.update(domain);
	}

	@Transactional
	public void delete(Long id) {
		friendRequestDao.deleteById(id);
	}

	@Transactional(readOnly = true)
	public List<FriendRequestDTO> getRequestsById(Long id) {
		List<FriendRequest> friendRequests = friendRequestDao.getRequestsById(id);
	  	return CollectionsTransformUtil.transform(friendRequests, this::transform);
	}
	
	
	@Transactional(readOnly = true)
	public List<FriendRequestDTO> getAll() {
		List<FriendRequest> friendRequests = friendRequestDao.getAll();
	  	return CollectionsTransformUtil.transform(friendRequests, this::transform);
	}
	
	
	public FriendRequestDTO transform(FriendRequest from) {
		if (from == null) {
			return null;
		}
		FriendRequestDTO to = new FriendRequestDTO();
		to.setId(from.getId());
		to.setAccepted(from.getAccepted());
		to.setDenied(from.getDenied());
		to.setRequestedUserId(from.getRequestedUserId());
		to.setUsers(CollectionsTransformUtil.transform(from.getUsers(), userService::transform));
		return to;
	}
	
	public FriendRequest transform(FriendRequestDTO from) {
		if (from == null) {
			return null;
		}
		FriendRequest to = new FriendRequest();
		to.setId(from.getId());
		to.setAccepted(from.getAccepted());
		to.setDenied(from.getDenied());
		to.setRequestedUserId(from.getRequestedUserId());
		to.setUsers(CollectionsTransformUtil.transform(from.getUsers(), userService::transform));
		return to;
	}
	
}
