package curium.api2.select.repositories;

import curium.api2.select.models.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SiteRepository extends JpaRepository<Site, Integer> {
	boolean existsByNomSite(String nomSite);

}