/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs2016bfxmlandtransactions.BE;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author jeppjleemoritzled
 */
public class BankAccount
{
    private final IntegerProperty accountNumber = new SimpleIntegerProperty();
    private final FloatProperty balance = new SimpleFloatProperty();

    public BankAccount(int accountNumber)
    {
        this.accountNumber.set(accountNumber);
        balance.set(0);
    }
    
    public float getBalance()
    {
        return balance.get();
    }

    public void setBalance(float value)
    {
        balance.set(value);
    }

    public FloatProperty balanceProperty()
    {
        return balance;
    }
    
    public int getAccountNumber()
    {
        return accountNumber.get();
    }

    public IntegerProperty accountNumberProperty()
    {
        return accountNumber;
    }
   
    
}
