package controllers;

import models.DatabaseLink;
import models.Developer;

import com.force.api.ApiConfig;
import com.force.api.ApiSession;
import com.force.api.Auth;
import com.force.api.QueryResult;

public class Database extends ParentController {

    public static void index() {
    	QueryResult<DatabaseLink> list = coredb.query("SELECT id, name, username__c, instance__c FROM database__c WHERE developer__c = '"+Developer.readFrom(session).getId()+"'", DatabaseLink.class);
        render(list);
    }
    
    public static void weblogin(String id) {

    	DatabaseLink d = coredb.query("SELECT username__c, token__c FROM database__c WHERE id='"+id+"'", DatabaseLink.class).getRecords().get(0);
    	
    	ApiSession s = Auth.refreshOauthTokenFlow(CONFIG, d.getToken());
    	System.out.println("Exchanged refresh token for access token: "+s.getAccessToken());
    	redirect(s.getApiEndpoint()+"/secur/frontdoor.jsp?un="+d.getUsername()+"&sid="+s.getAccessToken()+"&startURL=/home/home.jsp");
    }
    
    public static void delete(String id) {
    	DatabaseLink d = coredb.query("SELECT token__c FROM database__c WHERE id='"+id+"'", DatabaseLink.class).getRecords().get(0);
    	Auth.revokeToken(new ApiConfig(), d.getToken());
    	coredb.deleteSObject("Database__c", id);
    	index();
    }

}