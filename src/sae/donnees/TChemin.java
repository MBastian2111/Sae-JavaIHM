/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sae.donnees;

/**
 *
 * @author p2100635
 */
public class TChemin {
    private final String type_route;
    private final int distance;
    private String arrive;
    

    public TChemin(String type_route, int distance, String arrive) {
        this.type_route = type_route;
        this.distance = distance;
        this.arrive = arrive;
    }


    public TChemin(String type_route, String distance, String arrive) {
        this.type_route = type_route;
        this.distance = Integer.parseInt(distance);
        this.arrive = arrive;
    }
    
    public String getArrive() {
        return arrive;
    }

    public void setArrive(String arrive) {
        this.arrive = arrive;
    }

    public String getType_route() {
        return type_route;
    }

    public int getDistance() {
        return distance;
    }

    
    
    
    
    @Override
    public String toString() {
        return " Suivant{" + "type de route=" + type_route + ", distance=" + distance + ", arrive=" + arrive + '}';
    }

    
}
