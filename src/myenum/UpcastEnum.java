/**   
* @Title: UpcastEnum.java 
* @Package myenum 
* @Description:  
* @author kang.sun(kanggood@126.com)   
* @date 2014-10-20 下午8:39:48 
* @version V1.0   
*/
package myenum;
enum Test{ONE,TWO}
public class UpcastEnum {
	public static void main(String[] args){
		Test[] s = Test.values();
		Enum e = Test.TWO;
		System.out.println(e.getClass());
		for(Enum d:e.getClass().getEnumConstants())
			System.out.println(d);
	}

}	

class A{
	private A(){}
}

