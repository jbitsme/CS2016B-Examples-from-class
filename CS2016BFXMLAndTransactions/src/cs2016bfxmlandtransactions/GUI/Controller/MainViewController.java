/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs2016bfxmlandtransactions.GUI.Controller;

import cs2016bfxmlandtransactions.BE.BankAccount;
import cs2016bfxmlandtransactions.GUI.Model.BankAccountsModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author jeppjleemoritzled
 */
public class MainViewController implements Initializable
{
    
    
    private final BankAccountsModel aModel = new BankAccountsModel();
    @FXML
    private TableColumn<BankAccount, Integer> columnAccountNumber;
    @FXML
    private TableColumn<BankAccount, Float> columnBalance;
    @FXML
    private TableView<BankAccount> tableViewAccounts;
    @FXML
    private Label lblAccountNumber;
    @FXML
    private TextField editDepositAmount;
    @FXML
    private TextField editWithdrawAmount;
    @FXML
    private TextField editAmount;
    @FXML
    private Label lblFrom;
    @FXML
    private Label lblTo;
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        columnAccountNumber.setCellValueFactory(
                cellData->cellData.getValue(
                    ).accountNumberProperty().asObject());
        
        columnBalance.setCellValueFactory(
                cellData->cellData.getValue(
                    ).balanceProperty().asObject());
        
        lblFrom.textProperty().bind(
                aModel.getFromAccountProperty());
        
        lblTo.textProperty().bind(
                aModel.getToAccountProperty());
        
        tableViewAccounts.setItems(aModel.getAllAccounts());
    }    

    @FXML
    private void btnDepositClick(ActionEvent event)
    {
       float amount = Float.parseFloat(editDepositAmount.getText());
       BankAccount account = tableViewAccounts.getSelectionModel().getSelectedItem();
       aModel.deposit(account, amount);
    }

    @FXML
    private void btnWithdrawClick(ActionEvent event)
    {
       float amount = Float.parseFloat(editWithdrawAmount.getText());
       BankAccount account = tableViewAccounts.getSelectionModel().getSelectedItem();
       aModel.withdraw(account, amount);
    }

    @FXML
    private void btnTransferClick(ActionEvent event)
    {
        float amount = Float.parseFloat(editAmount.getText());
        aModel.transfer(amount);
    }

    @FXML
    private void tableViewClick(MouseEvent event)
    {
        if (event.getClickCount() == 2)
        {
            if(event.isAltDown())
                aModel.setToAccount(
                        tableViewAccounts.getSelectionModel().getSelectedItem());
            else
                aModel.setFromAccount(
                    tableViewAccounts.getSelectionModel().getSelectedItem()); 
        }
    }
    
}
