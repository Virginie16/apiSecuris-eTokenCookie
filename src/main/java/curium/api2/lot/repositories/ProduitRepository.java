package curium.api2.lot.repositories;

import curium.api2.lot.models.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, String> {
	boolean existsByNomProduit(String nomProduit);
}
