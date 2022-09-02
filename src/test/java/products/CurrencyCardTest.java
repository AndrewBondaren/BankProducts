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

class CurrencyCardTest {

    BankAccount bankAccount;
    AccountOperator accountOperator;
    String cardName;

    @BeforeEach
    void prepareTestInfo(){
        this.bankAccount = new BankAccount(BigDecimal.valueOf(Faker.instance().number().randomDouble(2, 20000, 4000000)), Currency.RUB.name());
        this.cardName = Faker.instance().funnyName().name();
        this.accountOperator = new AccountOperator(this.bankAccount);
    }

    @Test
    void getBalance() {
        var debitCard = new DebitCard(accountOperator, cardName);

        assertThat(debitCard.getBalance(), Matchers.comparesEqualTo(accountOperator.getBalance()));
    }

    @Test
    void nameCheck() {
        var debitCard = new DebitCard(accountOperator, cardName);

        assertEquals(cardName, debitCard.name);
    }

    @Test
    void writeOff() {
        var debitCard = new DebitCard(accountOperator, cardName);
        var currentBalance = debitCard.getBalance();
        var sumToWriteOff = BigDecimal.valueOf(555);

        var actualResult = currentBalance.subtract(sumToWriteOff);
        debitCard.writeOff(sumToWriteOff);

        assertThat(actualResult, Matchers.comparesEqualTo(accountOperator.getBalance()));
    }

    @Test
    void replenish() {
        var debitCard = new DebitCard(accountOperator, cardName);
        var sumToReplenish = BigDecimal.valueOf(9235);
        var currentBalance = debitCard.getBalance();

        var actualResult = currentBalance.add(sumToReplenish);
        debitCard.replenish(sumToReplenish);

        assertThat(actualResult, Matchers.comparesEqualTo(accountOperator.getBalance()));
    }


}