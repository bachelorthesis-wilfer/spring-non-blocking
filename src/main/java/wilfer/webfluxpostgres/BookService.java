package wilfer.webfluxpostgres;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Flux<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Mono<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public Mono<Book> save(Book book) {
        return bookRepository.save(book);
    }

    public Mono<Book> updateBook(Book book, Long bookId) {
        return bookRepository.findById(bookId)
                .flatMap(x -> {
                    x.setAuthor(book.getAuthor());
                    x.setTitle(book.getTitle());
                    x.setPrice(book.getPrice());
                    x.setIsbn(book.getIsbn());
                    return bookRepository.save(x);
                })
                .switchIfEmpty(Mono.empty());
    }

    public Mono<Void> deleteBook(Long bookId) {
        return bookRepository.deleteById(bookId);
    }

}
