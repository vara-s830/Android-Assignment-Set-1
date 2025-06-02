
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class BookRepository {

    private BookApiService apiService;
    private BookDao bookDao;
    private MutableLiveData<List<Book>> booksLiveData = new MutableLiveData<>();

    public BookRepository(BookApiService apiService, BookDao bookDao) {
        this.apiService = apiService;
        this.bookDao = bookDao;
    }

    public LiveData<List<Book>> getBooks() {
        apiService.getBooks().enqueue(new Callback<List<BookResponse>>() {
            @Override
            public void onResponse(Call<List<BookResponse>> call, Response<List<BookResponse>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Book> books = new ArrayList<>();
                    for (BookResponse bookResponse : response.body()) {
                        books.add(new Book(
                                bookResponse.getId(),
                                bookResponse.getTitle(),
                                bookResponse.getAuthor(),
                                bookResponse.getDescription(),
                                bookResponse.getThumbnailUrl(),
                                bookResponse.getRating()
                        ));
                    }
                    booksLiveData.postValue(books);
                }
            }

            @Override
            public void onFailure(Call<List<BookResponse>> call, Throwable t) {
                // Handle error
            }
        });
        return booksLiveData;
    }

    public LiveData<List<BookEntity>> getFavoriteBooks() {
        return bookDao.getAllFavorites();
    }

    public void saveFavorite(Book book) {
        BookEntity entity = new BookEntity();
        entity.setId(book.getId());
        entity.setTitle(book.getTitle());
        entity.setAuthor(book.getAuthor());
        entity.setDescription(book.getDescription());
        entity.setThumbnailUrl(book.getThumbnailUrl());
        entity.setRating(book.getRating());
        new Thread(() -> bookDao.insert(entity)).start();
    }

    public void removeFavorite(Book book) {
        BookEntity entity = new BookEntity();
        entity.setId(book.getId());
        // ... set other fields as needed, but only id is required for delete
        new Thread(() -> bookDao.delete(entity)).start();
    }
}
