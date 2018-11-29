package sample;

import java.io.*;
import java.util.ArrayList;

public class ModeleConcret implements Modele {

    // FONCTIONS À IMPLÉMENTER

    public int[] etat = {};

    public int[] getEtat() {
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

    public ArrayList<String> getGrille(int niveau){
        ArrayList<String> grille = null;
        try {
            File file_Micro = new File("res/MicroCosmos.txt");
            FileReader reader = new FileReader(file_Micro);
            BufferedReader bufferedReader = new BufferedReader(reader);
            grille = new ArrayList<>();

            String line = bufferedReader.readLine();
            if (niveau == 1){
                while (line != null && !line.startsWith(";")){
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
