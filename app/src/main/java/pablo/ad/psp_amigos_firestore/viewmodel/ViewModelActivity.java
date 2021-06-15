package pablo.ad.psp_amigos_firestore.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

import pablo.ad.psp_amigos_firestore.model.Repository;
import pablo.ad.psp_amigos_firestore.model.pojo.Contact;
import pablo.ad.psp_amigos_firestore.model.pojo.Friend;

public class ViewModelActivity extends AndroidViewModel {

    private Repository repository;

    private static List<Contact> contactList = new ArrayList<>();

    private static Friend currentFriend;

    public ViewModelActivity(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    public Friend getCurrentFriend() {
        return currentFriend;
    }

    public void setCurrentFriend(Friend currentFriend) {
        this.currentFriend = currentFriend;
    }

    public LiveData<List<Friend>> getLiveFriendList() {
        return repository.getLiveFriends();
    }


    public List<Contact> getContactList(){
        if (contactList.size() == 0 ) {
            contactList = repository.getContactList();
        }
        return contactList;
    }

    public void guardaContactos(){

        //repository.guardaContactos(contactList);//falla algo en esta lógica

        for (Contact c: contactList) {
            if (c.isChecked()){
                repository.saveFriend(new Friend(c.getName(), "01/06/2000", Integer.valueOf(c.getPhone())));
            }
        }
    }

    public void updateFriend(Friend friend){
        repository.update(friend);
    }

    public void deleteFriend(Friend friend) {
        repository.delete(friend);
    }

}