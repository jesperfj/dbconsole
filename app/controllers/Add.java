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
			    	.apiConfig(new ApiConfig()
					    	.setClientId(System.getenv("LINK_OAUTH_KEY"))
					    	.setRedirectURI(APP_URI+"/add/complete"))
				    .state(instance+" "+name)
				    .scope("id api refresh_token web")
				    .display(Display.POPUP));
    	render(oauthRedirect);
    }
    
    public static void complete(String code, String state) {
    	ApiSession s = Auth.completeOAuthWebServerFlow(new AuthorizationResponse()
    				.apiConfig(new ApiConfig()
    						.setClientId(System.getenv("LINK_OAUTH_KEY"))
    						.setClientSecret(System.getenv("LINK_OAUTH_SECRET"))
    						.setRedirectURI(APP_URI+"/add/complete"))
    				.code(code));
    	String username = new ForceApi(s.getApiConfig(),s).getIdentity().getUsername();
    	int n = state.indexOf(' ');
    	DatabaseLink link = new DatabaseLink();
    	link.setInstance(state.substring(0,n));
    	link.setName(state.substring(n+1));
    	link.setToken(s.getApiConfig().getRefreshToken());
    	link.setDeveloperId(Developer.readFrom(session).getId());
    	link.setUsername(username);
    	coredb.createSObject("database__c", link);
    	
    	render(link);
    }
}