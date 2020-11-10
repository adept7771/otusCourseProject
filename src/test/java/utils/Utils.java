package utils;

public class Utils {

    public static String getSystemVariableValue(String variableName){
                String browserNameFromSystem = null;
        try {
            return System.getProperty("browser");
        } catch (Exception e) {
            return null;
        }
    }
}
