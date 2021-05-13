package main;

import java.util.*;
import response.Book;

public class Storage {

    private static int currentId = 1;
    private static Map<Integer, Book> books = createNewMap();

    private static Map<Integer, Book> createNewMap() {
        return Collections.synchronizedMap(new HashMap<Integer, Book>());
    }

    public static List<Book> getAllBooks() {
        synchronized (books) {
            ArrayList<Book> bookList = new ArrayList<Book>();
            bookList.addAll(books.values());
            return bookList;
        }
    }

    public static int addBook(Book book) {
        synchronized (books) {
            int id = currentId++;
            book.setId(id);
            books.put(id, book);
            return id;
        }
    }

    public static Book getBook(int bookId) {
        if (books.containsKey(bookId)) {
            return books.get(bookId);
        }
        return null;
    }

    public static int replaceBook(Book newBook) {
        synchronized (books) {
            int bookId = newBook.getId();
            if (books.containsKey(bookId)) {
                books.replace(bookId, newBook);
            } else {
                newBook.setId(bookId);
                books.put(bookId, newBook);
            }
            return bookId;
        }
    }

    public static Book deleteBook(int id) {
        synchronized (books) {
            if (books.containsKey(id)) {
                return books.remove(id);
            }
            return null;
        }
    }

    public static List<Book> deleteAllBooks() {
        synchronized (books) {
            if (!books.isEmpty()) {
                ArrayList<Book> deleteBooks = new ArrayList<Book>();
                deleteBooks.addAll(books.values());
                books.clear();
                return deleteBooks;
            } else {
                System.out.println("The list of books is missing");
                return null;
            }
        }
    }
}
