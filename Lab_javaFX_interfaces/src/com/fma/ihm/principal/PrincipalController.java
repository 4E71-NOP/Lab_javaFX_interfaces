package com.fma.ihm.principal;

import com.fma.agents.GestionMemoire;

public class PrincipalController {

	public void PrincipalTabAction() {
		GestionMemoire.getInstance().executeGc();
	}

}
