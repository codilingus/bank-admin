package com.example.bank;

import com.example.bank.user.User;
import com.example.bank.validation.UserValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@RunWith(JUnit4.class)
@SpringBootTest
public class BankApplicationTests {

    private UserValidator userValidator = new UserValidator();

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
}
