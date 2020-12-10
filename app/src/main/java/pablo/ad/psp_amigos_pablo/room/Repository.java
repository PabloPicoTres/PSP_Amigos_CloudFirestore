package pablo.ad.psp_amigos_pablo.room;

import android.content.Context;
import android.database.Cursor;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Delete;
import androidx.room.Query;
import androidx.room.Update;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import pablo.ad.psp_amigos_pablo.room.dao.CallDao;
import pablo.ad.psp_amigos_pablo.room.dao.FriendDao;
import pablo.ad.psp_amigos_pablo.room.pojo.Call;
import pablo.ad.psp_amigos_pablo.room.pojo.Contact;
import pablo.ad.psp_amigos_pablo.room.pojo.Friend;
import pablo.ad.psp_amigos_pablo.room.pojo.FriendCallCount;
import pablo.ad.psp_amigos_pablo.util.UtilThread;

public class Repository {

    private FriendDao friendDao;
    private CallDao callDao;
    private LiveData<List<FriendCallCount>> liveFriendList;
    private LiveData<List<Call>> liveCallList;
    private Context context;

    public Repository(Context context) {
        FriendsDataBase db = FriendsDataBase.getDb(context);
        this.context = context;
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
                } catch (Exception e) {
                    Log.v("xyzyx", "ERROR: " + e.toString());
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
                    Log.v("xyzyx", "call: " + call.toString());
                    callDao.insert(call);
                } catch (Exception e) {
                    Log.v("xyzyx", "ERROR: " + e.toString());
                    Log.v("xyzyx", "call: " + call.toString());
                }
            }
        });
    }


    public int delete(long id) {
        final int[] result = {-1};
        UtilThread.threadExecutorPool.execute(new Runnable() {

            @Override
            public void run() {
                 //result[0] = friendDao.delete(friend);
                result[0] = friendDao.delete(friendDao.getFriendById(id));
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

    public String getFriendNameById(long id){
        /*final String[] name = new String[1];
        UtilThread.threadExecutorPool.execute(new Runnable() {

            @Override
            public void run() {
                name[0] = friendDao.getFriendNameById(id);

            }
        });

        Log.v("xyzyx", name[0]);
        return name[0];*/
        Log.v("xyzyx",friendDao.getFriendById(id).toString());
        return friendDao.getFriendById(id).getName();
    }

    public LiveData<List<Call>> getLiveCallList() {
        return liveCallList;
    }


    public List<Contact> getContactList() {
        List<Contact> contactList = new ArrayList<>();

        String[] data = new String[]{ContactsContract.Data.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER};
        String order = ContactsContract.Data.DISPLAY_NAME + " ASC";
        String selectionNumber = ContactsContract.Data.MIMETYPE + "='" +
                ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE + "' AND "
                + ContactsContract.CommonDataKinds.Phone.NUMBER + " IS NOT NULL";

        Cursor cursor =  context.getContentResolver().query(
                ContactsContract.Data.CONTENT_URI,
                data,
                selectionNumber,
                null,
                order);
        while(cursor.moveToNext()){

            Contact contactoAux = new Contact(cursor.getString(0), cursor.getString(1), false);
            contactList.add(contactoAux);

        }
        cursor.close();

        return contactList;
    }

    public void checkNewCall(String tlf, String date) {
        UtilThread.threadExecutorPool.execute(new Runnable() {

            @Override
            public void run() {

                List<Friend> friends = friendDao.getAllFriends();

                for (Friend f : friends){
                    if (f.getNumber() == Integer.valueOf(tlf)){
                        insert(new Call(f.getId(), date));
                        break;
                    }
                }

            }
        });
    }


    public void guardaContactos(List<Contact> contactList){
        UtilThread.threadExecutorPool.execute(new Runnable() {
            @Override
            public void run() {
                for (Contact c : contactList) {
                    if (c.isChecked()) {

                        boolean exist = false;

                        for (Friend f : friendDao.getAllFriends()) {
                            if (Integer.valueOf(c.getPhone()) == f.getNumber()) {
                                exist = true;
                            }
                        }

                        if (!exist) {
                            insert(new Friend(c.getName(), "1/6/2000", Integer.valueOf(c.getPhone())));
                        } else {
                            Toast.makeText(context, "El contacto ya es amigo", Toast.LENGTH_SHORT).show();
                        }

                    }

                }
            }
        });

    }
}