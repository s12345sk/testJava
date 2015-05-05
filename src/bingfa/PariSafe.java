/**   
* @Title: PariSafe.java 
* @Package bingfa 
* @Description:  
* @author kang.sun(kanggood@126.com)   
* @date 2015-3-2 上午9:51:58 
* @version V1.0   
*/
package bingfa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PariSafe {
	private int x;
	private int y;
	public PariSafe(){
		this(0,0);
	}
	public PariSafe(int x,int y){
		this.x = x;
		this.y = y;
	}	
	public void inX(){ x++; }
	public void inY(){ y++; }
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public class NotEqueException extends RuntimeException{
		public NotEqueException(){
			super("this pair is not equal!!");
		}
	}
	public void checkState(){
		if(x!=y)
			throw new NotEqueException();
	}
	public String toString(){
		return "x:"+x+" y:"+y;
	}
	public static void main(String[] args){
		PariManage3 p1 = new PariManage3();
		PariManage4 p2 = new PariManage4();
		Test2.test(p1, p2);
	}
  
}
abstract class PariManage{
	AtomicInteger counter = new AtomicInteger(0);
	PariSafe p = new PariSafe();
	public abstract void inc();
	private List<PariSafe> storage = Collections.synchronizedList(new ArrayList<PariSafe>());
	public PariSafe getPari(){
		return new PariSafe(p.getX(),p.getY());
	}
	public void store(PariSafe ps){
		storage.add(ps);
		try {
			TimeUnit.MILLISECONDS.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
class PariManage1 extends PariManage{

	/* (non-Javadoc)
	 * @see bingfa.PariManage#inc()
	 */
	@Override
	public synchronized void inc() {
		// TODO Auto-generated method stub
		p.inX();
		p.inY();
		store(getPari());
	}
	
}
class PariManage2 extends PariManage{

	/* (non-Javadoc)
	 * @see bingfa.PariManage#inc()
	 */
	@Override
	public void inc() {
		// TODO Auto-generated method stub
		PariSafe tmp;
		synchronized(this){
			p.inX();
			p.inY();
			tmp = getPari();
		}
		store(tmp);
	}
	
}
class PariManage3 extends PariManage{
	private Lock lock = new ReentrantLock();
	/* (non-Javadoc)
	 * @see bingfa.PariManage#inc()
	 */
	@Override
	public  void inc() {
		// TODO Auto-generated method stub
		lock.lock();
		p.inX();
		p.inY();
		store(getPari());
		lock.unlock();
	}
	
}class PariManage4 extends PariManage{
	private Lock lock = new ReentrantLock();
	/* (non-Javadoc)
	 * @see bingfa.PariManage#inc()
	 */
	@Override
	public  void inc() {
		// TODO Auto-generated method stub
		PariSafe tmp;
		lock.lock();
		try{
			p.inX();
			p.inY();
			tmp = getPari();
		}finally{
			lock.unlock();
		}
		store(tmp);
	}
	
}
class PariMaul implements Runnable{
	private PariManage pm;
	public PariMaul(PariManage pm){
		this.pm = pm;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true)
			pm.inc();
	}
	public String toString(){
		return "Pari:"+pm.getPari()+";count:"+pm.counter.get();
	}
	
}

class PariChecker implements Runnable{
	private PariManage pm;
	public PariChecker(PariManage pm){
		this.pm = pm;
	}
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			pm.counter.getAndIncrement();
			pm.getPari().checkState();
		}
	}
	
}

class Test{
	static void test(PariManage1 p1,PariManage2 p2){
		PariMaul pm1 = new PariMaul(p1);
		PariMaul pm2 = new PariMaul(p2);
		PariChecker pc1 = new PariChecker(p1);
		PariChecker pc2 = new PariChecker(p2);
		ExecutorService es = Executors.newCachedThreadPool();
		es.execute(pm1);
		es.execute(pm2);
		es.execute(pc1);
		es.execute(pc2);
		try {
			TimeUnit.MILLISECONDS.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("pm1:"+pm1+",pm2"+pm2);
		System.exit(0);
	}
	
}
class Test2{
	static void test(PariManage3 p1,PariManage4 p2){
		PariMaul pm1 = new PariMaul(p1);
		PariMaul pm2 = new PariMaul(p2);
		PariChecker pc1 = new PariChecker(p1);
		PariChecker pc2 = new PariChecker(p2);
		ExecutorService es = Executors.newCachedThreadPool();
		es.execute(pm1);
		es.execute(pm2);
		es.execute(pc1);
		es.execute(pc2);
		try {
			TimeUnit.MILLISECONDS.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("pm3:"+pm1+",pm4"+pm2);
		System.exit(0);
	}
}
