/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs2016bfxmlandtransactions.BLL;

import cs2016bfxmlandtransactions.BE.BankAccount;
import cs2016bfxmlandtransactions.DAL.BankAccountDBManager;
import java.util.List;

/**
 *
 * @author jeppjleemoritzled
 */
public class BankAccountManager
{
    private BankAccountDBManager dbManager = 
            new BankAccountDBManager();
    
    private void addToBalance(BankAccount account, Float amount)
    {
        float initialAmount = 
                dbManager.getBankAccount(
                        account.getAccountNumber()).getBalance();
        float newBalance = initialAmount + amount;
        account.setBalance(newBalance);
        dbManager.updateBankAccount(account);
    }
    
    public List<BankAccount> getAllAccounts()
    {
        return dbManager.getAllAccount();
    }
    
    public void deposit(BankAccount account, Float amount)
    {
        addToBalance(account, amount);
    }
    
    public void withdraw(BankAccount account, Float amount)
    {
        addToBalance(account, (-1*amount));
    }
    
    public void transfer(BankAccount fromAccount, 
            BankAccount toAccount, Float amount)
    {
        withdraw(fromAccount, amount);
        deposit(toAccount, amount);
    }
}
