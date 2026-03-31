package experiments;

import java.util.Date;

public class DateTimeStamp {

	public static String getTime() {
		Date date=new Date();
		String dateStr=date.toString();
		String finalDate=dateStr.replaceAll(" ", "").replaceAll(":", "");
		
		System.out.println( date.toString());
		System.out.println( finalDate);
		return finalDate;
	}

}
