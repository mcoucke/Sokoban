package sample;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

import java.util.ArrayList;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class VueIHMFX {

    CommandeTabString commandeGetEtat;
    CommandeTabTuple commandeGetMurs;
    CommandePosPerso commandeGetPosPerso;
    CommandeGridSize commandeGetGridSize;
    int nb_lignes;
    int nb_cols;
    GridPane gridPane = new GridPane();
    GridPane gridPaneNiveaux = new GridPane();
    Image[] sokoban = new Image[]{new Image(new FileInputStream(
            "Character.png"), 80, 80, false, false),
            new Image(new FileInputStream(
                    "CrateDark_Purple.png"), 80, 80, false, false),
            new Image(new FileInputStream(
                    "EndPoint_Yellow.png"), 80, 80, false, false),
            new Image(new FileInputStream(
                    "Ground_Sand.png"), 80, 80, false, false),
            new Image(new FileInputStream(
                    "Wall_Brown.png"), 80, 80, false, false)};


    public VueIHMFX(Controleur controleur) throws FileNotFoundException {
        commandeGetEtat = controleur.commandeGetEtat();
        commandeGetMurs = controleur.commandeGetMurs();
        commandeGetPosPerso = controleur.commandeGetPosPerso();
        commandeGetGridSize = controleur.commandeGetGridSize();
        nb_cols = commandeGetGridSize.exec();
        nb_lignes = (int) ((double) commandeGetEtat.exec().size() / ((double) nb_cols));
        dessine();
    }

    public void dessine() {
        gridPane.getChildren().clear();
        int ligne = 0;
        int col = 0;
        for (int i = 0; i < commandeGetEtat.exec().size(); i++) {
            ImageView img = new ImageView();
            if (commandeGetEtat.exec().get(i).equals("#")) {
                img.setImage(sokoban[4]);
                col++;
            } else if (commandeGetEtat.exec().get(i).equals(" ")) {
                img.setImage(sokoban[3]);
                col++;
            } else if (commandeGetEtat.exec().get(i).equals(".")) {
                img.setImage(sokoban[2]);
                col++;
            }

            gridPane.add(img, col - 1, ligne);

            if (commandeGetGridSize.exec() == col) {
                ligne++;
                col = 0;
            }
        }
        ImageView img = new ImageView();
        img.setImage(sokoban[0]);
        gridPane.add(img, commandeGetPosPerso.exec().getX(), commandeGetPosPerso.exec().getY());
        for (Tuple t : commandeGetMurs.exec()) {
            img = new ImageView();
            img.setImage(sokoban[1]);
            gridPane.add(img, t.getX(), t.getY());
        }
    }
}


