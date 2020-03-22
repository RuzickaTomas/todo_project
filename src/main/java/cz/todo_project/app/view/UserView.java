package cz.todo_project.app.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.context.PrimeFacesContext;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import cz.todo_project.app.dto.UserDTO;
import cz.todo_project.app.dto.UserDetailsImpl;
import cz.todo_project.app.dto.UserPropertiesDTO;
import cz.todo_project.app.entity.User;
import cz.todo_project.app.enums.PriorityEnum;
import cz.todo_project.app.enums.UserRoleEnum;
import cz.todo_project.app.service.FriendRequestService;
import cz.todo_project.app.service.UserService;

@Component
@ManagedBean
@ViewScoped
public class UserView {
    
    @Autowired
    private UserService userService;
    
 
    
    private List<UserDTO> users = new ArrayList<>();
    private List<UserDTO> friends = new ArrayList<>();
    
    private UserDTO newUser;
    
    private boolean toUpdate;
    
    @PostConstruct
    public void init() {  
		users = userService.getAll();
		friends = userService.getAll();
       	newUser = new UserDTO();
    	newUser.setProperties(new UserPropertiesDTO());
    }
    
    
    public List<UserDTO> getUsers() {
    	return users;
	}

    

	public List<UserDTO> getFriends() {
		return friends;
	}


	public void setFriends(List<UserDTO> friends) {
		this.friends = friends;
	}


	public void setusers(List<UserDTO> users) {
		this.users = users;
	}
	
	
	 public UserDTO getNewUser() {
		return newUser;
	}

	public void setNewUser(UserDTO newUser) {
		this.newUser = newUser;
	}
	
	
	public void prepareNewUser() {
		newUser = new UserDTO();
		newUser.setProperties(new UserPropertiesDTO());
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
	        FacesMessage msg = new FacesMessage("User Created", newUser.getEmail() + "");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			newUser.getProperties().setPassword(userService.hashPassword(newUser.getProperties().getPassword()));
			newUser.getProperties().setRole(UserRoleEnum.USER);
			userService.create(newUser);
		} else {	
	        FacesMessage msg = new FacesMessage("User Edited", newUser.getEmail() + "");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			newUser.getProperties().setPassword(userService.hashPassword(newUser.getProperties().getPassword()));
			newUser.getProperties().setRole(UserRoleEnum.USER);
			userService.update(newUser);
		}
		prepareNewUser();
		refreshList();
	}
	
	public void loginUser() throws IOException {
		PrimeFacesContext.getCurrentInstance().getExternalContext().redirect("/todo_project/pages/main.xhtml");
	}
	
	
	public void updateUser(UserDTO User) {
		toUpdate = User.getId() != null;
		setNewUser(User);
	}
	
	
	public void deleteUser(Long id) {
		userService.delete(id);
		refreshList();
	}

	 
	 public PriorityEnum[] getPriorityEnums() {
		 return PriorityEnum.values(); 
	 }
	 
	 @PreDestroy
	 public void destroy() {
		 users.clear();
	 }
    

	public void refresh(AjaxBehaviorEvent event) {
		refreshList();
	}

	private void refreshList() {
    	users = userService.getAll();
	}

    
    
}