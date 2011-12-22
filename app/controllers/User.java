package controllers;

import models.Developer;

import com.force.api.ApiConfig;
import com.force.api.Auth;

public class User extends ParentController {

	public static void index() {
		Developer developer = Developer.readFrom(session);
		render(developer);
	}
	
	public static void logout() {
		// TODO: Move this into force module
		Auth.revokeToken(new ApiConfig(), session.get("force_auth").split(" ")[0]);
		session.clear();
		Application.index();
	}
}
