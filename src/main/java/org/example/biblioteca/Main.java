package org.example.biblioteca;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Archivio archivio = new Archivio();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Aggiungi elemento");
            System.out.println("2. Ricerca per ISBN");
            System.out.println("3. Rimuovi elemento");
            System.out.println("4. Ricerca per anno");
            System.out.println("5. Ricerca per autore");
            System.out.println("6. Aggiorna elemento");
            System.out.println("7. Statistiche catalogo");
            System.out.println("8. Esci");

            System.out.print("Scegli un'opzione: ");
            int scelta = scanner.nextInt();
            scanner.nextLine();

            //L'ho gestito con i case in modo da far scegliere all'utente cosa vuole fare inserendo il numero del caso da eseguire, di norma si dovrebbe partire dal 1 cosi si aggiunge un elemento per poter eseguire gli altri in modo indipendente e non perforza in ordine.
            //Avrei voluto gestire meglio gli errori ma non sono riuscito a testare tutto quanto nel migliore dei modi.
            switch (scelta) {
                case 1:
                    System.out.print("Inserisci ISBN: ");
                    String isbn = scanner.nextLine();
                    System.out.print("Inserisci Titolo: ");
                    String titolo = scanner.nextLine();
                    System.out.print("Inserisci Anno di Pubblicazione: ");
                    int anno = scanner.nextInt();
                    System.out.print("Inserisci Numero Pagine: ");
                    int pagine = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("È un Libro o una Rivista (L/R)? ");
                    String tipo = scanner.nextLine();

                    if (tipo.equalsIgnoreCase("L")) {
                        System.out.print("Inserisci Autore: ");
                        String autore = scanner.nextLine();
                        System.out.print("Inserisci Genere: ");
                        String genere = scanner.nextLine();
                        archivio.aggiungiElemento(new Libro(isbn, titolo, anno, pagine, autore, genere));
                    } else if (tipo.equalsIgnoreCase("R")) {
                        System.out.print("Inserisci Periodicità (SETTIMANALE/MENSILE/SEMESTRALE): ");
                        String periodicita = scanner.nextLine().toUpperCase();
                        archivio.aggiungiElemento(new Rivista(isbn, titolo, anno, pagine, Periodicita.valueOf(periodicita)));
                    } else {
                        System.out.println("Tipo non valido!");
                    }
                    break;

                case 2:
                    System.out.print("Inserisci ISBN: ");
                    isbn = scanner.nextLine();
                    try {
                        ElementoCatalogo elemento = archivio.ricercaPerIsbn(isbn);
                        System.out.println("Elemento trovato: " + elemento);
                    } catch (EccezioneElementoNonTrovato e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3:
                    System.out.print("Inserisci ISBN: ");
                    isbn = scanner.nextLine();
                    archivio.rimuoviElemento(isbn);
                    System.out.println("Elemento rimosso.");
                    break;

                case 4:
                    System.out.print("Inserisci anno: ");
                    anno = scanner.nextInt();
                    scanner.nextLine();
                    archivio.ricercaPerAnno(anno).forEach(System.out::println);
                    break;

                case 5:
                    System.out.print("Inserisci autore: ");
                    String autore = scanner.nextLine();
                    archivio.ricercaPerAutore(autore).forEach(System.out::println);
                    break;

                case 6:
                    System.out.print("Inserisci ISBN elemento da aggiornare: ");
                    isbn = scanner.nextLine();
                    System.out.print("Inserisci nuovo Titolo: ");
                    titolo = scanner.nextLine();
                    System.out.print("Inserisci nuovo Anno di Pubblicazione: ");
                    anno = scanner.nextInt();
                    System.out.print("Inserisci nuovo Numero Pagine: ");
                    pagine = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("È un Libro o una Rivista (L/R)? ");
                    tipo = scanner.nextLine();

                    if (tipo.equalsIgnoreCase("L")) {
                        System.out.print("Inserisci nuovo Autore: ");
                        autore = scanner.nextLine();
                        System.out.print("Inserisci nuovo Genere: ");
                        String genere = scanner.nextLine();
                        archivio.aggiornaElemento(isbn, new Libro(isbn, titolo, anno, pagine, autore, genere));
                    } else if (tipo.equalsIgnoreCase("R")) {
                        System.out.print("Inserisci nuova Periodicità (SETTIMANALE/MENSILE/SEMESTRALE): ");
                        String periodicita = scanner.nextLine().toUpperCase();
                        archivio.aggiornaElemento(isbn, new Rivista(isbn, titolo, anno, pagine, Periodicita.valueOf(periodicita)));
                    } else {
                        System.out.println("Tipo non valido!");
                    }
                    break;

                case 7:
                    archivio.statisticheCatalogo();
                    break;

                case 8:
                    running = false;
                    break;

                default:
                    System.out.println("Scelta non valida.");
            }
        }

        scanner.close();
        System.out.println("Uscita dall'applicazione.");
    }
}
