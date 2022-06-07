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
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JPanel;
import sae.models.Paint;

/**
 *
 * @author p2105353
 */
public class Slate extends JPanel{
    
    /**
     * Correspond à l'interface qui permet de faire le lien entre l’objet {@link Slate} et {@link ArdoiseMagique}. 
     */
    private final IDrawer iDrawer;
    
    /**
     * Correspond à la liste des points à dessiner sur l'objet {@link Slate}
     */
    private final List<Paint> paints;
    
    /**
     * Cette variable détermine si l'antialias est actif ou pas (si oui ou non les points sont lisses ou pas)
     */
    private boolean antialias = false;
    
    /**
     * Correspond à la dimension du panneau
     */
    private final Dimension dimension;
    
    /**
     * Correspond au facteur de zoom (1.0 étant la taille original, 2.0 la taille est doublée, ...)
     */
    private float factor;
    
    
    /**
     * Crée un objet {@link Slate}
     * @param dimension Correspond à la dimension du panneau
     * @param iDrawer Correspond à l'interface qui permet de faire le lien entre l’objet {@link Slate} et {@link ArdoiseMagique}. 
     */
    public Slate(Dimension dimension, IDrawer iDrawer){
        this.factor     = 1.0f;
        this.dimension  = dimension;
        this.iDrawer    = iDrawer;
        this.paints     = new LinkedList<>();
        
        setLayout(null);
        setSize(dimension);
        setPreferredSize(dimension);
        setMinimumSize(dimension);
        setMaximumSize(dimension);
        
        Listener listener = new Listener();
        addMouseListener(listener);
        addMouseMotionListener(listener);
    }
    
    /**
     * Dé/Zoom le panneau. En réalité c'est ces proportions qui changent et la position et tailles des {@link Paint} changent aussi
     * @param factor Correspond au facteur de zoom (1.0 étant la taille original, 2.0 la taille est doublée, ...)
     */
    public void zoom(float factor){
        this.factor = factor;
        
        // On crée la nouvelle dimension à partir de la dimension de base multiplié par le factor
        Dimension newDimension = new Dimension((int) (this.dimension.width * factor), (int) (this.dimension.height * factor));
        
        // On modifie la taille du panneau
        setSize(newDimension);
        setPreferredSize(newDimension);
        setMinimumSize(newDimension);
        setMaximumSize(newDimension);
        
        // Et on valide sa modification de taille (car dynamiquement, nous sommes obligés de forcer son redimensionnement)
        revalidate();
        
        // Puis on repeint tout le panneau (cela tien compte du fait qu'en ayant augmenté la taille du panneau, les Paint ne sont plus placé au même endroit et ne font plus la même taille)
        repaint();
    }
    
