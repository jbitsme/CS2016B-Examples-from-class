/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs2016bfxmlandtransactions.GUI.Model;

import cs2016bfxmlandtransactions.BE.BankAccount;
import cs2016bfxmlandtransactions.BLL.BankAccountManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author jeppjleemoritzled
 */
public class BankAccountsModel
{
    private final ObservableList<BankAccount> accounts = 
            FXCollections.observableArrayList();
    
    private BankAccount fromAccount;
    private BankAccount toAccount;
    
    private final SimpleStringProperty fromAccountString = 
            new SimpleStringProperty("");
    
    private final SimpleStringProperty toAccountString = 
            new SimpleStringProperty("");
    
    BankAccountManager bManager = new BankAccountManager();
    
    public SimpleStringProperty getToAccountProperty()
    {
        return toAccountString;
    }
    public SimpleStringProperty getFromAccountProperty()
    {
        return fromAccountString;
    }
    public BankAccount getFromAccount()
    {
        return fromAccount;
    }
    
    public void setFromAccount(BankAccount account)
    {
        fromAccount = account;
        fromAccountString.set(fromAccount.getAccountNumber() +
                " (" + fromAccount.getBalance() + ")");
    }

    public BankAccount getToAccount()
    {
        return toAccount;
    }
    
    public void setToAccount(BankAccount account)
    {
        toAccount=account;
        toAccountString.set(toAccount.getAccountNumber() +
                " (" + toAccount.getBalance() + ")");
    }
    
    public void addAccount(BankAccount account)
    {
        bManager.addAccount(account);
        reloadAccounts();
    }
    
    public void removeAccount(BankAccount account)
    {
        bManager.removeAccount(account);
        reloadAccounts();
    }
    
    private void reloadAccounts()
    {
        accounts.clear();
        accounts.addAll(
            FXCollections.observableArrayList(bManager.getAllAccounts()));
    }
    
    public ObservableList<BankAccount> getAllAccounts()
    {
        reloadAccounts();
        return accounts;
    }
    
    public void deposit(BankAccount account, Float amount)
    {
        bManager.deposit(account, amount);
    }
    
    public void withdraw(BankAccount account, Float amount)
    {
        bManager.withdraw(account, amount);
    }
    
    public void transfer(Float amount)
    {
        bManager.transfer(fromAccount, toAccount, amount);
    }
}
