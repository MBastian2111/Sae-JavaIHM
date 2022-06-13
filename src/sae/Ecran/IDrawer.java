/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sae.Ecran;

import java.awt.Point;

/**
 *
 * @author p2105353
 */
public interface IDrawer {
    
    /**
     * Lorsque la souris se déplace sur le {@link Slate}
     * @param point Correspond à la nouvelle position de la souris
     */
    public void mouseAt(Point point);
    
    
}
