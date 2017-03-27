package camel.restlet.commande;

import java.util.HashMap;
import java.util.Map;

public class Client {
	int id;
	String nom;
	String prenom;
	String email;
	
	static Map<Integer, Client> referentielClient = new HashMap<>();
	static {
		referentielClient.put(1, new Client(1, "Marc", "Thierry", "marc.thierry@yahoo.com"));
		referentielClient.put(2, new Client(2, "Rémi", "Stéphane", "remi.stephane@gmail.com"));
		referentielClient.put(3, new Client(3, "Gérald","Dominique","gerald.dominique@laposte.fr"));
	}
	
	public Client() {}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public static Map<Integer, Client> getReferentielClient() {
		return referentielClient;
	}

	public Client(int id, String nom, String prenom, String email) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
	}

	static Client getProduitByReference(int id) {
		return referentielClient.get(id);
	}

}
