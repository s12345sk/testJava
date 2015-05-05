/**   
* @Title: IntC.java 
* @Package concurrent.event 
* @Description:  
* @author kang.sun(kanggood@126.com)   
* @date 2015-1-6 下午9:39:07 
* @version V1.0   
*/
package concurrent.event;

public class IntC extends IntGenerator{
	private int cuInt = 0;
	/* (non-Javadoc)
	 * @see concurrent.event.IntGenerator#next()
	 */
	public int next() {
		// TODO Auto-generated method stub
		cuInt++;
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cuInt++;
		return cuInt;
	}

}
