package mk.uikm.finki.emtlab.model.exception;

public class BookNotAvailableException extends RuntimeException {
    public BookNotAvailableException(Long id) {
        super(String.format("Book with id %d is not available for rental.", id));
    }
}
