package spring.start.here.springdata.controller;

import org.springframework.web.bind.annotation.*;
import spring.start.here.springdata.dto.TransferRequest;
import spring.start.here.springdata.model.Account;
import spring.start.here.springdata.service.TransferService;

@RestController
public class AccountController {
    private final TransferService transferService;

    public AccountController(TransferService transferService) {
        this.transferService = transferService;
    }
    @PostMapping("/transfer")
    public void transferMoney(@RequestBody TransferRequest request) {
        transferService.transferMoney(request.getSenderAccountId(), request.getReceiverAccountId(), request.getAmount());
    }

    @GetMapping("/accounts")
    public Iterable<Account> getAllAccounts(@RequestParam(required = false) String name) {
        if(name == null)
            return transferService.getAllAccounts();
        else
            return transferService.findAccountsByName(name);
    }
}
