package org.example.biblioteca;

public class Main {
    public static void main(String[] args) {
        // Test creazione di un Libro
        Libro libro = new Libro("123-456-789", "Il Signore degli Anelli", 1954, 1000, "J.R.R. Tolkien", "Fantasy");
        System.out.println("Test Libro: " + libro);

        // Test creazione di una Rivista
        Rivista rivista = new Rivista("987-654-321", "National Geographic", 2021, 100, Periodicita.MENSILE);
        System.out.println("Test Rivista: " + rivista);
    }
}
