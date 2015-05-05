/**   
* @Title: FileTest.java 
* @Package test 
* @Description:  
* @author kang.sun(kanggood@126.com)   
* @date 2014-12-3 下午5:35:03 
* @version V1.0   
*/
package test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileTest {
	
	public static void main(String[] args){
		File f = new File("test");
		FileWriter fw;
		try {
			fw = new FileWriter(f);
			fw.write("haha");
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
