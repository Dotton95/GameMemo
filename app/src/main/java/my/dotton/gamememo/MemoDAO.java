package my.dotton.gamememo;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MemoDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Memo memo);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllMemo(List<Memo> memos);

    @Delete
    void delete(Memo memo);

    @Update
    void update(Memo memo);

    @Query("SELECT * FROM memo group by code")
    List<Memo> selectGameCodeAll();

    @Query("SELECT * FROM memo order by sort")
    List<Memo> selectAllMemo();

    @Query("DELETE FROM memo")
    void deleteAllMemo();


}
