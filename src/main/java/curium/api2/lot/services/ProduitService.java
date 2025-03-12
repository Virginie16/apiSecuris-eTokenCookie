package curium.api2.lot.services;

import curium.api2.lot.models.Produit;
import curium.api2.lot.repositories.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProduitService {
	@Autowired
	private ProduitRepository produitRepository;

	// Vérifie si le produit existe dans la BDD
	public boolean isProduitConforme(String produit) {
		return produitRepository.existsByNomProduit(produit);
	}

	public List<String> getAllProduits() {
		return produitRepository.findAll()
				.stream()
				.map(Produit::getNomProduit)
				.collect(Collectors.toList());
	}
}
