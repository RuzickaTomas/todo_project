package cz.todo_project.app;

import io.sentry.event.BreadcrumbBuilder;

public final class BreadCrumbSingleton {

	private final BreadcrumbBuilder builder; 
	private static BreadCrumbSingleton singletonInstance;
	
	private BreadCrumbSingleton() {
		builder = new BreadcrumbBuilder();
	}
	
	public static BreadCrumbSingleton getInstance() {
		if (singletonInstance == null) {
			singletonInstance = new BreadCrumbSingleton();
		}
		return singletonInstance;
	}
	
	public BreadcrumbBuilder getBuilder() {
		return builder;
	}
		
}
