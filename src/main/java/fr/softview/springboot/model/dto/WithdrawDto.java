package fr.softview.springboot.model.dto;

/**
 * Created by sambakamara on 19/11/2017.
 */
public class WithdrawDto {

    private  String accountNumber;
    private int amount;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
