/**
 * Sample Skeleton for 'Game.fxml' Controller Class
 */

package it.polito.tdp;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;

public class GameController {
	private int numeroInserito;
	private int numeroSegreto;
	private int difficoltà;
	private int totTentativi;
	private int numeroTentativo;
	private int margine=10;
	private boolean inGame=false;
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="cmbDifficoltà"
    private ComboBox<Integer> cmbDifficoltà; // Value injected by FXMLLoader

    @FXML // fx:id="btnGioca"
    private Button btnGioca; // Value injected by FXMLLoader

    @FXML // fx:id="txtNumeroInserito"
    private TextField txtNumeroInserito; // Value injected by FXMLLoader

    @FXML // fx:id="btnProva"
    private Button btnProva; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private Label txtResult; // Value injected by FXMLLoader

    @FXML // fx:id="txtTentativo"
    private Label txtTentativo; // Value injected by FXMLLoader

    @FXML // fx:id="pgrBar"
    private ProgressBar pgrBar; // Value injected by FXMLLoader

    @FXML
    void doGioca(ActionEvent event) {
    	if(inGame) {
    		inGame=false;
    		btnGioca.setText("Gioca");
    		makeGuiVisible(false);
    		cmbDifficoltà.setDisable(false);
    	}
    	else {
    		inGame=true;
    		makeGuiVisible(true);
    		btnGioca.setText("Abbandona");
    		makeGuiVisible(true);
    		cmbDifficoltà.setDisable(true);
    		txtNumeroInserito.setDisable(false);
    		btnProva.setDisable(false);
    		txtNumeroInserito.setText("");
    		txtResult.setText("inserisci numero");

    	}
    	if(cmbDifficoltà.getValue()==null) {
    		return;
    	}
    	difficoltà=cmbDifficoltà.getValue();
    	numeroSegreto=(int)(Math.random()*difficoltà+1);
    	totTentativi=(int)(Math.log(difficoltà)/Math.log(2.0))+margine;
    	numeroTentativo=0;
    	txtTentativo.setText(String.format("tentativo: 0/%d",totTentativi));
    	pgrBar.setProgress((double)numeroTentativo/totTentativi);
    }

    private void makeGuiVisible(boolean visible) {
		// TODO Auto-generated method stub
		txtNumeroInserito.setVisible(visible);
		btnProva.setVisible(visible);
		txtResult.setVisible(visible);
		txtTentativo.setVisible(visible);
		pgrBar.setVisible(visible);
		
	}

	@FXML
    void doProva(ActionEvent event) {
    	try {
    	numeroInserito=Integer.parseInt(txtNumeroInserito.getText());
    	} catch(NumberFormatException e){
    		txtResult.setText("Inserisci un numero");
    		return;
    	}
    	numeroTentativo++;
    	txtTentativo.setText(String.format("tentativo: %d/%d", numeroTentativo,totTentativi));
    	pgrBar.setProgress((double)numeroTentativo/totTentativi);

    	if(numeroTentativo>totTentativi) {
    		txtResult.setText("hai perso! era "+numeroSegreto);
    		    		txtNumeroInserito.setDisable(true);
    		btnProva.setDisable(true);
    		return;
    	}
    	if(numeroInserito==numeroSegreto) {
    		txtResult.setText("hai vinto!");
    		txtNumeroInserito.setDisable(true);
    		btnProva.setDisable(true);
    		return;
    	}
    	if(numeroInserito<numeroSegreto) {
    		txtResult.setText("troppo piccolo");

    		return;
    	}
    	if(numeroInserito>numeroSegreto) {
    		txtResult.setText("troppo grande");
    		return;
    	}
    
    	
    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert cmbDifficoltà != null : "fx:id=\"cmbDifficoltà\" was not injected: check your FXML file 'Game.fxml'.";
        assert btnGioca != null : "fx:id=\"btnGioca\" was not injected: check your FXML file 'Game.fxml'.";
        assert txtNumeroInserito != null : "fx:id=\"txtNumeroInserito\" was not injected: check your FXML file 'Game.fxml'.";
        assert btnProva != null : "fx:id=\"btnProva\" was not injected: check your FXML file 'Game.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Game.fxml'.";
        assert txtTentativo != null : "fx:id=\"txtTentativo\" was not injected: check your FXML file 'Game.fxml'.";
        assert pgrBar != null : "fx:id=\"pgrBar\" was not injected: check your FXML file 'Game.fxml'.";

        cmbDifficoltà.getItems().addAll(10,100,1000);
        if(cmbDifficoltà.getItems().size()>0)
        	cmbDifficoltà.setValue(cmbDifficoltà.getItems().get(0));
        
    }
}
