/**   
* @Title: VendingMachine.java 
* @Package myenum 
* @Description:  自动售货机的例子。自动售货机，输入分为，投币，选择物品，放弃交易，或者停止。
* 需要对输入进行分类。
* 售货机状态为：初始化--》放钱--》发货--》找零--》初始化
* 
* @author kang.sun(kanggood@126.com)   
* @date 2015-4-17 下午5:04:48 
* @version V1.0   
*/
package myenum;
import static myenum.Input.*;

import java.awt.event.ItemEvent;
import java.util.EnumMap;

import util.Enums;
import util.Generator;
import static tool.Print.*;
enum Catagory{
	MONEY(YI,WU,SHI),
	ITEM(COLE,MIAN,WATER),
	AB(ABORT),
	TER(STOP);
	private Input[] inputs;
	Catagory(Input ...inputs ){
		this.inputs = inputs;
	}
	public static EnumMap<Input,Catagory> em = new EnumMap(Input.class);
	static{
		for(Catagory ca:Catagory.values()){
			for(Input in:ca.inputs)
					em.put(in, ca);				
		}
	}
	public static Catagory getCa(Input in){
		return em.get(in);
	}
}
class  Gen implements Generator<Input>{

	/* (non-Javadoc)
	 * @see util.Generator#next()
	 */
	@Override
	public Input next() {
		// TODO Auto-generated method stub
		Input in = Enums.rand(Input.class);
		print("-0----------"+in);
		return in;
	}
	
}
public class VendingMachine {
	private static int amount = 0;
	private static Input in;
	private static State sta = State.Resting;
	enum Auto{ isAuto}
	enum State{
		Resting{
			void next(Input in){
				switch(Catagory.getCa(in)){
				case MONEY:
					amount += in.getValue();
					print("put money:"+in.getValue()+",now is "+amount);
					sta = Pick;
					break;
				case TER:
					sta = Termimal;
					break;
				default:
					print(" no use !");
				}
			}
		},
		Pick{
			void next(Input in){
				switch(Catagory.getCa(in)){
				case MONEY:
					amount += in.getValue();
					print("put money:"+in.getValue()+",now is "+amount);
					break;
				case ITEM:
					if(in.getValue()>amount)
						print("select "+in+",but money is not full");
						
					else{
						VendingMachine.in = in;
						amount -= in.getValue();
						sta = Out;
					}
					break;
				case AB:
					sta = GiveChance;
					break;
				case TER:
					sta = Termimal;
					break;
				}
			}
		},
		Out(Auto.isAuto){
			void next(){
				print("your item is "+in);
				sta = GiveChance;
			}
		},
		GiveChance(Auto.isAuto){
			void next(){
				print("your chance is "+amount);
				amount = 0;
				sta = Resting;
			}
		},
		Termimal{
			void output(){
				print("halted!");
			}
		};
		
		private  boolean auto = false;
		
		State(){
			
		}
		State(Auto au){
			auto = true;
		}
		void next(){
			throw new RuntimeException("only automatic can go!");
		}
		void output(){
			print("now the amount is "+amount);
		}
		void next(Input input){
			throw new RuntimeException("only input can go!");
		}
		 
	}
	void run(Generator<Input> gen){
		while(sta!=State.Termimal){
			
			sta.next(gen.next());
		while(sta.auto)
			sta.next();
		}
	}
	/** 
	 * @Title: main 
	 * @Description: 
	 * @param @param args    设定文件 
	 * @return void    返回类型 
	 * @throws 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		VendingMachine  vh = new VendingMachine();
		vh.run(new Gen());
	}

}
