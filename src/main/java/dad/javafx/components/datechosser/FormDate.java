package dad.javafx.components.datechosser;

import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class FormDate implements Initializable {

	
   public FormDate() {
	// TODO Auto-generated constructor stub
}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	void onFirstButtonAction(ActionEvent event) {
//		monthCombo.getSelectionModel().select(calendar.get(Calendar.MONTH));
//		yearCombo.getSelectionModel().select(year);
//		dayCombo.getSelectionModel().select(calendar.get(Calendar.DAY_OF_MONTH)-1);
	}

	@FXML
	void onSecondButton(ActionEvent event) {
//		Alert alert=new Alert(AlertType.INFORMATION);
//		alert.setTitle("Fecha selecionada");
//		alert.setHeaderText("La fecha selecionada es:");
//		alert.setContentText(calendar.DAY_OF_MONTH+"/"+calendar.MONTH+"/"+year);
//		alert.showAndWait();
	}

	


}
