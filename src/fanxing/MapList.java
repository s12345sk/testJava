/**   
* @Title: MapList.java 
* @Package fanxing 
* @Description:  
* @author kang.sun(kanggood@126.com)   
* @date 2014-10-16 下午8:34:20 
* @version V1.0   
*/
package fanxing;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapList {
	public static <K,V> Map<K,V> map(){
		return new HashMap<K,V>();
	}
	public static void main(String[] args){
		Map<String,List<?extends Pets>> map = map();
		f(map);
		//泛型的问题：返回的值直接用作参数，则会变成object
//		f(map());
	}
	public static void f(Map<String,List<?extends Pets>> map){
		
	}

}
class Pets{}