/**   
* @Title: Mail.java 
* @Package myenum 
* @Description:  
* @author kang.sun(kanggood@126.com)   
* @date 2015-4-15 下午6:36:50 
* @version V1.0   
*/
package myenum;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static tool.Print.*;
import util.Enums;

public class Mail {
	private static int count = 0;
	private final int id = count++;
	enum deliver{YES,NO1,NO2,NO3,NO4,NO5,NO6}
	enum read{NOTREAD,SUCCESS,SUCCESS1,SUCCESS2,SUCCESS3}
	enum scan{ILLGABLE,SUCCESS,SUCCESS1,SUCCESS2,SUCCESS3}
	enum address{miss,SUCCESS,SUCCESS1,SUCCESS2,SUCCESS3}
	enum retu{miss,SUCCESS,SUCCESS1,SUCCESS2,SUCCESS3}
	deliver d;
	read r;
	scan s;
	address a;
	retu re;
	boolean dead = false;
	public String toString(){
		return "mail:"+id;
	}
	public String details(){
		return toString()+" deliver:"+d+" read:"+r+" scan:"+s+" address:"+a+" retu:"+re;
	}
	private static Mail rand(){
		Mail m = new Mail();
		m.d = Enums.rand(deliver.class);
		m.r = Enums.rand(read.class);
		m.s = Enums.rand(scan.class);
		m.a = Enums.rand(address.class);
		m.re = Enums.rand(retu.class);
		return m;
	}
	public static Iterable<Mail> generator(final int count){
		return new Iterable<Mail>(){
			int n = count;
			@Override
			public Iterator<Mail> iterator() {
				// TODO Auto-generated method stub
				return new Iterator<Mail>(){

					@Override
					public boolean hasNext() {
						// TODO Auto-generated method stub
						return --n>0;
					}

					@Override
					public Mail next() {
						// TODO Auto-generated method stub
						return Mail.rand();
					}

					@Override
					public void remove() {
						// TODO Auto-generated method stub
						
					}
					
				};
			}
			
		};
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
		List<Mail> l = new ArrayList<Mail>();
		for(Mail m :generator(100)){
			print(m.details());
			Handler.handle(m);
			if(m.dead == true) l.add(m);
			print("***********");
		}
		for(Mail m:l)
			print(m.details());
	}

}
class Handler{
	enum MailHandler{
		deliver{
			@Override
			boolean hand(Mail m) {
				// TODO Auto-generated method stub
				switch(m.d){
				case YES:
					print(m+" deleiver first!");
					return true;
				default:
					return false;	
				}
			}},
			read{
				@Override
				boolean hand(Mail m) {
					// TODO Auto-generated method stub
					switch(m.r){
					case NOTREAD:						
						return false;
					default:
						print(m+" deleiver second!");
						return true;	
					}
				}},
				scan{
					@Override
					boolean hand(Mail m) {
						// TODO Auto-generated method stub
						switch(m.s){
						case ILLGABLE:						
							return false;
						default:
							print(m+" deleiver third!");
							return true;	
						}
					}},
				 address{
						@Override
						boolean hand(Mail m) {
							// TODO Auto-generated method stub
							switch(m.a){
							case miss:						
								return false;
							default:
								print(m+" deleiver fouth!");
								return true;	
							}
						}},
				 retu{
							@Override
							boolean hand(Mail m) {
								// TODO Auto-generated method stub
								switch(m.re){
								case miss:	
									m.dead = true;
									return false;
								default:
									print(m+" return to sender!");
									return true;	
								}
							}};
		abstract boolean hand(Mail m);
	}
	public static void handle(Mail m){
		for(MailHandler mh:MailHandler.values())
			if(mh.hand(m))
				return;
		print(m+" is a dead!");
	}
}