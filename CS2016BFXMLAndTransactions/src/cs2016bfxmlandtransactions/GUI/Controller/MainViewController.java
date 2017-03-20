/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs2016bfxmlandtransactions.GUI.Controller;

import cs2016bfxmlandtransactions.BE.BankAccount;
import cs2016bfxmlandtransactions.BLL.BankAccountManager;
import cs2016bfxmlandtransactions.GUI.Model.BankAccountsModel;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 *
 * @author jeppjleemoritzled
 */
public class MainViewController implements Initializable
{
    
    
    private BankAccountsModel aModel = new BankAccountsModel();
    @FXML
    private Button button;
    @FXML
    private TableColumn<BankAccount, Integer> columnAccountNumber;
    @FXML
    private TableColumn<BankAccount, Float> columnBalance;
    @FXML
    private TableView<BankAccount> tableViewAccounts;
    
    @FXML
    private void handleButtonAction(ActionEvent event)
    {
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        columnAccountNumber.setCellValueFactory(
                cellData->cellData.getValue(
                    ).accountNumberProperty().asObject());
        
        columnBalance.setCellValueFactory(
                cellData->cellData.getValue(
                    ).balanceProperty().asObject());
        
        tableViewAccounts.setItems(aModel.getAllAccounts());
    }    
    
}
