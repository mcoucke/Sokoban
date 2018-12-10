package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class ControleurIHMFX {
    Controleur controleur;
    VueIHMFX vue;
    Button reset, undo, redo, solve, menu;
    Text nb_coups;
    GridPane buttonPane;

    ControleurIHMFX(Controleur controleur, VueIHMFX vue) {
        this.controleur = controleur;
        this.vue = vue;

        buttonPane = new GridPane();

        nb_coups = new Text("Coups : 0");

        reset = new Button("Reset");
        undo = new Button("Undo");
        redo = new Button("Redo");
        solve = new Button("Solve");
        menu = new Button("Menu");

        menu.setPrefSize(100, 50);
        reset.setPrefSize(100, 50);
        undo.setPrefSize(100, 50);
        redo.setPrefSize(100, 50);
        solve.setPrefSize(100, 50);


        menu.setStyle("-fx-border-color: grey;-fx-focus-color: transparent;-fx-faint-focus-color: transparent;");
        reset.setStyle("-fx-border-color: grey;-fx-focus-color: transparent;-fx-faint-focus-color: transparent;");
        undo.setStyle("-fx-border-color: grey;-fx-focus-color: transparent;-fx-faint-focus-color: transparent;");
        redo.setStyle("-fx-border-color: grey;-fx-focus-color: transparent;-fx-faint-focus-color: transparent;");
        solve.setStyle("-fx-border-color: grey;-fx-focus-color: transparent;-fx-faint-focus-color: transparent;");

        buttonPane.add(nb_coups, 1,0);
        buttonPane.add(menu,3,0);
        buttonPane.add(reset,4,0);
        buttonPane.add(undo,5,0);
        buttonPane.add(redo,6,0);
        buttonPane.add(solve,7,0);

        buttonPane.setStyle("-fx-alignment: center;");
        buttonPane.setHgap(10);


        reset.setOnAction(new ActionReset());
        undo.setOnAction(new ActionUndo());
        redo.setOnAction(new ActionRedo());
        solve.setOnAction(new ActionSolve());
        menu.setOnAction(new ActionMenu());
    }

    public void actualise(){
        nb_coups.setText("Coups : " + controleur.commandeGetNbCoups().exec());
    }

    class ActionReset implements EventHandler<ActionEvent> {
        public void handle(ActionEvent event) {
            controleur.reset();
            actualise();
        }
    }

    class ActionUndo implements EventHandler<ActionEvent> {
        public void handle(ActionEvent event) {
            controleur.undo();
            actualise();
        }
    }

    class ActionRedo implements EventHandler<ActionEvent> {
        public void handle(ActionEvent event) {
            controleur.redo();
            actualise();
        }
    }

    class ActionSolve implements EventHandler<ActionEvent> {
        public void handle(ActionEvent event) {
            controleur.solve();
            actualise();
        }
    }

    class ActionMenu implements EventHandler<ActionEvent> {
        public void handle(ActionEvent event) { controleur.retourMenu();}
    }
}