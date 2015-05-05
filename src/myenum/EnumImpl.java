/**   
* @Title: EnumImpl.java 
* @Package myenum 
* @Description:  
* @author kang.sun(kanggood@126.com)   
* @date 2014-10-21 上午11:17:01 
* @version V1.0   
*/
package myenum;

import java.util.Random;

import util.Generator;

public class EnumImpl {
	public static <T> void print(Generator<T> tt ){
		System.out.println(tt.next());
	}
	public static void main(String[] args){
		Tes t = Tes.ONE;
		for(int i=0;i<10;i++)
			print(t);
	}

}
 enum Tes implements Generator<Tes>{
	ONE,TWO,THREE,FOUR,FIVE;
	private Random rand = new Random(34);
	/* (non-Javadoc)
	 * @see util.Generator#next()
	 */
	@Override
	public Tes next() {
		// TODO Auto-generated method stub
		return values()[rand.nextInt(values().length)];
	}
	



}
