package cysewska.com.models.entities;


import javax.persistence.*;

/**
 * Created by Ola on 2016-08-04.
 */

@Entity
@Table(name = "USER")
public class UsersEntity {
    @Id
 //   @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "USER_ID")
    Long id;
    @Column(name = "USERNAME")
    String username;
    @Column(name = "PASSWORD")
    String password;
    public UsersEntity(Long id, String username, String password)
{
    this.id=id;
    this.username=username;
    this.password=password;
}

    public UsersEntity() {
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "UsersEntity{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public void setId(Long id) {
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
