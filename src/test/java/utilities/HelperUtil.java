package utilities;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class HelperUtil {

	public static String futureDate(int n) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Calendar cal = Calendar.getInstance();
		// Adding N Day to the current date
		cal.add(Calendar.DAY_OF_MONTH, n);
		// Date after adding one day to the current date
		return sdf.format(cal.getTime());
	}

}
