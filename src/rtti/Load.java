/**   
* @Title: Load.java 
* @Package rtti 
* @Description:  
* @author kang.sun(kanggood@126.com)   
* @date 2014-11-19 下午1:33:10 
* @version V1.0   
*/
package rtti;
class A{
	static{
		System.out.println("A");
	}
}
class B{
	static{
		System.out.println("B");
	}
}
class C{
	static{
		System.out.println("C");
	}
}
public class Load {
	public static void main(String[] args){
		new A();
		try {
			Class.forName("rtti.B");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("can`t find B.");
		}
		
	}

}
