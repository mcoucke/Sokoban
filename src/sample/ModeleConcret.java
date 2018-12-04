package sample;

import java.io.*;
import java.util.ArrayList;

public class ModeleConcret implements Modele {

    // FONCTIONS À IMPLÉMENTER

    public ArrayList<String> etat;
    public Tuple pos_perso = new Tuple();
    public ArrayList<Tuple> pos_caisses = new ArrayList<Tuple>();
    private ArrayList<ArrayList<String> > Niveaux;
    private int size_grid = 0;


    ModeleConcret(){
        etat = new ArrayList<>();
        Niveaux = new ArrayList<>();
        size_grid = getSizeOfGrid(1);
        getGrid(1);
        System.out.println(String.valueOf(pos_perso.getX()) + " " + String.valueOf(pos_perso.getY()));
    }


    public ArrayList<String> getEtat() {
        return etat;
    }

    public void move(int x, int y) {
        int pos = getPos();
        if(x == 0 && y == 1){ // haut
            etat.set(pos, " ");
            //ajouter déplacement du perso
        }
        else if(x == 0 && y == -1){ // bas

        }
        else if(x == 1 && y == 0){ // droite
            etat.set(pos, " ");
            etat.set(pos+1, "@");
        }
        else if(x == -1 && y ==0){ // gauche
            etat.set(pos, " ");
            etat.set(pos-1, "@");
        }
    }

    public void undo(){

    }

    public void redo(){

    }

    public void solve(){

    }

    public int getPos(){
        int pos = 0;
        for(int i=0; i < etat.size(); i++){
            if(etat.get(i).equals("@") || etat.get(i).equals("+")){
                pos = i;
            }
        }
        return pos;
    }

    public ArrayList<String> getGrille(int niveau){
        ArrayList<String> grille = null;
        int size = getSizeOfGrid(niveau);
        try {
            File file_Micro = new File("res/MicroCosmos.txt");
            FileReader reader = new FileReader(file_Micro);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line = bufferedReader.readLine();
            if (niveau == 1){
                int ligne = 0;
                while (line != null && !line.startsWith(";")){
                    if(line.length() < size_grid){
                        while (line.length() < size_grid){
                            line += " ";
                        }
                    }
                    for (int i = 0; i < line.length(); ++i){
                        if(line.charAt(i) == '@'){
                            etat.add(" ");
                            pos_perso.setPos(ligne, i);
                        }
                        else if(line.charAt(i) == '+'){
                            etat.add(".");
                            pos_perso.setPos(ligne, i);
                        }
                        else if(line.charAt(i) == '$'){
                            etat.add(" ");
                            pos_caisses.add(new Tuple(ligne, i));
                        }
                        else if(line.charAt(i) == '*'){
                            etat.add(".");
                            pos_caisses.add(new Tuple(ligne, i));
                        }
                        else {
                            etat.add(String.valueOf(line.charAt(i)));
                        }
                    }
                    etat.add(",");
                    line = String.valueOf(bufferedReader.readLine());
                    ligne++;
                }
            }
            else {
                int cpt_niveau = 1;


                while (line != null){
                    if(line.length() < size_grid){
                        while (line.length() < size_grid){
                            line += " ";
                        }
                    }
                    if(cpt_niveau == niveau && !line.startsWith(";")){
                        int ligne = 0;
                        while (line != null && !line.startsWith(";")){
                            if(line.length() < size_grid){
                                while (line.length() < size_grid){
                                    line += " ";
                                }
                            }
                            for (int i = 0; i < line.length(); ++i){
                                if(line.charAt(i) == '@'){
                                    etat.add(" ");
                                    pos_perso.setPos(ligne, i);
                                }
                                else if(line.charAt(i) == '+'){
                                    etat.add(".");
                                    pos_perso.setPos(ligne, i);
                                }
                                else if(line.charAt(i) == '$'){
                                    etat.add(" ");
                                    pos_caisses.add(new Tuple(ligne, i));
                                }
                                else if(line.charAt(i) == '*'){
                                    etat.add(".");
                                    pos_caisses.add(new Tuple(ligne, i));
                                }
                                else {
                                    etat.add(String.valueOf(line.charAt(i)));
                                }
                            }
                            etat.add(",");
                            line = String.valueOf(bufferedReader.readLine());
                            ligne++;
                        }
                    }else if(line.startsWith(";") && cpt_niveau == niveau){
                        break;
                    }
                    else if (line.startsWith(";")){
                        cpt_niveau++;
                        if(cpt_niveau == niveau){
                            line = bufferedReader.readLine();
                        }
                    }
                    line = bufferedReader.readLine();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getLvl(){
        try{
            File file_Micro = new File("res/MicroCosmos.txt");
            FileReader reader = new FileReader(file_Micro);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line;
            Niveaux.add(new ArrayList<String>());
            int current_lvl = 0;
            int current_size = getSizeOfGrid(current_lvl + 1);

            while ((line = bufferedReader.readLine()) != null){
                if(line.contains(";")){
                    current_lvl++;
                    current_size = getSizeOfGrid(current_lvl + 1);
                }else {
                    for (int i = 0; i < line.length(); ++i){
                        Niveaux.get(current_lvl).add(String.valueOf(line.charAt(i)));
                    }
                    if(line.length() < current_size){
                        int x = line.length();
                        while (x < current_size){
                            Niveaux.get(current_lvl).add(" ");
                            x++;
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void reset() {

    }
}
