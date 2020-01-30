/* *****************************************************************************
 *  Name:    Chi Qi
 *  NetID:   jinyuanq
 *  Precept: P03
 *
 *  Partner Name:   NA
 *  Partner NetID:   NA
 *  Partner Precept: NA
 *
 *  Description: a data type that resizes a W-by-H image using
 *  the seam-carving technique.
 **************************************************************************** */

import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.Picture;
import edu.princeton.cs.algs4.StdOut;

import java.awt.Color;

public class SeamCarver {

    private Picture pic; // picture for resizing

    // create a seam carver object based on the given picture
    public SeamCarver(Picture picture) {
        if (picture == null)
            throw new IllegalArgumentException();
        pic = new Picture(picture);

    }

    // current picture
    public Picture picture() {
        Picture res = new Picture(pic);
        return res;

    }

    // width of current picture
    public int width() {
        return pic.width();

    }

    // height of current picture
    public int height() {
        return pic.height();
    }

    // energy of pixel at column x and row y
    public double energy(int x, int y) {
        if (x < 0 || x > (pic.width() - 1) || y < 0 || y > (pic.height() - 1))
            throw new IllegalArgumentException("Out of range.");
        int left = x - 1;
        int right = x + 1;
        int above = y - 1;
        int below = y + 1;
        // handling pixels on borders by defining
        // the leftmost and rightmost columns as adjacent
        // and the topmost and bottommost rows as adjacent
        if (x == 0)
            left = pic.width() - 1;
        if (x == pic.width() - 1)
            right = 0;
        if (y == 0)
            above = pic.height() - 1;
        if (y == pic.height() - 1)
            below = 0;
        Color lcolor = pic.get(left, y);
        Color rcolor = pic.get(right, y);
        Color acolor = pic.get(x, above);
        Color bcolor = pic.get(x, below);

        //  Calculate the central differences for three colors and two axes
        double xR = lcolor.getRed() - rcolor.getRed();
        double xG = lcolor.getGreen() - rcolor.getGreen();
        double xB = lcolor.getBlue() - rcolor.getBlue();
        double yR = acolor.getRed() - bcolor.getRed();
        double yG = acolor.getGreen() - bcolor.getGreen();
        double yB = acolor.getBlue() - bcolor.getBlue();

        // return the dual-gradient energy
        return Math.sqrt(xR * xR + xG * xG + xB * xB +
                                 yR * yR + yG * yG + yB * yB);

    }

    // helper function to transpose the picture
    private void transpose() {
        Picture temp = new Picture(pic.height(), pic.width());
        for (int newcol = 0; newcol < pic.height(); newcol++) {
            for (int newrow = 0; newrow < pic.width(); newrow++)
                temp.set(newcol, newrow, pic.get(newrow, newcol));
        }
        pic = temp;

    }

    // sequence of indices for horizontal seam
    public int[] findHorizontalSeam() {
        transpose();
        int[] seam = findVerticalSeam();
        transpose();
        return seam;
    }

