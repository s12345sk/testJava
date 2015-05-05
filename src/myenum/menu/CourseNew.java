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

public enum CourseNew {
	Fruit(Food.Fruit.class),
	Meat(Food.Meat.class),
	Soul(Food.Soul.class);
	private Food[] values;
	private Random rand = new Random();
	private CourseNew(Class<? extends Food> f){
		values = f.getEnumConstants();
	}
	public Food randSel(){
		return Enums.rand(values);
	}
	public interface Food {
		enum Fruit implements Food{
			apple1,banana1,pear1
		}
		enum Meat implements Food{
			chicken1,pig1,cow1
		}
		enum Soul implements Food{
			sweet1,salty1,Spicy1
		}
	}
	public static void main(String[] args){
		print(Enums.rand(CourseNew.class).randSel());
		for(CourseNew c:CourseNew.values()){
			print("----"+c+"----");
			for(int i=0;i<3;i++)
				print(c.randSel());
		}
	}
	
	


}
