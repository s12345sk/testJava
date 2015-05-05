package io;
import java.io.*;
import java.util.regex.Pattern;
public class Dir1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File f = new File("d:"+File.separator);
		String[] list  = f.list(innerFilter("svn"));
		for(String l:list)
			System.out.println(l);

	}
	
	public static  FilenameFilter innerFilter(final String name)  {
		return new FilenameFilter() {
			private Pattern pat = Pattern.compile(name);
			@Override
			public boolean accept(File dir, String name) {
				// TODO Auto-generated method stub
				return pat.matcher(name).matches();
			}
		};
		
	}

}
