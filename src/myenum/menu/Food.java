/**   
* @Title: Food.java 
* @Package myenum.menu 
* @Description:  
* @author kang.sun(kanggood@126.com)   
* @date 2015-4-14 下午5:08:23 
* @version V1.0   
*/
package myenum.menu;

public interface Food {
	enum Fruit implements Food{
		apple,banana,pear
	}
	enum Meat implements Food{
		chicken,pig,cow
	}
	enum Soul implements Food{
		sweet,salty,Spicy
	}
}
