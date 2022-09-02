package products;

import abstracts.Replenishment;
import account.AccountOperator;

import java.math.BigDecimal;

public abstract class AbstractDeposit extends AbstractBaseDeposit implements Replenishment {


    AbstractDeposit(AccountOperator accountOperator, String name) {
        super(accountOperator, name);
    }

    @Override
    public void replenish(BigDecimal replenish) {
        super.accountOperator.replenish(replenish);
    }
}
