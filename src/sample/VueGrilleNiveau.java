package sample;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class VueGrilleNiveau {
    GridPane gridNiveau = new GridPane();
    ControleurNiveau controleurNiveau;


    public VueGrilleNiveau(ControleurNiveau _controleurNiveau){
        generateGrid();
        controleurNiveau = _controleurNiveau;
    }

    public void generateGrid() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 10; j++) {
                int number = i*10 + j +1;
                Button button = new Button("Niveau " + number);
                button.setMinSize(80,80);
                gridNiveau.add(button, j, i);
                gridNiveau.setVgap(20);
                gridNiveau.setHgap(20);
            }
        }
    }
}
