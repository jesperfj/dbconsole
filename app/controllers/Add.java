package controllers;

import models.DatabaseLink;
import models.Developer;

import com.force.api.ApiConfig;
import com.force.api.ApiSession;
import com.force.api.Auth;
import com.force.api.AuthorizationRequest;
import com.force.api.AuthorizationRequest.Display;
import com.force.api.AuthorizationResponse;
import com.force.api.ForceApi;

public class Add extends ParentController {

    public static void index() {
        render();
    }

    public static void start(String name, String instance) {
    	String oauthRedirect = Auth.startOAuthWebServerFlow(new AuthorizationRequest()
			    	.apiConfig(CONFIG)
				    .state(instance+" "+name)
				    .scope("id api refresh_token web")
				    .display(Display.POPUP));
    	render(oauthRedirect);
    }
    
    public static void complete(String code, String state) {
    	ApiSession s = Auth.completeOAuthWebServerFlow(new AuthorizationResponse()
    				.apiConfig(CONFIG)
    				.code(code));
    	String username = new ForceApi(CONFIG,s).getIdentity().getUsername();
    	int n = state.indexOf(' ');
    	DatabaseLink link = new DatabaseLink();
    	link.setInstance(state.substring(0,n));
    	link.setName(state.substring(n+1));
    	link.setToken(s.getRefreshToken());
    	link.setDeveloper(Developer.readFrom(session).getId());
    	link.setUsername(username);
    	coredb.createSObject("database__c", link);
    	
    	render(link);
    }
}