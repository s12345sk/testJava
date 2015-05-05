/**   
* @Title: Composite.java 
* @Package effective 
* @Description:  组合模式
* @author kang.sun(kanggood@126.com)   
* @date 2014-10-10 下午9:21:55 
* @version V1.0   
*/
package designmode;

import java.util.List;

public class Composite {

}

interface MachineComponent{
	public int getCount();
}
class Machine implements MachineComponent{

	/* (non-Javadoc)
	 * @see effective.MachineComponent#getCount()
	 */
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 1;
	}
	
}
class MachineComposite implements MachineComponent{
	
	private List<MachineComponent> list;

	/* (non-Javadoc)
	 * @see effective.MachineComponent#getCount()
	 */
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		int count = 0;
		for(MachineComponent mc:list ){
			count+=mc.getCount();
		}
		return count;
	}
	
}
