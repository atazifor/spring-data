package spring.start.here.springdata.service;

import org.springframework.stereotype.Service;
import spring.start.here.springdata.exceptions.AccountNotFoundException;
import spring.start.here.springdata.model.Account;
import spring.start.here.springdata.repository.AccountRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransferService {
    private final AccountRepository accountRepository;

    public TransferService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void transferMoney(long senderId, long receiverId, BigDecimal amount) {
        Account senderAccount = accountRepository.findById(senderId).orElseThrow(() -> new AccountNotFoundException());
        Account receiverAccount = accountRepository.findById(receiverId).orElseThrow(() -> new AccountNotFoundException());

        BigDecimal senderNewAmount = senderAccount.getAmount().subtract(amount);
        BigDecimal receiverNewAmount = receiverAccount.getAmount().add(amount);

        accountRepository.changeAmount(senderId, senderNewAmount);
        accountRepository.changeAmount(receiverId, receiverNewAmount);
    }

    public Iterable<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public List<Account> findAccountsByName(String name) {
        return accountRepository.findAccountsByName(name);
    }

}
