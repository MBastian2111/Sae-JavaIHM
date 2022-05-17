/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sae.function;

import java.util.ArrayList;
import java.util.LinkedList;
import sae.donnees.TChemin;
import sae.donnees.TLocalite;

/**
 *
 * @author p2100635
 */
public class Function {
    private LinkedList<TLocalite> liste;

    public Function() {
        this.liste = null;
    }

    public Function(LinkedList<TLocalite> liste) {
        this.liste = liste;
    }

    public LinkedList<TLocalite> getListe() {
        return liste;
    }

    public void setListe(LinkedList<TLocalite> liste) {
        this.liste = liste;
    }
    
    public void afficheTypeLien(String type){
        int num = 0;
        while(liste.size() > num){
            TLocalite ptliste = liste.get(num);
            int nbrVois = 0;
            while (ptliste.getVoisin().size() > nbrVois){
                TChemin ptliste2 = ptliste.getVoisin().get(nbrVois);
                if(ptliste2.getType_route().equals(type)){
                    if(ptliste2.getType_route().equals('N')){
                        System.out.println("La Nationale allant de " + ptliste.getNom() + " à " + ptliste2.getArrive());
                    } else if (ptliste2.getType_route().equals('A')){
                        System.out.println("L'Autoroute allant de " + ptliste.getNom() + " à " + ptliste2.getArrive());
                    } else {
                        System.out.println("La Départementale allant de " + ptliste.getNom() + " à " + ptliste2.getArrive());
                    }  
                } 
            nbrVois++;    
            }
        num ++;   
        }
    }
    
    
    public void afficheTypeLocalite(String type){
        int num = 0;
        while(liste.size() > num){
            TLocalite ptliste = liste.get(num);
            if(ptliste.getType().equals(type)){
                System.out.println(ptliste.getNom());
            } 
            num ++;
        }
           
    }
    
    
    //Ne gère pas encore les répétitions
    public void distance1 (String lieu){
        int num = 0;
        while(liste.size() > num){
            TLocalite ptliste = liste.get(num);
            if (ptliste.getNom().equals(lieu)){
                int nbrVois = 0;
                while (ptliste.getVoisin().size() > nbrVois){
                    System.out.println("Localité à 1-distance de "+ lieu +" :");
                    TChemin ptlisteroute = ptliste.getVoisin().get(nbrVois);
                    if (ptlisteroute.getDistance() == 1){
                       System.out.println(ptlisteroute.getArrive());
                    } else {
                        System.out.println("Aucune localité à 1-distance");
                    }
                }
                nbrVois++;
            } 
        }
        num++;
    }
    
    
    
    /**public void distance2 (String lieu){
        int num = 0;
        while(liste.size() > num){
            TLocalite ptliste = liste.get(num);
            if (ptliste.getNom().equals(lieu)){
                int nbrVois = 0;
                while (ptliste.getVoisin().size() > nbrVois){
                    System.out.println("Localité à 2-distance de "+ lieu +" :");
                    TChemin ptlisteroute = ptliste.getVoisin().get(nbrVois);
                    if (ptlisteroute.getDistance() == 2){
                       System.out.println(ptlisteroute.getArrive());
                    } else {
                        System.out.println("Aucune localité à 2-distance");
                    }
                }
                nbrVois++;
            } 
        }
        num++;
    }*/
}
