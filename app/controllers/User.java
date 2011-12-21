package controllers;

import models.Developer;

public class User extends ParentController {

	public static void index() {
		Developer developer = Developer.readFrom(session);
		render(developer);
	}
	
	public static void logout() {
		render();
	}
}
