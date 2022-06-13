/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sae.donnees;

import java.awt.Color;
import java.util.LinkedList;

/**
 *
 * @author p2100635
 */
public class TLocalite {
    private final String nom;
    private final TypeLocalite type;
    private LinkedList<TChemin> voisin;
    private int posX;
    private int posY;

    public TLocalite(String nom, String type, LinkedList<TChemin> voisin) {
        this.nom = nom;
        this.type = TypeLocalite.getTypeLocalite(type);
        //System.out.println(nom + " " + this.type.name());
        this.voisin = voisin;
        this.posX = 0;
        this.posY = 0;
    }

    public TLocalite(String type, String nom) {
        this.nom = nom;
        this.type = TypeLocalite.getTypeLocalite(type);
        //System.out.println(nom + " " + this.type.name());
        this.voisin = new LinkedList();
        this.posX = 0;
        this.posY = 0;
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

    public TypeLocalite getType() {
        return type;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public enum TypeLocalite {
        Autre("Inconnu"),
        V("Villes"),
        R("Restaurants"),
        L("Loisirs");

        public final String nom;

        TypeLocalite(String nom) {
            this.nom = nom;
        }

        public static TypeLocalite getTypeLocalite(String nom) {
            for (TypeLocalite typeLocalite : TypeLocalite.values()) {
                if (typeLocalite.name().equalsIgnoreCase(nom)) {
                    return typeLocalite;
                }
            }
            return Autre;
        }

    }
    
    
    
    @Override
    public String toString() {
        return "TLocalite{" + " Nom=" + nom + ", type de localité=" + type + ", voisin=" + voisin + '}';
    }
    
    
}
