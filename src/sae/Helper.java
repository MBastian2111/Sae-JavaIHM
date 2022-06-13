/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sae;

import java.io.File;
import java.util.Enumeration;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import sae.Ecran.EcranPrincipal;
import sae.donnees.TChemin;
import sae.donnees.TLocalite;

/**
 *
 * @author p2100635
 */
public class Helper {

    public static Predicate<TChemin> chemin(OptionsControls option) {
        return (TChemin c) -> {
            if (c.getType_route() == TChemin.TypeRoute.D && option.departementales) return true;
            if (c.getType_route() == TChemin.TypeRoute.A && option.autoroutes) return true;
            if (c.getType_route() == TChemin.TypeRoute.N && option.nationales) return true;
            return true;
        };
    }
    
    public static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        return list.stream().filter(predicate).collect(Collectors.toList());
    }
    
    public static Predicate<TLocalite> localite(OptionsControls option) {
        return (TLocalite l) -> {
            if (l.getType() == TLocalite.TypeLocalite.V && option.villes) return true;
            if (l.getType() == TLocalite.TypeLocalite.R && option.restaurant) return true;
            if (l.getType() == TLocalite.TypeLocalite.L && option.loisirs) return true;
            return true;
        };
    }
    
    public static TLocalite getLocalite(String nom) {
        TLocalite localite = EcranPrincipal.instance.slate.getNoeuds().stream()
                .filter((tLocalite) -> (tLocalite.getNom().equals(nom))).findFirst().orElse(null);
        return localite;
    }
    
    public static AbstractButton getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button;
            }
        }

        return null;
    }
}
