package sample;

import java.util.ArrayList;

public class FacadeModele {
    ModeleConcret modele = new ModeleConcret();

    public void move(int x, int y) {
        modele.move(x,y);
        modele.disableRedo();
    }

    public void reset() {
        modele.reset();
    }

    public void undo() { modele.undo(); }

    public void redo() { modele.redo(); }

    public void solve() { modele.solve(); }

    public void choixNiveau(int i) {
        modele.choixNiveau(i);
    }

    public boolean checkFin(){
        return modele.checkFin();
    }

    public ArrayList<String> getEtat() {
        return modele.getEtat();
    }

    public ArrayList<Tuple> getMurs() {
        return modele.getMurs();
    }

    public ArrayList<Tuple> getFins() {
        return modele.getFins();
    }

    public Tuple getPosPerso() {
        return modele.getPosPerso();
    }

    public int getGridSize() {
        return modele.getGridSize();
    }

    public int getNbCoups() {
        return modele.getNbCoups();
    }

    public int getCurrentLvl(){
        return modele.getCurrentLvl();
    }


}
