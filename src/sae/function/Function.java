/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sae.function;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import sae.Ecran.EcranPrincipal;
import sae.Helper;
import sae.LinkFunction;
import sae.donnees.TChemin;
import sae.donnees.TLocalite;

/**
 *
 * @author p2100635
 */
public class Function {

    /**
     * Instanciation de la variable private liste comme étant une LinkedList de
     * TLocalite
     */
    private LinkedList<TLocalite> liste;

    //Constructeur par défaut de la classe function
    public Function() {
        this.liste = (EcranPrincipal.instance.slate.show == null) ? new LinkedList() : new LinkedList<>(EcranPrincipal.instance.slate.show);
    }

    /**
     * Constructeur de la classe function
     *
     * @param liste étant une liste pouvant etre parcouru et contenant des
     * TLocalite
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

    public LinkedList<TChemin> getChemins() {
        LinkedList<TChemin> returned = new LinkedList<>();
        for (TLocalite premier : liste) {
            LinkedList<TChemin> chemins = new LinkedList<>(premier.getVoisin());
            if (!EcranPrincipal.instance.optionsControls.departementales) {
                chemins.removeIf(n -> n.getType_route() == TChemin.TypeRoute.D);
            }
            if (!EcranPrincipal.instance.optionsControls.nationales) {
                chemins.removeIf(n -> n.getType_route() == TChemin.TypeRoute.N);
            }
            if (!EcranPrincipal.instance.optionsControls.autoroutes) {
                chemins.removeIf(n -> n.getType_route() == TChemin.TypeRoute.A);
            }
            for (TChemin chemin : chemins) {
                for (TLocalite voisin : liste) {
                    if (voisin.getNom().equals(chemin.getArrive())) {
                        returned.add(chemin);
                    }
                }
            }
        }
        return returned;
    }

    /**
     * Méthode permettant de retourner le nombre de restaurants
     *
     * @return nombre qui s'incrémente au fur et à mesure que des restaurants
     * sont trouvés dans la liste
     */
    public int nombreRestaurant() {
        int num = 0;
        int nombre = 0;
        //Permet le parcours de la liste
        while (liste.size() > num) {
            //Création d'un pointeur pouvant prendre les localité de la liste
            TLocalite ptliste = liste.get(num);
            //Permet de vérifier si la localité est bien un restaurant
            if (ptliste.getType().equals(TLocalite.TypeLocalite.R)) {
                nombre++;
            }
            num++;
        }
        //retourne le nombre de restaurant
        return nombre;
    }

    /**
     * Méthode permettant de retourner le nombre de loisirs
     *
     * @return nombre qui s'incrémente au fur et à mesure que des loisirs sont
     * trouvés dans la liste
     */
    public int nombreLoisir() {
        int num = 0;
        int nombre = 0;
        //Permet le parcours de la liste
        while (liste.size() > num) {
            //Création d'un pointeur pouvant prendre les localité de la liste
            TLocalite ptliste = liste.get(num);
            //Permet de vérifier si la localité est bien un loisir
            if (ptliste.getType().equals(TLocalite.TypeLocalite.L)) {
                nombre++;
            }
            num++;
        }
        return nombre;
    }

    /**
     * Méthode permettant de retourner le nombre de villes
     *
     * @return nombre qui s'incrémente au fur et à mesure que des villes sont
     * trouvées dans la liste
     */
    public int nombreVille() {
        int num = 0;
        int nombre = 0;
        //Permet le parcours de la liste
        while (liste.size() > num) {
            //Création d'un pointeur pouvant prendre les localité de la liste
            TLocalite ptliste = liste.get(num);
            //Permet de vérifier si la localité est bien une ville
            if (ptliste.getType().equals(TLocalite.TypeLocalite.V)) {
                nombre++;
            }
            num++;
        }
        return nombre;
    }

    public ComboBoxModel<String> getModel() {
        List<String> options = liste.stream().map(TLocalite::getNom).collect(Collectors.toList());
        options.add(0, "Chargement...");
        return new DefaultComboBoxModel<>(options.toArray(String[]::new));
    }

