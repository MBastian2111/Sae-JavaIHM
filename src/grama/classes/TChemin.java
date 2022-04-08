/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grama.classes;

/**
 *
 * @author p2105353
 */
public class TChemin {
    private final String type_route;
    private final int distance;
    private TLocalite voisin;
    private TChemin suivant;

    public TChemin(String type_route, int distance, TLocalite voisin, TChemin suivant) {
        this.type_route = type_route;
        this.distance = distance;
        this.voisin = voisin;
        this.suivant = suivant;
    }

    public TLocalite getVoisin() {
        return voisin;
    }

    public void setVoisin(TLocalite voisin) {
        this.voisin = voisin;
    }

    public TChemin getSuivant() {
        return suivant;
    }

    public void setSuivant(TChemin suivant) {
        this.suivant = suivant;
    }


}