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
    FlowPane vueFinNiveau;
    Controleur controleur;

    ControleurFinNiveau(Controleur controleur){
        vueFinNiveau = new FlowPane();
        vueFinNiveau.setAlignment(Pos.CENTER);
        this.controleur = controleur;
        generateFin();
    }

    public void generateFin(){
        //Bouton retour menu
        Button menu = new Button("Menu niveaux");
        menu.setOnAction(new ActionRetourMenu());
        //Texte de fin du niveau
        int nb_coups = controleur.getNbCoups();
        Text text_fin = new Text("Bravo ! Vous avez gagné en "+nb_coups+" coups");
        text_fin.setFont(Font.font ("Verdana", 30));
        text_fin.setFill(Color.GREEN);

        vueFinNiveau.setHgap(10);

        vueFinNiveau.getChildren().add(menu);
        vueFinNiveau.getChildren().add(text_fin);
    }

    class ActionRetourMenu implements EventHandler<ActionEvent> {
        public void handle(ActionEvent event) {
            controleur.retourMenu();
        }
    }
}
