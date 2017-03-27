package camel.restlet.commande;

import java.util.List;

import org.apache.camel.Header;
import org.apache.camel.language.XPath;

public class ToCommande {
	
	public Commande toCommande(@Header("commandeId")  int commandeId, 
			@XPath("//client/text()") int idClient, 
			@XPath("//lignecommande/produit/text()") List<String> referenceProduits,
			@XPath("//lignecommande/quantite/text()") List<Integer> quantites
			) {
		
		Commande commande = new Commande(commandeId);
		commande.setClient(Client.getReferentielClient().get(idClient));
		for(int i = 0; i<referenceProduits.size(); i++) {
			LigneCommande lc = new LigneCommande(
					                  Produit.getProduitByReference(referenceProduits.get(i)),
					                  quantites.get(i));
			commande.getLigneCommandes().add(lc);
		}
		return commande;
		
	}

}
