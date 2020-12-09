package pablo.ad.psp_amigos_pablo.room.pojo;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;


@Entity(tableName = "call",
        foreignKeys = @ForeignKey(
                entity = Friend.class,
                parentColumns = "id",
                childColumns = "idFriend",
                onDelete = ForeignKey.RESTRICT))

public class Call {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "idFriend")
    private long idFriend;

    @NonNull
    @ColumnInfo(name = "callDate")
    private String callDate;

    public Call(long idFriend, @NonNull String callDate) {
        this.idFriend = idFriend;
        this.callDate = callDate;
    }

    public Call(){
        this(0,"");
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdFriend() {
        return idFriend;
    }

    public void setIdFriend(long idFriend) {
        this.idFriend = idFriend;
    }

    @NonNull
    public String getCallDate() {
        return callDate;
    }

    public void setCallDate(@NonNull String callDate) {
        this.callDate = callDate;
    }

    @Override
    public String toString() {
        return "Call{" +
                "id=" + id +
                ", idFriend=" + idFriend +
                ", callDate='" + callDate + '\'' +
                '}';
    }
}
