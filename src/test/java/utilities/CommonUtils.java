/*package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class CommonUtils {
	public static String APP_URL;
	public static String BROWSER;
	public static String UserName;
	public static String Password;
	public static String CHROME_DRIVER;
	public static String APP_TITLE;
	public static String USERNAME;
	public static String PASSWORD;
	public static String EXCELREADER;
	public static String  REGISTER_LINK;
	public static List<String> LINKLISTPAGE_LINKS;
	public static List<String> LINKLISTSUBPAGE_LINKS;
    public static List<String> LINKLISTPAGE_HEADERS;
    public static List<String> STACKPAGE_LINKS;
	
	static Properties properties = new Properties();
		
	public static void loadProperties() {
		
		FileInputStream inputstr = null;
		
		try {
		 inputstr = new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//config//configuration.properties");
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	
	try {
	    properties.load(inputstr);
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	APP_URL = properties.getProperty("applicationURL");  
	BROWSER = properties.getProperty("BROWSER");
	CHROME_DRIVER = properties.getProperty("CHROME_DRIVER");
	UserName = properties.getProperty("UserName");
	Password = properties.getProperty("Password");
	APP_TITLE = properties.getProperty("APP_TITLE");
	EXCELREADER = properties.getProperty("excelfilepath");
    REGISTER_LINK = properties.getProperty("registerurl");
    

	}
	
}*/

package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class CommonUtils {
    public static String APP_URL;
    public static String BROWSER;
    public static String APP_TITLE;
    public static String USERNAME;
    public static String PASSWORD;
    public static String EXCELREADER;
    public static String REGISTER_LINK;
    public static String CHROME_DRIVER;
    public static String HOME_URL;
    public static String LOGIN_URL;
    public static String DS_URL;
    public static String DS_TS_URL;
    public static String TRY_EDTR_URL;
    public static List<String> LINKLISTPAGE_LINKS;
    public static List<String> LINKLISTSUBPAGE_LINKS;
    public static List<String> LINKLISTPAGE_HEADERS;
    public static List<String> STACKPAGE_LINKS;
    public static String NOT_SIGNED_IN_MESSAGE;
    public static String DS_DROP_DOWN_LABEL;
    public static String NUMPY_NINJA_LABEL;
    public static String ARRAY_LABEL;
    public static String STACK_LABEL; 
    public static String GRAPH_LABEL;
    public static String TREE_LABEL;    
    public static String LINKED_LIST_LABEL;
    public static String QUEUE_LABEL;
    public static String SIGNIN_LINK_TEXT;
    public static String SIGNOUT_LINK_TEXT;
    public static String REGISTER_LINK_TEXT;
    
    static Properties properties = new Properties();

    public static void loadProperties() {
        try (FileInputStream input = new FileInputStream(System.getProperty("user.dir") +
                "/src/test/resources/config/configuration.properties")) {

            properties.load(input);

            // Load individual properties with validation
            APP_URL = safeGet("applicationURL");
            BROWSER = safeGet("BROWSER");
            CHROME_DRIVER = safeGet("CHROME_DRIVER");
            USERNAME = safeGet("UserName"); // consider renaming to "username"
            PASSWORD = safeGet("Password");
            APP_TITLE = safeGet("APP_TITLE");
            EXCELREADER = safeGet("excelfilepath");
            REGISTER_LINK = safeGet("registerurl");
            HOME_URL = safeGet("homepageURL");
            LOGIN_URL = safeGet("loginpageURL");
            DS_URL = safeGet("dataStructurePage");
            DS_TS_URL = safeGet("dataStructuresTimeComp");
            TRY_EDTR_URL = safeGet("tryEditorPage");
            NOT_SIGNED_IN_MESSAGE = safeGet("NotSignedInMessage");
            DS_DROP_DOWN_LABEL = safeGet("DSDropDownLabel");
            NUMPY_NINJA_LABEL = safeGet("NumpyNinjaLabel");            
            ARRAY_LABEL = safeGet("ArrayLabel");
            QUEUE_LABEL = safeGet("QueueLabel");            
            LINKED_LIST_LABEL = safeGet("LinkedListLabel");
            TREE_LABEL = safeGet("TreeLabel");
            STACK_LABEL = safeGet("StackLabel");
            QUEUE_LABEL = safeGet("QueueLabel");
            SIGNIN_LINK_TEXT = safeGet("signInLinkTxt");
            SIGNOUT_LINK_TEXT = safeGet("signOutLinkTxt");            
            REGISTER_LINK_TEXT = safeGet("registerLinkTxt");            
            
            LINKLISTPAGE_LINKS = parseList("LinkedListPageLinks");
            LINKLISTSUBPAGE_LINKS = parseList("LinkedListSubPageLinks");
            LINKLISTPAGE_HEADERS = parseList("LinkedListPageHeaders");
            STACKPAGE_LINKS = parseList("StackPageLinks");

            System.out.println("✅ Configuration properties loaded successfully.");

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

    private static List<String> parseList(String key) {
        String raw = properties.getProperty(key);
        if (raw == null || raw.trim().isEmpty()) {
            System.err.println("⚠️ Missing or empty list property: " + key);
            return List.of();
        }
        return Arrays.stream(raw.split(","))
                .map(String::trim)
                .toList();
    }
}