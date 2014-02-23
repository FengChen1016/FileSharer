

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import com.ibm.json.java.JSONArray;
import com.ibm.json.java.JSONObject;

/**
 * This sample application demonstrates how to use ElasticCaching REST API in a
 * Java Web application and deploy it on BlueMix.
 * 
 * You can refer to the Elastic Caching Java REST API Specification
 * http://pic.dhe.ibm.com/infocenter/wdpxc/v2r5/index.jsp?topic=%2Fcom.ibm.websphere.datapower.xc.doc%2Frdevrestoperations.html
 */
@Path("/cache")
public class ECacheProvider {
	// define ElasticCaching related configuration and set defaults
	protected static String restResource = "https://9.38.13.178/resources/datacaches/btAqgZXeRgu3yBhJatQt2AIR";
	protected static String username = "vAHfXwHfSkGyfNGT9qsvSwMP";
	protected static String password = "XM7oCGi4TimKdUPGTw0CAgJX";
	protected static String mapName = "sampleMap.NONE.P";

	private static String authString = null;

	private HashMap<String, String> cookies = new HashMap<String, String>();
	private int timeToLive = -1;

	static { // initialize
		Map<String, String> env = System.getenv();
		String vcap = env.get("VCAP_SERVICES");

		boolean foundService = false;
		if (vcap == null) {
			System.out.println("No VCAP_SERVICES found!");
		} else {
			try {
				// get ElasticCaching related configuration from VCAP_SERVICES environment variable
				JSONObject obj = JSONObject.parse(vcap);
				
				for (Iterator<?> iter = obj.keySet().iterator(); iter.hasNext();) {
					String name = (String) iter.next();
					if (name.startsWith("DataCache")) {
						JSONArray val = (JSONArray) obj.get(name);
						JSONObject serviceAttr = (JSONObject) val.get(0);
						JSONObject credentials = (JSONObject) serviceAttr.get("credentials");
						username = (String) credentials.get("username");
						password = (String) credentials.get("password");
						restResource = (String) credentials.get("restResource");
						System.out.println("Found configured username: " + username);
						System.out.println("Found configured password: " + password);
						System.out.println("Found configured resource: " + restResource);
						foundService = true;
						break;
					}
				}
			} catch (Exception e) {
			}
		}
		if (!foundService) {
			System.out.println("Did not find WXS service, using defaults.");
		}
		authString = ECacheConnection.getBasicAuthenticationString(username, password);
	}

	/**
	 * Process /cache/ request and return the relevant processing results 
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	@Path("/{key}")
	@GET
	public String getCacheValue(@PathParam("key") String key) throws Exception {
		System.out.println("get cache value of: " + key);
		URL url = getCacheURL(key);
		// get value from ElasticCaching
		try {
			byte[] result = ECacheConnection.getData(url, authString,
					this.cookies);
			if (result != null) {
				ObjectInputStream ois = new ObjectInputStream(
						new ByteArrayInputStream(result));
				ois.readShort();
				return (String) ois.readObject();
			} else {
				return "null";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "Opertaion Fail.";
		}
	}

	/**
	 * Process /cache/save request and return the relevant processing result
	 * 
	 * @param key
	 * @param value
	 * @return
	 * @throws Exception
	 */
	@Path("/save")
	@PUT
	public String save(@QueryParam("key") String key,
			@QueryParam("value") String value) throws Exception {
		System.out.println("Save key:" + key + " , value:" + value);

		URL url = getCacheURL(key);
		if (timeToLive > 0)
			url = new URL(url.toExternalForm() + "?ttl=" + timeToLive);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeShort(0);
		oos.writeObject(value);
		byte[] result = baos.toByteArray();
		// save key-value pair to ElasticCaching
		int status = ECacheConnection.postData(url, authString, this.cookies,
				result);
		if ((status != 200) && (status != 201) && (status != 204)) {
			return "Put Fail.";
		} else {
			return "Put successfully.";
		}
	}

	/**
	 * Process /cache/delete/ request and return the relevant processing result
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	@Path("/delete/{key}")
	@DELETE
	public String delete(@PathParam("key") String key) throws Exception {
		System.out.println("delete key:" + key);
		URL url = getCacheURL(key);
		// delete key from ElasticCaching
		int status = ECacheConnection.deleteData(url, authString, this.cookies);
		if ((status != 204) && (status != 200)) {
			return "Remove Fail.";
		} else {
			return "Remove successfully.";
		}
	}

	/**
	 * Get the fixed REST URI format
	 * 
	 * @param key
	 * @return
	 * @throws MalformedURLException
	 * @throws UnsupportedEncodingException 
	 */
	private URL getCacheURL(String key) throws MalformedURLException, UnsupportedEncodingException {
		return new URL(restResource + "/" + mapName + "/" + URLEncoder.encode(key,"UTF-8"));
	}
	
}
