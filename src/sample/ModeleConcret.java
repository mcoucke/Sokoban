package sample;

import java.util.ArrayList;

public class ModeleConcret implements Modele {

    // FONCTIONS À IMPLÉMENTER

    public ArrayList<String> etat = new ArrayList<String>();

    ModeleConcret(){
        etat.add("#");
        etat.add("#");
        etat.add("#");
        etat.add("#");
        etat.add(" ");
        etat.add("#");
        etat.add("#");
        etat.add("#");
        etat.add("#");
        etat.add(",");
        etat.add("#");
        etat.add(" ");
        etat.add(" ");
        etat.add("#");
        etat.add("#");
        etat.add("#");
        etat.add(" ");
        etat.add(" ");
        etat.add("#");
        etat.add(",");
        etat.add("#");
        etat.add(" ");
        etat.add("$");
        etat.add(" ");
        etat.add("*");
        etat.add(" ");
        etat.add("$");
        etat.add(" ");
        etat.add("#");
        etat.add(",");
        etat.add("#");
        etat.add(" ");
        etat.add(" ");
        etat.add(" ");
        etat.add("+");
        etat.add(" ");
        etat.add(" ");
        etat.add(" ");
        etat.add("#");
        etat.add(",");
        etat.add("#");
        etat.add("#");
        etat.add("#");
        etat.add(" ");
        etat.add(".");
        etat.add("$");
        etat.add("#");
        etat.add("#");
        etat.add("#");
        etat.add(",");
        etat.add(" ");
        etat.add(" ");
        etat.add("#");
        etat.add(" ");
        etat.add(".");
        etat.add(" ");
        etat.add("#");
        etat.add(",");
        etat.add(" ");
        etat.add(" ");
        etat.add("#");
        etat.add("#");
        etat.add("#");
        etat.add("#");
        etat.add("#");
        etat.add(",");
    }


    public ArrayList<String> getEtat() {
        return etat;
    }

    public void move(int indice) {

    }

    public void undo(){

    }

    public void redo(){

    }

    public void solve(){

    }

    @Override
    public void reset() {

    }
}
