
import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {BookEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract BookDao bookDao();
}
