/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sae.Ecran;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import java.util.Timer;
import sae.Helper;
import sae.donnees.ShowVoisins;
import sae.donnees.TChemin;
import sae.donnees.TLocalite;
import sae.function.Function;

/**
 *
 * @author p2105353
 */
public class Slate extends JPanel {

    /**
     * Correspond à l'interface qui permet de faire le lien entre l’objet
     * {@link Slate} et {@link ArdoiseMagique}.
     */
    private final IDrawer iDrawer;

    private final InformationPoint informationPoint;

    public Graphics2D g2d;
    /**
     * Correspond à la liste des points à dessiner sur l'objet {@link Slate}
     */
    public final List<TLocalite> noeuds;

    public List<TLocalite> getNoeuds() {
        return noeuds;
    }

    public List<TLocalite> show;

    /**
     * Cette variable détermine si l'antialias est actif ou pas (si oui ou non
     * les points sont lisses ou pas)
     */
    private boolean antialias = false;

    /**
     * Correspond à la dimension du panneau
     */
    private final Dimension dimension;

    /**
     * Correspond au facteur de zoom (1.0 étant la taille original, 2.0 la
     * taille est doublée, ...)
     */
    private float factor;

    private final RefreshThread refreshThread;

    /**
     * Crée un objet {@link Slate}
     *
     * @param dimension Correspond à la dimension du panneau
     * @param iDrawer Correspond à l'interface qui permet de faire le lien entre
     * l’objet {@link Slate} et {@link ArdoiseMagique}.
     */
    public Slate(Dimension dimension, IDrawer iDrawer, List<TLocalite> list, InformationPoint informationPoint) {
        this.factor = 1.0f;
        this.dimension = dimension;
        this.iDrawer = iDrawer;
        this.noeuds = list;
        this.show = new ArrayList<>(noeuds);
        this.refreshThread = new RefreshThread(this);
        this.informationPoint = informationPoint;

        EcranPrincipal.instance.comboVilleDepart.setModel(new Function(new LinkedList(show)).getModel());
        EcranPrincipal.instance.comboVilleDepart1.setModel(new Function(new LinkedList(show)).getComparaisonModel());
        EcranPrincipal.instance.comboVilleDepart3.setModel(new Function(new LinkedList(noeuds)).getModel());
        EcranPrincipal.instance.comboVilleDepart4.setModel(new Function(new LinkedList(noeuds)).getModel());
        
        //C'est un Timer où 0, c'est le délai avant la première éxecution (ms),
        //et 500 c'est la durée entre chaque execution.
        new Timer().scheduleAtFixedRate(refreshThread, 0, 500);

        setLayout(null);
        setSize(dimension);
        setPreferredSize(dimension);
        setMinimumSize(dimension);
        setMaximumSize(dimension);

        Listener listener = new Listener();
        addMouseListener(listener);
        addMouseMotionListener(listener);

        algoAleatoire();

        /**
         * DefaultComboBoxModel models1 = new DefaultComboBoxModel();
         * DefaultComboBoxModel models2 = new DefaultComboBoxModel();
         * DefaultComboBoxModel models3 = new DefaultComboBoxModel();
         * DefaultComboBoxModel models4 = new DefaultComboBoxModel(); for
         * (TLocalite tnoeud : list) { models1.addElement(tnoeud.getNom());
         * models2.addElement(tnoeud.getNom());
         * models3.addElement(tnoeud.getNom());
         * models4.addElement(tnoeud.getNom()); }
         */
    }

