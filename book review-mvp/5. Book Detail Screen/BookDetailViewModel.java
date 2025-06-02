
import androidx.lifecycle.ViewModel;

public class BookDetailViewModel extends ViewModel {

    private BookRepository repository;

    public BookDetailViewModel(BookRepository repository) {
        this.repository = repository;
    }

    public void saveFavorite(Book book) {
        repository.saveFavorite(book);
    }

    public void removeFavorite(Book book) {
        repository.removeFavorite(book);
    }
}
