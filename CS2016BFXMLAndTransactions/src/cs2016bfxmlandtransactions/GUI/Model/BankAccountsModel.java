/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs2016bfxmlandtransactions.GUI.Model;

import cs2016bfxmlandtransactions.BE.BankAccount;
import cs2016bfxmlandtransactions.BLL.BankAccountManager;
import cs2016bfxmlandtransactions.Util.BankUserException;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author jeppjleemoritzled
 */
public class BankAccountsModel
{

    private final ObservableList<BankAccount> accounts
            = FXCollections.observableArrayList();

    private BankAccount fromAccount;
    private BankAccount toAccount;

    private final SimpleStringProperty fromAccountString
            = new SimpleStringProperty("");

    private final SimpleStringProperty toAccountString
            = new SimpleStringProperty("");

    BankAccountManager bManager = new BankAccountManager();

    public SimpleStringProperty getToAccountProperty()
    {
        return toAccountString;
    }

    public SimpleStringProperty getFromAccountString()
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
        fromAccountString.set(fromAccount.getAccountNumber()
                + " (" + fromAccount.getBalance() + ")");
    }

    public BankAccount getToAccount()
    {
        return toAccount;
    }

    public void setToAccount(BankAccount account)
    {
        toAccount = account;
        toAccountString.set(toAccount.getAccountNumber()
                + " (" + toAccount.getBalance() + ")");
    }

    public void addAccount(BankAccount account)
    {
        try
        {
            bManager.addAccount(account);
            reloadAccounts();
        }
        catch (BankUserException be)
        {
            handleUserException(be);
        }
    }

    public void removeAccount(BankAccount account)
    {
        try
        {
            bManager.removeAccount(account);
            reloadAccounts();
        }
        catch (BankUserException be)
        {
            handleUserException(be);
        }
    }

    private void reloadAccounts()
    {
        try
        {
            accounts.clear();
            accounts.addAll(
                    FXCollections.observableArrayList(bManager.getAllAccounts()));
        }
        catch (BankUserException be)
        {
            handleUserException(be);
        }
    }

    public ObservableList<BankAccount> getAllAccounts()
    {
            reloadAccounts();
        
        return accounts;
    }

    public void deposit(BankAccount account, Float amount)
    {
        try
        {
            bManager.deposit(account, amount);
        }
        catch (BankUserException be)
        {
            handleUserException(be);
        }
    }

    public void withdraw(BankAccount account, Float amount)
    {
        try
        {
            bManager.withdraw(account, amount);
        }
        catch (BankUserException be)
        {
            handleUserException(be);
        }
    }

    public void transfer(Float amount)
    {
        try
        {
            bManager.transfer(fromAccount, toAccount, amount);
        }
        catch (BankUserException be)
        {
            handleUserException(be);
        }
    }

    private void handleUserException(BankUserException be)
    {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Something went wrong :/");
        alert.setHeaderText("Something went wrong :/");
        alert.setContentText(be.getMessage());
        alert.showAndWait();
    }
}
