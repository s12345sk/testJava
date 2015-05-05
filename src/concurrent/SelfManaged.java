/**   
* @Title: SelfManaged.java 
* @Package concurrent 
* @Description:  
* @author kang.sun(kanggood@126.com)   
* @date 2015-1-5 下午9:44:44 
* @version V1.0   
*/
package concurrent;

public class SelfManaged implements Runnable{
	private int count = 5;
	private Thread t = new Thread(this);
	public SelfManaged(){
		t.start();
	}
	public String toString(){
		return Thread.currentThread().getName()+"  "+count;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			System.out.println(this);
			if(--count<0)
				break;
		}
	}
	public static void main(String[] args){
		new SelfManaged();
	}

}
