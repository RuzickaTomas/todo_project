package cz.todo_project.app.view.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cz.todo_project.app.dto.UserDTO;
import cz.todo_project.app.service.UserService;

@Service
public class UserConverter implements Converter {

	@Autowired
	private UserService userService;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || !StringUtils.isNumeric(value)) {
			return null;
		}
		
		UserDTO user = userService.get(Long.parseLong(value));
		return user;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		UserDTO result = (UserDTO) value;
		return result.getId().toString();
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	
}
