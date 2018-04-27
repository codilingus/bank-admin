package com.example.bank.validation;

import com.example.bank.user.User;
import org.springframework.stereotype.Component;

import javax.jws.soap.SOAPBinding;


public class UserValidator {

    public static Boolean areValuesEmpty(User user) {
        return (user.getName() == null || user.getName().isEmpty()) ||
                ((user.getSurname() == null) || user.getSurname().isEmpty()) ||
                (user.getPesel() == null);
    }

    public static Boolean peselCounts11Numbers(User user) {
        return ((user.getPesel().toString().length() > 11)
                || (user.getPesel().toString().length() < 11));
    }
}
