package mk.uikm.finki.emtlab.event;

public record BookRentEvent(Long bookId, String bookName, int remainingCopies) {
}
