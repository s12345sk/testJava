/**   
* @Title: AbsDemo.java 
* @Package myenum 
* @Description:  
* @author kang.sun(kanggood@126.com)   
* @date 2015-4-15 下午2:41:01 
* @version V1.0   
*/
package myenum;
import static tool.Print.*;

import java.util.EnumSet;
public class AbsDemo {
	
	EnumSet<Wash> em = EnumSet.of(Wash.BaceWash);
	public void action(){
		for(Wash w:em)
			w.wash();
	}
	public void add(Wash w){
		em.add(w);
	}
	public String toString(){
		return em.toString();
	}
	/** 
	 * @Title: main 
	 * @Description: 
	 * @param @param args    设定文件 
	 * @return void    返回类型 
	 * @throws 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AbsDemo ad = new AbsDemo();
		ad.add(Wash.FootWash);
		ad.add(Wash.FaceWash);
		print(ad);
		ad.action();
	}
		

}

enum Wash{
	BaceWash{
		void wash(){
			print("wash ddddddddddd!");
		}
	},
	FaceWash{
		void wash(){
			print("wash face!");
		}
	},
	HandWash{
		void wash(){
			print("wash hand!");
		}
	},
	FootWash{
		void wash(){
			print("wash foot!");
		}
	};
	abstract void wash();
}
