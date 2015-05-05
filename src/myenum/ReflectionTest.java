/**   
* @Title: ReflectionTest.java 
* @Package myenum 
* @Description:  
* @author kang.sun(kanggood@126.com)   
* @date 2015-3-31 下午5:20:28 
* @version V1.0   
*/
package myenum;

import java.lang.reflect.*;

import java.util.HashSet;
import java.util.Set;

import tool.OSExecute;
import static tool.Print.*;
public class ReflectionTest {
	public Set<String> analy(Class<?> cl){
		print("analyzing---------"+cl+"----------");
		print("interfaces:");
		for(Type t:cl.getGenericInterfaces())
			print(t);
		print("base:"+cl.getSuperclass());
		Set<String> mes = new HashSet<String>();
		print("methods:");
		for(Method m:cl.getMethods())
			mes.add(m.getName());
		print(mes);
		//对于一个枚举类型，可以通过此方法，获取所有的同类枚举
		cl.getClass().getEnumConstants();
		return mes;
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ReflectionTest rt = new ReflectionTest();
		Set<String> c = rt.analy(EnumDemo.class);
		Set<String> p = rt.analy(Enum.class);
		print("c contains p:"+c.containsAll(p));
		c.removeAll(p);
		print("after is "+c);
		OSExecute.command("java ReflectionTest");
		
	}

}
enum EnumDemo{
	HERE,
	THERE
}