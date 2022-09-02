package products;

import account.AccountOperator;
import account.BankAccount;
import account.Currency;
import com.github.javafaker.Faker;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DepositTest {

    BankAccount bankAccount;
    AccountOperator accountOperator;
    String depositName;

    Deposit deposit;

    @BeforeEach
    void prepareTestInfo() {
        this.bankAccount = new BankAccount(BigDecimal.valueOf(Faker.instance().number().randomDouble(2, 20000, 4000000)), Currency.RUB.name());
        this.accountOperator = new AccountOperator(this.bankAccount);
        this.depositName = Faker.instance().funnyName().name();

        this.deposit = new Deposit(accountOperator, depositName);
    }

    @Test
    void getBalance() {
        assertThat(deposit.getBalance(), Matchers.comparesEqualTo(accountOperator.getBalance()));
    }

    @Test
    void replenish() {
        var sumToReplenish = BigDecimal.valueOf(9235);
        var currentBalance = deposit.getBalance();

        var actualResult = currentBalance.add(sumToReplenish);
        deposit.replenish(sumToReplenish);

        assertThat(actualResult, Matchers.comparesEqualTo(accountOperator.getBalance()));
    }

    @Test
    void closeDeposit() {
        deposit.closeDeposit();

        assertThat(BigDecimal.valueOf(0), Matchers.comparesEqualTo(accountOperator.getBalance()));
    }

}