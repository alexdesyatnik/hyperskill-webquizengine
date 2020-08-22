import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Main {

    static Map<Character, Integer> countCharacters(String s) {
        var m = new HashMap<Character, Integer>();
        for (var c : s.toCharArray()) {
            m.compute(c, (k, v) -> v == null ? 1 : v + 1);
        }
        return m;
    }

    public static void main(String[] args) {
        // put your code here
        var sc = new Scanner(System.in);
        var word1 = sc.nextLine().toLowerCase();
        var word2 = sc.nextLine().toLowerCase();

        var count1 = countCharacters(word1);
        var count2 = countCharacters(word2);

        // subtract count2 from count1
        for (var c : count2.keySet()) {
            count1.compute(c, (k, v) -> v == null ? -count2.get(c) : v - count2.get(c));
        }

        // sum all values in count1 (abs)
        int removeCount = 0;
        for (var n : count1.values()) {
            removeCount += Math.abs(n);
        }

        System.out.println(removeCount);
    }
}