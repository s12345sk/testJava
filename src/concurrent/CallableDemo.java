/**   
* @Title: CallableDemo.java 
* @Package concurrent 
* @Description:  
* @author kang.sun(kanggood@126.com)   
* @date 2015-1-5 下午8:42:17 
* @version V1.0   
*/
package concurrent;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableDemo {
	public static void main(String[] args){
		ExecutorService es = Executors.newCachedThreadPool();
		ArrayList<Future<Rel>> al = new ArrayList<Future<Rel>>();
		for(int i=0;i<5;i++){
			al.add(es.submit(new Result("test"+i)));
			
		}
		for(Future<Rel> fu:al)
			try {
				System.out.println(fu.get());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
class Result implements Callable<Rel>{
	
	private String name;
	/* (non-Javadoc)
	 * @see java.util.concurrent.Callable#call()
	 */
	public Result(String name){
		this.name = name;
	}
	@Override
	public Rel call() throws Exception {
		// TODO Auto-generated method stub
		return new Rel(name);
	}
	
}
class Rel{
	private static int count = 100;
	private final int id = count--;
	private String name;
	public Rel(String name){
		this.name = name;
	}
	public String toString(){
		return name+"--"+id;
	}
}