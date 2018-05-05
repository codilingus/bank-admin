package com.example.bank.controllers;

import com.example.bank.account.Account;
import com.example.bank.account.Payment;
import com.example.bank.account.Transfer;
import com.example.bank.paymentOrders.PaymentOrders;
import com.example.bank.repositories.AccountRepository;
import com.example.bank.repositories.PaymentOrdersRepository;
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
    @Autowired
    private PaymentOrdersRepository paymentOrdersRepository;


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
        return findAccount
                .map(account -> new ResponseEntity(account, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity(HttpStatus.BAD_REQUEST));
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

    @PostMapping("/user/{userId}/account/{accountId}/payment")
    public void payment(@RequestBody Payment payment, @PathVariable int userId, @PathVariable int accountId) {
        Optional<Account> findAccount = findUserAccount(userId, accountId);
        if (findAccount.isPresent()) {
            BigDecimal stringToBigDecimal = new BigDecimal(payment.getAmount());
            findAccount.get().add(stringToBigDecimal);
            accountRepository.save(findAccount.get());
        }
    }

    @PostMapping("/user/{userId}/account/{accountId}/payOff")
    public void payOff(@RequestBody Payment payment, @PathVariable int userId, @PathVariable int accountId) {
        Optional<Account> findAccount = findUserAccount(userId, accountId);
        if (findAccount.isPresent()) {
            BigDecimal stringToBigDecimal = new BigDecimal(payment.getAmount());
            findAccount.get().substract(stringToBigDecimal);
            accountRepository.save(findAccount.get());
        }
    }

    @PostMapping("/transfer")
    public void transfer(@RequestBody Transfer transfer) {
        Optional<Account> transferFrom = accountRepository.findById(transfer.getTransferFrom());
        Optional<Account> transferTo = accountRepository.findById(transfer.getTransferTo());
        if (transferFrom.isPresent() && transferTo.isPresent()) {
            BigDecimal stringToBigDecimal = new BigDecimal(transfer.getTransferAmount());
            transferFrom.get().substract(stringToBigDecimal);
            transferTo.get().add(stringToBigDecimal);
            accountRepository.save(transferFrom.get());
            accountRepository.save(transferTo.get());
        }
    }

    @PostMapping("/payment-orders")
    public ResponseEntity createPaymentOrder(@RequestBody PaymentOrders paymentOrders) {
        Optional<User> userOptional = StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .filter(user -> user.getPesel().equals(paymentOrders.getUserPesel()))
                .findFirst();
        if (userOptional.isPresent()) {
            paymentOrdersRepository.save(paymentOrders);
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
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
