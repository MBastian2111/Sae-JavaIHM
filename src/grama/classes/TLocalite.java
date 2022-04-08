/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grama.classes;
/**
 *
 * @author p2100635
 */
public class TLocalite {
    private final String nom;
    private final String type;
    private TLocalite suivant;
    private TListe tChemin;

    public TLocalite(String nom, String type, TLocalite suivant, TListe voisin) {
        this.nom = nom;
        this.type = type;
        this.suivant = suivant;
        this.tChemin = voisin;
    }

    public TLocalite getSuivant() {
        return suivant;
    }

    public void setSuivant(TLocalite suivant) {
        this.suivant = suivant;
    }
    
    public TListe getVoisin() {
        return tChemin;
    }

    public void setVoisin(TListe modVoisin) {
        this.tChemin = modVoisin;
    }


}