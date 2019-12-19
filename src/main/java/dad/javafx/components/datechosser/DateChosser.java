package dad.javafx.components.datechosser;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

import java.text.ParseException;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;

public class DateChosser extends GridPane implements Initializable {
	@FXML
	private GridPane view;

	@FXML
	private ComboBox<String> dayCombo;

	@FXML
	private ComboBox<String> monthCombo;

	@FXML
	private ComboBox<String> yearCombo;

	private Calendar calendar = Calendar.getInstance();

	private ObjectProperty<LocalDate> date = new SimpleObjectProperty<LocalDate>();
	private ListProperty<String> months = new SimpleListProperty<String>(FXCollections.observableArrayList());
	private ListProperty<String> days = new SimpleListProperty<String>(FXCollections.observableArrayList());
	private ListProperty<String> years = new SimpleListProperty<String>(FXCollections.observableArrayList());
	private int tam = 0;


	

	public DateChosser() {
		super();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/DateChooser.fxml"));
			loader.setController(this);
			loader.setRoot(this);
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		dayCombo.itemsProperty().bind(days);
		monthCombo.itemsProperty().bind(months);
		yearCombo.itemsProperty().bind(years);



		months.addAll("Enero", "Febrero", "Marzo", "Arbil", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre",
				"Nomviembre", "Diciembre");
		monthCombo.getSelectionModel().select(calendar.get(Calendar.MONTH));
		for (int i = 1900; i <= calendar.get(Calendar.YEAR); i++) {
			years.add(String.valueOf(i));
		}

		yearCombo.getSelectionModel().selectLast();
		dias();
		dayCombo.getSelectionModel().select(calendar.get(Calendar.DAY_OF_MONTH) - 1);

		monthCombo.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> actualizaDia());
		yearCombo.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> actualizaYear(nv));
		dayCombo.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> date());
		date();
	}

	private void actualizaDia() {
		if (dayCombo.getSelectionModel().getSelectedItem() != null) {
			int dia = Integer.parseInt(dayCombo.getSelectionModel().getSelectedItem());
			days.clear();
			dias();
			if (dia > tam)
				dayCombo.getSelectionModel().selectLast();
			else
				dayCombo.getSelectionModel().select(dia - 1);
		}
	}

	private void actualizaYear(String nv) {
		String expr = "^\\d*$";

		if (nv.matches(expr)) {
			int annio = Integer.parseInt(yearCombo.getSelectionModel().getSelectedItem());
			if (annio >= 1900 && annio <= calendar.get(Calendar.YEAR)) {
				actualizaDia();
				date();
			} else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Warning");
				alert.setHeaderText("Número requerido entre 1900 y " + calendar.get(Calendar.YEAR));
				alert.show();
			}
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Solo números");
			alert.show();
		}
	}

	private void date() {
		if (dayCombo.getSelectionModel().getSelectedItem() != null) {
			int annio = Integer.parseInt(yearCombo.getSelectionModel().getSelectedItem());
			int dia = Integer.parseInt(dayCombo.getSelectionModel().getSelectedItem());
			date.set(LocalDate.of(annio, monthCombo.getSelectionModel().getSelectedIndex() + 1, dia));
		}
	}

	private void dias() {
		tam = 0;
		int annio = Integer.parseInt(yearCombo.getSelectionModel().getSelectedItem());

		if (monthCombo.getSelectionModel().getSelectedItem().equals(months.get(1))) {
			if (Year.of(annio).isLeap())
				tam = 29;
			else
				tam = 28;

		} else {
			switch (monthCombo.getSelectionModel().getSelectedIndex() + 1) {
			case 4:
			case 6:
			case 9:
			case 11:
				tam = 30;
				break;
			default:
				tam = 31;
				break;

			}
		}

		for (int i = 1; i <= tam; i++) {
			days.add(i + "");
		}
	}

	public void casteaFecha() {
		String  annio=String.valueOf(LocalDate.now().getYear());
		String  day=String.valueOf(LocalDate.now().getDayOfMonth());
		
		
		yearCombo.getSelectionModel().select(annio);
		monthCombo.getSelectionModel().select(LocalDate.now().getMonthValue()-1);
		dayCombo.getSelectionModel().select(day);

	}
	public GridPane getView() {
		return view;
	}

	public final ObjectProperty<LocalDate> dateProperty() {
		return this.date;
	}

	public final LocalDate getDate() {
		return this.dateProperty().get();
	}

	public final void setDate(final LocalDate date) {
		this.dateProperty().set(date);
	}

}
