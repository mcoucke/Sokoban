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
    GridPane gridPane = new GridPane();
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
            else if(commandeGetEtat.exec().get(i).equals("$") || commandeGetEtat.exec().get(i).equals("*")){
                img.setImage(sokoban[1]);
                taille_l++;
            }
            else if(commandeGetEtat.exec().get(i).equals(".")){
                img.setImage(sokoban[2]);
                taille_l++;
            }
            else if(commandeGetEtat.exec().get(i).equals("@") || commandeGetEtat.exec().get(i).equals("+")){
                img.setImage(sokoban[0]);
                taille_l++;
            } else {
                ligne++;
                col += taille_l;
                taille_l = 0;
            }
            gridPane.add(img, taille_l, ligne);
        }
    }
}
