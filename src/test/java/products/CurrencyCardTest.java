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

    CurrencyCard currencyCard;

    @BeforeEach
    void prepareTestInfo() {
        this.bankAccount = new BankAccount(BigDecimal.valueOf(Faker.instance().number().randomDouble(2, 20000, 4000000)), Currency.USD.name());
        this.cardName = Faker.instance().funnyName().name();
        this.accountOperator = new AccountOperator(this.bankAccount);

        this.currencyCard = new CurrencyCard(this.accountOperator, this.cardName);
    }

    @Test
    void getBalance() {
        assertThat(currencyCard.getBalance(), Matchers.comparesEqualTo(accountOperator.getBalance()));
    }

    @Test
    void nameCheck() {
        assertEquals(cardName, currencyCard.name);
    }

    @Test
    void writeOff() {
        var currentBalance = currencyCard.getBalance();
        var sumToWriteOff = BigDecimal.valueOf(777);

        var actualResult = currentBalance.subtract(sumToWriteOff);
        currencyCard.writeOff(sumToWriteOff);

        assertThat(actualResult, Matchers.comparesEqualTo(accountOperator.getBalance()));
    }

    @Test
    void replenish() {
        var sumToReplenish = BigDecimal.valueOf(2235.55);
        var currentBalance = currencyCard.getBalance();

        var actualResult = currentBalance.add(sumToReplenish);
        currencyCard.replenish(sumToReplenish);

        assertThat(actualResult, Matchers.comparesEqualTo(accountOperator.getBalance()));
    }


}