package models;

import com.force.api.ApiConfig;
import com.force.api.ForceApi;

public class CoreDb {

	static public ForceApi api = new ForceApi(new ApiConfig().setForceURL(System.getenv("FORCE_COREDB_URL")));
	
}
