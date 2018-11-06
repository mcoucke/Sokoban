package sample;

import java.util.ArrayList;

public interface Modele {
    public int[] getEtat();
    public void move(int indice);
    public void undo();
    public void redo();
    public void solve();
    public void reset();
}
