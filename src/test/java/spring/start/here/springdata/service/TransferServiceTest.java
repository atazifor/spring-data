package spring.start.here.springdata.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import spring.start.here.springdata.model.Account;
import spring.start.here.springdata.repository.AccountRepository;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class) //allow use of @Mock and @InjectMocks
class TransferServiceTest {
    @Mock //framework create's mock object and inject to this annotated field
    private AccountRepository accountRepository;

    @InjectMocks //create tested object (real instance) and inject all mocks created with @Mock
    private TransferService transferService;

    @Test
    void transferMoneyHappyFlow() {
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