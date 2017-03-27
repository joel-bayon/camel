package camel.restlet.commande;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

//@XmlRootElement
public class Commande {
	int idCommande;
	Date date = new Date();
	
	Client client;
	List<LigneCommande> ligneCommandes = new ArrayList<>();
	
	public Commande() {}
	
	public Commande(int idCommande) {
		super();
		this.idCommande = idCommande;
		this.date = date;
	}
	public int getIdCommande() {
		return idCommande;
	}
	public void setIdCommande(int idCommande) {
		this.idCommande = idCommande;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public List<LigneCommande> getLigneCommandes() {
		return ligneCommandes;
	}
	public void setLigneCommandes(List<LigneCommande> ligneCommandes) {
		this.ligneCommandes = ligneCommandes;
	}

	@Override
	public String toString() {
		return "Commande [idCommande=" + idCommande + ", date=" + date + ", client=" + client + ", ligneCommandes="
				+ ligneCommandes + "]";
	}
	
}
