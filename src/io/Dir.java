package io;
import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;

public class Dir {
	public static void main(String[] args){
		String[] list;
		File path = new File("d://");
		if(args.length==0)
			list = path.list();
		else
			list = path.list(new filter(args[0]));
		for(String name:list)
			System.out.println(name);
	}

}
class filter implements FilenameFilter{
	
	private Pattern pat;
    public filter(String name){
    	pat = Pattern.compile(name);
    }
	@Override
	public boolean accept(File dir, String name) {
		// TODO Auto-generated method stub
		return pat.matcher(name).matches();
		
	}
	
}
