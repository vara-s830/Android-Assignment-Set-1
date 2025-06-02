import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BookListActivity extends AppCompatActivity {
    private BookListViewModel viewModel;
    private BookListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        // Initialize ViewModel and Repository
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "book-database").build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://your-api-url.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        BookApiService apiService = retrofit.create(BookApiService.class);
        BookRepository repository = new BookRepository(apiService, db.bookDao());
        viewModel = new ViewModelProvider(this, new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                return (T) new BookListViewModel(repository);
            }
        }).get(BookListViewModel.class);

        // Setup RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        adapter = new BookListAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Observe books
        viewModel.getBooks().observe(this, books -> adapter.setBooks(books));
    }
}

