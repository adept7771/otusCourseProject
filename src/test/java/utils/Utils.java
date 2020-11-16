package utils;

import core.Core;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Utils {

    private static Logger logger = LogManager.getLogger(Utils.class);

    public static String getSystemVariableValue(String variableName){
        try {
            logger.info("System variable: " + variableName + " is: " + System.getProperty("browser"));
            return System.getProperty("browser");
        } catch (Exception e) {
            logger.info("Can't get system variable: " + variableName);
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
