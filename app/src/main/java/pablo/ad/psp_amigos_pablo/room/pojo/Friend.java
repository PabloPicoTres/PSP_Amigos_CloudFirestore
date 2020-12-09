package pablo.ad.psp_amigos_pablo.room.pojo;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "friend", indices = {@Index(value = {"id"}, unique = true)})
public class Friend {

    /*
    *
    * Id, Nombre, Fecha de nacimiento (nullable), Teléfono
    *
    * */



    @PrimaryKey(autoGenerate = true)
    private long id;

    @NonNull
    @ColumnInfo(name = "name")
    private String name;


    @ColumnInfo(name = "birthdate")
    private String birthdate;

    @NonNull
    @ColumnInfo(name = "number")
    private int number;

    public Friend(@NonNull String name, String birthdate, @NonNull int number) {
        this.name = name;
        this.birthdate = birthdate;
        this.number = number;
    }

    public Friend() {
        this("", "", 0);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    @NonNull
    public int getNumber() {
        return number;
    }

    public void setNumber(@NonNull int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Friend{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
