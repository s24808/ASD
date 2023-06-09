import java.util.*;

// Klasa Wezel z użyciem interfejsu Comparable
class Wezel implements Comparable<Wezel> {
    char znak;
    int czestotliwosc;
    Wezel left;
    Wezel right;

    // Konstruktor
    public Wezel(char znak, int czestotliwosc) {
        this.znak = znak;
        this.czestotliwosc = czestotliwosc;
    }

    // Sprawdzenie czy dany węzeł jest liściem
    public boolean Lisc() {
        return (left == null && right == null);
    }

    // Porównanie węzłów na podstawie ich częstotliwości
    @Override
    public int compareTo(Wezel b) {
        return this.czestotliwosc - b.czestotliwosc;
    }
}

public class ASD4 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Podanie tekstu do zakodowania przez użytkownika
        System.out.print("Podaj tekst do zakodowania: ");
        String text = scanner.nextLine();

        // Usuwanie spacji i znaków niebędących literami alfabetu
        text = text.replaceAll("\\s", ""); // Usunięcie spacji
        text = text.replaceAll("[^a-zA-Z]", ""); // Usunięcie znaków, które nie są w alfabecie

        // Zliczanie wystąpień znaków i sortowanie
        int[] tabWystapien = new int[256];

        // Zliczanie wystąpień znaków w tekście
        for (char znak : text.toCharArray()) {
            tabWystapien[znak]++;
        }

        //Tworzenie kolejki
        PriorityQueue<Wezel> minHeap = new PriorityQueue<>();

        // Tworzenie węzłów
        for (int i = 0; i < tabWystapien.length; i++) {
            if (tabWystapien[i] > 0) {
                Wezel a = new Wezel((char) i, tabWystapien[i]);
                minHeap.offer(a);
            }
        }

        // Algorytm kodowania Huffmana
        while (minHeap.size() > 1) {
            // Sortowanie węzłów na podstawie ich częstotliwości
            List<Wezel> wezly = new ArrayList<>(minHeap);
            Collections.sort(wezly);

            // Pobieranie dwóch najmniejszych węzłów
            Wezel left = wezly.get(0);
            Wezel right = wezly.get(1);

            // Usuwanie pobranych węzłów z kolejki
            minHeap.remove(left);
            minHeap.remove(right);

            // Tworzenie węzła nadrzędnego
            Wezel parent = new Wezel('\0', left.czestotliwosc + right.czestotliwosc);
            parent.left = left;
            parent.right = right;

            // Dodawanie węzła nadrzędnego do kolejki
            minHeap.offer(parent);
        }
        // Pobieranie korzenia z kolejki
        Wezel korzen = minHeap.poll();

        // Wyświetlanie znaku, liczby wystąpień i kodu znaku
        System.out.println("Znak\tLiczba Wystapien\tKod");
        Wynik(korzen, "");

        scanner.close();
    }

    // Wyświetlanie wyniku
    private static void Wynik(Wezel korzen, String kod) {
        if (korzen.Lisc()) {
            System.out.println(korzen.znak + "\t\t" + korzen.czestotliwosc + "\t\t\t\t\t" + kod);
            return;
        }

        Wynik(korzen.left, kod + "0");
        Wynik(korzen.right, kod + "1");
    }
}
