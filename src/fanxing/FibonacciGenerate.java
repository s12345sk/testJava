/**   
* @Title: Fibonacci.java 
* @Package fanxing 
* @Description:  斐波那契数列生成
* @author kang.sun(kanggood@126.com)   
* @date 2014-10-16 下午7:49:35 
* @version V1.0   
*/
package fanxing;

import util.Generator;

public class FibonacciGenerate implements Generator<Integer> {
	
	int count = 0;
	/* (non-Javadoc)
	 * @see util.Generator#next()
	 */
	@Override
	public Integer next() {
		// TODO Auto-generated method stub
		count++;
		return fib(count);
	}
	private int fib(int n){
		if(n<=2)
			return 1;
		return fib(n-1)+fib(n-2);
	}
	public static void main(String[] args){
		FibonacciGenerate fb = new FibonacciGenerate();
		for(int i=0;i<18;i++)
			System.out.println(fb.next());
	}

}
