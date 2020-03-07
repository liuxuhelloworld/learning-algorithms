package solutions.clrs;

import java.util.Arrays;

public class Ex2_1_4 {
    public static byte[] add(byte[] a, byte[] b) {
        if (a.length != b.length) {
            return null;
        }

        byte[] c = new byte[a.length + 1];
        byte carry = 0;
        for (int i = a.length - 1; i >= 0; i--) {
            c[i + 1] = (byte)((a[i] + b[i] + carry) % 2);
            carry = (byte)((a[i] + b[i] + carry) / 2);
        }
        c[0] = carry;

        return c;
    }

    public static void main(String[] args) {
        byte[] a = {1, 0, 1, 0};
        byte[] b = {1, 1, 1, 1};
        System.out.println(Arrays.toString(add(a, b)));
    }
}
