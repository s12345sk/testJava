/**   
* @Title: ClassTest.java 
* @Package rtti 
* @Description:  
* @author kang.sun(kanggood@126.com)   
* @date 2014-11-19 下午2:01:24 
* @version V1.0   
*/
package rtti;

interface AA{}
interface BB{}
interface CC{}
class Toy{
	static{
		System.out.println("begin-------------");
	}
	Toy(){
		System.out.println("cddd");
	}
	Toy(int i){System.out.println("i"+"--------------");}
}
class FunToy extends Toy implements AA,BB,CC{
	static{
		System.out.println("begin2-------------");
	}
	FunToy(){
		super(1);
	}
}
public class ClassTest {
	static void printinfo(Class c){
		System.out.println("name :"+c.getName());
		System.out.println("interface :"+c.isInterface());
		System.out.println("sname :"+c.getSimpleName());
		System.out.println("cname :"+c.getCanonicalName());
	}
	public static void main(String[] args){
		Class c = null;
		try {
			c = Class.forName("rtti.FunToy");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("find no c");
			System.exit(1);
		}
		printinfo(c);
		for(Class cc:c.getInterfaces())
			printinfo(cc);
		Class up = c.getSuperclass();
		printinfo(up);
		try {
			Object obj = up.newInstance();
			printinfo(obj.getClass());
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
