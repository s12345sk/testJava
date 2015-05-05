/**   
* @Title: EnumMapDemo.java 
* @Package myenum 
* @Description:  
* @author kang.sun(kanggood@126.com)   
* @date 2015-4-15 下午2:18:14 
* @version V1.0   
*/
package myenum;
import static tool.Print.*;

import java.util.EnumMap;
import java.util.Map;

interface Command{ void execute(); }
public class EnumMapDemo {

	/** 
	 * @Title: main 
	 * @Description: 
	 * @param @param args    设定文件 
	 * @return void    返回类型 
	 * @throws 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EnumMap<Num,Command> em  = new EnumMap(Num.class);
		em.put(Num.one,new Command(){

			@Override
			public void execute() {
				// TODO Auto-generated method stub
				print("this is one!");
			}
			
		});
		em.put(Num.tow,new Command(){

			@Override
			public void execute() {
				// TODO Auto-generated method stub
				print("this is two!");
			}
			
		});
		
		for(Map.Entry<Num,Command> e:em.entrySet()){
			print(e.getKey());
			e.getValue().execute();
		}
		Command c = em.get(Num.three);
		print(c);
	}

}
