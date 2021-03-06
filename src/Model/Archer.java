package model;

import java.util.ArrayList;
import java.util.List;

/**
 *  Class Archer.
 *  @see Hex
 *  @see HexMap
 *  @see Joueur
 *  @see Unite
 */
public class Archer extends Unite {

	/**
	 * Points de port�e de l'unit�.
	 */
	private int portee;

	/**
	 *  Constructeur d'un archer.
	 *  @param hex Hexagone
	 *  @param joueur Joueur
	 *  @see Hex
	 *  @see Joueur
	 */
	public Archer(Hex hex, Joueur joueur) {
		super(hex, joueur);
		hex.setUnit(this);
		this.pointsAttaque = 17;
		this.pointsDefenseInit = 3;
		this.pointsDefense = this.pointsDefenseInit;
		this.pointsDeplacementInit = 100;
		this.pointsDeplacement = this.pointsDeplacementInit;
		this.pointsDeVieMax = 35;
		this.pointsDeVie = this.pointsDeVieMax;
		this.vision = 6;
		this.portee = 4;
	}

	/**
	 *  Constructeur d'un archer.
	 */
	public Archer() {
	}

	/**
	 * Heal de l'unit� si elle n'a pas boug�.
	 */
	@Override
	public void heal() {
		if (this.pointsDeplacement == this.pointsDeplacementInit) {
			this.pointsDeVie = (int) ((float) this.pointsDeVie * 1.10);
			if (this.pointsDeVie > this.pointsDeVieMax) {
				this.pointsDeVie = this.pointsDeVieMax;
			}
		}
	}

	/**
	 * R�initialise les points de d�placement de l'unit�.
	 */
	@Override
	public void initialize() {
		this.pointsDeplacement = this.pointsDeplacementInit;
	}

	/**
	 * M�thode combat propre � l'archer.
	 * Peut attaquer � distance.
	 * @param map Map
	 * @param joueurAct Joueur Actuel
	 * @param unite Unite
	 *  @see Hex
	 *  @see HexMap
	 *  @see Joueur
	 *  @see Unite
	 */
	@Override
	public void combat(HexMap map, Joueur joueurAct, Unite unite) {
		if (hasAttacked) {
			return;
		}

		final int crit = 2;
		final int chanceCrit = 2;
		int rand = (int) (Math.random() * 10);
		List<Hex> trajet = new ArrayList<Hex>();
		if (this.hex.distance(unite.hex) <= 4) {
			if (rand > chanceCrit) {
				unite.pointsDeVie = (int) (unite.pointsDeVie - (this.pointsAttaque - unite.pointsDefense));
				hasAttacked = true;
			} else {
				unite.pointsDeVie = (int) (unite.pointsDeVie - (crit * (this.pointsAttaque - unite.pointsDefense)));
				hasAttacked = true;
			}
		} else {
			trajet = map.pathfinding(this.hex, unite.hex);
			for (Hex hex : trajet) {
				if ((hex.distance(unite.hex) <= portee) && ((map.moveCost(hex, unite.hex)) <= this.pointsDeplacement) && hex.getUnit() == null) {
					this.getHex().setUnit(null);
				 	hex.setUnit(this);
					this.setHex(hex);
					map.reveal(joueurAct, this.hex, this.vision);
					this.setDefense((int) ((float) (this.getHex().getDefense() / 100) * this.pointsDefenseInit + this.pointsDefenseInit));
					if (rand > chanceCrit) {
						unite.pointsDeVie = (int) (unite.pointsDeVie - (this.pointsAttaque - unite.pointsDefense));
					} else {
						unite.pointsDeVie = (int) (unite.pointsDeVie - (crit * (this.pointsAttaque - unite.pointsDefense)));
					}
					break;
				}
			}
		}
		if (unite.getPointsDeVie() <= 0) {
			unite.joueur.getUnite().remove(unite);
			unite.getHex().setUnit(null);
		}
		this.pointsDeplacement = 0;
	}

	/**
	 * R�cup�re la port�e.
	 * @return int
	 */
	public int getPortee() {
		return portee;
	}
}
