package it.polito.tdp.poweroutages;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.poweroutages.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class PowerOutagesController{
	
	Model model;
	
	public PowerOutagesController() {
		model = null;
	}
	

	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> cbNERC;

    @FXML
    private TextField txtYears;

    @FXML
    private TextField txtHours;

    @FXML
    private Button btnAnalysis;

    @FXML
    private TextArea txtResult;

    @FXML
    void doAnalize(ActionEvent event) {
    	txtResult.setText(model.findSolution(txtYears.getText(), txtHours.getText(), cbNERC.getValue()));
    }

    @FXML
    void initialize() {
        assert cbNERC != null : "fx:id=\"cbNERC\" was not injected: check your FXML file 'PowerOutages.fxml'.";
        assert txtYears != null : "fx:id=\"txtYears\" was not injected: check your FXML file 'PowerOutages.fxml'.";
        assert txtHours != null : "fx:id=\"txtHours\" was not injected: check your FXML file 'PowerOutages.fxml'.";
        assert btnAnalysis != null : "fx:id=\"btnAnalysis\" was not injected: check your FXML file 'PowerOutages.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'PowerOutages.fxml'.";
    }
    
    public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
		cbNERC.getItems().setAll(model.getAllNercAreas());
	}
}
