/**   
* @Title: EqualTest.java 
* @Package effectivejava 
* @Description:  
* @author kang.sun(kanggood@126.com)   
* @date 2014-10-30 下午2:55:48 
* @version V1.0   
*/
package effectivejava;

import java.util.HashMap;
import java.util.Map;

public class EqualTest {
	private int a;
	private int b;
	private int c;
	public EqualTest(int a,int b,int c){
		try{
		check(a,34,"a");
		check(b,33,"b");
		check(c,35,"c");
		}catch(Exception e){
//			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		this.a  =  a;
		this.b  =  b;
		this.c  =  c;
	}
	private static void check(int nun,int max,String des) throws Exception{
		if(nun<0||nun>max)
			throw new Exception(des+" is wrong");
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		System.out.println("diao yong le !!");
		return 20;
	}
	/*
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(!(obj instanceof EqualTest))
			return false;
		EqualTest et = (EqualTest)obj;
		return et.a==this.a&&et.b==this.b&&et.c==this.c;
		
	}
	public static void main(String[] args){
		EqualTest et = new EqualTest(20,21,23);
		EqualTest et1 = new EqualTest(20,21,23);
		System.out.println(et.equals(et1));
		Map m = new HashMap();
		m.put(et, 1);
		System.out.println(m.get( new EqualTest(20,21,23)));
		
	}
	
}
