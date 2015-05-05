/**   
* @Title: OddChecker.java 
* @Package bingfa 
* @Description:  
* @author kang.sun(kanggood@126.com)   
* @date 2015-2-28 上午10:14:40 
* @version V1.0   
*/
package bingfa;

public class OddChecker extends IntGenerator{
	private int value = 0;
	public  int next(){
		value++;
		Thread.yield();
		value++;
		System.out.println(value);
		return value;
	}
	public static void main(String[] args){
		OddChecker oc = new OddChecker();
		EventChecker.test(oc,10);
	}


}
