/**   
* @Title: CoffeeGenerator.java 
* @Package fanxing 
* @Description:  
* @author kang.sun(kanggood@126.com)   
* @date 2014-10-15 下午9:29:36 
* @version V1.0   
*/
package fanxing.coffee;

import java.util.Iterator;
import java.util.Random;
import util.Generator;

public class CoffeeGenerator implements Generator<Coffee>,Iterable<Coffee> {

	public static Class[] types = {AA.class,BB.class,CC.class,DD.class,EE.class};
	Random rand = new Random(47);
	int size = 0;
	public CoffeeGenerator(int size){
		this.size = size;
	}
	public CoffeeGenerator(){}
	@Override
	public Coffee next() {
		// TODO Auto-generated method stub
		try {
			return (Coffee)types[rand.nextInt(types.length)].newInstance();
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
	class Ite implements Iterator<Coffee>{

		/* (non-Javadoc)
		 * @see java.util.Iterator#hasNext()
		 */
		int count = size;
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return count>0;
		}

		/* (non-Javadoc)
		 * @see java.util.Iterator#next()
		 */
		@Override
		public Coffee next() {
			// TODO Auto-generated method stub
			count--;
			return CoffeeGenerator.this.next();
		}

		/* (non-Javadoc)
		 * @see java.util.Iterator#remove()
		 */
		@Override
		public void remove() {
			// TODO Auto-generated method stub
			try {
				throw new Exception("not support!");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	

	public static void main(String[] args){
		CoffeeGenerator gen = new CoffeeGenerator();
		for(int i=0;i<5;i++){
			Coffee co = gen.next();
			System.out.println(co);
		}
			
		for(Coffee co:new CoffeeGenerator(5))
			System.out.println(co);
	}
	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<Coffee> iterator() {
		// TODO Auto-generated method stub
		return new Ite();
	}
	
	

}
