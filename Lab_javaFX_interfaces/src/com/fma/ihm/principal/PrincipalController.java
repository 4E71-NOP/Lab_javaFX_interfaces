package com.fma.ihm.principal;

import com.fma.agents.GestionMemoire;

/**
 * Classe dédiée au controlleur principal<br>
 * <br>
 * 
 * @author Faust MARIA DE ARAVALO - Tous droits réservés. Projet protfolio sur
 *         JavaFX2<br>
 *         <br>
 */
public class PrincipalController {

	public void PrincipalTabAction() {
		GestionMemoire.getInstance().executeGc();
	}

}
