package utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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
}
