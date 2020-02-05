/* *****************************************************************************
 *  Name:    Chi Qi
 *  NetID:   jinyuanq
 *  Precept: P03
 *
 *  Partner Name:    NA
 *  Partner NetID:   NA
 *  Partner Precept: NA
 *
 *  Description: maintain an ordered sequence of the characters in the alphabet
 *  by repeatedly reading a character from the input message;
 *  printing the position in the sequence in which that character appears;
 *  and moving that character to the front of the sequence.
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class MoveToFront {

    private static final int R = 256; // number of ASCII characters

    // apply move-to-front encoding, reading from standard input and
    // writing to standard output
    public static void encode() {
        // Initialize an ordered sequence of 256 characters
        int[] seq = new int[R];
        for (int i = 0; i < R; i++) {
            seq[i] = i;
        }
        // output the 8-bit index in the sequence where c appears;
        // and move c to the front.
        while (!BinaryStdIn.isEmpty()) {
            int c = BinaryStdIn.readByte() & 0xFF;
            int pre = seq[0];
            int i = 0;
            for (; i < R; i++) {
                if (i != 0) {
                    int temp = seq[i];
                    seq[i] = pre;
                    pre = temp;
                }
                if (pre == c)
                    break;
            }
            seq[0] = c;
            BinaryStdOut.write((byte) i);

        }
        BinaryStdOut.close();
    }

    // apply move-to-front decoding, reading from standard input and
    // writing to standard output
    public static void decode() {
        // Initialize an ordered sequence of 256 characters
        int[] seq = new int[R];
        for (int i = 0; i < R; i++) {
            seq[i] = i;
        }
        //  write the ith character in the sequence;
        //  and move that character to the front
        while (!BinaryStdIn.isEmpty()) {
            int c = BinaryStdIn.readByte() & 0xFF;
            int front = seq[c];
            for (int i = c - 1; i >= 0; i--) {
                seq[i + 1] = seq[i];
            }
            seq[0] = front;
            BinaryStdOut.write((char) front);
        }
        BinaryStdOut.close();
    }

    // if args[0] is "-", apply move-to-front encoding
    // if args[0] is "+", apply move-to-front decoding
    public static void main(String[] args) {
        if (args[0].equals("-"))
            encode();
        if (args[0].equals("+"))
            decode();
    }

}
