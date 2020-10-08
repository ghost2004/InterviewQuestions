package DoorDash;
/*
 * With given number of orders, returns all combination of pick/delivery sequence
 * The pick up of the order should always happen before delivery
 */
import java.util.*;
public class CombinationPickupDelivery {
    
    private enum OrderStatus {
        UNPROCESSED,
        PICKED,
        DELIVERED
    }
    
    public static void backtrack(List<String> list, String buffer, OrderStatus[] orderMap, int delivered, int n) {
        if (delivered == n) {
            list.add(buffer.substring(0, buffer.length()-2));
            return;
        }

        for (int i = 1; i <= n; i++) {
            if(orderMap[i] == OrderStatus.UNPROCESSED) {
                orderMap[i] = OrderStatus.PICKED;
                backtrack(list, buffer + "P" + String.valueOf(i) + ", ", orderMap, delivered, n);
                orderMap[i] = OrderStatus.UNPROCESSED;
            } else if (orderMap[i] == OrderStatus.PICKED) {
                orderMap[i] = OrderStatus.DELIVERED;
                backtrack(list, buffer + "D" + String.valueOf(i) + ", ", orderMap, delivered + 1, n);
                orderMap[i] = OrderStatus.PICKED;
            }
        }
    }
    
    public static List<String> getCombination(int n) {
        List<String> results = new ArrayList<>();
        OrderStatus[] orderMap = new OrderStatus[n+1];
        for (int i = 1; i <= n; i++)
            orderMap[i] = OrderStatus.UNPROCESSED;
        backtrack(results, "", orderMap, 0, n);
        
        return results;
    }
    
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
    
    public static int countOrders(int n) {
        if(n == 1)
            return 1;
        long mod = (long)1e9 + 7;
        long result = 1;
        
        for (int i = 1; i <= n; i++) {
            int combination = i * (2 * i - 1);
            result = result * combination % mod;
        }
        return (int)result;
        
    }
    
    public static void main(String args[]) {
        int n = 4;
        List<String> results = getCombination(n);
        for (String path : results) {
            System.out.println(path + " ==> " + isValid(path));
            
        }
        System.out.println("total " + results.size() + " expected " + countOrders(n));
    }

}
