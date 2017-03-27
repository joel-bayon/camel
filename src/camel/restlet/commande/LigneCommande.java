package camel.restlet.commande;

public class LigneCommande {
	Produit produit;
	int quantite;
	
	public LigneCommande(Produit produit, int quantite) {
		super();
		this.produit = produit;
		this.quantite = quantite;
	}
	public Produit getProduit() {
		return produit;
	}
	public void setProduit(Produit produit) {
		this.produit = produit;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	@Override
	public String toString() {
		return "LigneCommande [produit=" + produit + ", quantite=" + quantite + "]";
	}
	

}
