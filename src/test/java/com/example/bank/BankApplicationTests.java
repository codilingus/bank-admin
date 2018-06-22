package com.example.bank;

import com.example.bank.account.Account;
import com.example.bank.account.Transfer;
import com.example.bank.user.User;
import com.example.bank.validation.TransferValidator;
import com.example.bank.validation.UserValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@RunWith(JUnit4.class)
@SpringBootTest
public class BankApplicationTests {

    private UserValidator userValidator = new UserValidator();
    private TransferValidator transferValidator = new TransferValidator();

    @Test
    public void shouldValidWhenUserNameIsEmpty() {
        User user = new User("", "Kowalsky", "98082889887");
        boolean result = userValidator.validate(user);
        assertThat(result).isFalse();
    }

    @Test
    public void shouldValidWhenUserSurnameIsEmpty() {
        User user = new User("Magdalena", "", "98121245789");
        boolean result = userValidator.validate(user);
        assertThat(result).isFalse();
    }

    @Test
    public void shouldValidWhenUserIsNotAdult() {
        User user = new User("Janko", "Janeczko", "05221589653");
        boolean result = userValidator.validate(user);
        assertThat(result).isFalse();
    }

    @Test
    public void shouldValidWhenPeselIsEmpty() {
        User user = new User("Janko", "Janeczko", "");
        boolean result = userValidator.validate(user);
        assertThat(result).isFalse();
    }

    @Test
    public void shouldValidWhenPeselLenghtNotEquals11Numbers() {
        User user = new User("Krzysztof", "EisenBischler", "1234567");
        boolean result = userValidator.validate(user);
        assertThat(result).isFalse();
    }

    @Test
    public void shouldValidWhenNameConsistOfNumber(){
        User user = new User("Jank2", "Janeczko", "05221589653");
        boolean result = userValidator.validate(user);
        assertThat(result).isFalse();
    }

    @Test
    public void shouldValidWhenSurnameConsistOfNumber(){
        User user = new User("Janka", "Janeczko4", "05221589653");
        boolean result = userValidator.validate(user);
        assertThat(result).isFalse();
    }

    @Test
    public void shouldValidWhenThereIsMoney(){
        BigDecimal bd = new BigDecimal("20.0");
        Account account = new Account(1, bd, LocalDate.now());
        boolean result = transferValidator.isThereAnyMoney(account);
        assertThat(result).isTrue();
    }

    @Test
    public void shouldValidWhenThereIsNoMoney(){
        BigDecimal bd = new BigDecimal("0.0");
        Account account = new Account(1, bd, LocalDate.now());
        boolean result = transferValidator.isThereAnyMoney(account);
        assertThat(result).isFalse();
    }
}
