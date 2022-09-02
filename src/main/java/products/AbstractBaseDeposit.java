package products;

import abstracts.BalanceInfo;
import abstracts.BaseDeposit;
import account.AccountOperator;

import java.math.BigDecimal;

public abstract class AbstractBaseDeposit implements BaseDeposit, BalanceInfo {

    AccountOperator accountOperator;
    String name;

    AbstractBaseDeposit(AccountOperator accountOperator, String name){
        this.accountOperator = accountOperator;
        this.name = name;
    }

    @Override
    public BigDecimal getBalance() {
        return accountOperator.getBalance();
    }

    @Override
    public void closeDeposit() {
        accountOperator.closeBalance();
    }


}
