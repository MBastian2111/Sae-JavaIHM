/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sae;

import sae.donnees.ShowVoisins;

/**
 *
 * @author Administrateur
 */
public class OptionsControls {

    public boolean villes = true;
    public boolean restaurant = true;
    public boolean loisirs = true;
    public boolean nationales = true;
    public boolean autoroutes = true;
    public boolean departementales = true;

    public ShowVoisins showVoisins = null;
    public boolean showVoisin2 = false;
    public boolean showVoisin1 = false;
    
    public String firstComparaison = "";
    
    
    @Override
    public String toString() {
        return "OptionsControls{"
                + "villes=" + villes
                + ", restaurant=" + restaurant
                + ", loisirs=" + loisirs
                + ", nationales=" + nationales
                + ", autoroutes=" + autoroutes
                + ", departementales=" + departementales
                + '}';
    }
}
