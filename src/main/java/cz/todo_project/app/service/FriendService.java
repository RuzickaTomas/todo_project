package cz.todo_project.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cz.todo_project.app.dao.FriendDAOImpl;
import cz.todo_project.app.dto.FriendDTO;
import cz.todo_project.app.entity.Friend;
import cz.todo_project.app.view.converter.CollectionsTransformUtil;

@Service
public class FriendService {

	@Autowired
	private FriendDAOImpl friendDao;
	
	@Autowired
	private UserService userService;
	
	
	public List<FriendDTO> getAll() {
		 List<Friend> all = friendDao.getAll();
		 List<FriendDTO> result = CollectionsTransformUtil.transform(all, this::transform);
		 return result;
	}
	
	public FriendDTO get(Long id) {
		Friend domain = friendDao.get(id);
		return this.transform(domain);
	}
	
	public void create(FriendDTO domain) {
		Friend entity = transform(domain);
		friendDao.create(entity);
	}
	
	public void update(FriendDTO domain) {
		Friend entity = transform(domain);
		friendDao.update(entity);
	}
	
	public void delete(FriendDTO domain) {
		Friend entity = transform(domain);
		friendDao.delete(entity);
	}
	
	public FriendDTO transform(Friend from) {
		FriendDTO to = new FriendDTO();
		to.setId(from.getId());
		to.setState(from.getState());
		to.setUserId(from.getUserId());
		return to;
	}
	
	public Friend transform(FriendDTO from) {
		Friend to = new Friend();
		to.setId(from.getId());
		to.setState(from.getState());
		to.setUserId(from.getUserId());
		return to;
	}
	
	
}
