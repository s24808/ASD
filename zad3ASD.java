import java.util.Arrays;
import java.util.Random;

public class ASD3 {
    public static void main(String[] args) {

        //Generowanie losowej tablicy o rozmiarze 100 000 z ogarniczeniem maksymalnej liczby ze względu na ograniczoną pamięć
        int max = 1000; // Maksymalna wartość ograniczona do 1000
        int[] tab = generateRandomArray(100000, max); // Rozmiar tablicy: 100 000 ograniczony przez max

        //Wyświetlanie wyniku

        System.out.println("Tablica jest ograniczona ze względu na ograniczoną pamięć");
        System.out.println();
        System.out.println("Maksymalna wartość liczb: " + max);
        System.out.println("Rozmiar tablicy: 100 000");
        System.out.println("Wynik sortowania: ");

        int[] tabquicksort = tab.clone(); //Klonowanie tablicy wejściowej
        long startTime = System.currentTimeMillis(); //Start timer
        quickSort(tabquicksort, 0, tabquicksort.length - 1);
        long endTime = System.currentTimeMillis(); //Koniec timer
        System.out.println("Czas sortowania quicksort: " + (endTime - startTime) + " ms");

        int[] tabcountingsort = tab.clone(); //Klonowanie tablicy wejściowej
        startTime = System.currentTimeMillis(); //Start timer
        countingsort(tabcountingsort);
        endTime = System.currentTimeMillis(); //Koniec timer
        System.out.println("Czas sortowania countingsort: " + (endTime - startTime) + " ms");

        int[] tabheapsort = tab.clone(); //Klonowanie tablicy wejściowej
        startTime = System.currentTimeMillis(); //Start timer
        heapsort(tabheapsort);
        endTime = System.currentTimeMillis(); //Koniec timer
        System.out.println("Czas sortowania heapsort: " + (endTime - startTime) + " ms");
    }

    // Sortowanie szybkie - rekurencja
    private static void quickSort(int[] tab, int left, int right) {
        if (left < right) {
            int pivotIndex = partition(tab, left, right);
            quickSort(tab, left, pivotIndex - 1);
            quickSort(tab, pivotIndex + 1, right);
        }
    }

    // Dzielenie tablicy na dwie części - pivot
    private static int partition(int[] tab, int left, int right) {
        int pivot = tab[right];
        int i = left - 1;

        // Przeglądanie tablicy od lewej do prawej
        for (int j = left; j < right; j++) {
            if (tab[j] <= pivot) {
                i++;
                swap(tab, i, j);
            }
        }
        swap(tab, i + 1, right); // Zamiana elementów miejscami
        return i + 1; // Zwrócenie indeksu
    }

    // Sortowanie przez zliczanie
    public static void countingsort(int[] tab) {
        int max = Arrays.stream(tab).max().orElse(0); //Szukanie maksymalnej wartości w tablicy
        int[] count = new int[max + 1];
        int[] posortowana = new int[tab.length]; //Wynik - posortowna tablica

        for (int num : tab) {
            count[num]++; //Liczenie ile jest liczb
        }

        for (int i = 1; i <= max; i++) {
            count[i] += count[i - 1];
        }

        for (int i = tab.length - 1; i >= 0; i--) {
            posortowana[count[tab[i]] - 1] = tab[i]; //Umieszczenie liczby w odpowiednim miejsu w tablicy
            count[tab[i]]--;
        }

        System.arraycopy(posortowana, 0, tab, 0, tab.length); //Kopia posortowanej tablicy do tablicy wejsciowej(tab)
    }

    // Sortowanie przez kopcowanie
    public static void heapsort(int[] tab) {
        int n = tab.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(tab, n, i); // Budowanie kopca
        }

        for (int i = n - 1; i > 0; i--) {
            swap(tab, 0, i); // Zamiana korzenia kopca z ostatnim elementem
            heapify(tab, i, 0); // Przywracanie własności kopca
        }
    }

    //Sprawdzanie po kopcu
    private static void heapify(int[] tab, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && tab[left] > tab[largest]) {
            largest = left; // Sprawdzanie lewego "dziecka"
        }

        if (right < n && tab[right] > tab[largest]) {
            largest = right; // Sprawdzanie prawego "dziecka"
        }

        if (largest != i) {
            swap(tab, i, largest); // Zamiana elementów
            heapify(tab, n, largest); // Rekurencyjne przywracanie własności kopca dla poddrzewa
        }
    }

    // Zamiana miejscami dwóch elementów w tablicy
    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    //Generowanie losowej tablicy --> n=rozmiar tablicy
    private static int[] generateRandomArray(int n, int max) {
        Random random = new Random();
        int[] tab = new int[n];
        for (int i = 0; i < n; i++) {
            tab[i] = random.nextInt(max) + 1; // Losowa liczba całkowita w zakresie 1 - max
        }
        return tab;
    }
}
