package DoorDash;

/*
 * Given an array to represent the visiting of pages for different users and number of pages, find out the most frequency sequence of pages
 *  visited by users.
 * Example, looking for page number 3 in
 *  U1 Home
    U2 Home
    U1 Buy
    U2 Buy
    U1 Good
    U2 Good
    U1 Cheap
    U1 Checkout
 * the answer is Home -> Buy -> Good
 * It showed up twice for user 1 and user 2.
 * The other options like Buy-> Good-> Cheap and Good->Cheap->Checkout only showed up once     
 */
import java.util.*;
public class MostFrequencyPageSequence {
    private static String SEPARATOR = "-";
    public static List<String> getSequence(String visits[], int length) {
        Map<String, Integer> counterMap = new HashMap<>();
        Map<String, List<String>> visitedMap = new HashMap<>();
        
        int maxCount = 0;
        String maxStr = "";
        
        for (String v : visits) {
            String value[] = v.trim().split(" ");
            String user = value[0];
            String page = value[1];
            
            List<String> pages = visitedMap.getOrDefault(user, new ArrayList<>());
            pages.add(page);
            int size = pages.size();
            if (size >= length) {
                String buf = "";
                for (int i = size - length; i < size; i++) {
                    buf += pages.get(i) + SEPARATOR;
                }
                String key = buf.substring(0, buf.length() - 1);
                int count = counterMap.getOrDefault(key, 0) + 1;
                if (count > maxCount) {
                    maxCount = count;
                    maxStr = key;
                }
                counterMap.put(key, count);
            }
            visitedMap.put(user, pages);
        }
        
        
        return Arrays.asList(maxStr.split(SEPARATOR));
      
    }
    
    public static void printList(List<String> list) {
        String buf = "";
        for (String s:list) {
            buf += s +"->";
        }
        System.out.println(buf.substring(0, buf.length()-2));
    }
    public static void main(String args[]) {
        String visits1[] = { "U1 Home",
                "U2 Home",
                "U1 Buy",
                "U2 Buy",
                "U1 Good",
                "U2 Good",
                "U1 Cheap",
                "U1 Checkout"};
        printList(getSequence(visits1, 3));
    }

}
