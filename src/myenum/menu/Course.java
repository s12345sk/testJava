/**   
* @Title: Course.java 
* @Package myenum.menu 
* @Description:  
* @author kang.sun(kanggood@126.com)   
* @date 2015-4-14 下午5:38:39 
* @version V1.0   
*/
package myenum.menu;

import java.util.Random;
import static tool.Print.*;
import util.Enums;

public enum Course {
	Fruit(Food.Fruit.class),
	Meat(Food.Meat.class),
	Soul(Food.Soul.class);
	private Food[] values;
	private Random rand = new Random();
	private Course(Class<? extends Food> f){
		values = f.getEnumConstants();
	}
	public Food randSel(){
		return Enums.rand(values);
	}
	public static void main(String[] args){
		print(Enums.rand(Course.class).randSel());
		for(Course c:Course.values()){
			print("----"+c+"----");
			for(int i=0;i<3;i++)
				print(c.randSel());
		}
	}
	
	


}