    public ComboBoxModel<String> getComparaisonModel() {
        List<String> options = liste.stream().filter((o) -> o.getType() == TLocalite.TypeLocalite.V).map(TLocalite::getNom).collect(Collectors.toList());
        options.add(0, "Chargement...");
        options.remove(EcranPrincipal.instance.optionsControls.firstComparaison);
        return new DefaultComboBoxModel<>(options.toArray(String[]::new));
    }

    public int _nombreNationale() {
        return (int) getChemins().stream().filter(c -> c.getType_route() == TChemin.TypeRoute.N).count();
    }

    public int _nombreDepartementale() {
        return (int) getChemins().stream().filter(c -> c.getType_route() == TChemin.TypeRoute.D).count();
    }

    public int _nombreAutoroute() {
        return (int) getChemins().stream().filter(c -> c.getType_route() == TChemin.TypeRoute.A).count();
    }

    /**
     * Méthode permettant de compter le nombre de nationales
     *
     * @return nombre qui s'incrémente au fur et à mesure que des nationales
     * sont trouvées dans la liste
     */
    /**
     * public int nombreNationale(){ if
     * (!EcranPrincipal.instance.optionsControls.nationales) return 0; int num =
     * 0; int nombre = 0; //Permet le parcours de la liste while(liste.size() >
     * num){ //Création d'un pointeur pouvant prendre les localité de la liste
     * TLocalite ptliste = liste.get(num); int nbrVois = 0; //Permet le parcours
     * des voisins de la localité pointé par ptliste while
     * (ptliste.getVoisin().size() > nbrVois){ //Création d'un pointeur pouvant
     * parcourir les chemins de la localité pointé par ptliste TChemin ptliste2
     * = ptliste.getVoisin().get(nbrVois); //Vérifie si le chemin pointé par
     * ptliste2 est bien une nationale
     * if(ptliste2.getType_route().equals(TChemin.TypeRoute.N)){ nombre++; }
     * nbrVois++; } num ++; } return nombre;
    }
     */
    /**
     * Méthode permettant de compter le nombre de autoroutes
     *
     * @return nombre qui s'incrémente au fur et à mesure que des autoroutes
     * sont trouvées dans la liste
     */
    /**
     * public int nombreAutoroute(){ if
     * (!EcranPrincipal.instance.optionsControls.autoroutes) return 0; int num =
     * 0; int nombre = 0; //Permet le parcours de la liste while(liste.size() >
     * num){ //Création d'un pointeur pouvant prendre les localité de la liste
     * TLocalite ptliste = liste.get(num); int nbrVois = 0; //Permet le parcours
     * des voisins de la localité pointé par ptliste while
     * (ptliste.getVoisin().size() > nbrVois){ //Création d'un pointeur pouvant
     * parcourir les chemins de la localité pointé par ptliste TChemin ptliste2
     * = ptliste.getVoisin().get(nbrVois); //Vérifie si le chemin pointé par
     * ptliste2 est bien une autoroute
     * if(ptliste2.getType_route().equals(TChemin.TypeRoute.A)){ nombre++; }
     * nbrVois++; } num ++; } return nombre;
    }
     */
    /**
     * Méthode permettant de compter le nombre de départementale
     *
     * @return nombre qui s'incrémente au fur et à mesure que des départmentale
     * sont trouvées dans la liste
     */
    /**
     * public int nombreDepartementale(){ if
     * (!EcranPrincipal.instance.optionsControls.departementales) return 0; int
     * num = 0; int nombre = 0; //Permet le parcours de la liste
     * while(liste.size() > num){ //Création d'un pointeur pouvant prendre les
     * localité de la liste TLocalite ptliste = liste.get(num); int nbrVois = 0;
     * //Permet le parcours des voisins de la localité pointé par ptliste while
     * (ptliste.getVoisin().size() > nbrVois){ //Création d'un pointeur pouvant
     * parcourir les chemins de la localité pointé par ptliste TChemin ptliste2
     * = ptliste.getVoisin().get(nbrVois); //Vérifie si le chemin pointé par
     * ptliste2 est bien une départementale
     * if(ptliste2.getType_route().equals(TChemin.TypeRoute.D)){ nombre++; }
     * nbrVois++; } num ++; } return nombre;
    }
     */
    //Méthode permettant d'afficher tous les liens qui sont des nationales
    public void afficheNationale(String type) {
        int num = 0;
        //Permet le parcours de la liste
        while (liste.size() > num) {
            //Création d'un pointeur pouvant prendre les localité de la liste
            TLocalite ptliste = liste.get(num);
            int nbrVois = 0;
            //Permet le parcours des voisins de la localité pointé par ptliste
            while (ptliste.getVoisin().size() > nbrVois) {
                //Création d'un pointeur pouvant parcourir les chemins de la localité pointé par ptliste
                TChemin ptliste2 = ptliste.getVoisin().get(nbrVois);
                //Vérifie si le chemin pointé par ptliste2 est bien une départementale
                if (ptliste2.getType_route().equals('N')) {
                    //Affiche le point de départ de la nationale et l'arrivé en prenant le nom de la localité sur laquelle pointe ptliste et l'arrive associé au chemin pointé par ptliste2
                    System.out.println("Nationale allant de " + ptliste.getNom() + " à " + ptliste2.getArrive());
                }
                nbrVois++;
            }
            num++;
        }
    }

