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
    GridPane gridPane = new GridPane();
    GridPane gridPaneNiveaux = new GridPane();
    Image[] sokoban = new Image[]{ new Image(new FileInputStream(
            "Character.png"),80,80,false,false),
            new Image(new FileInputStream(
                    "CrateDark_Purple.png"),80,80,false,false),
            new Image(new FileInputStream(
                    "EndPoint_Yellow.png"),80,80,false,false),
            new Image(new FileInputStream(
                    "Ground_Sand.png"),80,80,false,false),
            new Image(new FileInputStream(
                    "Wall_Brown.png"),80,80,false,false)};




    public VueIHMFX(Controleur controleur) throws FileNotFoundException {
        commandeGetEtat = controleur.commandeGetEtat();
        commandeGetMurs = controleur.commandeGetMurs();
        commandeGetPosPerso = controleur.commandeGetPosPerso();
        dessine();
    }

    public void dessine() {
        int ligne = 0;
        int col = 0;
        int taille_l = 0;
        for(int i=0; i < commandeGetEtat.exec().size(); i++){
            ImageView img = new ImageView();
            if(commandeGetEtat.exec().get(i).equals("#")){
                img.setImage(sokoban[4]);
                taille_l++;
            }
            else if(commandeGetEtat.exec().get(i).equals(" ")){
                img.setImage(sokoban[3]);
                taille_l++;
            }
            else if(commandeGetEtat.exec().get(i).equals(".")){
                img.setImage(sokoban[2]);
                taille_l++;
            } else {
                ligne++;
                col += taille_l;
                taille_l = 0;
            }
            if(commandeGetPosPerso.exec().getX() == taille_l && commandeGetPosPerso.exec().getY() == ligne){
                img.setImage(sokoban[0]);
            }
            for(Tuple t : commandeGetMurs.exec()){
                if(t.getX() == taille_l && t.getY() == ligne){
                    img.setImage(sokoban[4]);
                }
            }
            gridPane.add(img, taille_l, ligne);
        }
    }

    /*
    public void generateGrid() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 10; j++) {
                int number = 4 * i + j + 1;
                Button button = new Button("Niveau " + number);
                button.setMaxHeight(80);
                button.setMaxWidth(200);
                gridPaneNiveaux.add(button, j, i);
            }
        }
    }*/
}
