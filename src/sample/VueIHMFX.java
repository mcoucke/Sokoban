package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class VueIHMFX {

    CommandeTabString commandeGetEtat;
    CommandeTabTuple commandeGetMurs;
    CommandePosPerso commandeGetPosPerso;
    CommandeInt commandeGetGridSize;
    CommandeTabTuple commandeGetFins;
    int nb_cols;
    GridPane gridPane = new GridPane();
    Image[] sokoban;


    public VueIHMFX(Controleur controleur) {
        commandeGetEtat = controleur.commandeGetEtat();
        commandeGetMurs = controleur.commandeGetMurs();
        commandeGetPosPerso = controleur.commandeGetPosPerso();
        commandeGetGridSize = controleur.commandeGetGridSize();
        commandeGetFins = controleur.commandeGetFins();
        nb_cols = commandeGetGridSize.exec();
        ClassLoader classLoader = getClass().getClassLoader();
//        try {
            sokoban = new Image[]{new Image(classLoader.getResourceAsStream(
                    "Character.png"), 65, 65, false, false),
                    new Image(classLoader.getResourceAsStream(
                            "CrateDark_Purple.png"), 65, 65, false, false),
                    new Image(classLoader.getResourceAsStream(
                            "EndPoint_Yellow.png"), 65, 65, false, false),
                    new Image(classLoader.getResourceAsStream(
                            "Ground_Sand.png"), 65, 65, false, false),
                    new Image(classLoader.getResourceAsStream(
                            "Wall_Brown.png"), 65, 65, false, false),
                    new Image(classLoader.getResourceAsStream(
                            "Crate_Yellow.png"), 65, 65, false, false)};
//        }
//        catch (FileNotFoundException ex){
//            System.out.println(ex.getMessage());
//        }
    }

    public void dessine() {
        gridPane.getChildren().clear();
        int ligne = 0;
        int col = 0;
        //Affichage grille
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
        //Affichage perso
        ImageView img = new ImageView();
        img.setImage(sokoban[0]);
        gridPane.add(img, commandeGetPosPerso.exec().getX(), commandeGetPosPerso.exec().getY());

        //Affichage caisses
        for (Tuple t_caisse : commandeGetMurs.exec()) {
            boolean is_in_fin = false;
            for(Tuple t_fin : commandeGetFins.exec()){
                if(t_caisse.getX() == t_fin.getX() && t_caisse.getY() == t_fin.getY()){
                    is_in_fin = true;
                }
            }
            img = new ImageView();
            if(is_in_fin){
                img.setImage(sokoban[5]);
            }
            else {
                img.setImage(sokoban[1]);
            }
            gridPane.add(img, t_caisse.getX(), t_caisse.getY());
        }
    }
}


