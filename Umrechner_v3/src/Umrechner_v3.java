import java.io.EOFException;
import java.text.DecimalFormat;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;

public class Umrechner_v3 extends Application {
	
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
			lbl_from, lbl_to, lbl_exRateTbl , lbl_titleTbl,
			lbl_tbl1o, lbl_tbl5o, lbl_tbl20o, lbl_tbl50o, //Umrechnungstabelle OBEN
			lbl_tbl1u, lbl_tbl5u, lbl_tbl20u, lbl_tbl50u; //Umrechnungstabelle UNTEN
	



	@Override
	public void start(Stage primary) throws Exception {
		try {
			
			window=primary;
			String appname = "Avengers CURRENCY";
			primary.setTitle(appname);

			//Gridfenster erstellen
			GridPane layout_tabelle = new GridPane();
			GridPane layout_calc = new GridPane();
			layout_calc.setStyle("-fx-background-color: linear-gradient(to right, lightgrey, grey);");
			layout_calc.setGridLinesVisible(true);
			layout_calc.setPadding(new Insets(20,20,20,20));
			layout_tabelle.setGridLinesVisible(true);
			
			
			
			//********* GridPane TABELLE
			ColumnConstraints tbl_col75 = new ColumnConstraints(75);		
			layout_tabelle.getColumnConstraints().add(tbl_col75);
			layout_tabelle.getColumnConstraints().add(tbl_col75);
			layout_tabelle.getColumnConstraints().add(tbl_col75);
			layout_tabelle.getColumnConstraints().add(tbl_col75);
			
			//REIHEN DEFINIEREN UND HINZUFÜGEN
			RowConstraints tbl_row75 = new RowConstraints(75);
			layout_tabelle.getRowConstraints().add(tbl_row75);
			layout_tabelle.getRowConstraints().add(tbl_row75);

			
			
			//********* MAIN - GridPane
			
			
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
			layout_calc.getRowConstraints().add(row25);
			layout_calc.getRowConstraints().add(row175);
			
			
			//*********** MAIN-GridPane Elemente erstellen
			btnCalc = new Button("LOS");
			btnCalc.setEffect(new DropShadow(10, 5, 5, Color.web("#333333")));
			GridPane.setConstraints(btnCalc, 3, 4, 1, 2, HPos.CENTER, VPos.CENTER);
			lbl_exRate = new Label("");
			GridPane.setConstraints(lbl_exRate, 0, 5, 2, 1, HPos.CENTER, VPos.CENTER);
			lbl_result = new Label("");
			lbl_result.setStyle("-fx-font-size: 23");
			GridPane.setConstraints(lbl_result, 0, 7, 3, 1, HPos.CENTER, VPos.TOP);
			lbl_from = new Label("VON");
			GridPane.setConstraints(lbl_from, 3, 2, 1, 1, HPos.CENTER, VPos.CENTER);
			lbl_to = new Label("NACH");
			GridPane.setConstraints(lbl_to, 3, 6, 1, 1, HPos.CENTER, VPos.CENTER);
			lbl_titleTbl = new Label("Betrag eingeben und Währungen wählen");
			GridPane.setConstraints(lbl_titleTbl, 1, 8, 3, 1, HPos.CENTER, VPos.CENTER);
			TextField userinput = new TextField();
			GridPane.setConstraints(userinput, 1, 3, 1, 1, HPos.CENTER, VPos.CENTER );
			
			
			//*********** GridPane TABELLE Elemente erstellen
			GridPane.setConstraints(layout_tabelle, 2, 9, 1, 2, HPos.CENTER, VPos.TOP);
//			layout_tabelle.add(new Label(""),1 ,0, 3, 1 );
			lbl_tbl1o = new Label("");
			layout_tabelle.add(lbl_tbl1o, 0, 0, 1, 1 );
			lbl_tbl1u = new Label("");
			layout_tabelle.add(lbl_tbl1u, 0, 1, 1, 1);
			lbl_tbl5o = new Label("");
			layout_tabelle.add(lbl_tbl5o, 1, 0, 1, 1 );
			lbl_tbl5u = new Label("");
			//lbl_tbl5u.setAlignment(Pos.TOP_CENTER);
			layout_tabelle.add(lbl_tbl5u, 1, 1, 1, 1);
			lbl_tbl20o = new Label("");
			layout_tabelle.add(lbl_tbl20o, 2, 0, 1, 1 );
			lbl_tbl20u = new Label("");
			layout_tabelle.add(lbl_tbl20u, 2, 1, 1, 1);
			lbl_tbl50o = new Label("");
			layout_tabelle.add(lbl_tbl50o, 3, 0, 1, 1 );
			lbl_tbl50u = new Label("");
			layout_tabelle.add(lbl_tbl50u, 3, 1, 1, 1);
			
			
			

		
			ChoiceBox<String> fromBox = new ChoiceBox<>();
			fromBox.setValue("€");
			fromBox.getItems().add("€");
			fromBox.getItems().add("$");
			fromBox.getItems().add("£");
            fromBox.getItems().add("¥");
			GridPane.setConstraints(fromBox, 3, 3, 1, 1, HPos.CENTER, VPos.CENTER);
			
			ChoiceBox<String> toBox = new ChoiceBox<>();
			toBox.setValue("€");
			toBox.getItems().add("€");
			toBox.getItems().add("$");
			toBox.getItems().add("£");
            toBox.getItems().add("¥");
			GridPane.setConstraints(toBox, 3, 7, 1, 1, HPos.CENTER, VPos.CENTER);
			
			
			//KINDER INS LAYOUT ADDEN
			layout_calc.getChildren().addAll(
											btnCalc, userinput,
											lbl_exRate, lbl_result ,
											lbl_from, lbl_to, lbl_titleTbl,
											fromBox, toBox, layout_tabelle /*, 
											lbl_tbl1o, lbl_tbl1u , lbl_tbl5o, lbl_tbl20o, lbl_tbl50o,
											 lbl_tbl5u, lbl_tbl20u, lbl_tbl50u */
											); 
			
			//Scene starten
			calcscene = new Scene(layout_calc, 420, 525);
			
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
						double i = 0;
						if (userinput.getText() == null || userinput.getText().trim().isEmpty()) {
						    i = -1;
						}
						else {
						i = Double.parseDouble(user_input);
						}
						String from = fromBox.getValue();
						String to = toBox.getValue();
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
		
		
		
		DecimalFormat f_exRate = new DecimalFormat("#0.0000");
		DecimalFormat f_value = new DecimalFormat("#0.00");	
		lbl_titleTbl.setText("Umrechnungstabelle");
		
		if(i <= -1) {
		return "Betrag korrigieren!";
		}
		
		//FROM EURO
		else if(from.equals("€") && to.equals("$")) {
			lbl_exRate.setText("Kurs: " + f_exRate.format(eurDoll));
			lbl_tbl1o.setText("1 €");
			lbl_tbl1u.setText(String.valueOf(f_value.format(1*eurDoll)) + " $");
			lbl_tbl5o.setText("5 €");
			lbl_tbl5u.setText(String.valueOf(f_value.format(5*eurDoll)) + " $");
			lbl_tbl20o.setText("20 €");
			lbl_tbl20u.setText(String.valueOf(f_value.format(20*eurDoll)) + " $");
			lbl_tbl50o.setText("50 €");
			lbl_tbl50u.setText(String.valueOf(f_value.format(50*eurDoll)) + " $");
			return "Betrag: " + String.valueOf(f_value.format(i*eurDoll)) + " $";
		}
		else if(from.equals("€") && to.equals("£")){
			lbl_exRate.setText("Kurs: " + f_exRate.format(eurPfund));
			lbl_tbl1o.setText("1 €");
			lbl_tbl1u.setText(String.valueOf(f_value.format(1*eurPfund)) + " £");
			lbl_tbl5o.setText("5 €");
			lbl_tbl5u.setText(String.valueOf(f_value.format(5*eurPfund)) + " £");
			lbl_tbl20o.setText("20 €");
			lbl_tbl20u.setText(String.valueOf(f_value.format(20*eurPfund)) + " £");
			lbl_tbl50o.setText("50 €");
			lbl_tbl50u.setText(String.valueOf(f_value.format(50*eurPfund)) + " £");
			return "Betrag: " + String.valueOf(f_value.format(i*eurPfund)) + " £";			
		}
		else if(from.equals("€") && to.equals("¥")){
			lbl_exRate.setText("Kurs: " + f_exRate.format(eurYen));
			lbl_tbl1o.setText("1 €");
			lbl_tbl1u.setText(String.valueOf(f_value.format(1*eurYen)) + " ¥");
			lbl_tbl5o.setText("5 €");
			lbl_tbl5u.setText(String.valueOf(f_value.format(5*eurYen)) + " ¥");
			lbl_tbl20o.setText("20 €");
			lbl_tbl20u.setText(String.valueOf(f_value.format(20*eurYen)) + " ¥");
			lbl_tbl50o.setText("50 €");
			lbl_tbl50u.setText(String.valueOf(f_value.format(50*eurYen)) + " ¥");
			return "Betrag: " + String.valueOf(f_value.format(i*eurYen)) + " ¥" ;
		}
		
		//FROM DOLLAR
		else if(from.equals("$") && to.equals("€")) {
			lbl_exRate.setText("Kurs: " + f_exRate.format(1/eurDoll));
			lbl_tbl1o.setText("1 $");
			lbl_tbl1u.setText(String.valueOf(f_value.format(1/eurDoll)) + " €");
			lbl_tbl5o.setText("5 $");
			lbl_tbl5u.setText(String.valueOf(f_value.format(5/eurDoll)) + " €");
			lbl_tbl20o.setText("20 $");
			lbl_tbl20u.setText(String.valueOf(f_value.format(20/eurDoll)) + " €");
			lbl_tbl50o.setText("50 $");
			lbl_tbl50u.setText(String.valueOf(f_value.format(50/eurDoll)) + " €");
			return "Betrag: " + String.valueOf(f_value.format(i/eurDoll)) + " €";
		}
		else if(from.equals("$") && to.equals("£")) {
			lbl_exRate.setText("Kurs: " + f_exRate.format(dolPfund));
			lbl_tbl1o.setText("1 $");
			lbl_tbl1u.setText(String.valueOf(f_value.format(1*dolPfund)) + " £");
			lbl_tbl5o.setText("5 $");
			lbl_tbl5u.setText(String.valueOf(f_value.format(5*dolPfund)) + " £");
			lbl_tbl20o.setText("20 $");
			lbl_tbl20u.setText(String.valueOf(f_value.format(20*dolPfund)) + " £");
			lbl_tbl50o.setText("50 $");
			lbl_tbl50u.setText(String.valueOf(f_value.format(50*dolPfund)) + " £");
			return "Betrag: " + String.valueOf(f_value.format(i*dolPfund)) + " £";
		}
		else if(from.equals("$") && to.equals("¥")) {
			lbl_exRate.setText("Kurs: " + f_exRate.format(dolYen));
			lbl_tbl1o.setText("1 $");
			lbl_tbl1u.setText(String.valueOf(f_value.format(1*dolYen)) + " £");
			lbl_tbl5o.setText("5 $");
			lbl_tbl5u.setText(String.valueOf(f_value.format(5*dolYen)) + " £");
			lbl_tbl20o.setText("20 $");
			lbl_tbl20u.setText(String.valueOf(f_value.format(20*dolYen)) + " £");
			lbl_tbl50o.setText("50 $");
			lbl_tbl50u.setText(String.valueOf(f_value.format(50*dolYen)) + " £");
			return "Betrag: " + String.valueOf(f_value.format(i*dolYen)) + " ¥";
		}
		
		//FROM PFUND
		else if(from.equals("£") && to.equals("€")) {
			lbl_exRate.setText("Kurs: " + f_exRate.format(1/eurPfund));
			lbl_tbl1o.setText("1 £");
			lbl_tbl1u.setText(String.valueOf(f_value.format(1/eurPfund)) + " €");
			lbl_tbl5o.setText("5 £");
			lbl_tbl5u.setText(String.valueOf(f_value.format(5/eurPfund)) + " €");
			lbl_tbl20o.setText("20 £");
			lbl_tbl20u.setText(String.valueOf(f_value.format(20/eurPfund)) + " €");
			lbl_tbl50o.setText("50 £");
			lbl_tbl50u.setText(String.valueOf(f_value.format(50/eurPfund)) + " €");
			return "Betrag: " + String.valueOf(f_value.format(i* (1/eurPfund))) + " €";
		}
		else if(from.equals("£") && to.equals("$")) {
			lbl_exRate.setText("Kurs: " + f_exRate.format(1/dolPfund));
			lbl_tbl1o.setText("1 £");
			lbl_tbl1u.setText(String.valueOf(f_value.format(1/dolPfund)) + " $");
			lbl_tbl5o.setText("5 £");
			lbl_tbl5u.setText(String.valueOf(f_value.format(5/dolPfund)) + " $");
			lbl_tbl20o.setText("20 £");
			lbl_tbl20u.setText(String.valueOf(f_value.format(20/dolPfund)) + " $");
			lbl_tbl50o.setText("50 £");
			lbl_tbl50u.setText(String.valueOf(f_value.format(50/dolPfund)) + " $");
			return "Betrag: " + String.valueOf(f_value.format(i* (1/dolPfund))) +" $";
		}
		else if(from.equals("£") && to.equals("¥")) {
			lbl_exRate.setText("Kurs: " + f_exRate.format(yenPfund));
			lbl_tbl1o.setText("1 £");
			lbl_tbl1u.setText(String.valueOf(f_value.format(1*yenPfund)) + " ¥");
			lbl_tbl5o.setText("5 £");
			lbl_tbl5u.setText(String.valueOf(f_value.format(5*yenPfund)) + " ¥");
			lbl_tbl20o.setText("20 £");
			lbl_tbl20u.setText(String.valueOf(f_value.format(20*yenPfund)) + " ¥");
			lbl_tbl50o.setText("50 £");
			lbl_tbl50u.setText(String.valueOf(f_value.format(50*yenPfund)) + " ¥");
			return "Betrag: " +String.valueOf(f_value.format(i* yenPfund)) + " ¥";	
		}
		
		//FROM YEN
		else if(from.equals("¥") && to.equals("€")) {
			lbl_exRate.setText("Kurs: " + f_exRate.format(1/eurYen));
			lbl_tbl1o.setText("1000 ¥");
			lbl_tbl1u.setText(String.valueOf(f_value.format(1000/eurYen)) + " €");
			lbl_tbl5o.setText("5000 ¥");
			lbl_tbl5u.setText(String.valueOf(f_value.format(5000/eurYen)) + " €");
			lbl_tbl20o.setText("20000 ¥");
			lbl_tbl20u.setText(String.valueOf(f_value.format(20000/eurYen)) + " €");
			lbl_tbl50o.setText("50000 ¥");
			lbl_tbl50u.setText(String.valueOf(f_value.format(50000/eurYen)) + " €");
			return "Betrag: " + String.valueOf(f_value.format(i * (1/eurYen))) + " €";
		}
		else if(from.equals("¥") && to.equals("$")) {
			lbl_exRate.setText("Kurs: " + f_exRate.format(1/dolYen));
			lbl_tbl1o.setText("1000 ¥");
			lbl_tbl1u.setText(String.valueOf(f_value.format(1000/dolYen)) + " $");
			lbl_tbl5o.setText("5000 ¥");
			lbl_tbl5u.setText(String.valueOf(f_value.format(5000/dolYen)) + " $");
			lbl_tbl20o.setText("20000 ¥");
			lbl_tbl20u.setText(String.valueOf(f_value.format(20000/dolYen)) + " $");
			lbl_tbl50o.setText("50000 ¥");
			lbl_tbl50u.setText(String.valueOf(f_value.format(50000/dolYen)) + " $");
			return "Betrag: " + String.valueOf(f_value.format(i * (1/dolYen))) + " $";			
		}
		else if(from.equals("¥") && to.equals("£")) {
			lbl_exRate.setText("Kurs: " + f_exRate.format(1/yenPfund));
			lbl_tbl1o.setText("1000 ¥");
			lbl_tbl1u.setText(String.valueOf(f_value.format(1000/yenPfund)) + " £");
			lbl_tbl5o.setText("5000 ¥");
			lbl_tbl5u.setText(String.valueOf(f_value.format(5000/yenPfund)) + " £");
			lbl_tbl20o.setText("20000 ¥");
			lbl_tbl20u.setText(String.valueOf(f_value.format(20000/yenPfund)) + " £");
			lbl_tbl50o.setText("50000 ¥");
			lbl_tbl50u.setText(String.valueOf(f_value.format(50000/yenPfund)) + " £");
			return "Betrag: " + String.valueOf(f_value.format(i* (1/yenPfund))) + " £";
			
		}
		else{ 
			lbl_tbl1o.setText("");
			lbl_tbl1u.setText("");
			lbl_tbl5o.setText("");
			lbl_tbl5u.setText("");
			lbl_tbl20o.setText("");
			lbl_tbl20u.setText("");
			lbl_tbl50o.setText("");
			lbl_tbl50u.setText("");
			lbl_exRate.setText("");
			return "Auswahl prüfen!";
			}
		
		//}//ENDE TRY
		
		
	}
	
	
	
}// ende CLASS
