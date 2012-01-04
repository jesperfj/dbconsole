package models;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import play.mvc.Scope.Session;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Developer {

	@JsonProperty(value="Id")
	String id;
	
	@JsonProperty(value="email__c")
	String email;
	
	@JsonProperty(value="apiKey__c")
	String apiKey;

	public Developer(String id, String email, String apiKey) {
		this.id = id;
		this.email = email;
		this.apiKey = apiKey;
	}

	public Developer() {}

	public String getId() {
		return id;
	}
	
	public String getEmail() {
		return email;
	}

    public void setId(String value) {
        id = value;
    }
    
	public void setEmail(String email) {
		this.email = email;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public static Developer readFrom(Session session) {
		String s = session.get("developer");
		if(s==null) return null;
		String[] parts = s.split(" ");
		return new Developer(parts[0], parts[1], parts[2]);
	}
	
	public void writeTo(Session session) {
		session.put("developer", id+" "+email+" "+apiKey);
	}

}
