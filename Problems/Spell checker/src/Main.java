import java.util.*;

class Main {
    public static void main(String[] args) {
        var sc = new Scanner(System.in);

        // fill the dictionary
        var dict = new HashSet<String>();
        var d = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < d; i++) {
            dict.add(sc.nextLine().toLowerCase());
        }

        // process the lines
        var l = Integer.parseInt(sc.nextLine());
        var wordsToCheck = new HashSet<String>();
        for (int i = 0; i < l; i++) {
            wordsToCheck.addAll(Arrays.asList(sc.nextLine().toLowerCase().split("\\s+")));
        }

        // remove all known words
        wordsToCheck.removeAll(dict);

        for (var word : wordsToCheck) {
            System.out.println(word);
        }
    }
}