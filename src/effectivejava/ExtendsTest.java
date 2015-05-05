/**   
* @Title: ExtendsTest.java 
* @Package effectivejava 
* @Description:  
* @author kang.sun(kanggood@126.com)   
* @date 2014-11-12 下午8:33:15 
* @version V1.0   
*/
package effectivejava;

public class ExtendsTest {
	public static void main(String[] args){
		C a = new C();
	}

}
class A{
	public static int times = 0;
	public A(){
		times++;
		System.out.println("a " + times);
	}
}
class B extends A{
	public B(){
		super();
		times++;
		System.out.println("B " + times);
	}
}
class C extends B{
	public C(){

		times++;
		System.out.println("C " + times);
	}
}
