/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sae.function;

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
    
    public void distance1 (String distance){
        
    }
}
