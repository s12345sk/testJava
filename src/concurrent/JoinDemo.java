/**   
* @Title: JoinDemo.java 
* @Package concurrent 
* @Description:  
* @author kang.sun(kanggood@126.com)   
* @date 2015-1-6 下午8:54:56 
* @version V1.0   
*/
package concurrent;

public class JoinDemo {
	public static void main(String[] args){
		Sleeper sl1 = new Sleeper("DOG",1200);
		sl1.interrupt();
		Sleeper sl2 = new Sleeper("PIG",1200);
		Joiner j1 = new Joiner(sl1,"ddddd");
		Joiner j2 = new Joiner(sl2,"xxxxx");
	}

}
class Sleeper extends Thread{
	private int count;
	public  Sleeper(String name,int count){
		super(name);
		this.count = count;	
		start();
	}
	public void run(){
		try {
			sleep(count);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println(getName()+" interrupt");
			return;
		}
		System.out.println(getName()+" awake");
	}
}
class Joiner extends Thread{
	private Sleeper sl;
	public Joiner(Sleeper sl,String name){
		super(name);
		this.sl = sl;	
		start();
	}
	public void run(){
		try {
			sl.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("aaaaaaaaaaaaaa!!!");
		}
		System.out.println(getName()+"  com");
	}
}
