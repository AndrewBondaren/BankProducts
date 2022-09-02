package products;

import abstracts.Credit;
import account.AccountOperator;

public class CreditCard extends AbstractCard implements Credit {

    double debt;
    double percent;

    CreditCard(AccountOperator accountOperator, String name, double debt, double percent) {
        super(accountOperator, name);
        this.debt = debt;
        this.percent = percent;
    }

    @Override
    public Double debtRequest() {
        return debt;
    }

}
