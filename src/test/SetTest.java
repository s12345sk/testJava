/**   
* @Title: SetTest.java 
* @Package test 
* @Description:  
* @author kang.sun(kanggood@126.com)   
* @date 2014-11-6 下午7:51:43 
* @version V1.0   
*/
package test;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

public class SetTest {
	public static void testSet(){
		Set<String> set = new TreeSet();
		String[] args = {"ab","ab","sc"};
		Collections.addAll(set, args);
		System.out.println(set);
	}
	public static void main(String[] args){
		testSet();
	}

}