    //Méthode permettant d'afficher tous les liens qui sont des autoroutes
    public void afficheAutoroute() {
        int num = 0;
        //Permet le parcours de la liste
        while (liste.size() > num) {
            //Création d'un pointeur pouvant prendre les localité de la liste
            TLocalite ptliste = liste.get(num);
            int nbrVois = 0;
            //Permet le parcours des voisins de la localité pointé par ptliste
            while (ptliste.getVoisin().size() > nbrVois) {
                //Création d'un pointeur pouvant parcourir les chemins de la localité pointé par ptliste
                TChemin ptliste2 = ptliste.getVoisin().get(nbrVois);
                //Vérifie si le chemin pointé par ptliste2 est bien une autoroute
                if (ptliste2.getType_route().equals('A')) {
                    //Affiche le point de départ de l'autoroute et l'arrivé en prenant le nom de la localité sur laquelle pointe ptliste et l'arrive associé au chemin pointé par ptliste2
                    System.out.println("Autoroute allant de " + ptliste.getNom() + " à " + ptliste2.getArrive());
                }
                nbrVois++;
            }
            num++;
        }
    }

    //Méthode permettant d'afficher tous les liens qui sont des départementales
    public ArrayList<String> afficheDepartementale() {
        int num = 0;
        ArrayList<String> departementale = new ArrayList<>();
        //Permet le parcours de la liste
        while (liste.size() > num) {
            //Création d'un pointeur pouvant prendre les localité de la liste
            TLocalite ptliste = liste.get(num);
            int nbrVois = 0;
            //Permet le parcours des voisins de la localité pointé par ptliste
            while (ptliste.getVoisin().size() > nbrVois) {
                //Création d'un pointeur pouvant parcourir les chemins de la localité pointé par ptliste
                TChemin ptliste2 = ptliste.getVoisin().get(nbrVois);
                //Vérifie si le chemin pointé par ptliste2 est bien une autoroute
                if (ptliste2.getType_route().equals('D')) {
                    //Affiche le point de départ de la départementale et l'arrivé en prenant le nom de la localité sur laquelle pointe ptliste et l'arrive associé au chemin pointé par ptliste2
                    System.out.println("Departementale allant de " + ptliste.getNom() + " à " + ptliste2.getArrive());
                }
                nbrVois++;
            }
            num++;
        }
        return departementale;
    }

    //Méthode permettant d'afficher toutes les localités qui sont des restaurants
    public ArrayList<String> afficheRestaurant() {
        int num = 0;
        ArrayList<String> restaurant = new ArrayList<>();
        System.out.println("La liste de tous les restaurants: ");
        //Permet le parcours de la liste
        while (liste.size() > num) {
            //Création d'un pointeur pouvant prendre les localité de la liste
            TLocalite ptliste = liste.get(num);
            //Vérifie si la localité est un restaurant
            if (ptliste.getType().equals('R')) {
                //Affiche le nom du restaurant pointé par ptliste
                restaurant.add(ptliste.getNom() + ", ");
            }
            num++;
        }
        return restaurant;
    }

