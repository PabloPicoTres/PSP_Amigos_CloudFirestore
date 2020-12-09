package pablo.ad.psp_amigos_pablo.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import pablo.ad.psp_amigos_pablo.room.dao.CallDao;
import pablo.ad.psp_amigos_pablo.room.dao.FriendDao;
import pablo.ad.psp_amigos_pablo.room.pojo.Call;
import pablo.ad.psp_amigos_pablo.room.pojo.Friend;

@Database(entities = {Friend.class, Call.class}, version = 1, exportSchema = false)
public abstract class FriendsDataBase extends RoomDatabase {

    public abstract CallDao getCallDao();
    public abstract FriendDao getFriendDao();

    private volatile static FriendsDataBase INSTANCE;

    public static synchronized FriendsDataBase getDb(final Context context) {
        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    FriendsDataBase.class, "AmigosApp.sqlite")
                    .build();
        }
        return INSTANCE;
    }
}
