/**   
* @Title: JsonToObkect.java 
* @Package JackSon 
* @Description:  
* @author kang.sun(kanggood@126.com)   
* @date 2014-12-3 下午6:46:52 
* @version V1.0   
*/
package jackSon;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class JsonToObject {
	public static void main(String[] args){
		ObjectMapper mapper = new ObjectMapper();
		try {
			User user = mapper.readValue(new File("user.json"), User.class);
			System.out.println(user);
			Map<String,Object> userData = mapper.readValue(new File("test"), Map.class);
			System.out.println(userData.get("name"));
			
			Map<String,Object> users = new HashMap<String,Object>();
			Map<String,String> nameStruct = new HashMap<String,String>();
			nameStruct.put("first", "sb");
			nameStruct.put("last", "heheh");
			users.put("name", nameStruct);
			users.put("gender", "MALE");
			users.put("verified", Boolean.FALSE);
			users.put("userImage", "Rm9vYmFyIQ==");
			mapper.writeValue(new File("user.json"), users);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
