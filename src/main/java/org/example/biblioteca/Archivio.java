package org.example.biblioteca;

import java.util.*;
import java.util.stream.Collectors;

public class Archivio {
    private List<ElementoCatalogo> catalogo = new ArrayList<>();

    public void aggiungiElemento(ElementoCatalogo elemento) {
        if (catalogo.stream().anyMatch(e -> e.getIsbn().equals(elemento.getIsbn()))) {
            System.out.println("Elemento con ISBN già presente!");
        } else {
            catalogo.add(elemento);
        }
    }

    public ElementoCatalogo ricercaPerIsbn(String isbn) throws EccezioneElementoNonTrovato {
        return catalogo.stream()
                .filter(e -> e.getIsbn().equals(isbn))
                .findFirst()
                .orElseThrow(() -> new EccezioneElementoNonTrovato("Elemento con ISBN " + isbn + " non trovato."));
    }

    public void rimuoviElemento(String isbn) {
        catalogo.removeIf(e -> e.getIsbn().equals(isbn));
    }

    public List<ElementoCatalogo> ricercaPerAnno(int anno) {
        return catalogo.stream()
                .filter(e -> e.getAnnoPubblicazione() == anno)
                .collect(Collectors.toList());
    }

    public List<Libro> ricercaPerAutore(String autore) {
        return catalogo.stream()
                .filter(e -> e instanceof Libro && ((Libro) e).getAutore().equalsIgnoreCase(autore))
                .map(e -> (Libro) e)
                .collect(Collectors.toList());
    }

    public void aggiornaElemento(String isbn, ElementoCatalogo nuovoElemento) {
        rimuoviElemento(isbn);
        aggiungiElemento(nuovoElemento);
    }

    public void statisticheCatalogo() {
        long totaleLibri = catalogo.stream().filter(e -> e instanceof Libro).count();
        long totaleRiviste = catalogo.stream().filter(e -> e instanceof Rivista).count();
        ElementoCatalogo maxPagine = catalogo.stream().max(Comparator.comparingInt(ElementoCatalogo::getNumeroPagine)).orElse(null);
        double mediaPagine = catalogo.stream().mapToInt(ElementoCatalogo::getNumeroPagine).average().orElse(0);

        System.out.println("Totale Libri: " + totaleLibri);
        System.out.println("Totale Riviste: " + totaleRiviste);
        System.out.println("Elemento con più pagine: " + (maxPagine != null ? maxPagine : "Nessun elemento"));
        System.out.println("Media pagine: " + mediaPagine);
    }
}
