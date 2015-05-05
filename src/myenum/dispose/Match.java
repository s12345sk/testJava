/**   
* @Title: Match.java 
* @Package myenum.dispose 
* @Description:  
* @author kang.sun(kanggood@126.com)   
* @date 2015-4-21 上午11:15:26 
* @version V1.0   
*/
package myenum.dispose;
import static tool.Print.*;
import util.Enums;
public class Match {
	
	public static <T extends Competor<T>> void match(T a,T b){
		print(a+" vs "+b+" is "+a.compete(b));
	}
	public static <T extends  Enum<T>&Competor<T>  > void match(Class<T> cl,int size){
		for(int i=0;i<size;i++)
			match(Enums.rand(cl),Enums.rand(cl));
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
		Match.match(Game.class, 20);
	}

}
