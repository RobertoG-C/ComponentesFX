package dad.javafx.components.datechosser;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class DateApp extends Application {
	prueba root;

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		root=new prueba();
		Scene scene = new Scene(root.getView(), 320, 200);
		
		primaryStage.setTitle("Custom components test app");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}

}
