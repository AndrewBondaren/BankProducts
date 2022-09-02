package account;

import java.math.BigDecimal;

public class BankAccount implements Account {

    private BigDecimal balance;
    private String currency;

    public BankAccount(BigDecimal balance, String currency) {
        this.balance = balance;
        this.currency = currency;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

}
