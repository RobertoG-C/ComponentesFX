package dad.javafx.components.datechosser;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.ResourceBundle;

import dad.javafx.components.datechosser.DateChosser;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class prueba implements Initializable {

	@FXML
	private GridPane view;
	@FXML
	private DateChosser dateChooser;

	@FXML
	private Button consulta;

	@FXML
	private Button inicia;

	private ObjectProperty<LocalDate> date = new SimpleObjectProperty<LocalDate>();

	public prueba() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Test.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		date.bindBidirectional(dateChooser.dateProperty());
		view.setAlignment(Pos.BASELINE_CENTER);
	}

	@FXML
	void butC(ActionEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setContentText(date.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString());
		alert.showAndWait();
	}

	@FXML
	void butI(ActionEvent event) {
		dateChooser.casteaFecha();
	}

	public GridPane getView() {
		return view;
	}
}