    //Méthode permettant d'afficher toutes les localités qui sont des loisirs
    public ArrayList<String> afficheLoisir() {
        int num = 0;
        ArrayList<String> loisir = new ArrayList<>();
        System.out.println("La liste de tous les loisirs: ");
        //Permet le parcours de la liste
        while (liste.size() > num) {
            //Création d'un pointeur pouvant prendre les localité de la liste
            TLocalite ptliste = liste.get(num);
            //Vérifie si la localité est un loisir
            if (ptliste.getType().equals('L')) {
                //Affiche le nom du loisir pointé par ptliste
                loisir.add(ptliste.getNom() + ", ");
            }
            num++;
        }
        return loisir;
    }

    //Méthode permettant d'afficher toutes les localités qui sont des villes
    public ArrayList<String> afficheVille() {
        int num = 0;
        ArrayList<String> ville = new ArrayList<>();
        System.out.println("La liste de toutes les villes: ");
        //Permet le parcours de la liste
        while (liste.size() > num) {
            //Création d'un pointeur pouvant prendre les localité de la liste
            TLocalite ptliste = liste.get(num);
            //Vérifie si la localité est une ville
            if (ptliste.getType().equals('V')) {
                //Affiche le nom de la ville pointé par ptliste
                ville.add(ptliste.getNom());
            }
            num++;
        }
        return ville;
    }

    //Méthode permettant de renvoyer un lieu recherché 
    public TLocalite RenvoiLocalite(String localite) {
        int num = 0;
        //Permet le parcours de la liste
        while (liste.size() > num) {
            //Création d'un pointeur pouvant prendre les localité de la liste
            TLocalite ptliste = liste.get(num);
            //Vérifie si la localité pointé par ptliste est identique à la recherche
            if (ptliste.getNom().equals(localite)) {
                //retourne le localité recherché
                return ptliste;
            }
        }
        return null;
    }

    //Méthode permettant d'afficher toutes les localités se trouvant à un seul lien d'écart du lieu demandé
    public ArrayList<String> distance1(String lieu) {
        int num = 0;
        int nombre = 0;
        ArrayList<String> dist1 = new ArrayList<>();
        //Permet le parcours de la liste
        while (liste.size() > num) {
            //Création d'un pointeur pouvant prendre les localité de la liste
            TLocalite ptliste = liste.get(num);
            //Vérifie si la localité pointé par ptliste est identique à la recherche
            if (ptliste.getNom().equals(lieu)) {
                int nbrVois = 0;
                System.out.println("Localité à 1-distance de " + lieu + " :");
                //Permet le parcours des voisins de la localité pointé par ptliste
                while (ptliste.getVoisin().size() > nbrVois) {
                    //Création d'un pointeur pouvant parcourir les chemins de la localité pointé par ptliste
                    TChemin ptlisteroute = ptliste.getVoisin().get(nbrVois);
                    //Affichage des voisins directes de la localité pointé par ptliste par l'arrivé dans le chemin pointé par ptlisteroute
                    dist1.add(ptlisteroute.getArrive());
                    nbrVois++;
                }
            }
            num++;
        }
        return dist1;
    }

