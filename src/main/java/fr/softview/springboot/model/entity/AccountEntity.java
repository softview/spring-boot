package fr.softview.springboot.model.entity;


import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Column;

/**
 * Created by sambakamara on 19/11/2017.
 */

@Entity
@Table (name = "account")
public class AccountEntity {

    @Id
    @Column (name = "account_number")
    private String number;

    private int balance;


    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
