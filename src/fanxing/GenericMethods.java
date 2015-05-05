/**   
* @Title: GenericMethods.java 
* @Package fanxing 
* @Description:  
* @author kang.sun(kanggood@126.com)   
* @date 2014-10-16 下午8:10:56 
* @version V1.0   
*/
package fanxing;

import java.util.ArrayList;
import java.util.List;

public class GenericMethods {
	public <T> void get(T t){
		System.out.println(t.getClass().getName());
	}
	public <T> T getObj(T t){
		return t;
	}
	public <T> List<T> te(){
//		T t = new T();
		return new ArrayList<T>();
	}
	public static void main(String[] args){
		GenericMethods gm = new GenericMethods();
		gm.get(gm.getObj("dd"));
		gm.get(12l);
		gm.get("dd");
		gm.get(0.1f);
		
	}

}
