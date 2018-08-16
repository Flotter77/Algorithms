public class Median {

    public static void main(String args[]){
        HeapMax leftMax = new HeapMax();
        HeapMin rightMin = new HeapMin();
        int sum = 0;
        int input1 = StdIn.readInt();
        int input2 = StdIn.readInt();
        int inputsize = 2;
        if (input1 <= input2){
           sum = input1 + input1;
           leftMax.insert(input1);
           rightMin.insert(input2);
        } else{
           sum = input1 + input2;
            leftMax.insert(input2);
            rightMin.insert(input1);

        }
        while (StdIn.hasNextLine()) {
            int input = StdIn.readInt();
          //  System.out.println("The input is " + input);
            inputsize ++;
          //  System.out.println("The input size is " + inputsize);
            if (input < rightMin.peekMin()) {
                  leftMax.insert(input);
            } else {
                 rightMin.insert(input);
            }
            if (rightMin.size() > leftMax.size()) {
                    int min = rightMin.extractMin();
                    leftMax.insert(min);
                  //  System.out.println("leftmax is "  + leftMax.peekMax()+ "; rightmin is " + rightMin.peekMin());
                  //  System.out.println("left size is "  + leftMax.size()+ "; right size is " + rightMin.size());
                } else if (leftMax.size() > rightMin.size() + 1){
                    int max = leftMax.extractMax();
                  //   System.out.println("max is" + max);
                   rightMin.insert(max);
                  //  System.out.println("leftmax is "  + leftMax.peekMax()+ "; rightmin is " + rightMin.peekMin());
                  // System.out.println("left size is "  + leftMax.size()+ "; right size is " + rightMin.size());
            }
         //   System.out.println(leftMax.mytoString());
         //   System.out.println(rightMin.mytoString());
            sum += leftMax.peekMax();
          //  System.out.println("The sum is " + sum);
        }

      System.out.println("The input size is " + inputsize);
      System.out.println("The result is " + sum % inputsize);
    }
}
