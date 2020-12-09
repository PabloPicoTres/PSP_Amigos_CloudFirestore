package pablo.ad.psp_amigos_pablo.room.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import pablo.ad.psp_amigos_pablo.room.pojo.Call;

@Dao
public interface CallDao {

    @Insert
    long insert(Call call);

    @Delete
    int delete(Call call);

    @Query("select * from call order by callDate")
    LiveData<List<Call>> getAllCalls();

}
