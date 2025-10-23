package utilities.enumClass;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//public class LinkedListPageData {
    public enum PageLinks{
        NUMPY_NINJA("NumpyNinja"),
        DATA_STRUCTURES("Data Structures"),
        //QATITANS1("Qatitans1"),
        SIGN_OUT("Sign out"),
        INTRODUCTION("Introduction"),
        CREATING_LINKED_LIST("Creating Linked LIst"),
        TYPES_OF_LINKED_LIST("Types of Linked List"),
        IMPLEMENT_LINKED_LIST("Implement Linked List in Python"),
        TRAVERSAL("Traversal"),
        INSERTION("Insertion"),
        DELETION("Deletion"),
    	PRACTICEQUESTIONS("Practice Questions"),
        OPERATIONSINSTACK("Operations in Stack"),
        IMPLEMENTATION("Implementation"),
        APPLICATION("Applications"),
        TRYHERE("Try here>>>"),
        TIMECOMPLEXITY("Time Complexity"),
        ARRAYSINPYTHON("Arrays in Python"),
        ARRAYSUSINGLIST("Arrays Using List"),
        BASICOPERATIONSINLISTS("Basic Operations in Lists"),
        APPLICATIONSOFARRAY("Applications of Array"), 
        IMPLEMENTATIONOFQUEUEINPYTHON("Implementation of Queue in Python"),
        IMPLEMENTATIONUSINGCOLLECTIONSDEQUES("Implementation using collections.deque"),
       IMPLEMENTATIONUSINGARRAY("Implementation using array"),
       QUEUEOPERATIONS("Queue Operations"),
       OVERVIEWOFTREES("Overview of Trees"),
       TERMINOLOGIES("Terminologies"),
       TYPESOFTREES("Types of Trees"),
       TREETRAVERSAL("Tree Traversals"),
       TRAVERSALSILLUSTRATION("Traversals-Illustration"),
       BINARYTREES("Binary Trees"),
      TYPESOFBINARYTREES("Types of Binary Trees"),
      IMPLENTATIONINPYTHON("Implementation in Python"),
      BINARYTREETRAVERSALS("Binary Tree Traversals"),
      IMPLEMENTATIONOFBINARYTREES("Implementation of Binary Trees"),
      APPLICATIONSOFBINARYTREES("Applications of Binary trees"),
      BINARYSEARCHTREES("Binary Search Trees"),
      IMPLEMENTATIONOFBST("Implementation Of BST"),
      GRAPH("Graph"),
     GRAPHREPRESTATIONS("Graph Representations");
     
     
     
          public final String text;

           PageLinks(String text) {
            this.text = text;
      }

        public static List<String> getAllTexts() {
            return Arrays.stream(values())
                         .map(link -> link.text)
                         .collect(Collectors.toList());
     
        }
    
   }
    
       