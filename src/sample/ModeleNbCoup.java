package sample;


import java.util.ArrayList;

public class ModeleNbCoup implements Modele {
    Modele modele;
    int nbCoup =0;

    public ModeleNbCoup(Modele modele) {
        this.modele = modele;
    }

    public int nbCoup(){
        return nbCoup;
    }
    public ArrayList<String> getEtat(){
        return modele.getEtat();
    }
    public void move(int x, int y) {
        nbCoup++;
        modele.move(x,y);
    }

    public void undo(){}
    public void redo(){}
    public void solve(){}

    public void reset(){
        nbCoup=0;
        modele.reset();
    }

}
