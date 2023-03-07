package contacts;



import java.time.LocalDateTime;
import java.util.Objects;
import contacts_1.logic.Validate;


public abstract class Contact { // to abstract class
    private String name;
    private String phone;

    private LocalDateTime createTime;
    private LocalDateTime editTime;

    public Contact() {
        this.setCreateTime(LocalDateTime.now());
        this.setEditTime(LocalDateTime.now());
    }

    public Contact(String name, String phone) {
        this();
        setName(name);
        setPhone(phone);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = Validate.formatName(name);
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = LocalDateTime.now().withSecond(0).withNano(0);
    }

    public LocalDateTime getEditTime() {
        return editTime;
    }
    public void setEditTime(LocalDateTime editTime) {
        this.editTime = LocalDateTime.now().withSecond(0).withNano(0);
    }

    public abstract String getFullName();

    @Override
    public String toString() {
        return "Number: " + this.phone
                + "\nTime created: " + this.createTime
                + "\nTime last edit: " + this.editTime;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contact compareContact)) return false;
        return this.name.equals(compareContact.getName()) &&
                this.name.equals(compareContact.getPhone());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phone);
    }
}
