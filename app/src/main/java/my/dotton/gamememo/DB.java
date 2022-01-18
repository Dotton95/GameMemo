package my.dotton.gamememo;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Memo.class},version = 1,exportSchema = false)
public abstract class DB extends RoomDatabase {
    private static DB db;

    public abstract MemoDAO memoDAO();

    public synchronized static DB getInstance(Context context){
        if(db == null){
            db = Room.databaseBuilder(context.getApplicationContext(), DB.class,"memo.db")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return db;
    }
}
