/* *****************************************************************************
 *
 *  Description:  Prints 'Hello, World' to the terminal window.
 *                By tradition, this is everyone's first program.
 *                Prof. Brian Kernighan initiated this tradition in 1974.
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.Huffman;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class BWtest {
    public static void main(String[] args) {
        // compression
        if (args[0].equals("-")) {
            Stopwatch stopwatch = new Stopwatch();
            BurrowsWheeler.transform();
            MoveToFront.encode();
            Huffman.compress();
            double time = stopwatch.elapsedTime();
            StdOut.println("The compression time is " + time);
        }
        else if (args[0].equals("+")) {
            Stopwatch stopwatch = new Stopwatch();
            Huffman.expand();
            MoveToFront.decode();
            BurrowsWheeler.inverseTransform();
            double time = stopwatch.elapsedTime();
            StdOut.println("The expand time is " + time);
        }
    }
}
