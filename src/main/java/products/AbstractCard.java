package products;

import abstracts.Replenishment;
import abstracts.WriteOff;
import account.AccountOperator;

import java.math.BigDecimal;

public abstract class AbstractCard extends AbstractBaseCard implements Replenishment, WriteOff {

    AbstractCard(AccountOperator accountOperator, String name) {
        super(accountOperator, name);
    }

    @Override
    public void replenish(BigDecimal replenish) {
        super.accountOperator.replenish(replenish);
    }

    @Override
    public void writeOff(BigDecimal amount) {
        super.accountOperator.writeOff(amount);
    }

}
