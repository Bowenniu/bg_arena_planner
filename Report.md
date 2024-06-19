# Report

Submitted report to be manually graded. We encourage you to review the report as you read through the provided
code as it is meant to help you understand some of the concepts. 

## Technical Questions

1. What is the difference between == and .equals in java? Provide a code example of each, where they would return different results for an object. Include the code snippet using the hash marks (```) to create a code block.
   * == : 
        In primitive data types (byte, short, char, int, long, float, double, boolean), "==" compares their values.In composite data types (objects, arrays), "==" compares their memory addresses for example, heap memory addresses.For composite data types, unless it is the same newly created object, the comparison result will be true; otherwise, the comparison result will be false. This is because each time a new object is created, new heap memory space is allocated.
   * .equals() :
        .equals() is a method in the Object class that compares the contents of two objects (reference types) to determine if they are equal.

   ```java
   // your code here
   public class Main {
    public static void main(String[]args) {
        String a1 = new String("12345");
        String a2 = new String("12345");
        System.out.println(a1 == a2);
        System.out.println(a1.equals(a2));
    }
   }
   ```




2. Logical sorting can be difficult when talking about case. For example, should "apple" come before "Banana" or after? How would you sort a list of strings in a case-insensitive manner? 
    * In Java, if you want to sort a list of strings in a case-insensitive manner, you can use the Collections.sort() method and pass in a custom Comparator that uses String.CASE_INSENSITIVE_ORDER for comparison.
```java
import java.util.ArrayList;
import java.util.List;
import java.util Collections;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("apple");
        list.add("Banana");
        list.add("Ant");
        list.add("Elephant");

        Collections.sort(list, String.CASE_INSENSITIVE_ORDER);

        for (int i = 0; i < list.size(); i++) {
        String s = list.get(i);
        System.out.println(s);
        }
    }
}
```
* The code shall print 
                        
                        Ant
                        apple
                        Banana
                        Elephant


3. In our version of the solution, we had the following code (snippet)
    ```java
    public static Operations getOperatorFromStr(String str) {
        if (str.contains(">=")) {
            return Operations.GREATER_THAN_EQUALS;
        } else if (str.contains("<=")) {
            return Operations.LESS_THAN_EQUALS;
        } else if (str.contains(">")) {
            return Operations.GREATER_THAN;
        } else if (str.contains("<")) {
            return Operations.LESS_THAN;
        } else if (str.contains("=="))...
    ```
    Why would the order in which we checked matter (if it does matter)? Provide examples either way proving your point. 

* The order of comparison in the getOperatorFromStr method is indeed crucial because it determines which comparison operator will be prioritized and returned when the string str contains multiple comparison operators.
  
* Consider the following scenarios:
    
    ------- If you check for == first, followed by <= or >=, then when the string is "x<=y", == will match first, incorrectly identifying it as equality (==) instead of less than or equal to (<=). This can lead to incorrect recognition.

    ------- If you check for <= or >= first, followed by < or >, then when the string is "x<y", <= or >= won't match, and < will correctly match. This is the correct order.

    ------- If you check for < or > first, followed by <= or >=, then when the string is "x<=y", < will match first, but what we actually want to match is <=.


4. What is the difference between a List and a Set in Java? When would you use one over the other?   
* In Java, List and Set are two commonly used collection interfaces that have significant differences in terms of element order, element uniqueness, performance, and usage scenarios.
    
    ------- Element Order:
    * List is an ordered collection that preserves the insertion order of elements. Elements in a List can be accessed by their index (position).
    * Set is an unordered collection that does not preserve the insertion order of elements. Elements in a Set are unique and cannot be repeated.

    ------- Element Uniqueness:
    * List allows duplicate elements.
    * Set does not allow duplicate elements. An element can only be in the set once. If an attempt is made to add an element to a Set that already exists, the operation will not succeed (depending on the Set implementation, it may throw an exception or simply ignore the operation)

    ------- Performance:
    * List implementations like ArrayList and LinkedList typically provide fast access based on index, but may be slower when checking for the existence of elements (especially if not using an ordered list and requiring linear search).
    * The implementations of Set, such as HashSet and TreeSet, typically perform faster checks for the existence of elements (especially HashSet, which provides close to constant-time lookup), but sacrifice the ordering of elements.

    ------- Usage Scenarios:
    * When it's necessary to maintain the order of elements upon insertion or to access elements by index, List should be used. For example, when representing a list of orders for a user (where the order of orders matters), List is a suitable choice.
    * When only a unique collection of elements is needed without concern for the order of elements, Set should be used. For example, when storing a unique set of tags for a user, Set is a better choice.



5. In [GamesLoader.java](src/main/java/student/GamesLoader.java), we use a Map to help figure out the columns. What is a map? Why would we use a Map here? 
    * The map interface represents a collection of key-value pairs. Keys are unique, while values can be duplicated. That is why we use the key to add and access the key-value pairs in a map.The commonly used implementation of the Map interface are HashMap and TreeMaps. Each element in a Map is an association of a key and a value. Keys are used to retrieve their corresponding values. A Map does not allow duplicate keys, but can have duplicate values as long as they correspond to different keys.

    * The main function of GamesLoader.java is to load game data stored in a CSV file and convert it into a collection of BoardGame objects.In GamesLoader.java, using a Map to store GameData enum values and their corresponding column indices primarily serves the following purposes:
    
    ------ Flexibility: The structure of CSV files may vary, such as different column orders or the addition of new columns. Using a Map allows us to avoid hardcoding column orders and positions.

    ------ Readability: Using a Map enhances code clarity and understanding. Compared to using multiple conditional statements or arrays to hardcode column positions and meanings, using a Map makes the code more intuitive and reduces the likelihood of errors.

    ------ Ease of Lookup: Once we have the Map, we can quickly find the corresponding column index using the GameData enum value, which represents the type or meaning of each column.

    ------ Error Handling: By checking if a GameData enum value exists in the Map, we can easily detect if the CSV file header is missing required columns or contains unexpected extra columns.


6. [GameData.java](src/main/java/student/GameData.java) is actually an `enum` with special properties we added to help with column name mappings. What is an `enum` in Java? Why would we use it for this application?







7. Rewrite the following as an if else statement inside the empty code block.
    ```java
    switch (ct) {
                case CMD_QUESTION: // same as help
                case CMD_HELP:
                    processHelp();
                    break;
                case INVALID:
                default:
                    CONSOLE.printf("%s%n", ConsoleText.INVALID);
            }
    ``` 

    ```java
    // your code here, don't forget the class name that is dropped in the switch block..
    if (ct == CMS_QUESTION || ct == CMD_HELP) {
        proecessHelp();
    } else if {
        (ct == INVALID);
        CONSOLE.printf("%s%n", ConsoleText.INVALID);
    } else {
        CONSOLE.printf("%s%n", ConsoleText.INVALID)
    }
    ```

## Deeper Thinking

ConsoleApp.java uses a .properties file that contains all the strings
that are displayed to the client. This is a common pattern in software development
as it can help localize the application for different languages. You can see this
talked about here on [Java Localization – Formatting Messages](https://www.baeldung.com/java-localization-messages-formatting).

Take time to look through the console.properties file, and change some of the messages to
another language (probably the welcome message is easier). It could even be a made up language and for this - and only this - alright to use a translator. See how the main program changes, but there are still limitations in 
the current layout. 

Post a copy of the run with the updated languages below this. Use three back ticks (```) to create a code block. 

```text
// your consoles output here
```

Now, thinking about localization - we have the question of why does it matter? The obvious
one is more about market share, but there may be other reasons.  I encourage
you to take time researching localization and the importance of having programs
flexible enough to be localized to different languages and cultures. Maybe pull up data on the
various spoken languages around the world? What about areas with internet access - do they match? Just some ideas to get you started. Another question you are welcome to talk about - what are the dangers of trying to localize your program and doing it wrong? Can you find any examples of that? Business marketing classes love to point out an example of a car name in Mexico that meant something very different in Spanish than it did in English - however [Snopes has shown that is a false tale](https://www.snopes.com/fact-check/chevrolet-nova-name-spanish/).  As a developer, what are some things you can do to reduce 'hick ups' when expanding your program to other languages?


As a reminder, deeper thinking questions are meant to require some research and to be answered in a paragraph for with references. The goal is to open up some of the discussion topics in CS, so you are better informed going into industry. 