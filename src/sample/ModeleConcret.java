package sample;

import java.io.*;
import java.util.ArrayList;

public class ModeleConcret implements Modele {

    // FONCTIONS À IMPLÉMENTER

    public ArrayList<String> etat;
    public Tuple pos_perso = new Tuple();
    public ArrayList<Tuple> pos_caisses = new ArrayList<Tuple>();
    private ArrayList<ArrayList<String> > Niveaux;
    private int current_LVL;
    private int size_grid = 0;


    ModeleConcret(){
        Niveaux = new ArrayList<>();
        current_LVL = 1;
        getLvl();
        size_grid = getSizeOfGrid(current_LVL);
        getCurrentLevel(current_LVL);
        System.out.println(etat);
        etat = new ArrayList<>();
        getGrid(current_LVL);
        System.out.println(etat);
    }

    private void getCurrentLevel(int current_lvl) {
        int ligne = 0;
        for (int i = 0; i <  Niveaux.get(current_lvl - 1).size(); ++i){
            String s = Niveaux.get(current_lvl - 1).get(i);
            if(s.equals("$")){
                Niveaux.get(current_lvl - 1).set(i," ");
                pos_perso.setPos(i, ligne);
            }else if(s.equals("*")){
                //TODO
            }else if(s.equals("+")){
                Niveaux.get(current_lvl - 1).set(i,".");
                pos_perso.setPos(i, ligne);
            }else if(s.equals(".")){

            }else if(s.equals("@")){
                //TODO
            }
        }

        etat = Niveaux.get(current_lvl - 1);
    }


    public ArrayList<String> getEtat() {
        return etat;
    }

    public ArrayList<Tuple> getMurs() {
        return pos_caisses;
    }

    public Tuple getPosPerso() {
        return pos_perso;
    }

    public int getGridSize() {
        return size_grid;
    }

    public void move(int x, int y) {
        //Si le personnage ne sort pas de la map
        Tuple new_pos = new Tuple(pos_perso.getX()+x, pos_perso.getY()+y);
        if(!((new_pos.getX() < 0) || (new_pos.getY() < 0)) &&
                !((new_pos.getX() > size_grid-1) || (new_pos.getY() > (etat.size()/size_grid)-1)) ){
            //Si le personnage ne se déplace pas contre un mur
            boolean collision = false;
            if(!(etat.get(new_pos.getX()+size_grid*pos_perso.getY()).equals("#")) &&
                    !(etat.get(size_grid*new_pos.getY()+pos_perso.getX()).equals("#"))){
                //Gestion caisses
                for(Tuple t : pos_caisses){
                    if((t.getX() == new_pos.getX()) && (t.getY() == new_pos.getY())){
                        collision = true;
                        boolean collision_caisse = false;
                        //On vérifie que la case suivante n'est pas une caisse
                        for(Tuple t2 : pos_caisses){
                            if(t2.getX() == new_pos.getX()+x && t2.getY() == new_pos.getY()+y){
                                collision_caisse = true;
                            }
                        }
                        //Si la case suivant la caisse est libre
                        if(!collision_caisse && !(etat.get(new_pos.getX()+x+size_grid*(new_pos.getY()+y)).equals("#"))){
                            t.setPos(t.getX()+x, t.getY()+y);
                            pos_perso.setPos(new_pos.getX(),new_pos.getY());
                        }
                    }
                }
                if(!collision){
                    pos_perso.setPos(new_pos.getX(),new_pos.getY());
                }
            }
        }
    }

    private boolean check_fin(){
        for(Tuple t_caisse : pos_caisses){
            boolean is_in_fin = false;
            for(Tuple t_fin : pos_fin){
                if(t_caisse.getX() == t_fin.getX() && t_caisse.getY() == t_fin.getY()){
                    is_in_fin = true;
                }
            }
            if(!is_in_fin){
                return false;
            }
        }
        return true;
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
                            pos_perso.setPos(i, ligne);
                        }
                        else if(line.charAt(i) == '+'){
                            etat.add(".");
                            pos_perso.setPos(i, ligne);
                        }
                        else if(line.charAt(i) == '$'){
                            etat.add(" ");
                            pos_caisses.add(new Tuple(i, ligne));
                        }
                        else if(line.charAt(i) == '*'){
                            etat.add(".");
                            pos_caisses.add(new Tuple(i, ligne));
                        }
                        else {
                            etat.add(String.valueOf(line.charAt(i)));
                        }
                    }
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
                                    pos_perso.setPos(i, ligne);
                                }
                                else if(line.charAt(i) == '+'){
                                    etat.add(".");
                                    pos_perso.setPos(i, ligne);
                                }
                                else if(line.charAt(i) == '$'){
                                    etat.add(" ");
                                    pos_caisses.add(new Tuple(i, ligne));
                                }
                                else if(line.charAt(i) == '*'){
                                    etat.add(".");
                                    pos_caisses.add(new Tuple(i, ligne));
                                }
                                else {
                                    etat.add(String.valueOf(line.charAt(i)));
                                }
                            }
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
                    Niveaux.add(new ArrayList<String>());
                }else {
                    if(line.length() < current_size){
                        while (line.length() < current_size){
                            line += " ";
                        }
                    }

                    for (int i = 0; i < line.length(); ++i){
                        Niveaux.get(current_lvl).add(String.valueOf(line.charAt(i)));
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
