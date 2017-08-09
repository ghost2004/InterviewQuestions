package Linkedin;
/*
 * Given dishes and its ingredients. Group dishes that has common ingredients
 * 
 * Input:
"Pasta" -> ["Tomato Sauce", "Onions", "Garlic"]
"Chicken Curry" --> ["Chicken", "Curry Sauce"]
"Fried Rice" --> ["Rice", "Onions", "Nuts"]
"Salad" --> ["Spinach", "Nuts"]
"Sandwich" --> ["Cheese", "Bread"]
"Quesadilla" --> ["Chicken", "Cheese"]

Output: ("Pasta", "Fried Rice") ("Fried Rice, "Salad") , ("Chicken Curry", "Quesadilla") ("Sandwich", "Quesadilla")

Follow up: What is the time and space complexity?
 */
import java.util.*;
public class DishIngredients {
    
    public List<List<String>> dishesWithCommonIngredients(Map<String, String[]> dishIngredientsMap) {
        List<List<String>> res = new ArrayList<List<String>>();
        Map<String, Set<String>> invertedMap = new HashMap<>();
        
        for (Map.Entry<String, String[]> entry:dishIngredientsMap.entrySet()) {
            String dish = entry.getKey();
            String ings[] = entry.getValue();
            for (String ing:ings) {
                 Set<String> dishes = invertedMap.get(ing);
                 if (dishes == null) {
                     dishes = new HashSet<>();
                     dishes.add(dish);
                     invertedMap.put(ing, dishes);
                 } else {
                     dishes.add(dish);
                 }
            }
        }
        for (Set<String> dishes:invertedMap.values()) {
            if (dishes.size() > 1) {
                List<String> d = new ArrayList<>();
                d.addAll(dishes);
                res.add(d);
            }
        }
        
        return res;
    }
    public void printList(List<List<String>> res) {
        for (List<String> list:res) {
            System.out.print("(");
            for (int i = 0; i < list.size(); i++) {
                System.out.print(list.get(i));
                if (i < list.size()-1) 
                    System.out.print(",");
            }
            System.out.print(") ");
        }
        System.out.println("\n-------");
    }
    public static void main(String args[]) {
        
        DishIngredients di = new DishIngredients();
        String Pasta[] = {"Tomato Sauce", "Onions", "Garlic"};
        String ChickenCurry[] = {"Chicken", "Curry Sauce"};
        String FriedRice[] = {"Rice", "Onions", "Nuts"};
        String Salad[] = {"Spinach", "Nuts"};
        String Sandwich[] = {"Cheese", "Bread"};
        String Quesadilla[] = {"Chicken", "Cheese"};
        
        Map<String, String[]> map = new HashMap<>();
        
        map.put("Pasta", Pasta);
        map.put("Chicken Curry", ChickenCurry);
        map.put("Fried Rice", FriedRice);
        map.put("Salad", Salad);
        map.put("Sandwich", Sandwich);
        map.put("Quesadilla", Quesadilla);
        
        di.printList(di.dishesWithCommonIngredients(map));
        
    }

}
