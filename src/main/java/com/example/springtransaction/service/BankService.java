package com.example.springtransaction.service;

import com.example.springtransaction.dto.request.AccountRequest;
import com.example.springtransaction.dto.response.AccountResponse;
import com.example.springtransaction.dto.response.ApiResponse;
import com.example.springtransaction.dto.response.TransferResponse;
import com.example.springtransaction.entity.Account;
import com.example.springtransaction.entity.TransactLog;
import com.example.springtransaction.enums.AccountState;
import com.example.springtransaction.enums.ErrorCode;
import com.example.springtransaction.exception.AccountException;
import com.example.springtransaction.exception.BankException;
import com.example.springtransaction.mapper.AccountMapper;
import com.example.springtransaction.repository.AccountRepository;
import com.example.springtransaction.repository.TransactLogRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BankService {
    @Autowired
    private AccountRepository accountRepo;

    @Autowired
    private TransactLogRepository transactLogRepo;

    @Autowired
    private LoggingService loggingService;

    @Autowired
    AccountMapper accountMapper;
    @Transactional(rollbackOn = { Exception.class })
    public void generateSampleData() {
        Account johnAccount = new Account("John", 1000L);
        Account bobAccount = new Account("Bob", 2000L);
        Account aliceAccount = new Account("Alice", 1500L);

        Account tomAccount = new Account("Tom", 100L);
        tomAccount.setState(AccountState.DISABLED);

        accountRepo.save(johnAccount);
        accountRepo.save(bobAccount);
        accountRepo.save(aliceAccount);
        accountRepo.save(tomAccount);
        accountRepo.flush();

    }

    public Account getAccountById(long accountID) throws AccountException {
        Optional<Account> oAccount = accountRepo.findById(accountID);

        if (oAccount.isPresent()) {
            Account account = oAccount.get();
            if (account.getState() == AccountState.DISABLED) {
                throw new AccountException(ErrorCode.ACCOUNT_DISABLED);
            } else {
                return account;
            }
        } else {
            throw new AccountException(ErrorCode.ID_NOT_FOUND);
        }
    }

    @Transactional(rollbackOn = { BankException.class })
    public TransferResponse transfer(long fromAccID, long toAccID, long amount) {
        Account fromAccount;
        Account toAccount;

        try {
            fromAccount = getAccountById(fromAccID);
            toAccount = getAccountById(toAccID);
        } catch (AccountException accountException){
            loggingService.saveLog(fromAccID, toAccID, amount, accountException.getErrorCode(), accountException.getMessage());
            throw new BankException(accountException.getErrorCode(), accountException.getMessage());
        }


        if (fromAccount.getBalance() < amount) {
            String detail = "Account " + fromAccount.getId() + " of " + fromAccount.getOwner() + " does not have enough balance";
            loggingService.saveLog(fromAccID, toAccID, amount, ErrorCode.BALANCE_NOT_ENOUGH, detail);
            throw new BankException(ErrorCode.BALANCE_NOT_ENOUGH,detail);
        }

        fromAccount.setBalance(fromAccount.getBalance() - amount);
    //Thử nghiệm chức năng dontRollbackOn
//    if (true) {
//      throw new DummyException();
//    }
        toAccount.setBalance(toAccount.getBalance() + amount);
        Date transferDate = new Date();
        TransactLog transactLog = new TransactLog(fromAccount, toAccount, amount, transferDate);

        accountRepo.save(fromAccount);
        accountRepo.save(toAccount);
        transactLogRepo.save(transactLog);

        loggingService.saveLog(fromAccID, toAccID, amount, ErrorCode.SUCCESS, ErrorCode.SUCCESS.getMessage());

        return TransferResponse.builder().transferDate(new Date()).build();
    }
    @Transactional()
    public boolean addNewAccount(AccountRequest account) throws AccountException {

        if(accountRepo.existsByOwner(account.getOwner())){
            throw new AccountException(ErrorCode.ACCOUNT_EXIST);
        }
        loggingService.saveLog(999, 999, 999L, ErrorCode.DATABASE_ERROR, ErrorCode.DATABASE_ERROR.getMessage());
        int result = accountRepo.addNewAccount(account.getOwner(),account.getBalance());
        return result > 0;
    }

    public List<AccountResponse> getAccounts(){
        List<Account> acounts = accountRepo.findAll();
         return acounts.stream().map(accountMapper::toAccountResponse).toList();
    }


}
