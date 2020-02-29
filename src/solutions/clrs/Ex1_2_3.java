package solutions.clrs;

public class Ex1_2_3 {
    private static final double EPS = 0.0001;

    private static double f(double x) {
        return 100*x*x - Math.pow(2, x);
    }

    public static void main(String[] args) {
        double left, right, mid;

        left = 1; right = 20;
        while (true) {
            mid = (left + right) / 2;
            if (Math.abs( f(mid)) <= EPS) {
                break;
            }
            if (f(mid) > 0) {
                left = mid;
            } else if (f(mid) < 0) {
                right = mid;
            }
        }
        System.out.println(mid);
    }
}
