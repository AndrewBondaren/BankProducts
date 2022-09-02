package account;

import abstracts.BalanceClose;
import abstracts.BalanceInfo;
import abstracts.Replenishment;
import abstracts.WriteOff;
import org.apache.log4j.Logger;

import java.math.BigDecimal;

public class AccountOperator implements Replenishment, WriteOff, BalanceInfo, BalanceClose {

    final static Logger logger = Logger.getLogger(AccountOperator.class);

    BankAccount bankAccount;

    public AccountOperator(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    @Override
    public void replenish(BigDecimal replenishAmount) {
        BigDecimal currentSum = bankAccount.getBalance();
        bankAccount.setBalance(currentSum.add(replenishAmount));

        logger.debug(String.format("Balance Replenish operation: %s + %s = %s", currentSum, replenishAmount, bankAccount.getBalance()));
    }

    @Override
    public void writeOff(BigDecimal writeOffAmount) {
        BigDecimal currentSum = bankAccount.getBalance();
        bankAccount.setBalance(currentSum.subtract(writeOffAmount));

        logger.debug(String.format("Balance WriteOff operation: %s - %s = %s", currentSum, writeOffAmount, bankAccount.getBalance()));
    }

    @Override
    public BigDecimal getBalance() {
        logger.debug(String.format("Balance getBalance operation: %s", bankAccount.getBalance()));
        return bankAccount.getBalance();
    }

    @Override
    public void closeBalance() {
        //TODO some implementation
        bankAccount.setBalance(BigDecimal.valueOf(0));
        logger.debug(String.format("Balance close operation: %s", bankAccount.getBalance()));
    }

}
