/**   
* @Title: CompletionServiceTest.java 
* @Package review 
* @Description: 对completionService进行简单的代码使用和测试
* @author kang.sun(kanggood@126.com)   
* @date 2014-10-9 下午5:03:20 
* @version V1.0   
*/
package review;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletionServiceTest implements Callable{
	private int id;
	public CompletionServiceTest(int id){
		this.id = id;
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException{
		ExecutorService es = Executors.newFixedThreadPool(3);
		CompletionService cs = new ExecutorCompletionService(es);
		for(int i=0;i<10;i++)
			cs.submit(new CompletionServiceTest(i));
		for(int m=0;m<10;m++){
			Object obj = cs.take().get();
			System.out.println(obj);
		}
		
		
	}

	/* (non-Javadoc)
	 * @see java.util.concurrent.Callable#call()
	 */
	@Override
	public Object call() throws Exception {
		// TODO Auto-generated method stub
		System.out.println(id+" :杀了我吧！");
		Thread.sleep(1000);
		return id+" ：舍身取义";
	}
	

}
