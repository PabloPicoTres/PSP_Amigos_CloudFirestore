package pablo.ad.psp_amigos_pablo.room;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Delete;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import pablo.ad.psp_amigos_pablo.room.dao.CallDao;
import pablo.ad.psp_amigos_pablo.room.dao.FriendDao;
import pablo.ad.psp_amigos_pablo.room.pojo.Call;
import pablo.ad.psp_amigos_pablo.room.pojo.Friend;
import pablo.ad.psp_amigos_pablo.room.pojo.FriendCallCount;
import pablo.ad.psp_amigos_pablo.util.UtilThread;

public class Repository {

    //private Friend currentFriend;

    private FriendDao friendDao;
    private CallDao callDao;

    private LiveData<List<FriendCallCount>> liveFriendList;
    private LiveData<List<Call>> liveCallList;
    //private MutableLiveData<Long> liveCarInsertId = new MutableLiveData<>();

    public Repository(Context context) {
        FriendsDataBase db = FriendsDataBase.getDb(context);
        friendDao = db.getFriendDao();
        callDao = db.getCallDao();
        liveFriendList = friendDao.getFriendCount();
        liveCallList = callDao.getAllCalls();
    }


    public LiveData<List<FriendCallCount>> getLiveFriendList() {
        return liveFriendList;
    }

    public void insert(Friend friend) {
        UtilThread.threadExecutorPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    long id = friendDao.insert(friend);
                    //liveCarInsertId.postValue(id);
                } catch (Exception e) {
                    Log.v("xyzyx", "ERROR: " + e.toString());
                    //liveCarInsertId.postValue(0l);
                }
            }
        });
    }

    public void insert(Call call) {
        UtilThread.threadExecutorPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    long id = callDao.insert(call);
                    //liveCarInsertId.postValue(id);
                } catch (Exception e) {
                    Log.v("xyzyx", "ERROR: " + e.toString());
                    //liveCarInsertId.postValue(0l);
                }
            }
        });
    }


    public int delete(Friend friend) {
        final int[] result = {-1};
        UtilThread.threadExecutorPool.execute(new Runnable() {

            @Override
            public void run() {
                 result[0] = friendDao.delete(friend);
            }
        });
        return result[0];
    }

    public int update(Friend friend) {
        final int[] result = {-1};
        UtilThread.threadExecutorPool.execute(new Runnable() {

            @Override
            public void run() {
                result[0] = friendDao.update(friend);
            }
        });
        return result[0];
    }

    public int delete(Call call) {
        final int[] result = {-1};
        UtilThread.threadExecutorPool.execute(new Runnable() {

            @Override
            public void run() {
                result[0] = callDao.delete(call);
            }
        });
        return result[0];
    }

    public LiveData<List<Call>> getLiveCallList() {
        return liveCallList;
    }
}