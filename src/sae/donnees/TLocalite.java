/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sae.donnees;

import java.util.LinkedList;

/**
 *
 * @author p2100635
 */
public class TLocalite {
    private final String nom;
    private final String type;
    private LinkedList<TChemin> voisin;

    public TLocalite(String nom, String type, LinkedList<TChemin> voisin) {
        this.nom = nom;
        this.type = type;
        this.voisin = voisin;
    }

    public TLocalite(String type, String nom) {
        this.nom = nom;
        this.type = type;
        this.voisin = new LinkedList();
    }

    public LinkedList<TChemin> getVoisin() {
        return voisin;
    }

    public void setVoisin(LinkedList<TChemin> voisin) {
        this.voisin = voisin;
    }
    
    public void ajoutLien(TChemin chem){
        this.voisin.add(chem);
    }

    public String getNom() {
        return nom;
    }

    public String getType() {
        return type;
    }

    
    
    
    @Override
    public String toString() {
        return "TLocalite{" + " Nom=" + nom + ", type de localité=" + type + ", voisin=" + voisin + '}';
    }
    
    
}
