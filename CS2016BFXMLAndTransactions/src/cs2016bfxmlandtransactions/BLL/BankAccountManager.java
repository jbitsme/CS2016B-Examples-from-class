/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs2016bfxmlandtransactions.BLL;

import cs2016bfxmlandtransactions.BE.BankAccount;
import cs2016bfxmlandtransactions.DAL.BankAccountDBManager;

/**
 *
 * @author jeppjleemoritzled
 */
public class BankAccountManager
{
    private BankAccountDBManager dbManager = 
            new BankAccountDBManager();
    
    public void deposit(BankAccount account, Float amount)
    {
    
    }
    
    public void withdraw(BankAccount account, Float amount)
    {
    
    }
    
    public void transfer(BankAccount fromAccount, 
            BankAccount toAccount, Float amount)
    {
        
    }
}
