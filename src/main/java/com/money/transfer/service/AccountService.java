package com.money.transfer.service;

import com.money.transfer.repo.AccountRepo;
import com.money.transfer.entity.TransferForm;
import com.money.transfer.entity.Account;
import com.money.transfer.exception.AccountNotFoundException;
import com.money.transfer.exception.InsufficientFundException;

import javax.transaction.Transactional;

public class AccountService {
    private AccountRepo accountRepo = new AccountRepo();


    public void transfer(TransferForm form) throws AccountNotFoundException,
            InsufficientFundException {
        Account accountFrom = accountRepo.findAccountByAccountNumber(form.getFromAccountNumber());
        Account accountTo = accountRepo.findAccountByAccountNumber(form.getToAccountNumber());

        if (accountFrom == null || accountTo == null)
            throw new AccountNotFoundException("Account not found");
        else {

            double accountFromBalance = accountFrom.getBalance();
            double amountoBetransfer = form.getAmount();
            if (accountFromBalance >= amountoBetransfer) {
                accountFrom.setBalance(accountFrom.getBalance() - form.getAmount());
                accountRepo.saveAccount(accountFrom);
                accountTo.setBalance(accountTo.getBalance() + form.getAmount());
                accountRepo.saveAccount(accountTo);

            } else if (accountFromBalance < amountoBetransfer)
                throw new InsufficientFundException("Inssufficient Fund");
        }
    }
}
