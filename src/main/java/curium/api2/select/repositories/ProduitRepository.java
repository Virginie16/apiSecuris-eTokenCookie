package curium.api2.select.repositories;

import curium.api2.select.models.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, String> {
	boolean existsByNomProduit(String nomProduit);
}
