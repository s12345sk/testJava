/**   
* @Title: Test.java 
* @Package effectivejava 
* @Description:  
* @author kang.sun(kanggood@126.com)   
* @date 2014-10-11 下午8:15:32 
* @version V1.0   
*/
package effectivejava;

public class Test {
	public static void main(String[] args){
		Bulider bul = 
				new Bulider.Build(0, "test").age(3).size(23).location("hz").name("lihai").bulider();
//		System.out.println(bul.age);
	}

}
