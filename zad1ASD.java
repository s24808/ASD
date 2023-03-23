public class ASD1 {

    //Implementacja algorytmu sortowania przez wstawienie
    public static void sortAlgorithm(int[] A) {
        for (int j = 2; j < A.length; j++) {
            int key = A[j];
            int i = j - 1;
            while (i >= 0 && A[i] > key) {
                A[i + 1] = A[i];
                i=i-1;
            }
            A[i + 1] = key;
        }
    }

    //Generowanie posortowanych tablic (rosnąco) --> n=rozmiar tablicy
    public static int[] generateSortedArray(int n){
        int[] A = new int[n];
        for(int i=0; i<n; i++){
            A[i] = i;
        }
        return A;
    }

    //Generowanie nieposortowanych tablic (malenąco) --> n=rozmiar tablicy
    public static int[] generateUnsortedArray(int n){
        int[] A = new int[n];
        for(int i=n-1; i>=0; i--){
            A[i] = n-i-1;
        }
        return A;
    }

    public static void main(String[] args) {
        //Generowanie posortowanych tablic o wyznaczonych rozmiarach n

        int[][] sorted = {
                generateSortedArray(2000),
                generateSortedArray(4000),
                generateSortedArray(8000),
                generateSortedArray(16000),
                generateSortedArray(32000)
        };

        //Wyswietlanie posortowanej tablicy o rozmiarze n, funkcji liniowej Fn = n, czasie sortowania Tn oraz stosunku Fn/Tn

        System.out.println("Best case (Posortowane tablice):");
        for(int[] A: sorted){
            long start = System.nanoTime();
            sortAlgorithm(A);
            int fn = A.length;
            long end = System.nanoTime();
            long tn = end - start;
            System.out.println("Rozmiar tablicy: " + A.length + ", Fn: " + fn + ", Tn: " + tn + " ns, Fn/Tn: " + (fn / tn));
        }

        //Generowanie nieposortowanych tablic o wyznaczonych rozmiarach n

        int[][] unsorted = {
                generateUnsortedArray(2000),
                generateUnsortedArray(4000),
                generateUnsortedArray(8000),
                generateUnsortedArray(16000),
                generateUnsortedArray(32000)
        };

        System.out.println(" ");

        //Wyswietlanie nieposortowanej tablicy o rozmiarze n, funkcji liniowej Fn = n^2, czasie sortowania Tn oraz stosunku Fn/Tn

        System.out.println("Worst case (Nieposortowane tablice):");
        for(int[] A: unsorted){
            long start = System.nanoTime();
            sortAlgorithm(A);
            int fn = A.length;
            long end = System.nanoTime();
            long tn = end - start;
            System.out.println("Rozmiar tablicy: " + A.length + ", Fn: " + (fn * fn) + ", Tn: " + tn + " ns, Fn/Tn: " + ((fn * fn) / tn));
        }
    }
}
