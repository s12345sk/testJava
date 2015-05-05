/**   
* @Title: ZhuanXing.java 
* @Package rtti 
* @Description:  
* @author kang.sun(kanggood@126.com)   
* @date 2014-11-19 下午3:30:30 
* @version V1.0   
*/
package rtti;
class Animal{}
class Dog extends Animal{}
public class ZhuanXing {
	public static void main(String[] args){
		Animal an = new Dog();
		Dog dog = (Dog)an;
		Animal an2 = new Animal();
		if(an2 instanceof Dog)
			dog = (Dog)an2;
	}

}
