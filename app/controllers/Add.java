package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

@With(controllers.force.Secure.class)
public class Add extends Controller {

    public static void index() {
        render();
    }

}