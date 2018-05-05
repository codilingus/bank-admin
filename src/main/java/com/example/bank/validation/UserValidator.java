package com.example.bank.validation;

import com.example.bank.user.User;
import org.springframework.stereotype.Component;

import javax.jws.soap.SOAPBinding;
import java.time.LocalDate;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAmount;

@Component
public class UserValidator {

    private Boolean areValuesEmpty(User user) {
        return (user.getName() == null || user.getName().isEmpty()) ||
                ((user.getSurname() == null) || user.getSurname().isEmpty()) ||
                (user.getPesel() == null);
    }

    private Boolean nameConsistsOfCharacters(User user){
        String regex = "[^A-Za-z]+";
        return user.getName().matches(regex) ||
                user.getSurname().matches(regex);
    }

    private Boolean peselCounts11Numbers(User user) {
        return (user.getPesel().toString().length() == 11);
    }

    private Boolean isUserAdult(User user) {
        int year = getInt(user, 0, 2);
        int month = getInt(user, 2, 4);
        int day = getInt(user, 4, 6);

        if (month > 20) {
            year += 2000;
            month -= 20;
        } else {
            year += 1900;
        }

        LocalDate birthDate = LocalDate.of(year, month, day);
        return LocalDate.now().minusYears(18).compareTo(birthDate) >= 0;
    }

    private int getInt(User user, int startIndex, int endIndex) {
        String intFromPesel = user.getPesel().toString().substring(startIndex, endIndex);
        return Integer.parseInt(intFromPesel);
    }

    public Boolean validate(User user) {
        return (!areValuesEmpty(user) && peselCounts11Numbers(user)  && nameConsistsOfCharacters(user));
    }
}