    public void refresh() {
        removeAll();
        revalidate();
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {

        // On caste le Graphics g en Graphics2D. C'est indispensable si l'on souhaite rendre certains Paint lisses ou pas
        g2d = (Graphics2D) g;

        // On définit la couleur qui sera utilisé pour peindre l'arrière plan du panneau
        g2d.setColor(Color.WHITE);

        // On dessine l'arrière plan. C'est un rectangle (blanc du coup) qui fait la taille du panneau
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Il nous reste à dessiner les objets Paint par dessus. Pour tous les dessiner, il nous faut parcourir la liste "paints" et récupérer chaque paint pour le dessiner
        // On aurait pu faire une boucle "for" classique, voire un "foreach". Mais nous avons un problème conséquent: La liste "paints" peut être composé de millions de points.
        // Une ArrayList classique serait donc un mauvais choix car plus on ajoute de "Paint", plus le programme va ralentir. Car ne l'oublions pas l'ArrayList est plus lente à l'ajout et la suppression d'élément en interne.
        // La LinkedList serait donc un bon compromis pour ajouter des millions de "Paint" de manière rapide. Sauf que la où on gagne en écriture on la perd en lecture. Car ne l'oublions par la LinkedList est plus lente à la lecture que l'ArrayList. Et pour cause à chaque fois que l'on recherche un élément précis, toute la liste doit être reparcourue.
        // La bonne nouvelle dans tout çà, c'est qu'il existe un objet Iterator pour toutes les List. Cet objet permet de parcourir une liste élément par élément sans ce soucier de l'index. Bien entendu, une fois qu'un élement est lu on ne peut pas revenir en arrière. Dans notre cas on s'en moque. Nous souhaitons lire tous les "Paint" les uns après les autres.
        // Tant qu'il y a un Paint à lire dans la liste, alors...
        // On récupère cet objet Paint
        // On détermine si le Paint est lisse ou pas. Comme la commandes setRenderingHint demande un peu de ressource système, il n'est pas nécessaire de l'appliquer sur chaque point. Mais seulement lorsqu'il y a un changement. D'où l'intérêt de l'attribut "antialias" qui garde en mémoire le type
        g2d.setRenderingHint(
                java.awt.RenderingHints.KEY_ANTIALIASING,
                java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
        antialias = true;

        // MAINTENANT COMPLIQUONS UN PEU LES CHOSES
        // Nous devons dessiner le paint. Celui-ci a une coordonnée, mais on sait aussi que l'utilisateur peut modifier le facteur de zoom, donc sa coordonnée absolue n'est pas sa coordonnée relative au zoom (sauf dans le cas ou le factor = 1: car dans ce cas le factor 1 représente les dimensions originales du panneau)
        // Nous devons donc calculer la coordonnée relative au zoom à partir de la coordonnée absolue du Paint
        // Tout d'abord on souhaite que la souris pointe le centre du Paint à dessiner. Donc il faut enlever la moitié de la taille du point à x et y. On obtient à ce stade la coordonnée relative (factor 1)
        // Mais comme nous ne sommes par forcément en factor 1, on multiplie x et y par le factor du panneau. On obtient donc la coordonnée relative du point en fonction d'un objet Paint et du facteur de zoom
        //int x = (int) ((noeud.getX() - noeud.getSize() / 2) * this.factor);
        //int y = (int) ((noeud.getY() - noeud.getSize() / 2) * this.factor);
        // Bien entendu si l'on modifie le zoom du panneau, alors la taille d'un point change aussi. C'est pourquoi on calcule la taille relative au zoom à partir de la taille absolue d'un Paint
        //int size = (int) (noeud.getSize() * this.factor);
        //new Random().nextInt(dimension.width - 50), new Random().nextInt(dimension.height - 50)
        g2d.setColor(Color.BLUE);

        if (!EcranPrincipal.instance.optionsControls.showVoisin1) {
            //System.out.println("no voisinnnn");
            drawMap();
        } else {
            if (EcranPrincipal.instance.optionsControls.showVoisins == null) {
                drawMap();
                return;
            }
            ShowVoisins voisins = EcranPrincipal.instance.optionsControls.showVoisins;
            drawVoisin(voisins.localite, new ArrayList<>(voisins.voisin.keySet()));
            if (EcranPrincipal.instance.optionsControls.showVoisin2) {
                for (TChemin chemin : voisins.voisin.keySet()) {
                    if (voisins.voisin.get(chemin) == null) {
                        continue;
                    }
                    TLocalite target = voisins.voisin.get(chemin);
                    drawVoisin(target, target.getVoisin());

                }
            }

        }
        if (EcranPrincipal.instance.optionsControls.showVoisins != null) {
            ShowVoisins voisins = EcranPrincipal.instance.optionsControls.showVoisins;
            int origineNoeudX = voisins.localite.getPosX();
            int origineNoeudY = voisins.localite.getPosY();
            g2d.setColor(Color.BLACK);
            g2d.fillOval(origineNoeudX, origineNoeudY, 30, 30);
            g2d.setColor(Color.RED);
            g2d.drawOval(origineNoeudX, origineNoeudY, 30, 30);
            String infoPrLabel = (voisins.localite.getNom());
            int X = voisins.localite.getPosX();
            int Y = voisins.localite.getPosY() + 40;
            g2d.drawString(infoPrLabel, X, Y);
        }
        //Tester la fonction de refresh
        /**
         * g2d.draw(new
         * Line2D.Double(Calendar.getInstance().get(Calendar.SECOND), 0,
         * getWidth(), getHeight()));
         */
        /*
        for (int i = 0; i < noeuds.size(); i++) {
            TLocalite premierNoeud = noeuds.get(i);
            for (int j = 0; j < premierNoeud.getVoisin().size(); j++) {
                String arrive = premierNoeud.getVoisin().get(j).getArrive();
                System.out.println(arrive);
                for (int k = 0; k < noeuds.size(); k++) {
                    if (noeuds.get(k).getNom().equals(arrive)) {
                        /**
                         * switch
                         * (premierNoeud.getVoisin().get(j).getType_route()) {
                         *
                         * case "D": System.out.println("Type de route : D");
                         * couleur = 1; g2d.setColor(Color.RED); break;
                         *
                         * case "N": System.out.println("Type de route : N");
                         * couleur = 2; g2d.setColor(Color.YELLOW); break;
                         *
                         * case "A": System.out.println("Type de route : A");
                         * couleur = 3; g2d.setColor(Color.BLUE); break;
                         * default: System.out.println("Choix incorrect");
                         * break;
                        }
                         *
                        TChemin chemin = premierNoeud.getVoisin().get(j);
                        g2d.setColor(chemin.getType_route().couleur);
                        g2d.drawLine(premierNoeud.getPosX() + 15, premierNoeud.getPosY() + 15, noeuds.get(k).getPosX() + 15, noeuds.get(k).getPosY() + 15);
                    }
                }
            }
        }*/
        //Voir les etats de chaque checkbox
        /**
         * if (EcranPrincipal.instance.optionsControls.departementales) {
         * g2d.setColor(Color.BLUE); g2d.fillOval(0, 0, 10, 10); } if
         * (EcranPrincipal.instance.optionsControls.nationales) {
         * g2d.setColor(Color.GREEN); g2d.fillOval(10, 0, 10, 10); } if
         * (EcranPrincipal.instance.optionsControls.autoroutes) {
         * g2d.setColor(Color.RED); g2d.fillOval(20, 0, 10, 10); } if
         * (EcranPrincipal.instance.optionsControls.villes) {
         * g2d.setColor(Color.BLACK); g2d.fillOval(0, 10, 10, 10); } if
         * (EcranPrincipal.instance.optionsControls.restaurant) {
         * g2d.setColor(Color.ORANGE); g2d.fillOval(10, 10, 10, 10); } if
         * (EcranPrincipal.instance.optionsControls.loisirs) {
         * g2d.setColor(Color.CYAN); g2d.fillOval(20, 10, 10, 10); }
         */
    }

    public void drawVoisin(TLocalite origin, List<TChemin> chemins) {
        
        for (TChemin chemin : chemins) {
            if (Helper.getLocalite(chemin.getArrive()) == null) {
                continue;
            }
            TLocalite arrive = Helper.getLocalite(chemin.getArrive());
            if (chemin.getType_route() == TChemin.TypeRoute.D && !EcranPrincipal.instance.optionsControls.departementales) {
                continue;
            }
            if (chemin.getType_route() == TChemin.TypeRoute.N && !EcranPrincipal.instance.optionsControls.nationales) {
                continue;
            }
            if (chemin.getType_route() == TChemin.TypeRoute.A && !EcranPrincipal.instance.optionsControls.autoroutes) {
                continue;
            }
            g2d.setColor(chemin.getType_route().couleur);
            g2d.drawLine(origin.getPosX() + 15, origin.getPosY() + 15, arrive.getPosX() + 15, arrive.getPosY() + 15);
        }
        
        for (TChemin chemin : chemins) {
            if (Helper.getLocalite(chemin.getArrive()) == null) {
                continue;
            }
            TLocalite arrive = Helper.getLocalite(chemin.getArrive());
            int voisinPosX = arrive.getPosX();
            int voisinPosY = arrive.getPosY();
            g2d.setColor(Color.BLACK);
            g2d.fillOval(voisinPosX, voisinPosY, 30, 30);
            
            String infoPrLabel = (arrive.getNom());
            int X = arrive.getPosX();
            int Y = arrive.getPosY() + 40;
            g2d.drawString(infoPrLabel, X, Y);
        }
        
    }

    public void drawMap() {

        int taille = show.size();
        show = new ArrayList<>(noeuds);
        if (!EcranPrincipal.instance.optionsControls.villes) {
            show.removeIf(n -> n.getType() == TLocalite.TypeLocalite.V);
        }
        if (!EcranPrincipal.instance.optionsControls.restaurant) {
            show.removeIf(n -> n.getType() == TLocalite.TypeLocalite.R);
        }
        if (!EcranPrincipal.instance.optionsControls.loisirs) {
            show.removeIf(n -> n.getType() == TLocalite.TypeLocalite.L);
        }
        for (TLocalite premier : show) {
            List<TChemin> chemins = new ArrayList<>(premier.getVoisin());
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
                for (TLocalite voisin : show) {
                    if (voisin.getNom().equals(chemin.getArrive())) {
                        g2d.setColor(chemin.getType_route().couleur);
                        g2d.drawLine(premier.getPosX() + 15, premier.getPosY() + 15, voisin.getPosX() + 15, voisin.getPosY() + 15);
                    }
                }
            }
        }
        if (taille != show.size()) {
            EcranPrincipal.instance.comboVilleDepart.setModel(new Function(new LinkedList<>(show)).getModel());
            EcranPrincipal.instance.comboVilleDepart1.setModel(new Function(new LinkedList<>(show)).getModel());
            EcranPrincipal.instance.comboVilleDepart2.setModel(new Function(new LinkedList<>(show)).getModel());
            EcranPrincipal.instance.comboVilleDepart3.setModel(new Function(new LinkedList(noeuds)).getModel());
            EcranPrincipal.instance.comboVilleDepart4.setModel(new Function(new LinkedList(noeuds)).getModel());
        }
        //System.out.println(taille + " " + show.size());
        g2d.setColor(Color.BLACK);
        for (TLocalite noeud : show) {

            g2d.fillOval(noeud.getPosX(), noeud.getPosY(), 30, 30);
            String infoPrLabel = (noeud.getNom());
            int X = noeud.getPosX();
            int Y = noeud.getPosY() + 40;
            g2d.drawString(infoPrLabel, X, Y);
        }
    }

    private void algoAleatoire() {
        int index = 0;
        while (index < noeuds.size()) {

            int iteration = 0;

            noeuds.get(index).setPosX(new Random().nextInt(dimension.width - 50));
            noeuds.get(index).setPosY(new Random().nextInt(dimension.height - 50));

            while (iteration < index + 1) {
                while ((Math.sqrt(Math.pow(noeuds.get(index).getPosX() - noeuds.get(iteration).getPosX(), 2) + Math.pow(noeuds.get(index).getPosY() - noeuds.get(iteration).getPosY(), 2))) < 30 * 3.5F && !noeuds.get(index).equals(noeuds.get(iteration))) {
                    //System.out.println("aaaaaaa suis la" + noeud.toString());

                    noeuds.get(index).setPosX(new Random().nextInt(dimension.width - 50));
                    noeuds.get(index).setPosY(new Random().nextInt(dimension.height - 50));
                    iteration = 0;

                    //System.out.println(iteration + " " + index + "\n");
                }
                iteration++;
            }
            index++;

        }
    }

    private class Listener extends MouseAdapter {

        /**
         * Lorsque la souris ne pointe plus le panneau (elle est donc sur un
         * autre composant). Alors les coordonnées de la souris sur le panneau
         * ne peuvent être déterminée...
         *
         * @param e Correspond à l'évènement reçu
         */
        @Override
        public void mouseExited(MouseEvent e) {
            // C'est pourquoi on envoie la valeur null à iDrawer
            iDrawer.mouseAt(null);
        }

        /**
         * Lorsque la souris se déplace sur le panneau (c'est qu'elle est bien
         * sur ce panneau). Alors on peut obtenir ses coordonnées
         *
         * @param e Correspond à l'évènement reçu
         */
        @Override
        public void mouseMoved(MouseEvent e) {
            // OUI MAIS ATTENTION...
            // On obtient des coordonnées absolues par rapport à la taille actuelle du panneau (qui a pû être zoomé)
            // Il nous faut donc calculer les coordonnées relatives de la souris en fonction de ses coordonnées absolues et du factor de zoom
            // Attention c'est une division et non une multiplication
            int x = (int) (e.getX() / Slate.this.factor);
            int y = (int) (e.getY() / Slate.this.factor);

            // On communique la coordonnée relative à iDrawer
            iDrawer.mouseAt(new Point(x, y));

            for (int i = 0; i < noeuds.size(); i++) {
                TLocalite noeud = noeuds.get(i);
                if (((noeud.getPosX() + 30) == x) && ((noeud.getPosY() + 30) == y)) {
                    String infoPrLabel = (noeud.getNom());
                    informationPoint.infoPoint(infoPrLabel);
                }
            }
        }

        /**
         * Lorsque la souris se déplace sur le panneau tout en cliquant (c'est
         * qu'elle est bien sur ce panneau et l'utilisateur est en train de
         * dessiner)
         *
         * @param e Correspond à l'évènement reçu
         */
        @Override
        public void mouseDragged(MouseEvent e) {
            // OUI MAIS ATTENTION...
            // On obtient des coordonnées absolues par rapport à la taille actuelle du panneau (qui a pû être zoomé)
            // Il nous faut donc calculer les coordonnées relatives de la souris en fonction de ses coordonnées absolues et du factor de zoom
            // Attention c'est une division et non une multiplication
            int x = (int) (e.getX() / Slate.this.factor);
            int y = (int) (e.getY() / Slate.this.factor);

            // SUR LE TRUC DU PROF ON POUVAIT DESSINNER ICI 
            // MAIS DUCOUP CA SERT A RIEN
            // On communique la coordonnée relative à iDrawer
            iDrawer.mouseAt(new Point(x, y));

            // Et on force le panneau à se repeindre. C'est important si l'on veut que ce que l'utilisateur dessine s'affiche. Cette méthode va appeler automatiquement la méthode paintComponent() du panneau
            repaint();
        }

        /**
         * Lorsque la souris clique sur le panneau (c'est qu'elle est bien sur
         * ce panneau et l'utilisateur est soit en train de récupérer une
         * couleur avec la pipette où il dessine point par point)
         *
         * @param e Correspond à l'évènement reçu
         */
        @Override
        public void mouseClicked(MouseEvent e) {
            // OSEF DE CE QU'IL Y A ICI POUR LE MOMENT SAUF SI ON ARRIVE 
            // Q FAIRE QLQ CHOSE OU IL FAUT CLIQUER 
        }
    }
}
