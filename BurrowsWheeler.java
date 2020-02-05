/* *****************************************************************************
 *  Name:    Chi Qi
 *  NetID:   jinyuanq
 *  Precept: P03
 *
 *  Partner Name:    NA
 *  Partner NetID:   NA
 *  Partner Precept: NA
 *
 *  Description: This API conducts Burrows-Wheeler transform and
 *  Burrows-Wheeler inverse transform. The input message will be transformed
 *  into a form that is more amenable for compression, and the inverse
 *  transform can recover the original input string.
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class BurrowsWheeler {
    private static final int R = 256; // number of ASCII characters

    // apply Burrows-Wheeler transform,
    // reading from standard input and writing to standard output
    public static void transform() {
        String s = BinaryStdIn.readString();
        CircularSuffixArray csa = new CircularSuffixArray(s);
        int n = csa.length();
        // find the index of the original string
        for (int i = 0; i < n; i++) {
            if (csa.index(i) == 0) {
                BinaryStdOut.write(i);
                break;
            }
        }
        // print out the last row
        for (int i = 0; i < n; i++) {
            int lastChar = (csa.index(i) + n - 1) % n;
            BinaryStdOut.write(s.charAt(lastChar), 8);
        }
        BinaryStdOut.close();

    }

    // apply Burrows-Wheeler inverse transform,
    // reading from standard input and writing to standard output
    public static void inverseTransform() {
        int first = BinaryStdIn.readInt();
        String s = BinaryStdIn.readString();
        int n = s.length();
        int[] next = new int[n];
        int[] count = new int[R + 1];
        // accumulated counts of each character by sorted order
        for (int i = 0; i < n; i++) {
            count[s.charAt(i) + 1]++;
        }
        for (int i = 0; i < R; i++) {
            count[i + 1] += count[i];
        }
        // find next suffix array from count
        for (int i = 0; i < n; i++) {
            int index = count[s.charAt(i)]++;
            next[index] = i;
        }
        // write original array
        for (int k = next[first], i = 0; i < n; k = next[k], i++) {
            BinaryStdOut.write(s.charAt(k));
        }
        BinaryStdOut.close();
    }

    // if args[0] is "-", apply Burrows-Wheeler transform
    // if args[0] is "+", apply Burrows-Wheeler inverse transform
    public static void main(String[] args) {
        if (args[0].equals("-")) transform();
        else if (args[0].equals("+")) inverseTransform();
    }

}
