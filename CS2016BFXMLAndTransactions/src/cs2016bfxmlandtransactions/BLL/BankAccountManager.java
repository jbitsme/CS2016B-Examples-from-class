/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs2016bfxmlandtransactions.BLL;

import cs2016bfxmlandtransactions.BE.BankAccount;
import cs2016bfxmlandtransactions.DAL.DALFacade;
import java.util.List;

/**
 *
 * @author jeppjleemoritzled
 */
public class BankAccountManager
{
    private DALFacade dalFacade = new DALFacade();
    
    public List<BankAccount> getAllAccounts()
    {
        return dalFacade.getAllAccounts();
    }
    
    public void addAccount(BankAccount account)
    {
        dalFacade.addAccount(account);
    }
    
    public void removeAccount(BankAccount account)
    {
        dalFacade.removeAccount(account);
    }
    
    public void deposit(BankAccount account, Float amount)
    {
        dalFacade.deposit(account, amount);
    }
    
    public void withdraw(BankAccount account, Float amount)
    {
        dalFacade.withdraw(account, (amount));
    }
    
    public void transfer(BankAccount fromAccount, 
            BankAccount toAccount, Float amount)
    {
        dalFacade.transfer(fromAccount, toAccount, amount);
    }
}
