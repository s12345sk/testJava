/**   
* @Title: GenericVaries.java 
* @Package fanxing 
* @Description:  可变参数和泛型
* @author kang.sun(kanggood@126.com)   
* @date 2014-10-16 下午8:51:20 
* @version V1.0   
*/
package fanxing;

import java.util.ArrayList;
import java.util.List;

public class GenericVaries {
	public static <T> List<T> makeList(T...t){
		List<T> list = new ArrayList<T>();
		for(T item:t)
			list.add(item);
		return list;
	}
	public static void main(String[] args){
		List<String> list = makeList("abcdefghijkmnopqrst".split(""));
		System.out.println(list);
		List<String> list1 = makeList("abcdefghijkmnopqrst","DDD","DSS");
		System.out.println(list1);
	}

}
