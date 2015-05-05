package review;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExcutorTest implements Runnable{
	private int id;
	public ExcutorTest(int id){
		this.id = id;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println(id+":start!");
		try {
			Thread.sleep(500);
			System.out.println(id+":  end!");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args){
		ExecutorService es = Executors.newFixedThreadPool(3);
//		ExecutorService es = Executors.newCachedThreadPool();
		for(int i=0;i<10;i++){
			es.submit(new Thread(new ExcutorTest(i)));
		}
		System.out.println("hello!=============================");
		es.shutdown();
	}

}
