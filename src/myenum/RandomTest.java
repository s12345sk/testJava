/**   
* @Title: RandomTest.java 
* @Package myenum 
* @Description:  
* @author kang.sun(kanggood@126.com)   
* @date 2015-4-14 下午5:00:10 
* @version V1.0   
*/
package myenum;
import static tool.Print.*;
import util.Enums;
public class RandomTest {
	
	enum activity{
		beat,kiss,make,hand
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
		for(int i=0;i<100;i++)
			print(Enums.rand(activity.class));
	}

}
