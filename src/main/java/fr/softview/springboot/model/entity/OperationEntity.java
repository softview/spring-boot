package fr.softview.springboot.model.entity;

import javax.persistence.*;


/**
 * Created by sambakamara on 19/11/2017.
 */

@Entity
@Table (name = "operation")
public class OperationEntity {

    @Id
    @Column (name = "operation_id")
    @GeneratedValue (strategy=GenerationType.IDENTITY)
    private int id;

    private  String type;
    private int amount;
    private String date;
    private int balance;

    @Column (name = "account_number")
    private String account;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
