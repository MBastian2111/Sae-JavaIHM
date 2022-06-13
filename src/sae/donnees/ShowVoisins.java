/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sae.donnees;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import sae.Ecran.EcranPrincipal;

/**
 *
 * @author Administrateur
 */
public class ShowVoisins {

    public final TLocalite localite;
    public final Map<TChemin, TLocalite> voisin;

    public ShowVoisins(TLocalite localite) {
        this.localite = localite;
        this.voisin = localite.getVoisin().stream().collect(
                Collectors.toMap(Function.identity(), tChemin -> {
                    String _arrive = tChemin.getArrive();
                    return EcranPrincipal.instance.slate.getNoeuds().stream().filter(tLocalite -> tLocalite.getNom().equals(_arrive)).findFirst().get();
                })
        );
    }
}
