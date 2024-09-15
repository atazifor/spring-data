package spring.start.here.springdata.service;

import org.junit.jupiter.api.Test;
import spring.start.here.springdata.model.Account;
import spring.start.here.springdata.repository.AccountRepository;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class TransferServiceTest {

    @Test
    void transferMoneyHappyFlow() {
        AccountRepository accountRepository = mock(AccountRepository.class);
        TransferService transferService = new TransferService(accountRepository);

        //assumptions
        Account sender = new Account();
        sender.setId(1l);
        sender.setName("Sender");
        sender.setAmount(new BigDecimal(100));

        Account receiver = new Account();
        receiver.setId(2l);
        receiver.setName("Receiver");
        receiver.setAmount(new BigDecimal(200));

        given(accountRepository.findById(1l)).willReturn(Optional.of(sender));
        given(accountRepository.findById(2l)).willReturn(Optional.of(receiver));

        //call/execution
        transferService.transferMoney(sender.getId(), receiver.getId(), new BigDecimal(50));

        //validations
        verify(accountRepository).changeAmount(1l, new BigDecimal(50));
        verify(accountRepository).changeAmount(2l, new BigDecimal(250));
    }
}