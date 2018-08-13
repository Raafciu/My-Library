import library.business.book.Book;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class Java8Test {

    /**
     * Zadanka do oceny na jutro
     * (bez pomocy, tylko internet i Twoja głowa :P):
     * <p>
     * 1. Przy użyciu streamów wybrać książki powyżej 400 stron i tylko takie, które są dostępne oraz wypisać pierwsze 5 książek w kolejności od największej liczby stron,
     * 2. Wybrać książki tylko Dana Brown'a i policzyć kwote wszystkich jego książek,
     * 3. Dla książek E. L. James'a ustawić kwotę 9.99,
     * 4. Sprawdzić czy w zbiorze istnieje książka Erica Carle'a, jeśli nie to ją dodać do zbioru (parametry jakie chcesz oprócz autora!),
     * 5. Zmapować dostępną listę wszystkich książek na mapę, gdzie kluczem będzie ISBN, a wartością obiekt Book.
     * 6. Przefiltrować książki powyżej 300 stron alfabetycznie (za pomocą streamów)
     * <p>
     * Tutaj głównie będziesz korzystał ze streamów, pamiętaj o Unit Testach, które będą przy okazji sprawdzały rezultat Twojego działania.
     * Do każdego zadania (oddziel je sobie w metodach z adnotacją @Test) napisz komentarz, dlaczego tak zrobiłeś, a jeśli czegoś nie umiesz
     * to napisz pytania. Tam gdzie jest możliwość sprawdzenia czegoś Optional'em to możesz go również wykorzystać.
     * <p>
     * POWODZENIA!
     */






    private final List<Book> bookList = Arrays.asList(
            new Book("z", "Brown, Dan", 412, 33.22, true),
            new Book("b", "Rowling, J.K.", 33, 33.22, false),
            new Book("c", "Rowling, J.K.", 786, 33.22, false),
            new Book("d", "Rowling, J.K.", 12, 33.22, true),
            new Book("e", "James, E. L.", 41, 33.22, false),
            new Book("g", "Rowling, J.K.", 124, 33.22, false),
            new Book("f", "Rowling, J.K.", 232, 33.22, true),
            new Book("h", "Rowling, J.K.", 654, 33.22, true),
            new Book("i", "Brown, Dan", 432, 33.22, false),
            new Book("j", "Rowling, J.K.", 11, 33.22, false),
            new Book("k", "James, E. L.", 326, 33.22, true),
            new Book("l", "Meyer, Stephenie", 454, 33.22, true),
            new Book("m", "Larsson, Stieg", 311, 33.22, false),
            new Book("n", "James, E. L.", 45, 33.22, true),
            new Book("o", "Brown, Dan", 32, 33.22, false),
            new Book("p", "Meyer, Stephenie", 863, 33.22, true),
            new Book("q", "Deception Point", 1119, 33.22, false),
            new Book("r", "Haddon, Mark", 123, 33.22, false)
    );

    //            zad1

    /**
     * jak zamienic z IntStreama z powrotem na obiekt nie nadpisując go?
     */
    @Test
    public void shouldPrintBooksTest() {
        bookList
                .stream()
                .filter(book -> book.getPages() > 400)
                .filter(Book::isAvailable)
                .map(Book::getPages)
                .sorted()
                .limit(5)
                .forEach(System.out::println);
    }

    //                zad2

    /**
     * mapowanie na double było potrzebne, aby wyliczyć cene wszystkich ksiazek danego autora
     * zrobiłem sprawdzenie
     */
    @Test
    public void shouldGetBooksBrownDanAndSumUpThePriceTest() {
        final double DELTA = 1e-15;
        String expectedAuthor = "Brown, Dan";
        AtomicReference<Double> expectedSum = new AtomicReference<>(0.0);
        AtomicReference<String> checkAuthor = new AtomicReference<>("");
        bookList.forEach(book -> {
            checkAuthor.set(book.getAuthor());
            if (checkAuthor.get().equals(expectedAuthor))
                expectedSum.updateAndGet(sum -> sum + book.getPrice());
        });

        double sum = bookList.stream()
                .filter(book -> book.getAuthor().equals(expectedAuthor))
                .mapToDouble(Book::getPrice)
                .sum();
        Assert.assertEquals(expectedSum.get(), sum, DELTA);
    }

    //                    zad3
    @Test
    public void shouldSetPriceOfBooksTest() {
        final double DELTA = 1e-15;
        String expectedAuthor = "James, E. L.";
        double newPrice = 9.99;

        List<Book> newList = bookList.stream()
                .filter(book -> book.getAuthor().equals(expectedAuthor))
                .peek(book -> book.setPrice(newPrice))
                .collect(Collectors.toList());

        List<Book> jamesList = bookList.stream()
                .filter(book -> book.getAuthor().equals(expectedAuthor))
                .collect(Collectors.toList());
        jamesList.forEach(System.out::println);

        newList.forEach(book -> Assert.assertEquals(newPrice, book.getPrice(), DELTA));

        //   Assert.assertEquals();
    }

    //                zad4
    @Test
    public void checkAndAddIfNotExistBookTest() {
        List<Book> notFinalList = new ArrayList<>(bookList);
        String expectedAuthor = "Eric Carl";
        Book newBook = new Book("isbn", expectedAuthor, 134, 12.55, true);

        Optional<Book> optional = bookList.stream()
                .filter(book -> book.getAuthor().equalsIgnoreCase(expectedAuthor))
                .findAny();

        Assert.assertFalse(bookList.contains(newBook));

        if (!optional.isPresent())
            notFinalList.add(newBook);

        Assert.assertFalse(bookList.contains(newBook));
        Assert.assertTrue(notFinalList.contains(newBook));
    }

    //                zad5
    @Test
    public void shouldListToMapTest() {
        String checkKey = "Z";

        Map<String, Book> bookMap = bookList.stream()
                .collect(Collectors.toMap(Book::getIsbn, bookList -> bookList));
        bookMap.entrySet().stream()
                .filter(stringBookEntry -> stringBookEntry.getKey().equalsIgnoreCase(checkKey))
                .forEach(System.out::println);
    }

    //                zad6

    /**
     * tak jest chyba najszybciej
     */
    @Test
    public void shouldFilterBookListTest() {
        bookList.stream()
                .filter(book -> book.getPages() > 300)
                .map(Book::toString)
                .sorted(String::compareToIgnoreCase)
                .forEach(System.out::println);
    }

}