package account;

import abstracts.BalanceClose;
import abstracts.BalanceInfo;
import abstracts.Replenishment;
import abstracts.WriteOff;

import java.math.BigDecimal;

public class AccountOperator implements Replenishment, WriteOff, BalanceInfo, BalanceClose {

    BankAccount bankAccount;

    public AccountOperator(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    @Override
    public void replenish(BigDecimal replenishAmount) {
        BigDecimal currentSum = bankAccount.getBalance();
        bankAccount.setBalance(currentSum.add(replenishAmount));
    }

    @Override
    public void writeOff(BigDecimal writeOffAmount) {
        BigDecimal currentSum = bankAccount.getBalance();
        bankAccount.setBalance(currentSum.subtract(writeOffAmount));
    }

    @Override
    public BigDecimal getBalance() {
        return bankAccount.getBalance();
    }

    @Override
    public void closeBalance() {
        //TODO some implementation
        bankAccount.setBalance(BigDecimal.valueOf(0));
    }

}
