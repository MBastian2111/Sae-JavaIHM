/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sae.models;


/**
 *
 * @author p2105353
 */
public class Paint {
    
    /**
     * Correspond à sa position absolue x sur l'objet {@link Slate}
     */
    private final int x;
    
    /**
     * Correspond à sa position absolue y sur l'objet {@link Slate}
     */
    private final int y;
    
    /**
     * Détermine si le point doit être lisse ou pas
     */
    private final boolean smooth;
    /**
     * Correspond à la taille du point (diamètre dans le cas d'un rond, largeur/hauteur dans le cas d'un carré)
     */
    private final int size;
    
    
    /**
     * Crée un point à dessiner sur un {@link Slate}
     * @param color Correspond à la couleur du point à dessiner
     * @param x Correspond à sa position absolue x sur l'objet {@link Slate}
     * @param y Correspond à sa position absolue y sur l'objet {@link Slate}
     * @param size Correspond à la taille du point (diamètre dans le cas d'un rond, largeur/hauteur dans le cas d'un carré)
     * @param smooth Détermine si le point doit être lisse ou pas
     * @param tool Correspond à l'outil (la forme de l'embout du pinceau)
     */
    public Paint(int x, int y, int size, boolean smooth) {
        this.x      = x;
        this.y      = y;
        this.size   = size;
        this.smooth = smooth;
    }
    
     /**
     * Renvoie la position absolue x sur l'objet {@link Slate}
     * @return Retourne la position absolue x sur l'objet {@link Slate}
     */
    public int getX() {
        return x;
    }

    /**
     * Renvoie la position absolue y sur l'objet {@link Slate}
     * @return Retourne la position absolue y sur l'objet {@link Slate}
     */
    public int getY() {
        return y;
    }

    /**
     * Renvoie si oui ou non le point est lisse ou pas
     * @return Retourne true s'il l'est, sinon false
     */
    public boolean isSmooth() {
        return smooth;
    }
    
    /**
     * Renvoie la taille du point (diamètre dans le cas d'un rond, largeur/hauteur dans le cas d'un carré)
     * @return Retourne la taille du point (diamètre dans le cas d'un rond, largeur/hauteur dans le cas d'un carré)
     */
    public int getSize() {
        return size;
    }
    
    
}
