package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *  Class Joueur.
 */
public class Joueur implements Serializable {

	/**
	 *  Nom du joueur.
	 */
	private String nom;
	/**
	 *  Liste d'unités d'un joueur.
	 */

	private ArrayList<Unite> unites = new ArrayList<Unite>();
	/**
	 *  Identifiant unique du joueur
	 */
	public int id;

	private boolean isIA;

	/**
	 *  Constructeur d'un joueur.
	 *  @param nom String
	 */
	public Joueur(String nom, int newId) {
		this.setNom(nom);
		this.id = newId;
	}

	/**
	 *  Récupère le nom du joueur.
	 *  @return nom String
	 */
	public String getNom() {
		System.out.println("ID in getter:"+id);
		return nom;
	}

	/**
	 *  Assigne un nom au joueur.
	 *  @param nom String
	 */
	public void setNom(String nom) {
		this.nom = nom.toUpperCase();
	}

	/**
	 *  Récupère la liste d'unitée d'un joueur.
	 *  @return unites ArrayList<Unite>
	 */
	public ArrayList<Unite> getUnite() {
		return unites;
	}

	/**
	 *  Ajoute une unite à celles possédées par le joueur.
	 *  @param unit String
	 */
	public void addUnit(Unite unit) {
		unites.add(unit);
	}


	/**
	 *  Assigne un id au joueur.
	 *  @param id ind
	 */
	public void setID(int id) {
		this.id = id;
	}


	/**
	 *  Indique si le joueur est humain ou non
	 *  @return nom String
	 */
	public String getIsIA() {
		return isIA;
	}

	/**
	 *  Modifie le statut du joueur (humain / IA)
	 *  @param nom String
	 */
	public void setIsIA(boolean isIA) {
		this.isIA = isIA;
	}
}
