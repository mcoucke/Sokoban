package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class IHMFX extends Application implements Observateur {
    VueIHMFX vue;

    public void actualise(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                vue.dessine();
            }
        });
    };

    @Override
    public void start(Stage primaryStage) throws Exception{
        Controleur controleur = Controleur.getControleur();
        controleur.abonne(this);

        vue = new VueIHMFX(controleur);
        ControleurIHMFX controleurIHMFX = new ControleurIHMFX(controleur,vue);
        vue.gridPane.setAlignment(Pos.CENTER);

        /* montage de la scene */
        MonteurScene monteurScene = new MonteurScene();

        Scene scene = monteurScene.
                setCentre(vue.gridPane).
                ajoutBas(controleurIHMFX.reset).
                setLargeur(1200).
                setHauteur(700).
                retourneScene();

        primaryStage.setScene(scene);

        primaryStage.setTitle("Sokoban");
        primaryStage.show();
    }

    public void lance() {
        launch(new String[]{});
    }
}

