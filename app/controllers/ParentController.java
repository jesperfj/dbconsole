package controllers;

import java.math.BigInteger;
import java.security.SecureRandom;

import models.Developer;

import org.codehaus.jackson.map.ObjectMapper;

import play.modules.force.Force;
import play.mvc.Before;
import play.mvc.Controller;

import com.force.api.ApiConfig;
import com.force.api.ForceApi;
import com.force.api.QueryResult;

import controllers.force.Secure;

public class ParentController extends Controller {

	static protected final ForceApi coredb = new ForceApi(new ApiConfig().setForceURL(System.getenv("FORCE_COREDB_URL")));
	static protected final String APP_URI = System.getenv("APP_URI");
	static protected final boolean ON_LOCALHOST = APP_URI.startsWith("http://localhost");

	static SecureRandom random = new SecureRandom();

	@Before
	public static void prepare() {
		for(String key: request.headers.keySet()) {
			System.out.println("header | "+key+": "+request.headers.get(key));
		}
		if(!ON_LOCALHOST && !request.headers.get("host").toString().startsWith("https")) {
			// if we're not on localhost and we receive an insecure call, redirect to https
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
				coredb.createSObject("Developer__c", d);
			} else {
				d = qr.getRecords().get(0);
			}
			d.writeTo(session);
		}
		renderArgs.put("developer",d);
	}
}
