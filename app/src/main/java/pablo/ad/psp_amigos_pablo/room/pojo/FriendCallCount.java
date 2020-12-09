package pablo.ad.psp_amigos_pablo.room.pojo;

import androidx.room.Embedded;

public class FriendCallCount {

    @Embedded
    private Friend friend;

    private long count;

    public Friend getFriend() {
        return friend;
    }

    public void setFriend(Friend friend) {
        this.friend = friend;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "FriendCallCount{" +
                "friend=" + friend +
                ", count=" + count +
                '}';
    }
}
