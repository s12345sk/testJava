/**   
* @Title: OSExecute.java 
* @Package tool 
* @Description:  
* @author kang.sun(kanggood@126.com)   
* @date 2015-4-1 上午9:29:25 
* @version V1.0   
*/
package tool;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class OSExecute { 
	public static void command(String command) {
    try {
        Process process=new ProcessBuilder(command.split(" ")).start();
        BufferedReader results=new BufferedReader(new InputStreamReader(process.getInputStream()));
        String s;
        while((s=results.readLine())!=null){
            System.out.println(s);
        }
        BufferedReader errors=new BufferedReader(new InputStreamReader(process.getErrorStream()));
        while((s=errors.readLine())!=null){
            System.err.println(s);
        }
    } catch (Exception e) {
        if(!command.startsWith("CMD /C")){
            command("CMD /C"+command);
        }else{
            throw new RuntimeException(e);
        }
    }
}
public static void main(String[] args) {
    OSExecute.command("java");
}

}
