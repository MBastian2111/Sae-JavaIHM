/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package sae.Ecran;

/**
 *
 * @author mouni
 */
public interface InformationPoint {
    /**
     * Lorsque la souris se déplace sur le {@link Slate}, si elle s'approche d'un point,
     * alors on retourne les informations du point en question
     * 
     * @param infoPrLabel 
     */
    public void infoPoint(String infoPrLabel);
}
