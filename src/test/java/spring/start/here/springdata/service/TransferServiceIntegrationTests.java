package spring.start.here.springdata.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import spring.start.here.springdata.model.Account;
import spring.start.here.springdata.repository.AccountRepository;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TransferServiceIntegrationTests {
    @MockBean
    private AccountRepository accountRepository;

    @Autowired
    private TransferService transferService;

    @Test
    public void transferServeTransferAmountTest() {

        Account sender = new Account();
                sender.setId(1);
                sender.setName("sender");
                sender.setAmount(new BigDecimal(800));

        Account receiver = new Account();
        receiver.setId(3);
        receiver.setAmount(new BigDecimal(2300));
        receiver.setName("receiver");

        when(accountRepository.findById(1l)).thenReturn(Optional.of(sender));
        when(accountRepository.findById(3l)).thenReturn(Optional.of(receiver));

        transferService.transferMoney(sender.getId(), receiver.getId(), new BigDecimal(500));

        verify(accountRepository).changeAmount(1, new BigDecimal(300));
        verify(accountRepository).changeAmount(3, new BigDecimal(2800));

    }
}
