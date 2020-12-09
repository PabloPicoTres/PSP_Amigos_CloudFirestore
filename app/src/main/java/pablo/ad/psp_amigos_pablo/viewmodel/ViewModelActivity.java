package pablo.ad.psp_amigos_pablo.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import pablo.ad.psp_amigos_pablo.room.Repository;
import pablo.ad.psp_amigos_pablo.room.pojo.Call;
import pablo.ad.psp_amigos_pablo.room.pojo.Friend;
import pablo.ad.psp_amigos_pablo.room.pojo.FriendCallCount;

public class ViewModelActivity extends AndroidViewModel {

    private Repository repository;

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

    //public MutableLiveData<Long> getLiveCarInsertId() {
    //    return repository.getLiveCarInsertId();
    //}

}