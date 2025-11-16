package utilities.enumClass;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;



public enum PageLinks{
	//Common
	NUMPYNINJA_COMMON("Common","NumpyNinja"),
	DATASTRUCTURES_COMMON("Common","Data Structures"),
	//QATITANS1("Qatitans1"),
	SIGNOUT_COMMON("Common","Sign out"),
	
	NUMPYNINJA_LOGIN("Login","NumpyNinja"),
	DATASTRUCTURES_LOGIN("Login","Data Structures"),
	SIGNIN_LOGIN("Login","Sign in"),
	REGISTER_LOGIN("Login","Register"),
	
	PRACTICEQUESTIONS_ALLSUBMODULES("AllSubModules","Practice Questions"),
	TRYHERE_ALLSUBMODULES("AllSubModules","Try here>>>"),

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
	
	//DSINTRO Module
	TIMECOMPLEXITY_DSNTRO("DSIntro","Time Complexity"),
	//PRACTICE_QUESTIONS_DSNTRO("DSIntro","Practice Questions"),
	 
	//Array module for now
	TIMECOMPLEXITY_ARRAYS("Array","Time Complexity"),
	ARRAYSINPYTHON_ARRAYS("Array","Arrays in Python"),
	ARRAYSUSINGLIST_ARRAYS("Array","Arrays Using List"),
	BASICOPERATIONSINLISTS_ARRAYS("Array","Basic Operations in Lists"),
	APPLICATIONSOFARRAY_ARRAYS("Array","Applications of Array"), 
	IMPLEMENTATIONOFQUEUEINPYTHON_ARRAYS("Array","Implementation of Queue in Python"),
	IMPLEMENTATIONUSINGCOLLECTIONSDEQUES_ARRAYS("Array","Implementation using collections.deque"),
	IMPLEMENTATIONUSINGARRAY_ARRAYS("Array","Implementation using array"),
	QUEUEOPERATIONS_ARRAYS("Array","Queue Operations"),
	OVERVIEWOFTREES_ARRAYS("Array","Overview of Trees"),
	TERMINOLOGIES_ARRAYS("Array","Terminologies"),
	TYPESOFTREES_ARRAYS("Array","Types of Trees"),
	TREETRAVERSAL_ARRAYS("Array","Tree Traversals"),
	TRAVERSALSILLUSTRATION_ARRAYS("Array","Traversals-Illustration"),
	BINARYTREES_ARRAYS("Array","Binary Trees"),
	TYPESOFBINARYTREES_ARRAYS("Array","Types of Binary Trees"),
	IMPLENTATIONINPYTHON_ARRAYS("Array","Implementation in Python"),
	BINARYTREETRAVERSALS_ARRAYS("Array","Binary Tree Traversals"),
	IMPLEMENTATIONOFBINARYTREES_ARRAYS("Array","Implementation of Binary Trees"),
	APPLICATIONSOFBINARYTREES_ARRAYS("Array","Applications of Binary trees"),
	BINARYSEARCHTREES_ARRAYS("Array","Binary Search Trees"),
	IMPLEMENTATIONOFBST_ARRAYS("Array","Implementation Of BST"),
	GRAPH_ARRAYS("Array","Graph"),
	GRAPHREPRESTATIONS_ARRAYS("Array","Graph Representations");

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

