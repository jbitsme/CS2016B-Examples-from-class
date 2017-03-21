/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs2016bfxmlandtransactions.DAL;

import cs2016bfxmlandtransactions.BE.BankAccount;
import java.util.List;

/**
 *
 * @author jeppjleemoritzled
 */
public class DALFacade
{
    private DBAccountManagerTransactions dbTransManager = 
            new DBAccountManagerTransactions();
    
    private DBAccountManagerDirect dbDirectManager = 
            new DBAccountManagerDirect();
    
    public List<BankAccount> getAllAccounts()
    {
        return dbDirectManager.getAllAccount();
    }
    
    public void addAccount(BankAccount account)
    {
        dbDirectManager.addAccount(account);
    }
    
    public void removeAccount(BankAccount account)
    {
        dbDirectManager.removeAccount(account);
    }
    
    public void deposit(BankAccount account, Float amount)
    {
        addToBalance(account, amount);
    }
    
    public void withdraw(BankAccount account, Float amount)
    {
        addToBalance(account, (-1*amount));
    }
    
    private void addToBalance(BankAccount account, Float amount)
    {
        dbTransManager.startTransaction();
        float initialAmount = 
                dbTransManager.getBankAccount(
                        account.getAccountNumber()).getBalance();
        float newBalance = initialAmount + amount;
        account.setBalance(newBalance);
        dbTransManager.updateBankAccount(account);
        
        dbTransManager.commitTransaction();
    }
    
    public void transfer(BankAccount fromAccount, 
            BankAccount toAccount, Float amount)
    {
        dbTransManager.startTransaction();
        
        float fromBalance = 
                dbTransManager.getBankAccount(
                        fromAccount.getAccountNumber()).getBalance();
        fromAccount.setBalance(fromBalance - amount);
        dbTransManager.updateBankAccount(fromAccount);
        
        float toBalance = 
                dbTransManager.getBankAccount(
                        toAccount.getAccountNumber()).getBalance();
        toAccount.setBalance(toBalance + amount);
        dbTransManager.updateBankAccount(toAccount);
        
        dbTransManager.commitTransaction();
    }
}
