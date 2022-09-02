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

class CreditCardTest {

    BankAccount bankAccount;
    AccountOperator accountOperator;
    String cardName;
    double debt;
    double percent;

    CreditCard creditCard;

    @BeforeEach
    void prepareTestInfo() {
        this.bankAccount = new BankAccount(BigDecimal.valueOf(Faker.instance().number().randomDouble(2, 20000, 4000000)), Currency.RUB.name());
        this.cardName = Faker.instance().funnyName().name();
        this.accountOperator = new AccountOperator(this.bankAccount);
        this.debt = Faker.instance().number().randomDouble(2, 3000, 40000);
        this.percent = Faker.instance().number().randomDouble(0, 1, 10);

        this.creditCard = new CreditCard(this.accountOperator, this.cardName, this.debt, this.percent);
    }

    @Test
    void getBalance() {
        assertThat(creditCard.getBalance(), Matchers.comparesEqualTo(accountOperator.getBalance()));
    }

    @Test
    void nameCheck() {
        assertEquals(cardName, creditCard.name);
    }

    @Test
    void writeOff() {
        var currentBalance = creditCard.getBalance();
        var sumToWriteOff = BigDecimal.valueOf(555);

        var actualResult = currentBalance.subtract(sumToWriteOff);
        creditCard.writeOff(sumToWriteOff);

        assertThat(actualResult, Matchers.comparesEqualTo(accountOperator.getBalance()));
    }

    @Test
    void replenish() {
        var sumToReplenish = BigDecimal.valueOf(9235);
        var currentBalance = creditCard.getBalance();

        var actualResult = currentBalance.add(sumToReplenish);
        creditCard.replenish(sumToReplenish);

        assertThat(actualResult, Matchers.comparesEqualTo(accountOperator.getBalance()));
    }

    @Test
    void debtRequest() {
        assertEquals(debt, creditCard.debtRequest());
    }

}