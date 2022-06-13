/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sae.Ecran;

import java.util.LinkedList;
import java.util.TimerTask;
import sae.Helper;
import sae.Ecran.EcranPrincipal;
import sae.function.Function;


/**
 *
 * @author Administrateur
 */
public class RefreshThread extends TimerTask {
    
    private final Slate panel;
    
    public RefreshThread(Slate panel){
        this.panel = panel;
    }
    
    @Override
    public void run(){
        Function fct = new Function(new LinkedList<>(panel.show));
        EcranPrincipal.instance.jNbrD.setText("Dep: " + fct._nombreDepartementale() / 2);
        //System.out.println(fct._nombreDepartementale());
        EcranPrincipal.instance.jNbrN.setText("Nat... : " + fct._nombreNationale() / 2);
        EcranPrincipal.instance.jNbrA.setText("Aut... : " + fct._nombreAutoroute() / 2);
        EcranPrincipal.instance.jNbrV.setText("Vil... : " + fct.nombreVille());
        EcranPrincipal.instance.jNbrR.setText("Res... : " + fct.nombreRestaurant());
        EcranPrincipal.instance.jNbrL.setText("Loi... : " + fct.nombreLoisir());
        
        panel.repaint();
    }
    
}
