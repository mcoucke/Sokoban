package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;


public class IHMFX extends Application implements Observateur {
    VueIHMFX vue_jeu;
    VueGrilleNiveau vue_menu;
    Controleur controleur;
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
        controleur = Controleur.getControleur();
        controleur.abonne(this);

        vue_menu = new VueGrilleNiveau(controleur);
        ControleurNiveau controleur_menu = new ControleurNiveau(controleur, vue_menu);

        /* montage de la scene */
        MonteurScene monteurScene = new MonteurScene();

        scene_menu = monteurScene.
                setCentre(controleur_menu.gridNiveau).
                setLargeur(1400).
                setHauteur(800).
                retourneScene();


        this.primaryStage.setScene(scene_menu);

        this.primaryStage.setTitle("Sokoban");
        this.primaryStage.show();
    }

    public void setScene(String scene){
        if(scene.equals("jeu")){
            vue_jeu = new VueIHMFX(this.controleur);
            ControleurIHMFX controleurIHMFX = new ControleurIHMFX(controleur,vue_jeu);
            vue_jeu.gridPane.setAlignment(Pos.CENTER);

            /* montage de la scene */
            MonteurScene monteurScene = new MonteurScene();

            scene_jeu = monteurScene.
                    setCentre(vue_jeu.gridPane).
                    ajoutBas(controleurIHMFX.buttonPane).
                    setLargeur(1400).
                    setHauteur(800).
                    retourneScene();

            scene_jeu.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent keyEvent) {
                    switch(keyEvent.getCode()) {
                        case UP:
                            controleur.move(0,-1);
                            vue_jeu.dessine();
                            controleurIHMFX.actualise();
                            controleur.checkFin();
                            break;
                        case DOWN:
                            controleur.move(0,1);
                            vue_jeu.dessine();
                            controleurIHMFX.actualise();
                            controleur.checkFin();
                            break;
                        case LEFT:
                            controleur.move(-1,0);
                            vue_jeu.dessine();
                            controleurIHMFX.actualise();
                            controleur.checkFin();
                            break;
                        case RIGHT:
                            controleur.move(1,0);
                            vue_jeu.dessine();
                            controleurIHMFX.actualise();
                            controleur.checkFin();
                            break;
                    }
                }
            });
            this.primaryStage.setScene(scene_jeu);
        }
        else if(scene.equals("menu")){
            this.primaryStage.setScene(scene_menu);
        }
        else if(scene.equals("fin")){
            MonteurScene monteurScene = new MonteurScene();
            ControleurFinNiveau controleur_fin_niv = new ControleurFinNiveau(Controleur.getControleur());
            Scene scene_fin_niv = monteurScene.
                    setCentre(vue_jeu.gridPane).
                    ajoutBas(controleur_fin_niv.vueFinNiveau).
                    setLargeur(1400).
                    setHauteur(800).
                    retourneScene();
            this.primaryStage.setScene(scene_fin_niv);
        }
        else {
            System.out.println(scene);
        }
    }

    public void lance() {
        launch(new String[]{});
    }
}

