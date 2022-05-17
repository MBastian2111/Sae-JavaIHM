/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sae.Ouverture;



import sae.donnees.TChemin;
import sae.donnees.TLocalite;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
/**
 *
 * @author p2100635
 */
public class Input {
    private LinkedList<TLocalite> listeNoeuds;

    public Input(File name_file) {
        try{
            java.io.InputStream is = new java.io.FileInputStream(name_file);
            listeNoeuds = insertNoeud(is);
            is.close();
            //System.out.println(listeNoeuds);
        }
        catch(FileNotFoundException e){
            System.out.println("Le fichier n'existe pas !");
        }
        catch(IOException e){
            System.out.println("Erreur d'entrée sortie !");
        }
    }

    public LinkedList<TLocalite> getListeLocalite() {
        return listeNoeuds;
    }

    public void setListeLocalite(LinkedList<TLocalite> listeNoeuds) {
        this.listeNoeuds = listeNoeuds;
    }

    private LinkedList<TLocalite> insertNoeud(InputStream is) throws IOException {
        int unsignedByte;
        String champ;
        LinkedList<TLocalite> listeNoeuds = new LinkedList<>();
        while ((unsignedByte = is.read()) > -1) {
            champ="";
            while ((char) unsignedByte != ';') {
                champ+=((char) unsignedByte);
                unsignedByte=is.read();
            }

            String[] champSepare = champ.split(":");
            String[] infoNoeudInit = champSepare[0].split(",");
            String[] infoLien = champSepare[1].split(",");
            String[] infoNoeudLie = champSepare[3].split(",");
            listeNoeuds.add(new TLocalite(infoNoeudInit[0], infoNoeudInit[1]));

            try {
                listeNoeuds.getLast().ajoutLien(new TChemin(infoLien[0], infoLien[1], infoNoeudLie[1]));
            } catch (NumberFormatException ex) {
                System.out.println("Erreur dans l'insertion des données");
            }
            unsignedByte = is.read();

            while ((char) unsignedByte != '\n'){
                while ((char) unsignedByte == ';'){
                    unsignedByte = is.read();
                }
                if ((char) unsignedByte == '\r'){
                    unsignedByte = is.read();
                    continue;
                }
                champ = "";
                while ((char) unsignedByte != ';'){
                    if ((char) unsignedByte == '\r'){
                        break;
                    }
                    champ+=((char) unsignedByte);
                    unsignedByte=is.read();
                }
                champSepare = champ.split(":");
                infoLien = champSepare[0].split(",");
                infoNoeudLie = champSepare[2].split(",");
                try {
                    listeNoeuds.getLast().ajoutLien(new TChemin(infoLien[0], infoLien[1], infoNoeudLie[1]));
                } catch (NumberFormatException ex) {
                    System.out.println("Erreur dans l'insertion des données");
                }
                unsignedByte = is.read();
            }
        }
        return listeNoeuds;
    }
}
