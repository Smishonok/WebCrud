package org.valentinenikolaev.webcrud.models;

import javax.persistence.Entity;

@Entity
public class Account {


    private Long id;
    private User user;
    private String login;
    private String passwordToken;
    private AccountStatus status;



}
