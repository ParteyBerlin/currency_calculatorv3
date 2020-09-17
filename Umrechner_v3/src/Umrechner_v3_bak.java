import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class Umrechner_v3_bak extends Application {
	
	public static double  
					eurDoll = 1.19, 
					eurYen = 125.98,
					eurPfund = 0.89,
					dolYen = 106.20,
					dolPfund =0.75,
					yenPfund = 141.91;
	Stage window;
	Scene calcscene;
	Scene info;
	Button btnCalc;
	Label 	lbl_calc, lbl_exRate, lbl_result,
			lbl_from, lbl_to, 
			lbl_tbl1o, lbl_tbl5o, lbl_tbl20o, lbl_tbl50o, //Umrechnungstabelle OBEN
			lbl_tbl1u, lbl_tbl5u, lbl_tbl20u, lbl_tbl50u; //Umrechnungstabelle UNTEN
	



	@Override
	public void start(Stage primary) throws Exception {
		try {
			
			window=primary;
			String appname = "Avengers CURRENCY";
			primary.setTitle(appname);
			
			//Gridfenster erstellen
			GridPane layout_calc = new GridPane();
			layout_calc.setGridLinesVisible(true);
			layout_calc.setPadding(new Insets(20,20,20,20));
			
			
			//SPALTEN DEFINIEREN UND HINZUFÜGEN
			ColumnConstraints col15 = new ColumnConstraints(15);
			ColumnConstraints col50 = new ColumnConstraints(50);
			ColumnConstraints col100 = new ColumnConstraints(100);
			ColumnConstraints col150 = new ColumnConstraints(150);
			layout_calc.getColumnConstraints().add(col15);
			layout_calc.getColumnConstraints().add(col150);
			layout_calc.getColumnConstraints().add(col50);
			layout_calc.getColumnConstraints().add(col150);
			layout_calc.getColumnConstraints().add(col15);
			
			//REIHEN DEFINIEREN UND HINZUFÜGEN
			RowConstraints row10 = new RowConstraints(10);
			RowConstraints row25 = new RowConstraints(25);
			RowConstraints row50 = new RowConstraints(50);
			RowConstraints row120 = new RowConstraints(120);
			RowConstraints row175 = new RowConstraints(175);
			layout_calc.getRowConstraints().add(row10);
			layout_calc.getRowConstraints().add(row50);
			layout_calc.getRowConstraints().add(row25);
			layout_calc.getRowConstraints().add(row50);
			layout_calc.getRowConstraints().add(row25);
			layout_calc.getRowConstraints().add(row50);
			layout_calc.getRowConstraints().add(row25);
			layout_calc.getRowConstraints().add(row50);
			layout_calc.getRowConstraints().add(row25);
			layout_calc.getRowConstraints().add(row175);
			
			//Elemente erstellen
			btnCalc = new Button("LOS");
			GridPane.setConstraints(btnCalc, 3, 5, 1, 1, HPos.CENTER, VPos.CENTER);
			lbl_exRate = new Label("exRate");
			GridPane.setConstraints(lbl_exRate, 1, 5, 1, 1, HPos.CENTER, VPos.CENTER);
			lbl_result = new Label("result");
			GridPane.setConstraints(lbl_result, 1, 7, 1, 1, HPos.CENTER, VPos.CENTER);
			lbl_from = new Label("VON");
			GridPane.setConstraints(lbl_from, 3, 2, 1, 1, HPos.CENTER, VPos.CENTER);
			lbl_to = new Label("NACH");
			GridPane.setConstraints(lbl_to, 3, 6, 1, 1, HPos.CENTER, VPos.CENTER);
			TextField userinput = new TextField();
			GridPane.setConstraints(userinput, 1, 3, 1, 1, HPos.CENTER, VPos.CENTER );
			
			ChoiceBox<String> fromBox = new ChoiceBox<>();
			fromBox.getItems().add("€");
			fromBox.getItems().add("$");
			fromBox.getItems().add("¥");
			fromBox.getItems().add("£");
			GridPane.setConstraints(fromBox, 3, 3, 1, 1, HPos.CENTER, VPos.CENTER);
			
			ChoiceBox<String> toBox = new ChoiceBox<>();
			toBox.getItems().add("€");
			toBox.getItems().add("$");
			toBox.getItems().add("¥");
			toBox.getItems().add("£");
			GridPane.setConstraints(toBox, 3, 7, 1, 1, HPos.CENTER, VPos.CENTER);
			
			
			//KINDER INS LAYOUT ADDEN
			layout_calc.getChildren().addAll(
											btnCalc, userinput,
											lbl_exRate, lbl_result ,
											lbl_from, lbl_to,
											fromBox, toBox /*, 
											lbl_tbl1o, lbl_tbl5o, lbl_tbl20o, lbl_tbl50o,
											lbl_tbl1u, lbl_tbl5u, lbl_tbl20u, lbl_tbl50u */
											); 
			
			//Scene starten
			calcscene = new Scene(layout_calc, 420, 500);
			
			//CSS einbinden
			calcscene.getStylesheets().add(getClass().getResource("app.css").toExternalForm());
			
			//SCENE AUF DIE STAGE UND STARTEN			
			primary.setScene(calcscene);
			primary.show();
			
			//Eventhandling
			
			btnCalc.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle (ActionEvent event) {
					if(event.getSource() == btnCalc) {
						String user_input = userinput.getText();
						Double i = Double.parseDouble(user_input);
						String from = fromBox.getValue();
						String to = toBox.getValue();
						//calculate(i, from, to);
						//System.out.println(y + x);
						lbl_result.setText(calculate(i, from, to));
					}
				}
			});// ENDE handle(ActionEvent)
		}
		
		catch(Exception e){
			e.printStackTrace();			
		}
		
		
	}//ende  start(Stage)
	
	
	
	public static void main(String[] args) {
		launch(args);
	} // ende MAIN
	

	private String calculate(Double i, String from, String to) {
		if(from.equals("€") && to.equals("$")) {
			lbl_exRate.setText("Kurs: " + eurDoll);
			Double x = (i*eurDoll * 100);
			x = (double) Math.round(x);
			x = x / 100;
			return "Betrag: " + String.valueOf(x);
		}
		else if(from.equals("€") && to.equals("¥")) {
			Double exRate_round = (1/eurYen * 100);
			exRate_round = (double) Math.round(exRate_round);
			exRate_round = exRate_round/100;
			lbl_exRate.setText("Kurs: " + exRate_round);
			Double x = (i/eurYen * 100);
			x = (double) Math.round(x);
			x = x / 100;
			return "Betrag: " + String.valueOf(Math.round(x));
		}
		else if(from.equals("$") && to.equals("€")) {
			Double exRate_round = (1/eurDoll * 100);
			exRate_round = (double) Math.round(exRate_round);
			exRate_round = exRate_round/100;
			lbl_exRate.setText("Kurs: " + exRate_round);
			Double x = (i/eurDoll * 100);
			x = (double) Math.round(x);
			x = x / 100;
			return "Betrag: " + String.valueOf(x);
			
		}
		return null;
	}
	
	
	
}// ende CLASS
