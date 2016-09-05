package cysewska.com.entities;


import javax.persistence.*;

/**
 * Created by Ola on 2016-08-04.
 */

@Entity
@Table(name = "USER")
public class UsersEntity {
    @Id
   /* @GeneratedValue(strategy = GenerationType.TABLE)*/
    @Column(name = "USER_ID")
    int id;
    @Column(name = "USERNAME")
    String username;
    @Column(name = "PASSWORD")
    String password;
public UsersEntity(int id, String username, String password)
{
    this.id=id;
    this.username=username;
    this.password=password;
}

    public UsersEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