    //Méthode permettant d'afficher toutes les localités selon leur type demandé à un distance du lieu demandé
    public ArrayList<String> distance1(String lieu, String typeChercher) {
        int num = 0;
        ArrayList<String> dist2 = new ArrayList<>();
        //Permet le parcours de la liste
        while (liste.size() > num) {
            //Création d'un pointeur pouvant prendre les localité de la liste
            TLocalite ptliste = liste.get(num);
            //Vérifie si la localité pointé par ptliste est identique à la recherche
            if (ptliste.getNom().equals(lieu)) {
                int nbrVois = 0;
                //Permet le parcours des voisins de la localité pointé par ptliste
                while (ptliste.getVoisin().size() > nbrVois) {
                    System.out.println(typeChercher + " à 1-distance de " + lieu + " :");
                    //Création d'un pointeur pouvant parcourir les chemins de la localité pointé par ptliste
                    TChemin ptlisteroute = ptliste.getVoisin().get(nbrVois);
                    //Création d'une variable de texte qui va prendre le nom du voisin
                    String chercher = ptlisteroute.getArrive();
                    int parcours = 0;
                    //Permet un nouveau parcours de la liste
                    while (liste.size() > parcours) {
                        //Création d'un nouveau pointeur pouvant prendre les localité de la liste
                        TLocalite ptliste2 = liste.get(parcours);
                        //Vérifie si la localité pointé par ptliste2 à un type identique à celui recherché et que le nom de la localité est la même
                        if (ptliste2.getType().equals(typeChercher) && ptliste2.getNom().equals(chercher)) {
                            //Affiche les noms des voisins par l'arrive du chemin pointé par ptlisteroute
                            dist2.add(ptlisteroute.getArrive());
                        }
                        parcours++;
                    }
                    nbrVois++;
                }
            }
            num++;
        }
        return dist2;
    }

