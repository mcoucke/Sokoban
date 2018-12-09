package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;

public class ControleurIHMFX {
    Controleur controleur;
    VueIHMFX vue;
    Button reset, undo, redo, solve, menu;
    FlowPane buttonPane;

    ControleurIHMFX(Controleur controleur, VueIHMFX vue) {
        this.controleur = controleur;
        this.vue = vue;

        buttonPane = new FlowPane();

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

        buttonPane.getChildren().add(menu);
        buttonPane.getChildren().add(reset);
        buttonPane.getChildren().add(undo);
        buttonPane.getChildren().add(redo);
        buttonPane.getChildren().add(solve);

        buttonPane.setStyle("-fx-alignment: center;");
        buttonPane.setHgap(10);


        reset.setOnAction(new ActionReset());
        undo.setOnAction(new ActionUndo());
        redo.setOnAction(new ActionRedo());
        solve.setOnAction(new ActionSolve());
        menu.setOnAction(new ActionMenu());
    }

    class ActionReset implements EventHandler<ActionEvent> {
        public void handle(ActionEvent event) {
            controleur.reset();
        }
    }

    class ActionUndo implements EventHandler<ActionEvent> {
        public void handle(ActionEvent event) { controleur.undo();}
    }

    class ActionRedo implements EventHandler<ActionEvent> {
        public void handle(ActionEvent event) { controleur.redo();}
    }

    class ActionSolve implements EventHandler<ActionEvent> {
        public void handle(ActionEvent event) { controleur.solve();}
    }

    class ActionMenu implements EventHandler<ActionEvent> {
        public void handle(ActionEvent event) { controleur.retourMenu();}
    }
}