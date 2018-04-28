package com.example.bank.controllers;

import com.example.bank.account.Account;
import com.example.bank.account.Payment;
import com.example.bank.repositories.AccountRepository;
import com.example.bank.repositories.UserRepository;
import com.example.bank.user.User;
import com.example.bank.user.UserBasic;
import com.example.bank.validation.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserValidator userValidator;
    @Autowired
    private AccountRepository accountRepository;


    @GetMapping("/user")
    public List<UserBasic> getAllUserBasic() {

        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .map(user -> user.getUserBasic())
                .collect(Collectors.toList());
    }

    @GetMapping("/user/{id}")
    public User getUserDetails(@PathVariable int id) {
        return userRepository.findById(id).orElse(null);
    }

    @GetMapping("/user/{userId}/account/{accountId}")
    public ResponseEntity getAccountDetails(@PathVariable Integer userId, @PathVariable Integer accountId) {
        Optional<Account> findAccount = findUserAccount(userId, accountId);
        if (findAccount.isPresent()) {
            return new ResponseEntity(findAccount.get(), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/user/{id}/account")
    public void addUsersAccount(@PathVariable int id, @RequestBody Account account) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            user.get().addAccount(account);
            account.setCreationDate(LocalDate.now());
            userRepository.save(user.get());
        }
    }

    @PostMapping("/user")
    public ResponseEntity addUser(@RequestBody User user) {
        if (!userValidator.validate(user)) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } else {
            userRepository.save(user);
            return new ResponseEntity(HttpStatus.OK);
        }
    }

    @PutMapping("/user/{id}")
    public ResponseEntity updateUser(@RequestBody User user, @PathVariable int id) {
        if (!userValidator.validate(user)) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } else {
            user.setUserId(id);
            userRepository.save(user);
            return new ResponseEntity(HttpStatus.OK);
        }
    }

    @PutMapping("/user/{userId}/account/{accountId}/payment")
    public void payment(@RequestBody Payment payment, @PathVariable int userId, @PathVariable int accountId) {
        Optional<Account> findAccount = findUserAccount(userId, accountId);
        if (findAccount.isPresent()) {
            BigDecimal stringToBigDecimal = new BigDecimal(payment.getAmount());
            findAccount.get().add(stringToBigDecimal);
            accountRepository.save(findAccount.get());
        }
    }

    @PutMapping("/user/{userId}/account/{accountId}/payOff")
    public void payOff(@RequestBody Payment payment, @PathVariable int userId, @PathVariable int accountId) {
        Optional<Account> findAccount = findUserAccount(userId, accountId);
        if (findAccount.isPresent()) {
            BigDecimal stringToBigDecimal = new BigDecimal(payment.getAmount());
            findAccount.get().substract(stringToBigDecimal);
            accountRepository.save(findAccount.get());
        }
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
    }

    private Optional<Account> findUserAccount(Integer userId, Integer accountId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return user.get().getUsersAccount().stream()
                    .filter(account -> account.getAccountId() == accountId)
                    .findFirst();
        }
        return Optional.empty();
    }
}
