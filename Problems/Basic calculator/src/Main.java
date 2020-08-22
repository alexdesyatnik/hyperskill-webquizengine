import java.util.Map;
import java.util.function.BiFunction;

class Problem {

    public static void main(String[] args) {
        var op = args[0];
        var a = Integer.parseInt(args[1]);
        var b = Integer.parseInt(args[2]);

        Map<String, BiFunction<Integer, Integer, Integer>> ops = Map.of(
                "+", (x, y) -> x + y,
                "-", (x, y) -> x - y,
                "*", (x, y) -> x * y);

        if (!ops.containsKey(op)) {
            System.out.println("Unknown operator");
            return;
        }
        System.out.println(ops.get(op).apply(a, b));
    }
}