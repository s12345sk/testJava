/**   
* @Title: InnerThreadDemo.java 
* @Package concurrent 
* @Description:  
* @author kang.sun(kanggood@126.com)   
* @date 2015-1-6 下午8:04:34 
* @version V1.0   
*/
package concurrent;

public class InnerThreadDemo {
	public static void main(String[] args){
//		InnerThread it = new InnerThread("helloa");
//		InnerThread1 it1 = new InnerThread1("ni ming a");
//		InnerThread2 it2 = new InnerThread2("rundddle a ");
//		InnerThread3 it3 = new InnerThread3("dfaasfsad a ");
		new ThreadMehod("th").runTask();
	}

}
//task
class ThreadMehod{
	private Thread t;
	private int count = 5;
	private String name;
	public ThreadMehod(String name){
		this.name = name;
	}
	public void runTask(){
		if(t==null){
			t = new Thread(name){
				public void run(){
					while(true){
						System.out.println(this);
						if(--count<0)return;
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				public String toString(){
					return t.getName()+" "+count;
				}
			};
			t.start();
		}
	}
}
//匿名runnable接口
class InnerThread3{
	private Thread t;
	private int count = 5;
	public InnerThread3(String name){
		t = new Thread(new Runnable(){
			public void run(){
				while(true){
					System.out.println(this);
					if(--count<0)return;
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			public String toString(){
				return t.getName()+" "+count;
			}
		},name);
		t.start();
	}
}
//实现runnable接口
class InnerThread2{
	private Inner t;
	private int count = 5;
	public InnerThread2(String name){
		t = new Inner(name);
	}
	private class Inner implements Runnable{

		private Thread t;
		public Inner(String name){
			t = new Thread(this,name);
			t.start();
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(true){
				System.out.println(this);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(count--<0)return;
			}
		}
		public String toString(){
			return t.getName()+" "+count;
		}
		
	}
}

//匿名内部类
class InnerThread1{
	private Thread t;
	private int count = 5;
	public InnerThread1(String name){
		t = new Thread(name){
			public void run(){
				while(true){
					System.out.println(this);
					if(count--<0)break;
					try {
						sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				}
			public String toString(){
				return getName()+" "+count;
			}
		};
		t.start();	}
}
//内部类
class InnerThread {
	private Inner t;
	private int count = 5;
	public InnerThread(String name){
		t = new Inner(name);
	}
	private class Inner extends Thread{
		Inner(String name){
			super(name);
			start();
		}
		public String toString(){
			return getName()+" "+count;
		}
		public void run(){
			while(true){
				System.out.println(this);
				if(count--<0)break;
				try {
					sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}