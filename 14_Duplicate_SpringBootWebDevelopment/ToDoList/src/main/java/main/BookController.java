package main;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import response.Book;

@RestController
public class BookController {

    @GetMapping("/books/")
    public List<Book> list() {
        return Storage.getAllBooks();
    }

    @PostMapping("/books/")
    public int add(Book book) {
        return Storage.addBook(book);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity get(@PathVariable int id) {
        Book book = Storage.getBook(id);
        if (book == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(book, HttpStatus.OK);
    }

    @PutMapping("/books/")
    public int replaceBook(Book newBook) {
        return Storage.replaceBook(newBook);
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity deleteBook(@PathVariable int id) {
        Book book = Storage.deleteBook(id);
        if (book == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(book, HttpStatus.OK);
    }
}

