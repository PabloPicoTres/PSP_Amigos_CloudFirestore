package pablo.ad.psp_amigos_pablo.room.pojo;

public class Contact {

    private String name, phone;
    private boolean checked;

    public Contact(String name, String phone, boolean checked) {
        this.name = name;
        this.phone = phone;
        this.checked = checked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", checked=" + checked +
                '}';
    }
}
