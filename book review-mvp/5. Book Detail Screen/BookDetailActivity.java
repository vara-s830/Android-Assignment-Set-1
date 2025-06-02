public class BookDetailActivity extends AppCompatActivity {
    private BookDetailViewModel viewModel;
    private Book book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        // Get book from intent (make Book implement Parcelable or Serializable)
        book = (Book) getIntent().getSerializableExtra("book");

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
                return (T) new BookDetailViewModel(repository);
            }
        }).get(BookDetailViewModel.class);

        // Bind data to views
        TextView title = findViewById(R.id.title);
        TextView author = findViewById(R.id.author);
        TextView description = findViewById(R.id.description);
        TextView rating = findViewById(R.id.rating);
        ImageView image = findViewById(R.id.image);
        Button favoriteButton = findViewById(R.id.favoriteButton);

        title.setText(book.getTitle());
        author.setText(book.getAuthor());
        description.setText(book.getDescription());
        rating.setText(String.valueOf(book.getRating()));
        // image.setImageResource(R.drawable.placeholder);

        favoriteButton.setOnClickListener(v -> {
            // Check if book is already favorite (not implemented here)
            viewModel.saveFavorite(book);
            favoriteButton.setText("Saved!");
        });
    }
}
