package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class IHMFX extends Application implements Observateur {
    VueIHMFX vue_jeu;
    VueGrilleNiveau vue_menu;
    private Scene scene_jeu;
    private Scene scene_menu;
    private Stage primaryStage;

    public void actualise(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                vue_jeu.dessine();
            }
        });
    };

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        Controleur controleur = Controleur.getControleur();
        controleur.abonne(this);

        vue_menu = new VueGrilleNiveau(controleur);
        ControleurNiveau controleur_menu = new ControleurNiveau(controleur, vue_menu);

        vue_jeu = new VueIHMFX(controleur);
        ControleurIHMFX controleurIHMFX = new ControleurIHMFX(controleur,vue_jeu);
        vue_jeu.gridPane.setAlignment(Pos.CENTER);

        /* montage de la scene */
        MonteurScene monteurScene = new MonteurScene();

        scene_menu = monteurScene.
                setCentre(controleur_menu.gridNiveau).
                setLargeur(1400).
                setHauteur(700).
                retourneScene();

        scene_jeu = monteurScene.
                setCentre(vue_jeu.gridPane).
                ajoutBas(controleurIHMFX.buttonPane).
                setLargeur(1400).
                setHauteur(700).
                retourneScene();

        scene_jeu.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch(keyEvent.getCode()) {
                    case UP:
                        controleur.move(0,-1);
                        vue_jeu.dessine();
                        break;
                    case DOWN:
                        controleur.move(0,1);
                        vue_jeu.dessine();
                        break;
                    case LEFT:
                        controleur.move(-1,0);
                        vue_jeu.dessine();
                        break;
                    case RIGHT:
                        controleur.move(1,0);
                        vue_jeu.dessine();
                        break;
                }
            }
        });


        this.primaryStage.setScene(scene_menu);

        this.primaryStage.setTitle("Sokoban");
        this.primaryStage.show();
    }

    public void setScene(String scene){
        if(scene.equals("jeu")){
            this.primaryStage.setScene(scene_jeu);
        }
        else if(scene.equals("menu")){
            this.primaryStage.setScene(scene_menu);
        }
        else {
            System.out.println(scene);
        }
    }

    public void lance() {
        launch(new String[]{});
    }
}

