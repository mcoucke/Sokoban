package sample;

import java.util.ArrayList;

public class FacadeModele {
    ModeleConcret modele = new ModeleConcret();

    public void move(int x) {
        modele.move(x);
    }

    public void reset() {
        modele.reset();
    }

    public ArrayList<String> getEtat() {
        return modele.getEtat();
    }

}
