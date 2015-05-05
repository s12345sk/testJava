/**   
* @Title: EnumSets.java 
* @Package myenum 
* @Description:  不能将enum实例当做一个类型
* @author kang.sun(kanggood@126.com)   
* @date 2015-4-15 下午2:02:35 
* @version V1.0   
*/
package myenum;

import java.util.EnumSet;
import static tool.Print.*;

enum Num{
	one,tow,three,four,five,six
}
public class EnumSets {

	/** 
	 * @Title: main 
	 * @Description: 
	 * @param @param args    设定文件 
	 * @return void    返回类型 
	 * @throws 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EnumSet<Num> es = EnumSet.noneOf(Num.class);
		es.add(Num.one);
		print(es);
		es.addAll(EnumSet.of(Num.three,Num.four));
		print(es);
		es.removeAll(EnumSet.range(Num.three, Num.five));
		print(es);
	}

}
