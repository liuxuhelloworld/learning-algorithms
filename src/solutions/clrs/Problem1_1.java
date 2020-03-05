package solutions.clrs;

import java.util.function.DoubleFunction;

public class Problem1_1 {
    private static final double EPS = 0.0001;

    private static final double COFF = 100*365*24*60.0*60;

    private static double func1(double x) {
        return COFF*Math.pow(10, 6) - x*Math.log(x)/Math.log(2.0);
    }

    private static double func2(double x) {
        return COFF*Math.pow(10, 6) - Math.pow(2.0, x);
    }

    private static double func3(double x) {
        double val = 1.0;
        for (double v = 1.0; v <= x; v++) {
            val *= v;
        }
        return COFF*Math.pow(10, 6) - val;
    }

    private static long solve(DoubleFunction<Double> func, double left, double right) {
        double mid;

        while (true) {
            mid = (left + right) / 2;
            double val = func.apply(mid);
            if (Math.abs(val) <= EPS || Math.abs(left-right) <= EPS) {
                break;
            }
            if (val > 0) {
                left = mid;
            } else if (val < 0) {
                right = mid;
            }
        }

        long i = (long) mid;
        while (func.apply((double)i) < 0) {
            i--;
        }
        return i;
    }

    public static void main(String[] args) {
        long s1 = solve(Problem1_1::func1, 1, COFF*Math.pow(10, 6));
        long s2 = solve(Problem1_1::func2, 1, s1);
        long s3 = solve(Problem1_1::func3, 1, s2);

        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
    }
}
