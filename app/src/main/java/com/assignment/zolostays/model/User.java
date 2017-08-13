package com.assignment.zolostays.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Unique;

/**
 * Created by Rashmi on 13/08/17.
 */

@Entity(nameInDb = "users")
public class User {

    @Id(autoincrement = true)
    private Long id;

    @Property(nameInDb = "phone_number")
    @Unique
    @NotNull
    private String phoneNumber;

    @Property(nameInDb = "email_id")
    @NotNull
    private String emailId;

    @Property(nameInDb = "name")
    @NotNull
    private String name;

    @Property(nameInDb = "password")
    @NotNull
    private String password;

    @Generated(hash = 177166102)
    public User(Long id, @NotNull String phoneNumber, @NotNull String emailId,
            @NotNull String name, @NotNull String password) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.emailId = emailId;
        this.name = name;
        this.password = password;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
