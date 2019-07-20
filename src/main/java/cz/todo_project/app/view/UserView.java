package cz.todo_project.app.view;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cz.todo_project.app.dto.UserDTO;
import cz.todo_project.app.enums.PriorityEnum;
import cz.todo_project.app.service.UserService;

@Component
@ManagedBean
@ViewScoped
public class UserView implements InitializingBean {
    
    @Autowired
    private UserService UserService;
    
    private List<UserDTO> Users = new ArrayList<>();
    private UserDTO newUser;
    
    private boolean toUpdate;
    
    
    public void init() {
    	Users = UserService.getAll();
    	newUser = new UserDTO();
    } 
    
    public List<UserDTO> getUsers() {
		return Users;
	}


	public void setUsers(List<UserDTO> Users) {
		this.Users = Users;
	}
	
	
	 public UserDTO getNewUser() {
		return newUser;
	}

	public void setNewUser(UserDTO newUser) {
		this.newUser = newUser;
	}
	
	
	public void prepareNewUser() {
		newUser = new UserDTO();
		toUpdate = false;
	}
	
	
	public boolean isToUpdate() {
		return toUpdate;
	}

	public void setToUpdate(boolean toUpdate) {
		this.toUpdate = toUpdate;
	}

	public void saveUser() {
		if (!toUpdate && newUser.getId() == null) {
	        FacesMessage msg = new FacesMessage("User Created", newUser.getId() + "");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			UserService.create(newUser);
		} else {	
	        FacesMessage msg = new FacesMessage("User Edited", newUser.getId() + "");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			UserService.update(newUser);
		}
		prepareNewUser();
		refreshList();
	}
	
	
	
	public void updateUser(UserDTO User) {
		toUpdate = User.getId() != null;
		setNewUser(User);
	}
	
	
	public void deleteUser(Long id) {
		UserService.delete(id);
		refreshList();
	}

	 
	 public PriorityEnum[] getPriorityEnums() {
		 return PriorityEnum.values(); 
	 }


    //replacement of init @PostConstruct
	@Override
	public void afterPropertiesSet() throws Exception {
		init();
	}
    
	
	public void onLoad(AjaxBehaviorEvent event) {
		init();
	}

	public void refresh(AjaxBehaviorEvent event) {
		refreshList();
	}

	private void refreshList() {
    	Users = UserService.getAll();
	}

    
    
}