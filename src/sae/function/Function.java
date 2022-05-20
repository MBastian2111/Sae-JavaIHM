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
    
    
    
    
    
    /**
     * Méthode permettant de retourner le nombre de restaurants
     * @return nombre qui s'incrémente au fur et à mesure que des restaurants sont trouvés dans la liste
     */
    public int nombreRestaurant(){
        int num = 0;
        int nombre = 0;
        while(liste.size() > num){
            TLocalite ptliste = liste.get(num);
            if(ptliste.getType().equals('R')){
                nombre++;
            } 
            num ++;
        }
        return nombre;
    }
    
    
    /**
     * Méthode permettant de retourner le nombre de loisirs
     * @return nombre qui s'incrémente au fur et à mesure que des loisirs sont trouvés dans la liste
     */
    public int nombreLoisir(){
        int num = 0;
        int nombre = 0;
        while(liste.size() > num){
            TLocalite ptliste = liste.get(num);
            if(ptliste.getType().equals('L')){
                nombre++;
            } 
            num ++;
        }
        return nombre;
    }
    
    
    /**
     * Méthode permettant de retourner le nombre de villes
     * @return nombre qui s'incrémente au fur et à mesure que des villes sont trouvées dans la liste
     */
    public int nombreVille(){
        int num = 0;
        int nombre = 0;
        while(liste.size() > num){
            TLocalite ptliste = liste.get(num);
            if(ptliste.getType().equals('V')){
                nombre++;
            } 
            num ++;
        }
        return nombre;
    }
    
    /**
     * Méthode permettant de compter le nombre de nationales
     * @return nombre qui s'incrémente au fur et à mesure que des nationales sont trouvées dans la liste
     */
    public int nombreNationale(){
        int num = 0;
        int nombre = 0;
        while(liste.size() > num){
            TLocalite ptliste = liste.get(num);
            int nbrVois = 0;
            while (ptliste.getVoisin().size() > nbrVois){
                TChemin ptliste2 = ptliste.getVoisin().get(nbrVois);
                if(ptliste2.getType_route().equals('N')){
                    nombre++;
                } 
            nbrVois++;    
            }
        num ++;   
        }
        return nombre;
    }
    
    /**
     * Méthode permettant de compter le nombre de autoroutes
     * @return nombre qui s'incrémente au fur et à mesure que des autoroutes sont trouvées dans la liste
     */
    public int nombreAutoroute(){
        int num = 0;
        int nombre = 0;
        while(liste.size() > num){
            TLocalite ptliste = liste.get(num);
            int nbrVois = 0;
            while (ptliste.getVoisin().size() > nbrVois){
                TChemin ptliste2 = ptliste.getVoisin().get(nbrVois);
                if(ptliste2.getType_route().equals('A')){
                    nombre++;
                } 
            nbrVois++;    
            }
        num ++;   
        }
        return nombre;
    }
    
    /**
     * Méthode permettant de compter le nombre de départementale
     * @return nombre qui s'incrémente au fur et à mesure que des départmentale sont trouvées dans la liste
     */
    public int nombreDepartementale(){
        int num = 0;
        int nombre = 0;
        while(liste.size() > num){
            TLocalite ptliste = liste.get(num);
            int nbrVois = 0;
            while (ptliste.getVoisin().size() > nbrVois){
                TChemin ptliste2 = ptliste.getVoisin().get(nbrVois);
                if(ptliste2.getType_route().equals('D')){
                    nombre++;
                } 
            nbrVois++;    
            }
        num ++;   
        }
        return nombre;
    }
    
    
    
    //Méthode permettant d'afficher tous les liens qui sont des nationales
    public void afficheNationale(String type){
        int num = 0;
        while(liste.size() > num){
            TLocalite ptliste = liste.get(num);
            int nbrVois = 0;
            while (ptliste.getVoisin().size() > nbrVois){
                TChemin ptliste2 = ptliste.getVoisin().get(nbrVois);
                if(ptliste2.getType_route().equals('N')){
                   System.out.println("Nationale allant de " + ptliste.getNom() + " à " + ptliste2.getArrive());   
                } 
            nbrVois++;    
            }
        num ++;   
        }
    }
    
    
    //Méthode permettant d'afficher tous les liens qui sont des autoroutes
    public void afficheAutoroute(){
        int num = 0;
        while(liste.size() > num){
            TLocalite ptliste = liste.get(num);
            int nbrVois = 0;
            while (ptliste.getVoisin().size() > nbrVois){
                TChemin ptliste2 = ptliste.getVoisin().get(nbrVois);
                if(ptliste2.getType_route().equals('A')){
                    System.out.println("Autoroute allant de " + ptliste.getNom() + " à " + ptliste2.getArrive());    
                } 
            nbrVois++;    
            }
        num ++;   
        }
    }
    
    
    //Méthode permettant d'afficher tous les liens qui sont des départementales
    public void afficheDepartementale(){
        int num = 0;
        while(liste.size() > num){
            TLocalite ptliste = liste.get(num);
            int nbrVois = 0;
            while (ptliste.getVoisin().size() > nbrVois){
                TChemin ptliste2 = ptliste.getVoisin().get(nbrVois);
                if(ptliste2.getType_route().equals('D')){
                    System.out.println("Departementale allant de " + ptliste.getNom() + " à " + ptliste2.getArrive());    
                } 
            nbrVois++;    
            }
        num ++;   
        }
    }
    
    
    
    //Méthode permettant d'afficher toutes les localités qui sont des restaurants
    public void afficheRestaurant(){
        int num = 0;
        System.out.println("La liste de tous les restaurants: ");
        while(liste.size() > num){
            TLocalite ptliste = liste.get(num);
            if(ptliste.getType().equals('R')){
                System.out.print(ptliste.getNom() + ", ");
            } 
            num ++;
        }
           
    }
    
    //Méthode permettant d'afficher toutes les localités qui sont des loisirs
    public void afficheLoisir(){
        int num = 0;
        System.out.println("La liste de tous les loisirs: ");
        while(liste.size() > num){
            TLocalite ptliste = liste.get(num);
            if(ptliste.getType().equals('L')){
                System.out.print(ptliste.getNom() + ", ");
            } 
            num ++;
        }
           
    }
    
    //Méthode permettant d'afficher toutes les localités qui sont des villes
    public void afficheVille(){
        int num = 0;
        System.out.println("La liste de toutes les villes: ");
        while(liste.size() > num){
            TLocalite ptliste = liste.get(num);
            if(ptliste.getType().equals('V')){
                System.out.print(ptliste.getNom() + ", ");
            } 
            num ++;
        }
           
    }
    
    //Méthode permettant de renvoyer un lieu recherché 
    public TLocalite RenvoiLocalite(String localite){
        int num = 0;
        while(liste.size() > num){
            TLocalite ptliste = liste.get(num);
            if (ptliste.getNom().equals(localite)){
                return ptliste;
            }
        }
        return null;
    }
    
    //Méthode permettant d'afficher toutes les localités se trouvant à un seul lien d'écart du lieu demandé
    public void distance1 (String lieu){
        int num = 0;
        int nombre = 0;
        while(liste.size() > num){
            TLocalite ptliste = liste.get(num);
            if (ptliste.getNom().equals(lieu)){
                int nbrVois = 0;
                System.out.println("Localité à 1-distance de "+ lieu +" :");
                while (ptliste.getVoisin().size() > nbrVois){
                    TChemin ptlisteroute = ptliste.getVoisin().get(nbrVois);
                    System.out.println(ptlisteroute.getArrive());
                    nombre++;
                }
                nbrVois++;
            } 
            System.out.println("Il y a " + nombre + " localité qui se trouve à un lien de " + ptliste.getNom());
        }
        num++;
    }
    
    //Méthode permettant d'afficher toutes les localités selon leur type demandé à un distance du lieu demandé
    public void distance1 (String lieu, String locaChercher){
        int num = 0;
        while(liste.size() > num){
            TLocalite ptliste = liste.get(num);
            if (ptliste.getNom().equals(lieu)){
                int nbrVois = 0;
                while (ptliste.getVoisin().size() > nbrVois){
                    System.out.println(locaChercher + " à 1-distance de "+ lieu +" :");
                    TChemin ptlisteroute = ptliste.getVoisin().get(nbrVois);
                    String chercher = ptlisteroute.getArrive();
                    int parcours = 0;
                    while (liste.size() > parcours){
                        TLocalite ptliste2 = liste.get(parcours);
                        if (ptliste2.getType().equals(locaChercher) && ptliste2.getNom().equals(chercher)){
                            System.out.println(ptlisteroute.getArrive());
                        } 
                    }
                    parcours++;
                }
                nbrVois++;
            } 
        }
        num++;
    }
    
    
    //Méthode permettant d'afficher
    public void distance2 (String lieu){
        int num = 0;
        while(liste.size() > num){
            TLocalite ptliste = liste.get(num);
            if (ptliste.getNom().equals(lieu)){
                int nbrVois = 0;
                while (ptliste.getVoisin().size() > nbrVois){
                    int num2 = 0;
                    TChemin ptlisteroute = ptliste.getVoisin().get(nbrVois);
                    String distDeux = ptlisteroute.getArrive();
                    while (liste.size() > num2){
                        TLocalite ptliste2 = liste.get(num2);
                        if (ptliste2.getNom().equals(distDeux)){
                            int nbrVois2 = 0;
                            while (ptliste.getVoisin().size() > nbrVois2){
                                TChemin ptlisteroute2 = ptliste2.getVoisin().get(nbrVois2);
                                System.out.println(ptlisteroute2.getArrive());
                            }
                            nbrVois2++;
                        } 
                    }
                    num2++;
                }
                nbrVois++;
            } 
        }
        num++;
    }
    
    
    
    public void distance2 (String lieu, String locaChercher){
        int num = 0;
        while(liste.size() > num){
            TLocalite ptliste = liste.get(num);
            if (ptliste.getNom().equals(lieu)){
                int nbrVois = 0;
                while (ptliste.getVoisin().size() > nbrVois){
                    int num2 = 0;
                    TChemin ptlisteroute = ptliste.getVoisin().get(nbrVois);
                    String distDeux = ptlisteroute.getArrive();
                    while (liste.size() > num2){
                        TLocalite ptliste2 = liste.get(num2);
                        if (ptliste2.getNom().equals(distDeux)){
                            int nbrVois2 = 0;
                            while (ptliste.getVoisin().size() > nbrVois2){
                                TChemin ptlisteroute2 = ptliste2.getVoisin().get(nbrVois2);
                                String chercher = ptlisteroute2.getArrive();
                                int parcours = 0;
                                while (liste.size() > parcours){
                                    TLocalite ptliste3 = liste.get(parcours);
                                    if (ptliste3.getNom().equals(chercher) && ptliste3.getType().equals(locaChercher)){
                                        System.out.println(ptliste3.getNom());
                                    }
                                }
                                parcours++;
                            }
                            nbrVois2++;
                        } 
                    }
                    num2++;
                }
                nbrVois++;
            } 
        }
        num++;
    }
    
}
