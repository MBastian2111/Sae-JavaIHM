/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sae.donnees;

import java.awt.*;

/**
 *
 * @author p2100635
 */
public class TChemin {
    private final TypeRoute typeRoute;
    private final int distance;
    private String arrive;

    public TChemin(String type_route, String distance, String arrive) {
        this.typeRoute = TypeRoute.getTypeRoute(type_route);
        this.distance = Integer.parseInt(distance);
        this.arrive = arrive;
    }
    
    public String getArrive() {
        return arrive;
    }

    public void setArrive(String arrive) {
        this.arrive = arrive;
    }

    public TypeRoute getType_route() {
        return typeRoute;
    }

    public int getDistance() {
        return distance;
    }


    public enum TypeRoute {
        Autre("Inconnu", Color.GRAY),
        D("Départementale", Color.RED),
        N("Nationale", Color.GREEN),
        A("Autoroutes", Color.BLUE);

        public final String nom;
        public final Color couleur;

        TypeRoute(String nom, Color couleur) {
            this.nom = nom;
            this.couleur = couleur;
        }

        public static TypeRoute getTypeRoute(String nom) {
            for (TypeRoute typeRoute : TypeRoute.values()) {
                if (typeRoute.name().equalsIgnoreCase(nom)) {
                    return typeRoute;
                }
            }
            return TypeRoute.Autre;
        }

    }
    
    
    @Override
    public String toString() {
        return " Suivant{" + "type de route=" + typeRoute + ", distance=" + distance + ", arrive=" + arrive + '}';
    }
}
