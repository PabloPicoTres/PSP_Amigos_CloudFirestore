package pablo.ad.psp_amigos_pablo.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import pablo.ad.psp_amigos_pablo.room.pojo.Friend;
import pablo.ad.psp_amigos_pablo.room.pojo.FriendCallCount;

@Dao
public interface FriendDao {

    @Delete
    int delete(Friend friend);

    @Insert
    long insert(Friend friend);

    @Update
    int update(Friend friend);

    @Query("select f.name, f.number, f.birthdate, count(c.id) count from friend f left join call c on f.id = c.idFriend group by f.id order by count(c.id)")
    LiveData<List<FriendCallCount>> getFriendCount();

    @Query("select * from friend order by name")
    List<Friend> getAllFriends();

    @Query("select * from friend where id = :id")
    Friend getFriendById(long id);

    @Query("select name from friend where id = :id")
    String getFriendNameById(long id);

}
