package camel.restlet.commande;

import java.util.HashMap;
import java.util.Map;

public class Produit {
	String reference;
	String libelle;
	float prix;
	
	static Map<String, Produit> referentielProduit = new HashMap<>();
	static {
		referentielProduit.put("a", new Produit("a", "bouteille de rouge", 17.0f));
		referentielProduit.put("a", new Produit("b", "saucisson de Valbone", 8.0f));
		referentielProduit.put("c", new Produit("c", "mandarine de Corse", 10.0f));
	}
	
	public Produit() {}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public float getPrix() {
		return prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}

	public static Map<String, Produit> getReferentielProduit() {
		return referentielProduit;
	}

	public static void setReferentielProduit(Map<String, Produit> referentielProduit) {
		Produit.referentielProduit = referentielProduit;
	}

	public Produit(String reference, String libelle, float prix) {
		super();
		this.reference = reference;
		this.libelle = libelle;
		this.prix = prix;
	}

	@Override
	public String toString() {
		return "Produit [reference=" + reference + ", libelle=" + libelle + ", prix=" + prix + "]";
	}
	
	static Produit getProduitByReference(String reference) {
		return referentielProduit.get(reference);
	}

}
