
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import java.util.List;

public class BookListViewModel extends ViewModel {

    private BookRepository repository;
    private LiveData<List<Book>> books;

    public BookListViewModel(BookRepository repository) {
        this.repository = repository;
        this.books = repository.getBooks();
    }

    public LiveData<List<Book>> getBooks() {
        return books;
    }
}
