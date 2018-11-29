package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class ControleurIHMFX {
    Controleur controleur;
    VueIHMFX vue;
    Button reset, undo, redo, solve;

    ControleurIHMFX(Controleur controleur, VueIHMFX vue) {
        this.controleur = controleur;
        this.vue = vue;

        reset = new Button("Reset");
        undo = new Button("Undo");
        redo = new Button("Redo");
        solve = new Button("Solve");

        reset.setOnAction(new ActionReset());
        undo.setOnAction(new ActionUndo());
        redo.setOnAction(new ActionRedo());
        solve.setOnAction(new ActionSolve());
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

    class MyAction implements EventHandler<ActionEvent> {
        int indice;

        MyAction(int indice) {
            this.indice = indice;
        }

        @Override
        public void handle(ActionEvent event) {
            controleur.move(indice);
        }
    }
}