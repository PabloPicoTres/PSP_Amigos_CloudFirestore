package pablo.ad.psp_amigos_pablo.viewmodel;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

import pablo.ad.psp_amigos_pablo.room.Repository;
import pablo.ad.psp_amigos_pablo.room.pojo.Call;
import pablo.ad.psp_amigos_pablo.room.pojo.Contact;
import pablo.ad.psp_amigos_pablo.room.pojo.Friend;
import pablo.ad.psp_amigos_pablo.room.pojo.FriendCallCount;

public class ViewModelActivity extends AndroidViewModel {

    private Repository repository;

    private static List<Contact> contactList = new ArrayList<>();

    private static FriendCallCount currentFriend;

    public ViewModelActivity(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    public FriendCallCount getCurrentFriend() {
        return currentFriend;
    }

    public void setCurrentFriend(FriendCallCount currentFriend) {
        this.currentFriend = currentFriend;
    }

    public LiveData<List<FriendCallCount>> getLiveFriendList() {
        return repository.getLiveFriendList();
    }

    public LiveData<List<Call>> getLiveCallList() {
        return repository.getLiveCallList();
    }


    public List<Contact> getContactList(){
        if (contactList.size() == 0 ) {
            contactList = repository.getContactList();
        }
        return contactList;
    }

    public String getFriendNameById(long id){
        return repository.getFriendNameById(id);
    }

    public void guardaContactos(){

        //repository.guardaContactos(contactList);//falla algo en esta lógica

        for (Contact c: contactList) {
            if (c.isChecked()){
                repository.insert(new Friend(c.getName(), "1/6/2000", Integer.valueOf(c.getPhone())));
            }
        }
    }

    public void updateFriend(Friend friend){
        repository.update(friend);
    }

    public void deleteFriend(long id) {
        repository.delete(id);
    }

}