package katalyst;
import static spark.Spark.*;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;



public class MainServer {
	
	Avatar[] avatars;
	Avatar[] eyesSort;
	Avatar[] nosesSort;
	Avatar[] mouthsSort;
	
	public MainServer() {
    	DatabaseHelper helper = new DatabaseHelper();
		System.out.println(getClass());
 
    	try {
			avatars = helper.readData(new String(Files.readAllBytes(Paths.get("./src/main/resources/katalyst/AvatarData.txt"))));
		} catch (IOException e) {

			InputStream texts = getClass().getResourceAsStream("./src/main/resources/katalyst/AvatarData.txt");
			try {
				avatars = helper.readData(IOUtils.toString(texts,"UTF-8"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return;
		}
       	SortModel model = new SortModel(this.avatars);
    	eyesSort = model.sortAvatar("eyes");
    	nosesSort = model.sortAvatar("nose");
    	mouthsSort = model.sortAvatar("mouth");
		
	}
	// Enables CORS on requests. This method is an initialization method and should be called once.
	private static void enableCORS(final String origin, final String methods, final String headers) {
	    options("/*", (request, response) -> {

	        String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
	        if (accessControlRequestHeaders != null) {
	            response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
	        }

	        String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
	        if (accessControlRequestMethod != null) {
	            response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
	        }

	        return "OK";
	    });

	    before((request, response) -> {
	        response.header("Access-Control-Allow-Origin", origin);
	        response.header("Access-Control-Request-Method", methods);
	        response.header("Access-Control-Allow-Headers", headers);
	        // Note: this may or may not be necessary in your particular application
	    });
	}
	
    public static void main(String[] args) {
    	MainServer server = new MainServer();
        port(3000);
    	enableCORS("*", "PUT,GET,POST,DELETE,OPTIONS","accept, content-type, x-parse-application-id, x-parse-rest-api-key, x-parse-session-token");
    	
        
        get("/", (req, res) -> {
        	return server.eyesSort[0].generateJson();});
        get("/avatar/:index", (req, res) -> { 
        	String index = req.params().get(":index");
        	int i = Integer.parseInt(index);
        	res.type("application/json");
        	res.header("FOO", "bar");
        	JSONObject response = server.avatars[i].generateJson();
        	System.out.println(response);
        	return response;});
        get("/avatars/page/:page", (req, res) -> { 
        	String Pageindex = req.params().get(":page");
        	int i = Integer.parseInt(Pageindex);
        	JSONArray dataArray = new JSONArray();
        	for (int j = 0; j < 10; j++) {
        		JSONObject avatar = server.avatars[(i-1)*10+j].generateJson();
        		dataArray.put(avatar);
        		System.out.println(avatar);
        	}
        	res.type("application/json");
        	res.header("FOO", "bar");
        	return dataArray;});
        get("/avatars/page/:page/sort/:sortparam", (req, res) -> { 
        	String pageIndex = req.params().get(":page");
        	String sortParam = req.params().get(":sortparam");
        	int i = Integer.parseInt(pageIndex);
        	res.type("application/json");
        	res.header("FOO", "bar");
        	switch(sortParam) {
        		case "eyes": return generateArray(server.eyesSort,i);
        		case "noses": return generateArray(server.nosesSort,i);
        		case "mouths": return generateArray(server.mouthsSort,i);
        		default: return generateArray(server.avatars,i);
        	}});
    }
    private static JSONArray generateArray(Avatar[] sorted, int pagenum) {
    	JSONArray dataArray = new JSONArray();
    	for (int j = 0; j < 10; j++) {
    		JSONObject avatar = sorted[(pagenum-1)*10+j].generateJson();
    		dataArray.put(avatar);
    	}
    	return dataArray;
    }
}
