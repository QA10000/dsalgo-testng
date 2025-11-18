package utilities;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;



public enum PageLinks{
	//Common
	NUMPYNINJA_COMMON("Common","NumpyNinja"),
	DATASTRUCTURES_COMMON("Common","Data Structures"),
	USERNAME("Common","Qatitans1"),
	SIGNOUT_COMMON("Common","Sign out"),
	SIGNIN_LOGIN("Login","Sign in"),
	REGISTER_LOGIN("Login","Register"),
	LOGGEDOUT_LOGIN("Login","Logged out successfully"),
	PRACTICEQUESTIONS_ALLSUBMODULES("AllSubModules","Practice Questions"),
	TRYHERE_ALLSUBMODULES("AllSubModules","Try here>>>"),
	LOGIN_PAGE_URL("URL", "https://dsportalapp.herokuapp.com/login"),
    HOME_PAGE_URL("URL", "https://dsportalapp.herokuapp.com/home"),
    DATA_STRUCTURE_PAGE_URL("URL", "https://dsportalapp.herokuapp.com/data-structures-introduction"),
    TRY_EDITOR_PAGE_URL("URL", "https://dsportalapp.herokuapp.com/try-editor"),
    DATA_STRUCTURE_PAGE_TIME_COMPLEXITY("URL","https://dsportalapp.herokuapp.com/data-structures-introduction/time-complexity/" ),

	//LinkedList Module
	INTRODUCTION_LINKEDLIST("LinkedList","Introduction"),
	CREATINGLINKEDLIST_LINKEDLIST("LinkedList","Creating Linked LIst"),
	TYPESOFLINKEDLIST_LINKEDLIST("LinkedList","Types of Linked List"),
	IMPLEMENTLINKEDLIST_LINKEDLIST("LinkedList","Implement Linked List in Python"),
	TRAVERSAL_LINKEDLIST("LinkedList","Traversal"),
	INSERTION_LINKEDLIST("LinkedList","Insertion"),
	DELETION_LINKEDLIST("LinkedList","Deletion"),

	//Stack Module
	OPERATIONSINSTACK_STACK("Stack","Operations in Stack"),
	IMPLEMENTATION_STACK("Stack","Implementation"),
	APPLICATIONS_STACK("Stack","Applications"),

	//Home Module
	ONE_HOME("Home","Operations in Home"),
	TWO_HOME("Home","Implementation"),
	THREE_HOME("Home","Applications"),
	ERROR_MSG("Home","You are not logged in"),
    HOME_PAGE_TITLE("Title","NumpyNinja"),
    
    //DataStructure
	TIMECOMPLEXITY_DSINTRO("DSIntro","Time Complexity");
	 
	

	private final String module;
	private final String linkText; 

	PageLinks(String module, String linkText) {
		this.module = module;
		this.linkText = linkText;
	}

	public String getModule() {
		return module;
	}

	public String getLinkText() {
		return linkText;
	}

	public static List<String> getLinksForModule(String moduleName) {
		return Arrays.stream(PageLinks.values())
				 .filter(p -> p.getModule().equalsIgnoreCase(moduleName))
				 .map(PageLinks::getLinkText)
				 .collect(Collectors.toList());
	}
	
	public static List<String> getLinksForModules(Module... modules) {
	    Set<String> moduleNames = Arrays.stream(modules)
	                                    .map(Enum::name)
	                                    .collect(Collectors.toSet());

	    return Arrays.stream(PageLinks.values())
	                 .filter(p -> moduleNames.contains(p.getModule().toUpperCase()))
	                 .map(PageLinks::getLinkText)
	                 .distinct()
	                 .collect(Collectors.toList());
	}

}

