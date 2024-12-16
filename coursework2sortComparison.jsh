import java.io.*;
import java.nio.file.*;
import java.util.*;




int getCardNumber(String numStr) {
    switch (numStr) {
        case "A": return 1;
        case "J": return 11;
        case "Q": return 12;
        case "K": return 13;
        default: return Integer.parseInt(numStr);
    }
}

int getSuitPriority(char suit) {
    switch (suit) {
        case 'H': return 1;
        case 'C': return 2;
        case 'D': return 3;
        case 'S': return 4;
        default: return 0;
    }
}

ArrayList<Integer> cardCompare(String card1, String card2) {
    int num1 = getCardNumber(card1.substring(0, card1.length()-1));
    int num2 = getCardNumber(card2.substring(0, card2.length()-1));
    char suit1 = card1.charAt(card1.length()-1);
    char suit2 = card2.charAt(card2.length()-1);
    
    if (suit1 != suit2) {
        if (getSuitPriority(suit1) < getSuitPriority(suit2)) 
            return new ArrayList<>(Arrays.asList(-1));
        return new ArrayList<>(Arrays.asList(1));
    }
    
    if (num1 < num2) return new ArrayList<>(Arrays.asList(-1));
    if (num1 > num2) return new ArrayList<>(Arrays.asList(1));
    return new ArrayList<>(Arrays.asList(0));
}




String order = "HCDS";


ArrayList<String> bubbleSort(ArrayList<String> list) {
    int n = list.size();
    for (int i = 0; i < n - 1; i++) {
        for (int j = 0; j < n - i - 1; j++) {
            if (compare(list.get(j), list.get(j + 1)) > 0) {
                
                String temp = list.get(j);
                list.set(j, list.get(j + 1));
                list.set(j + 1, temp);
            }
        }
    }
    return list;
}


int compare(String a, String b) {
    int indexA = order.indexOf(a.charAt(1));
    int indexB = order.indexOf(b.charAt(1));

    if (indexA == indexB) {
        
        return Integer.compare(Integer.parseInt(a.substring(0, 1)), Integer.parseInt(b.substring(0, 1)));
    } else {
       
        return Integer.compare(indexA, indexB);
    }
}
void bubbleSort(String[] arr) {
    int n = arr.length;
    for (int i = 0; i < n-1; i++) {
        for (int j = 0; j < n-i-1; j++) {
            if (arr[j].compareTo(arr[j+1]) > 0) {
                
                String temp = arr[j];
                arr[j] = arr[j+1];
                arr[j+1] = temp;
            }
        }
    }
}
void mergeSort(String[] arr) {
    if (arr.length > 1) {
        int mid = arr.length / 2;
        String[] left = Arrays.copyOfRange(arr, 0, mid);
        String[] right = Arrays.copyOfRange(arr, mid, arr.length);

        mergeSort(left);
        mergeSort(right);

        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i].compareTo(right[j]) <= 0) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }
        while (i < left.length) {
            arr[k++] = left[i++];
        }
        while (j < right.length) {
            arr[k++] = right[j++];
        }
    }
}
long measureBubbleSort(String filename) {
    try {
        List<String> lines = Files.readAllLines(Paths.get(filename));
        String[] arr = lines.toArray(new String[0]);
        long startTime = System.currentTimeMillis();
        bubbleSort(arr);
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    } catch (IOException e) {
        e.printStackTrace();
        return -1;
    }
}
long measureMergeSort(String filename) {
    try {
        List<String> lines = Files.readAllLines(Paths.get(filename));
        String[] arr = lines.toArray(new String[0]);
        long startTime = System.currentTimeMillis();
        mergeSort(arr);
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    } catch (IOException e) {
        e.printStackTrace();
        return -1;
    }
}
void sortComparison(String[] filenames) {
    
    System.out.println("           10, 100, 1000");

    
    System.out.print("bubbleSort, ");
    for (String filename : filenames) {
        long time = measureBubbleSort(filename);
        System.out.print(time + ", ");
    }
    System.out.println();

    
    System.out.print("mergeSort, ");
    for (String filename : filenames) {
        long time = measureMergeSort(filename);
        System.out.print(time + ", ");
    }
    System.out.println();
}