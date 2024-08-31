package wilfer.webfluxpostgres;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public Mono<ResponseEntity<List<Book>>> getAllBooks() {
        return bookService.getAllBooks()
                .collectList()
                .map(ResponseEntity::ok);
    }

    @GetMapping("/books/{id}")
    public Mono<ResponseEntity<Book>> getBookById(@PathVariable Long id) {
        return bookService.getBookById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping("/books")
    public Mono<ResponseEntity<Book>> saveBook(@RequestBody Book book) {
        return bookService.save(book)
                .map(it -> ResponseEntity.status(201).body(it));
    }

    @PutMapping("/books/{id}")
    public Mono<ResponseEntity<Book>> updateBook(@RequestBody Book book, @PathVariable Long id) {
        return bookService.updateBook(book, id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/books/{id}")
    public Mono<ResponseEntity<Void>> deleteBook(@PathVariable Long id) {
        return bookService.deleteBook(id)
                .thenReturn(ResponseEntity.noContent().build());
    }
}