    //Méthode permettant d'afficher les localités à 2 ditances du lieu demandé
    public ArrayList<String> distance2(String lieu) {
        int num = 0;
        ArrayList<String> res = new ArrayList<>();
        //Permet le parcours de la liste
        while (liste.size() > num) {
            //Création d'un pointeur pouvant prendre les localité de la liste
            TLocalite ptliste = liste.get(num);
            //Vérifie que la localité pointé par ptliste est identique au lieu cherché
            if (ptliste.getNom().equals(lieu)) {
                int nbrVois = 0;
                //Permet le parcours des voisins de la localité pointé par ptliste
                while (ptliste.getVoisin().size() > nbrVois) {
                    int num2 = 0;
                    //Création d'un pointeur pouvant parcourir les chemins de la localité pointé par ptliste
                    TChemin ptlisteroute = ptliste.getVoisin().get(nbrVois);
                    //Création d'une variable de texte qui va prendre le nom du voisin
                    String distDeux = ptlisteroute.getArrive();
                    //Permet un nouveau parcours de la liste
                    while (liste.size() > num2) {
                        //Création d'un nouveau pointeur pouvant prendre les localité de la liste
                        TLocalite ptliste2 = liste.get(num2);
                        //Vérifie si la localité est bien le voisin de ptliste recherché
                        if (ptliste2.getNom().equals(distDeux)) {
                            int nbrVois2 = 0;
                            //Permet un nouveau parcours des voisins de la localité pointé par ptliste2
                            while (ptliste.getVoisin().size() > nbrVois2) {
                                //Création d'un pointeur pouvant parcourir les chemins de la localité pointé par ptliste2
                                TChemin ptlisteroute2 = ptliste2.getVoisin().get(nbrVois2);
                                //Ajout à l'arraylist de l'arrivé du chemin pointé par ptlisteroute2 (affiche du nom du voisin de la localité)
                                res.add(ptlisteroute2.getArrive());
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
        return res;
    }

    /*
    //Méthode permettant d'afficher les localités selon le type cherché à 2 ditances du lieu demandé
    public ArrayList<String> distance2 (String lieu, TLocalite.TypeLocalite locaChercher){
        int num = 0;
        ArrayList<String> res = new ArrayList<>();
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
                                        //Ajout à l'arrylist de la localité pointé par ptliste3
                                        res.add(ptliste3.getNom());
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
        return res;
    }
     */
    //Méthode permettant de vérifier si deux localités sont bien à deux distances
    public boolean vérificationdistance2(String localite1, String localite2) {
        ArrayList<String> list = _distance2(localite1);
        return list.contains(localite2);
    }

    /*
    // Retourne la ville la plus ouverte à 2 de distances
    public String comparerOuverture (String villeA, String villeB){
        
        ArrayList<String> ouvertA = distance2 (villeA, TLocalite.TypeLocalite.V);
        ArrayList<String> ouvertB = distance2 (villeB, TLocalite.TypeLocalite.V);
        if (ouvertA.size() < ouvertB.size()){
            System.out.println(ouvertB.size());
            return villeB;
        } else if (ouvertA.size() > ouvertB.size()){
            System.out.println(ouvertA.size());
            return villeA;
        } else {
            return ("égal");
        }
    }
    
    // Retourne la ville la plus gastronomique à 2 de distances
    public String comparerGastronomie (String villeA, String villeB){
        
        ArrayList<String> ouvertA = distance2 (villeA, TLocalite.TypeLocalite.R);
        ArrayList<String> ouvertB = distance2 (villeB, TLocalite.TypeLocalite.R);
        if (ouvertA.size() < ouvertB.size()){
            System.out.println(ouvertB.size());
            return villeB;
        } else if (ouvertA.size() > ouvertB.size()){
            System.out.println(ouvertA.size());
            return villeA;
        } else {
            return ("égal");
        }
    }
 

    // Retourne la ville la plus culturelle à 2 de distances
    public String comparerCulture (String villeA, String villeB){
        
        ArrayList<String> ouvertA = distance2 (villeA, TLocalite.TypeLocalite.L);
        ArrayList<String> ouvertB = distance2 (villeB, TLocalite.TypeLocalite.L);
        if (ouvertA.size() < ouvertB.size()){
            System.out.println(ouvertB.size());
            return villeB;
        } else if (ouvertA.size() > ouvertB.size()){
            System.out.println(ouvertA.size());
            return villeA;
        } else {
            return ("égal");
        }
    }*/
    public ArrayList<String> _distance2(String ville) {
        ArrayList<String> returned = new ArrayList<>();
        TLocalite localite = Helper.getLocalite(ville);
        LinkedList<TChemin> voisins = localite.getVoisin();
        for (TChemin chemin : voisins) {
            TLocalite step = Helper.getLocalite(chemin.getArrive());
            for (TChemin cheminStep : step.getVoisin()) {
                TLocalite sec = Helper.getLocalite(cheminStep.getArrive());
                returned.add(sec.getNom());
            }
        }
        return returned;
    }

    public ArrayList<String> distance2OfType(String ville, TLocalite.TypeLocalite type) {
        ArrayList<String> returned = new ArrayList<>();
        TLocalite localite = Helper.getLocalite(ville);
        LinkedList<TChemin> voisins = localite.getVoisin();
        for (TChemin chemin : voisins) {
            TLocalite step = Helper.getLocalite(chemin.getArrive());
            for (TChemin cheminStep : step.getVoisin()) {
                TLocalite sec = Helper.getLocalite(cheminStep.getArrive());
                if (sec.getType() == type) {
                    returned.add(sec.getNom());
                }
            }
        }
        return returned;
    }

    public String _comparerGastronomie(String villeA, String villeB) {
        ArrayList<String> list1 = distance2OfType(villeA, TLocalite.TypeLocalite.R);
        ArrayList<String> list2 = distance2OfType(villeB, TLocalite.TypeLocalite.R);
        if (list1.size() > list2.size()) {
            return villeA;
        } else if (list1.size() < list2.size()) {
            return villeB;
        } else {
            return ("Egalité");
        }
    }

    public String _comparerCulture(String villeA, String villeB) {
        ArrayList<String> list1 = distance2OfType(villeA, TLocalite.TypeLocalite.L);
        ArrayList<String> list2 = distance2OfType(villeB, TLocalite.TypeLocalite.L);
        if (list1.size() > list2.size()) {
            return villeA;
        } else if (list1.size() < list2.size()) {
            return villeB;
        } else {
            return ("Egalité");
        }
    }

    public String _comparerOuverture(String villeA, String villeB) {
        ArrayList<String> list1 = distance2OfType(villeA, TLocalite.TypeLocalite.V);
        ArrayList<String> list2 = distance2OfType(villeB, TLocalite.TypeLocalite.V);
        if (list1.size() > list2.size()) {
            return villeA;
        } else if (list1.size() < list2.size()) {
            return villeB;
        } else {
            return ("Egalité");
        }
    }

}
