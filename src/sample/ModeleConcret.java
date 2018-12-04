package sample;

import java.io.*;
import java.util.ArrayList;

public class ModeleConcret implements Modele {

    // FONCTIONS À IMPLÉMENTER

    public ArrayList<String> etat = new ArrayList<String>();
    public Tuple pos_perso = new Tuple();
    public ArrayList<Tuple> pos_caisses = new ArrayList<Tuple>();


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
            grille = new ArrayList<>();

            String line = bufferedReader.readLine();
            if (niveau == 1){
                while (line != null && !line.startsWith(";")){
                    if(line.length() < size){
                        while (line.length() < size){
                            line += " ";
                        }
                    }
                    line = line.replace("@", " ");
                    line = line.replace("+", ".");
                    line = line.replace("$", " ");
                    line = line.replace("*", ".");
                    System.out.println(line);
                    grille.add(line);
                    line = String.valueOf(bufferedReader.readLine());
                }

                System.out.println(grille);
            }
            else {
                int cpt_niveau = 1;
                grille = new ArrayList<>();


                while (line != null){
                    if(line.length() < size){
                        while (line.length() < size){
                            line += " ";
                        }
                    }
                    if(cpt_niveau == niveau && !line.startsWith(";")){
                        grille.add(line);
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

                System.out.println(grille);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return grille;
    }

    @Override
    public void reset() {

    }
}
