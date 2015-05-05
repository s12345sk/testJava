package concurrent;

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
	public static void main(String[] args){
		ExecutorService es = Executors.newCachedThreadPool();
		CompletionService cs = new ExecutorCompletionService(es);
		for(int i=0;i<10;i++)
			cs.submit(new CompletionServiceTest(i));
		for(int i=0;i<10;i++)
			try {
				System.out.println(cs.take().get());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

	@Override
	public Object call() throws Exception {
		// TODO Auto-generated method stub
		int  time = (int)(Math.random()*1000);
		System.out.println(id+" :begin!");
		Thread.sleep(time);
		System.out.println(id+" end！");
		return this.id+" "+time;
	}

}
