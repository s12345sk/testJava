package io;
import java.io.*;
import java.util.regex.Pattern;
public class Dir2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File f = new File("d:"+File.separator);
		String[] list = f.list(new FilenameFilter(){
				private Pattern pat = Pattern.compile("svn");

				@Override
				public boolean accept(File dir, String name) {
					// TODO Auto-generated method stub
					return pat.matcher(name).matches();
				}
		});
		for(String s :list)
			System.out.println(s);

	}

}
