

package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class CommonUtils {
    public static String APP_URL;
    public static String BROWSER;
   
    public static String EXCELREADER;
    public static String HOME_URL;

   

    static Properties properties = new Properties();

    public static void loadProperties() {
        try (FileInputStream input = new FileInputStream(System.getProperty("user.dir") +
                "/src/test/resources/config/configuration.properties")) {

            properties.load(input);

            // Load individual properties with validation
            APP_URL = safeGet("applicationURL");
            BROWSER = safeGet("BROWSER");
            EXCELREADER = safeGet("excelfilepath");
            //HOME_URL = safeGet("homepageURL");



        } catch (IOException e) {
            System.err.println("❌ Failed to load configuration.properties");
            e.printStackTrace();
        }
    }

    private static String safeGet(String key) {
        String value = properties.getProperty(key);
        if (value == null || value.trim().isEmpty()) {
            System.err.println("⚠️ Missing or empty property: " + key);
        }
        return value;
    }

  
}


