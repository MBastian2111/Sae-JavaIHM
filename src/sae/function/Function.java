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
    
    /**
     * Instanciation de la variable private liste comme étant une LinkedList de TLocalite
     */
    private LinkedList<TLocalite> liste;

    
    //Constructeur par défaut de la classe function
    public Function() {
        this.liste = null;
    }

    /**
     * Constructeur de la classe function
     * @param liste étant une liste pouvant etre parcouru et contenant des TLocalite
     */
    public Function(LinkedList<TLocalite> liste) {
        this.liste = liste;
    }

    //Getters et Setters de liste
    
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
        //Permet le parcours de la liste
        while(liste.size() > num){
            //Création d'un pointeur pouvant prendre les localité de la liste
            TLocalite ptliste = liste.get(num);
            //Permet de vérifier si la localité est bien un restaurant
            if(ptliste.getType().equals('R')){
                nombre++;
            } 
            num ++;
        }
        //retourne le nombre de restaurant
        return nombre;
    }
    
    
    /**
     * Méthode permettant de retourner le nombre de loisirs
     * @return nombre qui s'incrémente au fur et à mesure que des loisirs sont trouvés dans la liste
     */
    public int nombreLoisir(){
        int num = 0;
        int nombre = 0;
        //Permet le parcours de la liste
        while(liste.size() > num){
            //Création d'un pointeur pouvant prendre les localité de la liste
            TLocalite ptliste = liste.get(num);
            //Permet de vérifier si la localité est bien un loisir
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
        //Permet le parcours de la liste
        while(liste.size() > num){
            //Création d'un pointeur pouvant prendre les localité de la liste
            TLocalite ptliste = liste.get(num);
            //Permet de vérifier si la localité est bien une ville
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
        //Permet le parcours de la liste
        while(liste.size() > num){
            //Création d'un pointeur pouvant prendre les localité de la liste
            TLocalite ptliste = liste.get(num);
            int nbrVois = 0;
            //Permet le parcours des voisins de la localité pointé par ptliste
            while (ptliste.getVoisin().size() > nbrVois){
                //Création d'un pointeur pouvant parcourir les chemins de la localité pointé par ptliste
                TChemin ptliste2 = ptliste.getVoisin().get(nbrVois);
                //Vérifie si le chemin pointé par ptliste2 est bien une nationale
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
        //Permet le parcours de la liste
        while(liste.size() > num){
            //Création d'un pointeur pouvant prendre les localité de la liste
            TLocalite ptliste = liste.get(num);
            int nbrVois = 0;
            //Permet le parcours des voisins de la localité pointé par ptliste
            while (ptliste.getVoisin().size() > nbrVois){
                //Création d'un pointeur pouvant parcourir les chemins de la localité pointé par ptliste
                TChemin ptliste2 = ptliste.getVoisin().get(nbrVois);
                //Vérifie si le chemin pointé par ptliste2 est bien une autoroute
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
        //Permet le parcours de la liste
        while(liste.size() > num){
            //Création d'un pointeur pouvant prendre les localité de la liste
            TLocalite ptliste = liste.get(num);
            int nbrVois = 0;
            //Permet le parcours des voisins de la localité pointé par ptliste
            while (ptliste.getVoisin().size() > nbrVois){
                //Création d'un pointeur pouvant parcourir les chemins de la localité pointé par ptliste
                TChemin ptliste2 = ptliste.getVoisin().get(nbrVois);
                //Vérifie si le chemin pointé par ptliste2 est bien une départementale
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
        //Permet le parcours de la liste
        while(liste.size() > num){
            //Création d'un pointeur pouvant prendre les localité de la liste
            TLocalite ptliste = liste.get(num);
            int nbrVois = 0;
            //Permet le parcours des voisins de la localité pointé par ptliste
            while (ptliste.getVoisin().size() > nbrVois){
                //Création d'un pointeur pouvant parcourir les chemins de la localité pointé par ptliste
                TChemin ptliste2 = ptliste.getVoisin().get(nbrVois);
                //Vérifie si le chemin pointé par ptliste2 est bien une départementale
                if(ptliste2.getType_route().equals('N')){
                    //Affiche le point de départ de la nationale et l'arrivé en prenant le nom de la localité sur laquelle pointe ptliste et l'arrive associé au chemin pointé par ptliste2
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
        //Permet le parcours de la liste
        while(liste.size() > num){
            //Création d'un pointeur pouvant prendre les localité de la liste
            TLocalite ptliste = liste.get(num);
            int nbrVois = 0;
            //Permet le parcours des voisins de la localité pointé par ptliste
            while (ptliste.getVoisin().size() > nbrVois){
                //Création d'un pointeur pouvant parcourir les chemins de la localité pointé par ptliste
                TChemin ptliste2 = ptliste.getVoisin().get(nbrVois);
                //Vérifie si le chemin pointé par ptliste2 est bien une autoroute
                if(ptliste2.getType_route().equals('A')){
                    //Affiche le point de départ de l'autoroute et l'arrivé en prenant le nom de la localité sur laquelle pointe ptliste et l'arrive associé au chemin pointé par ptliste2
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
        //Permet le parcours de la liste
        while(liste.size() > num){
            //Création d'un pointeur pouvant prendre les localité de la liste
            TLocalite ptliste = liste.get(num);
            int nbrVois = 0;
            //Permet le parcours des voisins de la localité pointé par ptliste
            while (ptliste.getVoisin().size() > nbrVois){
                //Création d'un pointeur pouvant parcourir les chemins de la localité pointé par ptliste
                TChemin ptliste2 = ptliste.getVoisin().get(nbrVois);
                //Vérifie si le chemin pointé par ptliste2 est bien une autoroute
                if(ptliste2.getType_route().equals('D')){
                    //Affiche le point de départ de la départementale et l'arrivé en prenant le nom de la localité sur laquelle pointe ptliste et l'arrive associé au chemin pointé par ptliste2
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
        //Permet le parcours de la liste
        while(liste.size() > num){
            //Création d'un pointeur pouvant prendre les localité de la liste
            TLocalite ptliste = liste.get(num);
            //Vérifie si la localité est un restaurant
            if(ptliste.getType().equals('R')){
                //Affiche le nom du restaurant pointé par ptliste
                System.out.print(ptliste.getNom() + ", ");
            } 
            num ++;
        }
           
    }
    
    //Méthode permettant d'afficher toutes les localités qui sont des loisirs
    public void afficheLoisir(){
        int num = 0;
        System.out.println("La liste de tous les loisirs: ");
        //Permet le parcours de la liste
        while(liste.size() > num){
            //Création d'un pointeur pouvant prendre les localité de la liste
            TLocalite ptliste = liste.get(num);
            //Vérifie si la localité est un loisir
            if(ptliste.getType().equals('L')){
                //Affiche le nom du loisir pointé par ptliste
                System.out.print(ptliste.getNom() + ", ");
            } 
            num ++;
        }
           
    }
    
    //Méthode permettant d'afficher toutes les localités qui sont des villes
    public void afficheVille(){
        int num = 0;
        System.out.println("La liste de toutes les villes: ");
        //Permet le parcours de la liste
        while(liste.size() > num){
            //Création d'un pointeur pouvant prendre les localité de la liste
            TLocalite ptliste = liste.get(num);
            //Vérifie si la localité est une ville
            if(ptliste.getType().equals('V')){
                //Affiche le nom de la ville pointé par ptliste
                System.out.print(ptliste.getNom() + ", ");
            } 
            num ++;
        }
           
    }
    
    //Méthode permettant de renvoyer un lieu recherché 
    public TLocalite RenvoiLocalite(String localite){
        int num = 0;
        //Permet le parcours de la liste
        while(liste.size() > num){
            //Création d'un pointeur pouvant prendre les localité de la liste
            TLocalite ptliste = liste.get(num);
            //Vérifie si la localité pointé par ptliste est identique à la recherche
            if (ptliste.getNom().equals(localite)){
                //retourne le localité recherché
                return ptliste;
            }
        }
        return null;
    }
    
    //Méthode permettant d'afficher toutes les localités se trouvant à un seul lien d'écart du lieu demandé
    public void distance1 (String lieu){
        int num = 0;
        int nombre = 0;
        //Permet le parcours de la liste
        while(liste.size() > num){
            //Création d'un pointeur pouvant prendre les localité de la liste
            TLocalite ptliste = liste.get(num);
            //Vérifie si la localité pointé par ptliste est identique à la recherche
            if (ptliste.getNom().equals(lieu)){
                int nbrVois = 0;
                System.out.println("Localité à 1-distance de "+ lieu +" :");
                //Permet le parcours des voisins de la localité pointé par ptliste
                while (ptliste.getVoisin().size() > nbrVois){
                    //Création d'un pointeur pouvant parcourir les chemins de la localité pointé par ptliste
                    TChemin ptlisteroute = ptliste.getVoisin().get(nbrVois);
                    //Affichage des voisins directes de la localité pointé par ptliste par l'arrivé dans le chemin pointé par ptlisteroute
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
        //Permet le parcours de la liste
        while(liste.size() > num){
            //Création d'un pointeur pouvant prendre les localité de la liste
            TLocalite ptliste = liste.get(num);
            //Vérifie si la localité pointé par ptliste est identique à la recherche
            if (ptliste.getNom().equals(lieu)){
                int nbrVois = 0;
                //Permet le parcours des voisins de la localité pointé par ptliste
                while (ptliste.getVoisin().size() > nbrVois){
                    System.out.println(locaChercher + " à 1-distance de "+ lieu +" :");
                    //Création d'un pointeur pouvant parcourir les chemins de la localité pointé par ptliste
                    TChemin ptlisteroute = ptliste.getVoisin().get(nbrVois);
                    //Création d'une variable de texte qui va prendre le nom du voisin
                    String chercher = ptlisteroute.getArrive();
                    int parcours = 0;
                    //Permet un nouveau parcours de la liste
                    while (liste.size() > parcours){
                        //Création d'un nouveau pointeur pouvant prendre les localité de la liste
                        TLocalite ptliste2 = liste.get(parcours);
                        //Vérifie si la localité pointé par ptliste2 à un type identique à celui recherché et que le nom de la localité est la même
                        if (ptliste2.getType().equals(locaChercher) && ptliste2.getNom().equals(chercher)){
                            //Affiche les noms des voisins par l'arrive du chemin pointé par ptlisteroute
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
    
    
    //Méthode permettant d'afficher les localités à 2 ditances du lieu demandé
    public void distance2 (String lieu){
        int num = 0;
        //Permet le parcours de la liste
        while(liste.size() > num){
            //Création d'un pointeur pouvant prendre les localité de la liste
            TLocalite ptliste = liste.get(num);
            //Vérifie que la localité pointé par ptliste est identique au lieu cherché
            if (ptliste.getNom().equals(lieu)){
                int nbrVois = 0;
                //Permet le parcours des voisins de la localité pointé par ptliste
                while (ptliste.getVoisin().size() > nbrVois){
                    int num2 = 0;
                    //Création d'un pointeur pouvant parcourir les chemins de la localité pointé par ptliste
                    TChemin ptlisteroute = ptliste.getVoisin().get(nbrVois);
                    //Création d'une variable de texte qui va prendre le nom du voisin
                    String distDeux = ptlisteroute.getArrive();
                    //Permet un nouveau parcours de la liste
                    while (liste.size() > num2){
                        //Création d'un nouveau pointeur pouvant prendre les localité de la liste
                        TLocalite ptliste2 = liste.get(num2);
                        //Vérifie si la localité est bien le voisin de ptliste recherché
                        if (ptliste2.getNom().equals(distDeux)){
                            int nbrVois2 = 0;
                            //Permet un nouveau parcours des voisins de la localité pointé par ptliste2
                            while (ptliste.getVoisin().size() > nbrVois2){
                                //Création d'un pointeur pouvant parcourir les chemins de la localité pointé par ptliste2
                                TChemin ptlisteroute2 = ptliste2.getVoisin().get(nbrVois2);
                                //Affichage de l'arrivé du chemin pointé par ptlisteroute2 (affiche du nom du voisin de la localité)
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
    
    
    //Méthode permettant d'afficher les localités selon le type cherché à 2 ditances du lieu demandé
    public void distance2 (String lieu, String locaChercher){
        int num = 0;
        //Permet le parcours de la liste
        while(liste.size() > num){
            //Création d'un pointeur pouvant prendre les localité de la liste
            TLocalite ptliste = liste.get(num);
            //Vérifie que la localité pointé par ptliste est identique au lieu cherché
            if (ptliste.getNom().equals(lieu)){
                int nbrVois = 0;
                //Permet le parcours des voisins de la localité pointé par ptliste
                while (ptliste.getVoisin().size() > nbrVois){
                    int num2 = 0;
                    //Création d'un pointeur pouvant parcourir les chemins de la localité pointé par ptliste
                    TChemin ptlisteroute = ptliste.getVoisin().get(nbrVois);
                    //Création d'une variable de texte qui va prendre le nom du voisin pointé par ptlisteroute
                    String distDeux = ptlisteroute.getArrive();
                    //Permet un nouveau parcours de liste
                    while (liste.size() > num2){
                        //Création d'un nouveau pointeur pouvant prendre les localité de la liste
                        TLocalite ptliste2 = liste.get(num2);
                        //Vérifie si la localité est identique au voisin recherché
                        if (ptliste2.getNom().equals(distDeux)){
                            int nbrVois2 = 0;
                            //Permet un nouveau parcours des voisins de la localité pointé par ptliste2
                            while (ptliste.getVoisin().size() > nbrVois2){
                                //Création d'un pointeur pouvant parcourir les chemins de la localité pointé par ptliste2
                                TChemin ptlisteroute2 = ptliste2.getVoisin().get(nbrVois2);
                                //Création d'une variable de texte qui va prendre le nom du voisin pointé par ptlisteroute2
                                String chercher = ptlisteroute2.getArrive();
                                int parcours = 0;
                                //Nouveau parcours de liste
                                while (liste.size() > parcours){
                                    //Création d'un nouveau pointeur pouvant prendre les localité de la liste
                                    TLocalite ptliste3 = liste.get(parcours);
                                    //Vérifie si la localité pointé par ptliste3 est identique au voisin cherché et si son type est égale au type cherché
                                    if (ptliste3.getNom().equals(chercher) && ptliste3.getType().equals(locaChercher)){
                                        //Affichage du nom de la localité pointé par ptliste3
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
