import java.util.*;

class Main {
    
    static Map<Character, Integer> countCharacters(String s) {
        var m = new HashMap<Character, Integer>();
        for (var c : s.toCharArray()) {
            m.compute(c, (k, v) -> (v == null) ? 1 : v + 1);
        }
        return m;
    }
    
    public static void main(String[] args) {
        var sc = new Scanner(System.in);
        var word1 = sc.nextLine().toLowerCase();
        var word2 = sc.nextLine().toLowerCase();
        System.out.println(countCharacters(word1).equals(countCharacters(word2)) ? "yes" : "no");        
    }
}
