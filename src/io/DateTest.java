package io;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTest {

	/**
	 * @param args
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		String from = "2013-04-01";
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		 Date d = sdf.parse(from);
		 Calendar cal=Calendar.getInstance();
		 cal.setTime(d);
		 System.out.println(d);
		 for(int i=0;i<50;i++){
			 cal.add(Calendar.DATE, 1);
		 System.out.println(cal.getTime());
		 }
	}

}
