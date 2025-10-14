package utilities;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LinkedListPageData {
    public enum PageLink {
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
        DELETION("Deletion");

        public final String text;

        PageLink(String text) {
            this.text = text;
        }

        public static List<String> getAllTexts() {
            return Arrays.stream(values())
                         .map(link -> link.text)
                         .collect(Collectors.toList());
        }
    }

    public enum SubPageLink {
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
        DELETION("Deletion");

        public final String text;

        SubPageLink(String text) {
            this.text = text;
        }

        public static List<String> getAllTexts() {
            return Arrays.stream(values())
                         .map(link -> link.text)
                         .collect(Collectors.toList());
        }
    }

    /*public enum PageHeader {
        LINKED_LIST("Linked List"),
        TOPICS_COVERED("Topics Covered");

        public final String text;

        PageHeader(String text) {
            this.text = text;
        }

        public static List<String> getAllTexts() {
            return Arrays.stream(values())
                         .map(header -> header.text)
                         .collect(Collectors.toList());
        }
    }*/
}
