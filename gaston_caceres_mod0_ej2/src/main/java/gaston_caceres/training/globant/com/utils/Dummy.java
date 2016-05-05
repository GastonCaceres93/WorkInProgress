package gaston_caceres.training.globant.com.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.joda.time.DateTime;

public class Dummy {

	public static void main(String[] args) {
		DateTime date = new DateTime();
		
			String dateFormatted = null;
			SimpleDateFormat format = new SimpleDateFormat("MMM dd, yyyy");
			String formattedDate = null;
			try {
				
			DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.ENGLISH);
			formattedDate = df.format(date.toDate());

			SimpleDateFormat fs = new SimpleDateFormat("MM/dd/yy");
			formattedDate = format.format(fs.parse(formattedDate));
			
			} catch (Exception e) {
				// TODO: handle exception
			}
			System.out.println(formattedDate);
	}

}
