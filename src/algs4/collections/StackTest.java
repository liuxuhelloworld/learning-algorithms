package algs4.collections;

import edu.princeton.cs.algs4.*;

public class StackTest {
    public static void main(String[] args) {
        Stack<String> ops = new Stack<>();
        Stack<Double> vals = new Stack<>();

        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            switch (s) {
                case "(":
                    break;
                case "+":
                case "-":
                case "*":
                case "/":
                case "sqrt":
                    ops.push(s);
                    break;
                case ")":
                    String op = ops.pop();
                    Double val = vals.pop();
                    switch (op) {
                        case "+":
                            val = vals.pop() + val;
                            break;
                        case "-":
                            val = vals.pop() - val;
                            break;
                        case "*":
                            val = vals.pop() * val;
                            break;
                        case "/":
                            val = vals.pop() / val;
                            break;
                        case "sqrt":
                            val = Math.sqrt(val);
                            break;
                    }
                    vals.push(val);
                    break;
                default:
                    vals.push(Double.parseDouble(s));
                    break;
            }
        }

        StdOut.println(vals.pop());
    }
}
