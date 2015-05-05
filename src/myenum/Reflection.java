/**   
* @Title: Reflection.java 
* @Package myenum 
* @Description:  
* @author kang.sun(kanggood@126.com)   
* @date 2014-10-20 下午8:22:42 
* @version V1.0   
*/
package myenum;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

enum Explore{HRER,THRER}
public class Reflection {
	public static Set<String> analy(Class<?> enumClass){
		System.out.println("-----分析"+enumClass+"开始......");
		System.out.println("接口是：");
		for(Type t:enumClass.getGenericInterfaces())
			System.out.println(t);
		System.out.println("Base"+enumClass.getSuperclass());
		System.out.println("方法有:");
		Set<String> set = new HashSet<String>();
		for(Method m:enumClass.getMethods()){
			set.add(m.getName());
		}
		System.out.println(set);
		return set;
	}
	public static void main(String[] args){
		Set<String> exp = analy(Explore.class);
		Set<String> enu = analy(Enum.class);
		System.out.println(exp.containsAll(enu));
		exp.removeAll(enu);
		System.out.println(exp);
		
	}
}
