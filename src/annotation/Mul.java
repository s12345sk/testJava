/**   
* @Title: Mul.java 
* @Package annotation 
* @Description:  
* @author kang.sun(kanggood@126.com)   
* @date 2015-4-23 下午4:24:40 
* @version V1.0   
*/
package annotation;
import static tool.Print.*;
@ExtractInterface("IMul")
public class Mul {
	
	public int mul(int x,int y){
		int total = 0;
		for(int i=0;i<x;i++){
			total = add(total,y);
		}
		return total;
	}
	private int add(int x,int y){
		return x+y;
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
		Mul mul = new Mul();
		print("13*48="+mul.mul(13, 48));
		
	}

}
