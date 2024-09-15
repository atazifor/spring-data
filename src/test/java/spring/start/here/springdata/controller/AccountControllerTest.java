package spring.start.here.springdata.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import spring.start.here.springdata.service.TransferService;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class AccountControllerTest {
    @Mock
    private TransferService transferService;

    @InjectMocks
    private AccountController accountController;

    @Test
    public void testGetAllAccountsWithNoName() {
        given(transferService.getAllAccounts()).willReturn(List.of());
        //given(transferService.findAccountsByName(anyString())).willReturn(List.of());

        accountController.getAllAccounts(null);

        verify(transferService, never()).findAccountsByName(anyString());
        verify(transferService, atMostOnce()).getAllAccounts();
    }

    @Test
    public void testGetAllAccountsWithName() {
        given(transferService.findAccountsByName(anyString())).willReturn(List.of());
        accountController.getAllAccounts("test");
        verify(transferService, atMostOnce()).findAccountsByName("test");
    }

}