package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ControleurFinNiveau {
    GridPane vueFinNiveau;
    Controleur controleur;

    ControleurFinNiveau(Controleur controleur){
        vueFinNiveau = new GridPane();
        vueFinNiveau.setAlignment(Pos.CENTER);
        this.controleur = controleur;
        generateFin();
    }

    public void generateFin(){
        //Bouton retour menu
        Button menu = new Button("Menu niveaux");
        menu.setPrefSize(120, 40);
        menu.setOnAction(new ActionRetourMenu());
        menu.setStyle("-fx-border-color: grey;-fx-focus-color: transparent;-fx-faint-focus-color: transparent;");

        //Bouton menu suivant
        Button next = new Button("Suivant");
        next.setPrefSize(100,40);
        next.setOnAction(new ActionSuivant());
        next.setStyle("-fx-border-color: grey;-fx-focus-color: transparent;-fx-faint-focus-color: transparent;");

        //Texte de fin du niveau
        int nb_coups = controleur.getNbCoups();
        Text text_fin = new Text("Bravo ! Vous avez gagné en "+nb_coups+" coups");
        text_fin.setFont(Font.font ("Verdana", 22));
        text_fin.setFill(Color.GREEN);

        vueFinNiveau.setHgap(10);

        vueFinNiveau.add(menu,1,0);
        vueFinNiveau.add(text_fin,4,0);
        vueFinNiveau.add(next,6,0);
    }

    class ActionRetourMenu implements EventHandler<ActionEvent> {
        public void handle(ActionEvent event) {
            controleur.retourMenu();
        }
    }

    class ActionSuivant implements EventHandler<ActionEvent> {
        public void handle(ActionEvent event) {
            controleur.niveauSuivant();
        }
    }
}