    // sequence of indices for vertical seam
    public int[] findVerticalSeam() {
        int w = pic.width();
        int h = pic.height();
        int[] edgeTo = new int[w * h];
        double[] distTo = new double[w * h];
        double[][] energyTo = new double[w][h];
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                energyTo[i][j] = energy(i, j);
            }
        }

        IndexMinPQ<Double> pq = new IndexMinPQ<Double>(w * h);
        // intialize the first row with energy/edge weight from a virtual top
        // vertex and all other cells with positive infintity value
        for (int i = 0; i < w; i++) {
            distTo[i] = energyTo[i][0];
            pq.insert(i, distTo[i]);
        }
        for (int i = w; i < w * h; i++)
            distTo[i] = Double.POSITIVE_INFINITY;

        // Dijkstra's algorithm to find the shortest distance
        while (pq.size() != 0) {
            int cur = pq.minIndex();
            int row = cur / w;
            int col = cur % w;
            // reach the last row
            if (row == h - 1)
                break;
            pq.delMin();
            // examine the connected edges
            for (int nextTo = col - 1; nextTo <= col + 1; nextTo++) {
                if (nextTo >= 0 && nextTo < w) {
                    int next = to1D(nextTo, row + 1, w);
                    double eNextTo = energyTo[nextTo][row + 1];
                    if (distTo[cur] + eNextTo <
                            distTo[next]) {
                        distTo[next] =
                                distTo[cur] + eNextTo;
                        edgeTo[next] = cur;
                        pq.insert(next, distTo[next]);
                    }
                }
            }
        }
        // trace back the shortest distance for seam[]
        int[] vseam = new int[h];
        int last = pq.delMin();
        vseam[h - 1] = last % w;
        for (int i = h - 2; i >= 0; i--) {
            int prev = edgeTo[last];
            vseam[i] = prev % w;
            last = prev;
        }
        return vseam;
    }

    // helper function to get the 1D index
    private int to1D(int col, int row, int w) {
        return w * (row) + col;

    }

    // remove horizontal seam from current picture
    public void removeHorizontalSeam(int[] seam) {
        if (!validateSeam(seam, pic.height(), pic.width()))
            throw new IllegalArgumentException();
        transpose();
        removeVerticalSeam(seam);
        transpose();
    }

    // remove vertical seam from current picture
    public void removeVerticalSeam(int[] seam) {
        if (!validateSeam(seam, pic.width(), pic.height()))
            throw new IllegalArgumentException();
        Picture newpic = new Picture(pic.width() - 1, pic.height());
        // get the pixel from orginal picture to the new picture
        for (int row = 0; row < pic.height(); row++) {
            for (int col = 0; col < seam[row]; col++) {
                newpic.set(col, row, pic.get(col, row));
            }
            for (int col = seam[row]; col < pic.width() - 1; col++) {
                newpic.setRGB(col, row, pic.getRGB(col + 1, row));
            }
        }
        pic = newpic;
    }

    // helper function to validate the seam[] input
    private boolean validateSeam(int[] seam, int w, int h) {
        if (seam == null || seam.length != h)
            return false;
        else {
            int t = seam.length;
            for (int i = 0; i < t - 1; i++) {
                if (seam[i] < 0 || seam[i] >= w ||
                        Math.abs(seam[i + 1] - seam[i]) > 1)
                    return false;
            }
            if (seam[t - 1] < 0 || seam[t - 1] >= w)
                return false;
        }
        return true;

    }

    //  unit testing (required)
    public static void main(String[] args) {
        Picture test = new Picture(args[0]);
        SeamCarver testsc = new SeamCarver(test);
        // Testing width(), height() and energy()
        StdOut.println("The width of the pic is " + testsc.width());
        StdOut.println("The height of the pic is " + testsc.height());
        StdOut.println("The energy of upper left corner is " +
                               testsc.energy(0, 0));
        // Testing findVerticalSeam() and findHorizontalSeam()
        int[] vseam = testsc.findVerticalSeam();
        int[] hseam = testsc.findHorizontalSeam();

        StdOut.println("The vertical seam is: ");
        for (int i = 0; i < vseam.length; i++) {
            StdOut.print(vseam[i] + "; ");
        }
        StdOut.println();
        StdOut.println("The horizontal seam is: ");
        for (int i = 0; i < hseam.length; i++) {
            StdOut.print(hseam[i] + "; ");
        }
        // remove the vertical seam
        testsc.removeVerticalSeam(vseam);
        // remove the horizontal seam
        hseam = testsc.findHorizontalSeam();
        testsc.removeHorizontalSeam(hseam);
        // Testing picture
        Picture testnew = testsc.picture();
        StdOut.println();
        StdOut.println("The width of the new pic is " + testnew.width());
        StdOut.println("The height of the new pic is " + testnew.height());

        
    }


}
