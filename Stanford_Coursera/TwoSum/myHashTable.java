import java.util.ArrayList;
import java.util.HashSet;


public class myHashTable{

        final int MAX_SLOT =  524287;
        final Bucket[] buckets = new Bucket[MAX_SLOT];
        ArrayList<Long> valSet = new ArrayList<>();
        int size = 0;


        int size(){
            return size;
        }

        int idx(Long value){
            int index;
            int key = (int) (value / 10000);
            if(key < 0)
                key *= (-1);
            index = key % MAX_SLOT;
            return index;
        }

        public void put(Long value) {
            valSet.add(value);
            int i = idx(value);
            if (buckets[i] == null)
                buckets[i] = new Bucket();
            ListNode prev = find(buckets[i], value);
            if (prev.next == null){
                prev.next = new ListNode(i, value);
                size++;}
        }

        public ArrayList<Long> keySet(){
            return valSet;

        }

        public boolean contains(Long val) {
            int i = idx(val);
            if (buckets[i] == null) return false;
            else {
                ListNode prev = find(buckets[i], val);
                if (prev.next == null) return false;
                else return true;
            }
        }

        public boolean contains(int index) {
            if (buckets[index] == null) return false;
            else return true;
        }


        public void remove(Long val) {
            int i = idx(val);
            if (buckets[i] == null) return;
            ListNode prev = find(buckets[i], val);
            if (prev.next == null) return;
            prev.next = prev.next.next;
            valSet.remove(val);
            size--;
        }

        ListNode find(Bucket bucket, Long val) {
            ListNode node = bucket.head, prev = null;
            while (node != null && node.val != val) {
                prev = node;
                node = node.next;
            }
            return prev;
        }
    }

    class Bucket {
        final ListNode head = new ListNode(-1L);
    }

    class ListNode {
        Long val;
        ListNode next;
        ListNode(Long val) {
            this.val = val;
        }
    }



