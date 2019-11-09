package cz.todo_project.app.enums;

import org.springframework.security.core.GrantedAuthority;

public enum UserRoleEnum implements GrantedAuthority {
	DEV, ADMIN, USER, UNKNOWN;

	@Override
	public String getAuthority() {
		
		return "ROLE_"+name();
	}
}
