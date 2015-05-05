/**   
* @Title: Game.java 
* @Package myenum.dispose 
* @Description:  
* @author kang.sun(kanggood@126.com)   
* @date 2015-4-21 上午10:54:05 
* @version V1.0   
*/
package myenum.dispose;
import static myenum.dispose.Outcome.*;
public enum Game implements Competor<Game>{
	ROCK(DROW,WIN,LOSE),
	SCI(LOSE,DROW,WIN),
	PAPER(WIN,LOSE,DROW);
	private Outcome vRock,vSci,Vpaper;
	Game(Outcome vRock,Outcome vSci,Outcome Vpaper){
		this.vRock = vRock;
		this.vSci = vSci;
		this.Vpaper = Vpaper;
	}
	/* (non-Javadoc)
	 * @see myenum.dispose.Competor#compete(myenum.dispose.Competor)
	 */
	@Override
	public Outcome compete(Game competor) {
		// TODO Auto-generated method stub
		switch(competor){
		case ROCK:return vRock;
		case SCI:return vSci;
		case PAPER:return Vpaper;
		default:return null;
		}
		
	}
	
	public void main(String[] args){
		
	}
	
	
}
