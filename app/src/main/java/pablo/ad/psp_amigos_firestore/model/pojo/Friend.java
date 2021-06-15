package pablo.ad.psp_amigos_firestore.model.pojo;


public class Friend {

    /*
    *
    *  Nombre, Fecha de nacimiento (nullable), Teléfono
    *
    * */

    private String name;

    private String birthdate;

    private int number;

    public Friend(String name, String birthdate, int number) {
        this.name = name;
        this.birthdate = birthdate;
        this.number = number;
    }

    public Friend() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Friend{" +
                ", name='" + name + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
