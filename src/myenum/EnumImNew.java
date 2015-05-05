/**   
* @Title: EnumImNew.java 
* @Package myenum 
* @Description:  
* @author kang.sun(kanggood@126.com)   
* @date 2015-4-1 上午9:43:05 
* @version V1.0   
*/
package myenum;

import java.util.Random;

import util.Enums;
import util.Generator;
import static tool.Print.*;
public class EnumImNew {
	public static <T> void  printNext(Generator<T> t){
		print(t.next());
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
		
		for(int i=0;i<10;i++)
			print(Enums.rand(NewE.class));
	}

}
 enum NewE implements Generator<NewE>{
	Q,W,E,R,T,Y,U;
	private Random rand = new Random(47);

	/* (non-Javadoc)
	 * @see util.Generator#next()
	 */
	public NewE next(){
		return values()[rand.nextInt(values().length)];
	}
}
