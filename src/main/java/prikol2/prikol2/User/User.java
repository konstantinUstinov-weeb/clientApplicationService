package prikol2.prikol2.User;


import jakarta.persistence.*;
import prikol2.prikol2.Application.models.Application;

import java.util.List;


@Entity
@Table(name = "users")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String userName;
    @Column
    private String login;
    @Column
    private String password;


    @OneToMany(mappedBy = "user")
    private List<Application> applications;

    public User() {}

    public Long getId() {return id;}

    public String getUserName() {return userName;}

    public void setUserName(String userName) {this.userName = userName;}

    public String getLogin() {return login;}

    public void setLogin(String login) {this.login = login;}

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}
}
