package controllers;

import models.CoreDb;
import play.mvc.Controller;
import play.mvc.With;

import com.force.api.Identity;

public class Application extends Controller {

    public static void index() {
    	Identity identity = CoreDb.api.getIdentity();
        render(identity);
    }

}