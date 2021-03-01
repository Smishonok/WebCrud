package org.valentinenikolaev.webcrud.models;

import javax.persistence.*;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "login")
    private String login;

    @Column(name = "passwordToken")
    private String passwordToken;

    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    public Account() {
    }

    public Long getId() {
        return id;
    }

    public Account setId(Long id) {
        this.id = id;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Account setUser(User user) {
        this.user = user;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public Account setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getPasswordToken() {
        return passwordToken;
    }

    public Account setPasswordToken(String passwordToken) {
        this.passwordToken = passwordToken;
        return this;

    }

    public AccountStatus getStatus() {
        return status;
    }

    public Account setStatus(AccountStatus status) {
        this.status = status;
        return this;
    }
}
