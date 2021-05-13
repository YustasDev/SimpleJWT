package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import response.Book;

public class Storage {

    private static int currentId = 1;
    private static HashMap<Integer, Book> books = new HashMap<Integer, Book>();

    public static List<Book> getAllBooks() {
        ArrayList<Book> bookList = new ArrayList<Book>();
        bookList.addAll(books.values());
        return bookList;
    }

    public static int addBook(Book book) {
        int id = currentId++;
        book.setId(id);
        books.put(id, book);
        return id;
    }

    public static Book getBook(int bookId) {
        if (books.containsKey(bookId)) {
            return books.get(bookId);
        }
        return null;
    }

    public static int replaceBook(Book newBook) {
        int bookId = newBook.getId();
        if (books.containsKey(bookId)) {
            books.replace(bookId, newBook);
        } else {
            newBook.setId(bookId);
            books.put(bookId, newBook);
        }
        return bookId;
    }

    public static Book deleteBook(int id) {
        if (books.containsKey(id)) {
            return books.remove(id);
        }
        return null;
    }
}
