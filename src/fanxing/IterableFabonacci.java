/**   
* @Title: IterableFabonacci.java 
* @Package fanxing 
* @Description:  
* @author kang.sun(kanggood@126.com)   
* @date 2014-10-16 下午7:59:43 
* @version V1.0   
*/
package fanxing;

import java.util.Iterator;

public class IterableFabonacci extends FibonacciGenerate implements Iterable<Integer>{
	private int size;
	public IterableFabonacci(int size){
		this.size = size;
	}

	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<Integer> iterator() {
		// TODO Auto-generated method stub
		
		return new Iterator<Integer>(){

			@Override
			public boolean hasNext() {
				// TODO Auto-generated method stub
				
				return size>0;
			}

			@Override
			public Integer next() {
				size--;
				// TODO Auto-generated method stub
				return IterableFabonacci.this.next();
			}

			@Override
			public void remove() {
				// TODO Auto-generated method stub
				
			}
			
		};
	}
	public static void main(String[] args){
		for(int i:new IterableFabonacci(18))
			System.out.println(i);
	}

}
