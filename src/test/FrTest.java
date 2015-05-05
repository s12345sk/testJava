package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FrTest {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		File f = new File("C:\\Users\\renren\\Desktop\\logs\\sb\\9.4spam");
		
		File f2 = new File("C:\\Users\\renren\\Desktop\\logs\\sb\\re");
	    FileReader fr = new FileReader(f);
	    
	    FileWriter fw = new FileWriter(f2);
	    BufferedReader br = new BufferedReader(fr);
	    
	    String s;
	    while((s=br.readLine())!=null){
	    	File f1 = new File("C:\\Users\\renren\\Desktop\\logs\\sb\\id_phone");
	    	FileReader fr1 = new FileReader(f1);
	    	BufferedReader br1 = new BufferedReader(fr1);
	    
	    	String[] info = s.split("\\s+");
	    	System.out.println("开始查找："+info[1]);
	    	String s1 ;
	    	while((s1=br1.readLine())!=null){
	    		System.out.println("开始查找："+s1);
	    		if(s1.contains(s)){
	    			fw.write(info[1]+"  "+s1);
	    			System.out.println("已经找到："+info[1]+"  "+s1);
	    		}
	    	}
	    	br1.close();
	    	fr1.close();
	    }
	    br.close();
	    fr.close();
//	    fr1.close();
	    fw.close();

	}

}
