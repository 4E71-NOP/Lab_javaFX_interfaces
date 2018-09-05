package com.fma.agents;

/**
 * 
 * Classe permettant d'éxécuter le ramasse-miettes<br>
 * <br>
 * 
 * @author Faust MARIA DE ARAVALO - Tous droits réservés. Projet protfolio sur
 *         JavaFX2<br>
 *         <br>
 */
public class GestionMemoire {

	public static GestionMemoire instance = null;

	private GestionMemoire() {
	}

	public static GestionMemoire getInstance() {
		if (instance == null) {
			instance = new GestionMemoire();
		}
		return instance;
	}

	public void executeGc() {

		long memAvant = Runtime.getRuntime().freeMemory();
		long tempsAvant = System.currentTimeMillis();
		System.gc();
		long tempsApres = System.currentTimeMillis();
		long memApres = Runtime.getRuntime().freeMemory();

		String str = "Mémoire libre avant GC: " + (memAvant / 1048576) + "Mo / après GC: " + (memApres / 1048576)
				+ "Mo";

		long memBilan = memAvant - memApres;
		if (memBilan < 0) {
			str += " gain de : " + Math.abs(memBilan / 1048576) + "Mo.";
		} else {
			str += " Allocation de : " + (memBilan / 1048576) + "Mo.";
		}
		str += " Temps d'exécution: " + (tempsApres - tempsAvant) + "ms";

		System.out.println(str);
	}

}
