package products;

import abstracts.BalanceInfo;
import account.AccountOperator;

import java.math.BigDecimal;

public abstract class AbstractBaseCard implements BalanceInfo {

    AccountOperator accountOperator;
    String name;

    AbstractBaseCard(AccountOperator accountOperator, String name) {
        this.accountOperator = accountOperator;
        this.name = name;
    }

    @Override
    public BigDecimal getBalance() {
        //return BigDecimal.valueOf(2);
        return accountOperator.getBalance();
    }

}
