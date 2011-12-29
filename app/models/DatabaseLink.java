package models;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Generated from information gathered from /services/data/v23.0/sobjects/Database__c/describe
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class DatabaseLink {
    public static boolean CREATEABLE = true;
    public static boolean DELETABLE = true;
    public static boolean UPDATEABLE = true;

    @JsonProperty(value="Id")
    private String id;
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
    @JsonProperty(value="instance__c")
    private String instance__c;
    @JsonProperty(value="token__c")
    private String token__c;
    @JsonProperty(value="Developer__c")
    private String developer__c;
    @JsonProperty(value="username__c")
    private String username__c;

    /**
     * Constructor.
     */
    public DatabaseLink() { }

    /**
     * Constructor with required fields.
     */
    public DatabaseLink(String token__c, String developer__c, String instance__c) {
        this.token__c = token__c;
        this.developer__c = developer__c;
        this.instance__c = instance__c;
    }

    public String getId() {
        return id;
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
    public String getInstance__c() {
        return instance__c;
    }
    /**
     * alias for #getInstance__c
     */
    public String getInstance() {
        return instance__c;
    }
    public String getToken__c() {
        return token__c;
    }
    /**
     * alias for #getToken__c
     */
    public String getToken() {
        return token__c;
    }
    public String getDeveloper__c() {
        return developer__c;
    }
    /**
     * alias for #getDeveloper__c
     */
    public String getDeveloper() {
        return developer__c;
    }
    public String getUsername__c() {
        return username__c;
    }
    /**
     * alias for #getUsername__c
     */
    public String getUsername() {
        return username__c;
    }
    /**
     * Name is an optional field.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * instance__c is a required field.
     */
    public void setInstance__c(String instance__c) {
        this.instance__c = instance__c;
    }
    /**
     * alias for #setInstance__c
     */
    public void setInstance(String instance__c) { 
        this.instance__c = instance__c;
    }
    /**
     * token__c is a required field.
     */
    public void setToken__c(String token__c) {
        this.token__c = token__c;
    }
    /**
     * alias for #setToken__c
     */
    public void setToken(String token__c) { 
        this.token__c = token__c;
    }
    /**
     * Developer__c is a required field.
     */
    public void setDeveloper__c(String developer__c) {
        this.developer__c = developer__c;
    }
    /**
     * alias for #setDeveloper__c
     */
    public void setDeveloper(String developer__c) { 
        this.developer__c = developer__c;
    }
    /**
     * username__c is an optional field.
     */
    public void setUsername__c(String username__c) {
        this.username__c = username__c;
    }
    /**
     * alias for #setUsername__c
     */
    public void setUsername(String username__c) { 
        this.username__c = username__c;
    }
}
