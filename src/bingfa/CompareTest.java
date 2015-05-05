/**   
* @Title: CompareTest.java 
* @Package bingfa 
* @Description:  
* @author kang.sun(kanggood@126.com)   
* @date 2015-3-31 上午10:59:31 
* @version V1.0   
*/
package bingfa;

import java.util.Random;
import java.util.concurrent.PriorityBlockingQueue;
import static tool.Print.*;
public class CompareTest implements Comparable<CompareTest>{
	private final int count;
	public CompareTest(int count){
		this.count = count;
	}
	public int getCount(){
		return count;
	}
	/**
	 * @throws InterruptedException  
	 * @Title: main 
	 * @Description: 
	 * @param @param args    设定文件 
	 * @return void    返回类型 
	 * @throws 
	 */
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		Random rand = new Random();
		PriorityBlockingQueue<CompareTest> pLine = new PriorityBlockingQueue<CompareTest>();
		for(int i=0;i<10;i++)
			pLine.add(new CompareTest(rand.nextInt(100)));
		while(pLine.size()!=0)
			print(pLine.take());
			
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(CompareTest o) {
		// TODO Auto-generated method stub
		return count>o.getCount()?1:-1;
	}
	public String toString(){
		return "id:"+getCount();
	}

}
