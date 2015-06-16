package photoblog.entity.users;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Diana
 */

@Entity
@Table(name = "USER", uniqueConstraints = {@UniqueConstraint(columnNames={"id", "login"})})
public class User implements Serializable {

    @Id @GeneratedValue
    @Column(name="ID")
    private int id;
    @Column(name="NAME")
    private String name;
    @Column(name="SURNAME")
    private String surname;
    @Column(name="EMAIL")
    private String email;
    @Column(name="PASSWORD")
    private String pasword;
    @Column(name="LOGIN")
    private String login;
    
    public User(){
    }

    public User(String name, String surname, String email, String pasword, String login) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.pasword = pasword;
        this.login = login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasword() {
        return pasword;
    }

    public void setPasword(String pasword) {
        this.pasword = pasword;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    
    
    
}
