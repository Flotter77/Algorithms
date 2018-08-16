import java.util.ArrayList;

public class HeapMin {
    private ArrayList<Integer> arr = new ArrayList<>();
    private int size;

    int size(){
        return size;
    }

    int peekMin(){
        return arr.get(0);
    }

    void insert(int input) {
        arr.add(input);
        size++;
        int index = arr.lastIndexOf(input);
        int parentI = (index - 1 ) / 2;
        while (parentI >= 0 && arr.get(index) < arr.get(parentI)) {
            int temp = arr.get(index);
            arr.set(index, arr.get(parentI));
            arr.set(parentI, temp);
            index = parentI;
            parentI = (index - 1 ) / 2;
        }
    }

    int extractMin(){
        int min = arr.get(0);
        arr.set(0, arr.get(size-1));
        arr.remove(size-1);
        size--;
        int parentI = 0;
        int kid1 = parentI * 2 + 1;
        int kid2 = kid1 + 1;
        while (kid2 < size){
            int parentValue = arr.get(parentI);
            int kid1Value = arr.get(kid1);
            int kid2Value = arr.get(kid2);
            if  (parentValue <= Math.min(kid1Value, kid2Value)){
                break;
            } else {
                if (kid1Value <= kid2Value) {
                    arr.set(kid1, parentValue);
                    arr.set(parentI, kid1Value);
                    parentI = kid1;
                } else {
                    arr.set(kid2, parentValue);
                    arr.set(parentI, kid2Value);
                    parentI = kid2;
                }
            }
            kid1 = parentI * 2 + 1;
            kid2 = kid1 + 1;
        }

        if (kid1 == size -1 && arr.get(parentI) > arr.get(kid1)) {
            int kid1Value = arr.get(kid1);
            arr.set(kid1,arr.get(parentI));
            arr.set(parentI, kid1Value);
        }
        return min;
    }

    String mytoString(){
        String a = "";
        for (Integer x: arr){
            a = a + " " + x;
        }
        return a;
    }

}



