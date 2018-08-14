import java.util.*;

/* Inspired by @HuangLiPang Two-Sum_Algorithm(hash table chaining).c */


public class TwoSum {

    public static int idx(Long value){
        final int MAX_SLOT =  524287;
        int index;
        int key = (int) (value / 10000);
        if(key < 0)
            key *= (-1);
        index = key % MAX_SLOT;
        return index;
    }


    public static void main(String args[]) {
        /*Separate input of long variable into positive and negative hashmaps*/
        myHashTable phashtable = new myHashTable();
        myHashTable nhashtable = new myHashTable();
        while (StdIn.hasNextLine()) {
            Long input = StdIn.readLong();
            if (input < 0) {
                nhashtable.put(input);
              //  System.out.println("index: " + idx(input) + "nhead.val: " + nhashmap.buckets[idx(input)].head.next.val);
            } else {
                phashtable.put(input);
              //  System.out.println("index: " + idx(input) + "phead.val: " + phashmap.buckets[idx(input)].head.next.val);
            }
        }
        System.out.println("Negative numbers size " + nhashtable.size() + "; " + "Positive numbers size " + phashtable.size());

        /*Store the result in a hashset*/
        HashSet<Long> result = new HashSet<>();


        for (Long val : phashtable.keySet()) {
            Long tempValue = (val - (val % 10000)) * (-1);
        //    System.out.println("Temp value: " + tempValue);
            /* so only iterate 3 times */
            for (int i = -10000; i <= 10000; i += 10000) {
                int index = idx(tempValue + i);
                if (tempValue < 0) {
                    if (nhashtable.contains(index)) {
                      //  System.out.println("nIndex:" + index);
                        ListNode nodePtr = nhashtable.buckets[index].head, prev = null;
                        while (nodePtr.next != null) {
                            prev = nodePtr;
                            nodePtr = nodePtr.next;
                        //    System.out.println("nnode value"+ nodePtr.val);
                            Long matchValue = nodePtr.val + val;
                            if (-10000 <= matchValue && matchValue <= 10000 && nodePtr.val != val) {
                                result.add(matchValue);
                            //    System.out.println("nmatchvalue: "+ matchValue);
                            }
                        }
                    }
                } else {
                    if (phashtable.contains(index)) {
                      //  System.out.println("pIndex:" + index);
                        ListNode nodePtr = phashtable.buckets[index].head, prev = null;
                        while (nodePtr.next != null) {
                            prev = nodePtr;
                            nodePtr = nodePtr.next;
                          //  System.out.println("pnode value"+ nodePtr.val);
                            Long matchValue = nodePtr.val + val;
                            if (-10000 <= matchValue && matchValue <= 10000 && nodePtr.val != val) {
                                result.add(matchValue);
                              //  System.out.println("pmatchvalue: "+ matchValue);
                            }
                        }
                    }
                }
            }
        }
        int x = result.size();
        System.out.println(x);
    }
}
