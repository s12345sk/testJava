/**   
* @Title: BasicGenerator.java 
* @Package fanxing 
* @Description:  
* @author kang.sun(kanggood@126.com)   
* @date 2014-10-16 下午9:27:59 
* @version V1.0   
*/
package fanxing;

import util.Generator;

public class BasicGenerator<T> implements Generator<T>{

	private Class<T> type;
	public BasicGenerator(Class<T> t){
		type = t;
	}
	@Override
	public T next() {
		// TODO Auto-generated method stub
		try {
			return type.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	public static <T> Generator<T> create(Class<T> type){
		return new BasicGenerator<T>(type);
	}
	public static void main(String[] args){
		Generator<Count> gen = BasicGenerator.create(Count.class);
		for(int i=0;i<5;i++)
			System.out.println(gen.next());
	}

}
class Count{
	private static int count = 0;
	private final int id = count++;
	public String toString(){
		return this.getClass().getSimpleName()+" is "+id;
	}
}
