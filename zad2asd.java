import java.util.Arrays;
import java.util.Random;

public class ASD2 {

    // Funkcja sortująca quicksort
    public static void quickSort(int[] tab) {
        quickSort(tab, 0, tab.length - 1); 
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

    // Zamiana miejscami dwóch elementów w tablicy
    private static void swap(int[] tab, int i, int j) {
        int temp = tab[i];
        tab[i] = tab[j];
        tab[j] = temp;
    }
    
    //Tworzenie losowej tablicy z liczb z przedziału 1-100 o rozmiarze 50
    public static void main(String[] args) {
        int[] tab = new int[50];
        Random random = new Random();
        for (int i = 0; i < 50; i++) {
            tab[i] = random.nextInt(100) + 1;
        }
        
    //Wyświetlanie wyniku
        System.out.println();
        System.out.println("Tablica jest generowana losowo. Maksymalna liczba w tablicy to 100, a najmniejsza to 0:");
        System.out.println();
        System.out.println("Tablica przed sortowaniem:");
        System.out.println(Arrays.toString(tab));
        System.out.println();
        quickSort(tab);
        System.out.println("Tablica po sortowaniu:");
        System.out.println(Arrays.toString(tab));
    }
}

