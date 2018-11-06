package sample;

public class FacadeModele {
    ModeleConcret modele = new ModeleConcret();

    public void move(int x) {
        modele.move(x);
    }

    public void reset() {
        modele.reset();
    }

}
