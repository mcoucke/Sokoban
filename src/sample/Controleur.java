package sample;

import javafx.stage.Stage;

import java.util.ArrayList;

public class Controleur implements Sujet {
    private static Controleur singleton;
    private Stage stage;


    public static Controleur getControleur() {
        if (singleton == null)
            singleton = new Controleur(new FacadeModele());
        return singleton;
    }

    FacadeModele facadeModele;
    ArrayList<Observateur> observateurs = new ArrayList<Observateur>();

    private Controleur(FacadeModele facadeModele) {
        this.facadeModele = facadeModele;
    }

    public void abonne(Observateur observateur) {
        observateurs.add(observateur);
    }

    @Override
    public void notifie() {
        for (Observateur observateur:observateurs)
            observateur.actualise();
    }

    public void move(int x, int y) {
        facadeModele.move(x,y);
        notifie();
    }

    public void reset() {
        facadeModele.reset();
        notifie();
    }

    public void undo() {
        facadeModele.undo();
        notifie();
    }

    public void redo() {
        facadeModele.redo();
        notifie();
    }

    public void solve() {
        facadeModele.solve();
        notifie();
    }

    public void retourMenu(){
        observateurs.get(0).setScene("menu");
        notifie();
    }

    public void choixNiveau(int i) {
        observateurs.get(0).setScene("jeu");

        facadeModele.choixNiveau(i);
        notifie();
    }

    public void checkFin(){
        if(facadeModele.checkFin()){
            observateurs.get(0).setScene("fin");
            notifie();
        }
    }

    public int getNbCoups(){
        return facadeModele.getNbCoups();
    }

    public void niveauSuivant(){
        int currentLvl = facadeModele.getCurrentLvl();
        choixNiveau(currentLvl + 1);
    }


    public CommandeTabString commandeGetEtat() {
        return new CommandeTabString() {
            @Override
            public ArrayList<String> exec() {
                return facadeModele.getEtat();
            }
        };
    }

    public CommandeTabTuple commandeGetMurs() {
        return new CommandeTabTuple() {
            @Override
            public ArrayList<Tuple> exec() {
                return facadeModele.getMurs();
            }
        };
    }

    public CommandeTabTuple commandeGetFins(){
        return new CommandeTabTuple() {
            @Override
            public ArrayList<Tuple> exec() {
                return facadeModele.getFins();
            }
        };
    }

    public CommandePosPerso commandeGetPosPerso() {
        return new CommandePosPerso() {
            @Override
            public Tuple exec() {
                return facadeModele.getPosPerso();
            }
        };
    }

    public CommandeInt commandeGetGridSize() {
        return new CommandeInt() {
            @Override
            public int exec() {
                return facadeModele.getGridSize();
            }
        };
    }

    public CommandeInt commandeGetNbCoups() {
        return new CommandeInt() {
            @Override
            public int exec() {
                return facadeModele.getNbCoups();
            }
        };
    }



}
