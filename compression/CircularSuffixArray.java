/* *****************************************************************************
 *  Name:    Chi Qi
 *  NetID:   jinyuanq
 *  Precept: P03
 *
 *  Partner Name:    NA
 *  Partner NetID:   NA
 *  Partner Precept: NA
 *
 *  Description:this data type is the abstraction of a sorted array of
 *  the n circular suffixes of a string of length n.
 *  We define index[i] to be the index of the original suffix that
 *  appears ith in the sorted array.
 *  This API provides the client access to the index[] values.
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class CircularSuffixArray {
    private final int length; // the length of the input string
    private final int[] suffix;  // the index of the suffix array

    // circular suffix array of s
    public CircularSuffixArray(String s) {
        if (s == null)
            throw new IllegalArgumentException("The input is null.");
        length = s.length();
        suffix = new int[length];


        // sort the array of the nested class CircularSuffix;
        CircularSuffix[] cs = new CircularSuffix[length];
        for (int i = 0; i < length; i++) {
            cs[i] = new CircularSuffix(i, s);
        }
        Arrays.sort(cs);
        // store the sorted array in the suffix
        for (int i = 0; i < length; i++) {
            suffix[i] = cs[i].id;
        }

    }

    // nested class to compare circular suffix
    private class CircularSuffix implements Comparable<CircularSuffix> {

        private final int id; // the id of Original Suffixes
        private final String s; // the original string

        // constructor
        private CircularSuffix(int id, String s) {
            this.id = id;
            this.s = s;
        }

        // compare circular suffix by the circular strings
        public int compareTo(CircularSuffix that) {
            for (int i = 0; i < length; i++) {
                char cA = s.charAt((this.id + i) % length);
                char cB = s.charAt((that.id + i) % length);
                if (cA < cB)
                    return -1;
                else if (cA > cB)
                    return 1;
            }
            return 0;
        }
    }

    // length of s
    public int length() {
        return length;

    }

    // returns index of ith sorted suffix
    public int index(int i) {
        if (i < 0 || i >= length)
            throw new IllegalArgumentException("Out of bounds.");
        return suffix[i];
    }

    // unit testing (required)
    public static void main(String[] args) {
        String test = "ABRACADABRA!";
        CircularSuffixArray csa = new CircularSuffixArray(test);
        StdOut.println("The length is " + csa.length());
        StdOut.println("The index of 11th in the sorted array is " + csa.index(11));
        // additional tet
        CircularSuffixArray csa2 = new CircularSuffixArray(args[0]);
        StdOut.println("The length is " + csa2.length());
        StdOut.println("The index of 1st in the sorted array is " + csa2.index(1));
    }

}
