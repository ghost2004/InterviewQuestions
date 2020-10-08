package DoorDash;

/*
 * With given order of pickup and delivery, check if they are valid or not
 * The pick up of the order should always happen before delivery
# P1, D1, P2, D2 ==> valid,
# D1, P1 ==> invalid
# P1, P2, P3, D3, D2, D1 ==> valid,
# P1, P2, D2 ===> invalid
# P2, D1 ==> invalid
 */
import java.util.*;

public class ValidPickupDelivery {
    public static boolean isValid(String sequence) {
        String array[] = sequence.split(",");
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < array.length; i++) {
            String s = array[i].trim();
            Integer id = Integer.valueOf(s.substring(1));
            if (s.charAt(0) == 'P') {
                set.add(id);
            } else {
                if (!set.remove(id)) return false;
            }
        }
        return set.isEmpty();
    }
    
    public static void main(String args[]) {
        String testCases[] = { "P1, D1, P2, D2 ", "D1, P1", "P1, P2, P3, D3, D2, D1", "P1, P2, D2", "P2, D1 " };
        for (String s: testCases)
            System.out.println(s + " ==> " + isValid(s));
    }
}
