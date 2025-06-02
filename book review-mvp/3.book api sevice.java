
import retrofit2.Call;
import retrofit2.http.GET;
import java.util.List;

public interface BookApiService {

    @GET("books")
    Call<List<BookResponse>> getBooks();
}
