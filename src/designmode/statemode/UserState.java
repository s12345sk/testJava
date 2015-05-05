/**   
* @Title: UserState.java 
* @Package designmode.statemode 
* @Description:  
* @author kang.sun(kanggood@126.com)   
* @date 2015-3-26 上午9:34:55 
* @version V1.0   
*/
package designmode.statemode;

public enum UserState {
	
	NORMAL(1,1),
	WARN(2,3),
	FREEZE(3,5);
	private int id;
	private int num;
	private UserState(int id,int num){
		this.id = id;
		this.num = num;
	}
	public UserState vote(int num){
		if(num>=1&&num<3)
			num = 1;
		if(num>=3&&num<5)
			num = 3;
		if(num>=5)
			num = 5;
		return getByNum(num);
		
	}
	public UserState appeal(){
		return UserState.NORMAL;
	}
	private  UserState getByNum(int num){
		for(UserState us: UserState.values()){
			if (us.num ==num)
				return us;
		}
		return null;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @return the num
	 */
	public int getNum() {
		return num;
	}
	
	

}
