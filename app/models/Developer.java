package models;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import play.mvc.Scope.Session;

/**
 * Generated from information gathered from /services/data/v23.0/sobjects/Developer__c/describe
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class Developer {
    public static boolean CREATEABLE = true;
    public static boolean DELETABLE = true;
    public static boolean UPDATEABLE = true;

    @JsonProperty(value="Id")
    private String id;
    @JsonProperty(value="OwnerId")
    private String ownerId;
    @JsonProperty(value="IsDeleted")
    private Boolean isDeleted;
    @JsonProperty(value="Name")
    private String name;
    @JsonProperty(value="CreatedDate")
    private String createdDate;
    @JsonProperty(value="CreatedById")
    private String createdById;
    @JsonProperty(value="LastModifiedDate")
    private String lastModifiedDate;
    @JsonProperty(value="LastModifiedById")
    private String lastModifiedById;
    @JsonProperty(value="SystemModstamp")
    private String systemModstamp;
    @JsonProperty(value="apiKey__c")
    private String apiKey__c;
    @JsonProperty(value="email__c")
    private String email__c;

    /**
     * Constructor.
     */
    public Developer() { }

	public Developer(String id, String email, String apiKey) {
		this.id = id;
		this.email__c = email;
		this.apiKey__c = apiKey;
	}


	public String getId() {
        return id;
    }
    public String getOwnerId() {
        return ownerId;
    }
    public Boolean getIsDeleted() {
        return isDeleted;
    }
    public String getName() {
        return name;
    }
    public String getCreatedDate() {
        return createdDate;
    }
    public String getCreatedById() {
        return createdById;
    }
    public String getLastModifiedDate() {
        return lastModifiedDate;
    }
    public String getLastModifiedById() {
        return lastModifiedById;
    }
    public String getSystemModstamp() {
        return systemModstamp;
    }
    public String getApiKey__c() {
        return apiKey__c;
    }
    /**
     * alias for #getApiKey__c
     */
    public String getApiKey() {
        return apiKey__c;
    }
    public String getEmail__c() {
        return email__c;
    }
    /**
     * alias for #getEmail__c
     */
    public String getEmail() {
        return email__c;
    }
    /**
     * OwnerId is a reference to a parent entity.
     */
    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }
    /**
     * Name is an optional field.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * apiKey__c is an optional field.
     */
    public void setApiKey__c(String apiKey__c) {
        this.apiKey__c = apiKey__c;
    }
    /**
     * alias for #setApiKey__c
     */
    public void setApiKey(String apiKey__c) { 
        this.apiKey__c = apiKey__c;
    }
    /**
     * email__c is an optional field.
     */
    public void setEmail__c(String email__c) {
        this.email__c = email__c;
    }
    /**
     * alias for #setEmail__c
     */
    public void setEmail(String email__c) { 
        this.email__c = email__c;
    }
    
	public static Developer readFrom(Session session) {
		String s = session.get("developer");
		if(s==null) return null;
		String[] parts = s.split(" ");
		return new Developer(parts[0], parts[1], parts[2]);
	}
	
	public void writeTo(Session session) {
		session.put("developer", id+" "+email__c+" "+apiKey__c);
	}

}
