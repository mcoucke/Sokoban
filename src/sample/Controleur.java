package sample;

import java.util.ArrayList;

public class Controleur implements Sujet {
    private static Controleur singleton;


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

    public void move(int x) {
        facadeModele.move(x);
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

    public CommandeTabString commandeGetEtat() {
        return new CommandeTabString() {
            @Override
            public ArrayList<String> exec() {
                return facadeModele.getEtat();
            }
        };
    }



}
