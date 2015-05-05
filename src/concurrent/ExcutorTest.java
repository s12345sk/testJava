package concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExcutorTest extends Thread{
	
	private String name;
	private static int i=0;
	public ExcutorTest(String name){
		this.name = name;
	}
	public void run(){
		System.out.println("["+name+":start!]");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("["+name+":end!]");		
		
	}

	public static void main(String[] args) {
			ExecutorService service =  Executors.newFixedThreadPool(5);
			for(int i=0;i<10;i++)
			  service.execute(new ExcutorTest("thread :"+i));
			service.shutdown();
			
			Executors.newFixedThreadPool(5).execute(new Thread(){
				public void run(){
					while(true){
					System.out.println(i++);
					if(i==100){
						
						break;
					}
					}
				}
				
			});
		
		

	}

}
