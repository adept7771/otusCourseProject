package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Utils {

    public static String getSystemVariableValue(String variableName){
        try {
            return System.getProperty("browser");
        } catch (Exception e) {
            return null;
        }
    }

    public static String getSystemMonthInMMMFormat(){
        return new SimpleDateFormat("MMM", Locale.ENGLISH).format(Calendar.getInstance().getTime());
    }

    public static String getSystemDateIn_dd_MMM_yyyy(){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy", Locale.US);
        return formatter.format(date);
    }
}
