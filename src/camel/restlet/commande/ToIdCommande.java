package camel.restlet.commande;

public class ToIdCommande {
	static int cptCommande = 1;
	
	public String toIdCommande() {
		return "<idCommande>" +cptCommande++ + "</idCommande>";
	}
	

}
