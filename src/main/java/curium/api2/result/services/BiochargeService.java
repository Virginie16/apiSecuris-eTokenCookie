package curium.api2.result.services;

import curium.api2.result.models.Biocharge;
import curium.api2.result.repositories.BiochargeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BiochargeService {
	@Autowired
	private BiochargeRepository biochargeRepository;



	public List<Biocharge> getLotsFiltres(String site, String produit) {
		return biochargeRepository.findBySiteProduitAndPeriodeWithLogging(site, produit);
	}
}
