/**   
* @Title: SpiltTest.java 
* @Package test 
* @Description:  
* @author kang.sun(kanggood@126.com)   
* @date 2014-10-29 下午3:57:20 
* @version V1.0   
*/
package test;

public class SpiltTest {
	public static final String s= "you ttt";
	public static void main(String[] args){
		String[] ss = s.split(" ");
		System.out.println(ss.length+"  "+ss[0]);
	}

}
