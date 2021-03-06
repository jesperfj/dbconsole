package controllers;

import java.math.BigInteger;
import java.security.SecureRandom;

import models.Developer;

import org.codehaus.jackson.map.ObjectMapper;

import play.modules.force.Force;
import play.mvc.Before;
import play.mvc.Catch;
import play.mvc.Controller;

import com.force.api.ApiConfig;
import com.force.api.ApiTokenException;
import com.force.api.ForceApi;
import com.force.api.QueryResult;

import controllers.force.Secure;

public class ParentController extends Controller {

	// This app requires the following environment variables
	
	static protected final String APP_URI 			 = System.getenv("APP_URI");
	static protected final String LINK_OAUTH_KEY 	 = System.getenv("LINK_OAUTH_KEY");
	static protected final String LINK_OAUTH_SECRET  = System.getenv("LINK_OAUTH_SECRET");
	static protected final String FORCE_OAUTH_KEY 	 = System.getenv("FORCE_OAUTH_KEY");
	static protected final String FORCE_OAUTH_SECRET = System.getenv("FORCE_OAUTH_SECRET");
	static protected final String FORCE_COREDB_URL   = System.getenv("FORCE_COREDB_URL");
	
	static protected final ForceApi coredb = new ForceApi(new ApiConfig().setForceURL(FORCE_COREDB_URL));
	static protected final boolean ON_LOCALHOST = APP_URI.startsWith("http://localhost");

	static protected final ApiConfig CONFIG = new ApiConfig()
	 	.setClientId(LINK_OAUTH_KEY)
	 	.setClientSecret(LINK_OAUTH_SECRET)
	 	.setRedirectURI(APP_URI+"/add/complete");

	static SecureRandom random = new SecureRandom();

	@Before
	public static void prepare() {
		if(!ON_LOCALHOST && request.headers.get("x-forwarded-proto")!=null && !request.headers.get("x-forwarded-proto").values.contains("https")) {
			redirect(APP_URI+request.url);
			return;
		}
		Secure.checkAuthenticated();
		
		Developer d = Developer.readFrom(session); 
		if(d==null) {
			d = new Developer();
			d.setEmail(Force.forceApi(session).getIdentity().getUsername());
			QueryResult<Developer> qr = 
					coredb.query("SELECT id, email__c, apiKey__c FROM Developer__c WHERE email__c='"+d.getEmail()+"'", 
								 Developer.class);
			if(qr.getTotalSize()==0) {
				// wasn't in core db. Generate an API key and create a record
				d.setApiKey(new BigInteger(130, random).toString(32));
				d.setId(coredb.createSObject("Developer__c", d));
			} else {
				d = qr.getRecords().get(0);
			}
			d.writeTo(session);
		}
		renderArgs.put("developer",d);
	}
	
	@Catch(ApiTokenException.class)
	public static void catchBadApiToken(ApiTokenException e) {
		System.out.println("Caught API Token Exception "+e.getMessage());
        session.clear();
        Application.index();
	}
}
