package sample;

import java.util.ArrayList;

public interface Modele {
    public ArrayList<String> getEtat();
    public void move(int x, int y);
    public void undo();
    public void redo();
    public void solve();
    public void reset();
    public void choixNiveau(int i);
    public boolean checkFin();
}
