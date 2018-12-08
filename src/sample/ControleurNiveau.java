package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class ControleurNiveau {
    GridPane gridNiveau;
    Controleur controleur;
    VueGrilleNiveau vue;

    ControleurNiveau(Controleur controleur, VueGrilleNiveau vue){
        gridNiveau = new GridPane();
        gridNiveau.setAlignment(Pos.CENTER);
        generateGrid();
        this.controleur = controleur;
        this.vue = vue;
    }

    public void generateGrid() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 10; j++) {
                int number = i*10 + j +1;
                Button button = new Button("Niveau " + number);
                button.setMinSize(80,80);
                button.setOnAction(new ActionNiveau());
                gridNiveau.add(button, j, i);
                gridNiveau.setVgap(20);
                gridNiveau.setHgap(20);
            }
        }
    }

    class ActionNiveau implements EventHandler<ActionEvent> {
        public void handle(ActionEvent event) {
            Button b = (Button)event.getSource();
            String name = b.getText();
            int niveau = Integer.parseInt(name.substring(7));
            controleur.choixNiveau(niveau);
        }
    }
}