    @Override
    public void paintComponent(Graphics g){
        
        // On caste le Graphics g en Graphics2D. C'est indispensable si l'on souhaite rendre certains Paint lisses ou pas
        Graphics2D g2d = (Graphics2D) g;
        
        // On définit la couleur qui sera utilisé pour peindre l'arrière plan du panneau
        g2d.setColor(Color.WHITE);
        
        // On dessine l'arrière plan. C'est un rectangle (blanc du coup) qui fait la taille du panneau
        g2d.fillRect(0, 0, getWidth(), getHeight());
        
        // Il nous reste à dessiner les objets Paint par dessus. Pour tous les dessiner, il nous faut parcourir la liste "paints" et récupérer chaque paint pour le dessiner
        // On aurait pu faire une boucle "for" classique, voire un "foreach". Mais nous avons un problème conséquent: La liste "paints" peut être composé de millions de points.
        // Une ArrayList classique serait donc un mauvais choix car plus on ajoute de "Paint", plus le programme va ralentir. Car ne l'oublions pas l'ArrayList est plus lente à l'ajout et la suppression d'élément en interne.
        // La LinkedList serait donc un bon compromis pour ajouter des millions de "Paint" de manière rapide. Sauf que la où on gagne en écriture on la perd en lecture. Car ne l'oublions par la LinkedList est plus lente à la lecture que l'ArrayList. Et pour cause à chaque fois que l'on recherche un élément précis, toute la liste doit être reparcourue.
        // La bonne nouvelle dans tout çà, c'est qu'il existe un objet Iterator pour toutes les List. Cet objet permet de parcourir une liste élément par élément sans ce soucier de l'index. Bien entendu, une fois qu'un élement est lu on ne peut pas revenir en arrière. Dans notre cas on s'en moque. Nous souhaitons lire tous les "Paint" les uns après les autres.
        Iterator<Paint> iterator = paints.iterator();
        
        // Tant qu'il y a un Paint à lire dans la liste, alors...
        while(iterator.hasNext()){
            
            // On récupère cet objet Paint
            Paint paint = iterator.next();
            
            
            // On détermine si le Paint est lisse ou pas. Comme la commandes setRenderingHint demande un peu de ressource système, il n'est pas nécessaire de l'appliquer sur chaque point. Mais seulement lorsqu'il y a un changement. D'où l'intérêt de l'attribut "antialias" qui garde en mémoire le type
            if(!antialias && paint.isSmooth()){
                g2d.setRenderingHint(
                    java.awt.RenderingHints.KEY_ANTIALIASING,
                    java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
                antialias = true;
            }else if(antialias && !paint.isSmooth()){
                g2d.setRenderingHint(
                    java.awt.RenderingHints.KEY_ANTIALIASING,
                    java.awt.RenderingHints.VALUE_ANTIALIAS_OFF);
                antialias = false;
            }
            
            // MAINTENANT COMPLIQUONS UN PEU LES CHOSES
            
            // Nous devons dessiner le paint. Celui-ci a une coordonnée, mais on sait aussi que l'utilisateur peut modifier le facteur de zoom, donc sa coordonnée absolue n'est pas sa coordonnée relative au zoom (sauf dans le cas ou le factor = 1: car dans ce cas le factor 1 représente les dimensions originales du panneau)
            // Nous devons donc calculer la coordonnée relative au zoom à partir de la coordonnée absolue du Paint
            // Tout d'abord on souhaite que la souris pointe le centre du Paint à dessiner. Donc il faut enlever la moitié de la taille du point à x et y. On obtient à ce stade la coordonnée relative (factor 1)
            // Mais comme nous ne sommes par forcément en factor 1, on multiplie x et y par le factor du panneau. On obtient donc la coordonnée relative du point en fonction d'un objet Paint et du facteur de zoom
            int x = (int) ((paint.getX() - paint.getSize() / 2) * this.factor);
            int y = (int) ((paint.getY() - paint.getSize() / 2) * this.factor);
            
            // Bien entendu si l'on modifie le zoom du panneau, alors la taille d'un point change aussi. C'est pourquoi on calcule la taille relative au zoom à partir de la taille absolue d'un Paint
            int size = (int) (paint.getSize() * this.factor);
            
            
            // ICI
            // IL VA FALLOIR DETERMINER COMMENT IMPLEMENTER LES ROUTES ET COMMENT LES DESSINER A TELLE POSITION 
            // AINSI QUE LES ROUTES IL VA FALLOIR LES RELIER TOUT EN PRENANT EN COMPTE QUE SI ON RETIRE UN NOEUD
            // ON DOIT AUSSI RETIRER LES TRAITS CORRESPONDANTS.
            
        }
    }
    
    
    private class Listener extends MouseAdapter{

        
        /**
         * Lorsque la souris ne pointe plus le panneau (elle est donc sur un autre composant). Alors les coordonnées de la souris sur le panneau ne peuvent être déterminée...
         * @param e Correspond à l'évènement reçu
         */
        @Override
        public void mouseExited(MouseEvent e) {
            // C'est pourquoi on envoie la valeur null à iDrawer
            iDrawer.mouseAt(null);
        }
        
        /**
         * Lorsque la souris se déplace sur le panneau (c'est qu'elle est bien sur ce panneau). Alors on peut obtenir ses coordonnées
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
        }
        
        /**
         * Lorsque la souris se déplace sur le panneau tout en cliquant (c'est qu'elle est bien sur ce panneau et l'utilisateur est en train de dessiner)
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
         * Lorsque la souris clique sur le panneau (c'est qu'elle est bien sur ce panneau et l'utilisateur est soit en train de récupérer une couleur avec la pipette où il dessine point par point)
         * @param e Correspond à l'évènement reçu
         */
        @Override
        public void mouseClicked(MouseEvent e) {
            // OSEF DE CE QU'IL Y A ICI POUR LE MOMENT SAUF SI ON ARRIVE 
            // Q FAIRE QLQ CHOSE OU IL FAUT CLIQUER 
        }
        }
}
