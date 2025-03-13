package curium.api2.result.controllers;

import curium.api2.result.models.Biocharge;
import curium.api2.result.models.ChangeControl;
import curium.api2.result.services.BiochargeService;
import curium.api2.result.services.ChangeControlService;
import curium.api2.select.services.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/result")
public class ResultController {
	@Autowired
	private BiochargeService biochargeService;

	@Autowired
	private ChangeControlService changeControlService;

	private final ValidationService validationService;

	public ResultController(ValidationService validationService) {
		this.validationService = validationService;
	}

	@GetMapping("/biocharge")
	public ResponseEntity<List<Biocharge>> getLotsFiltres(
			@RequestParam(name = "site", required = true) String site,
			@RequestParam(name = "produit", required = true) String produit) {

		// Valider les paramètres
		ResponseEntity<?> validationResponse = validationService.validerParamètres(site, produit);
		if (validationResponse != null) {// Retourner l'erreur si invalide
			return (ResponseEntity<List<Biocharge>>) validationResponse;
		}

		// Récupérer les lots filtrés
		List<Biocharge> biocharges = biochargeService.getLotsFiltres(site, produit);

		// Vérifier si des lots ont été trouvés
		if (biocharges == null || biocharges.isEmpty()) {
			return status(HttpStatus.NOT_FOUND)
					.body(Collections.singletonList(new Biocharge("No lots found for the provided site and product")));
		}

		// Retourner les lots filtrés
		return ResponseEntity.ok(biocharges);
	}

	@GetMapping("/change-control")
	public ResponseEntity<List<ChangeControl>> getLotsFiltresChange(
			@RequestParam(name = "site", required = false) String site,
			@RequestParam(name = "produit", required = false) String produit) {

		// Valider les paramètres
		ResponseEntity<?> validationResponse = validationService.validerParamètres(site, produit);
		if (validationResponse != null) {
			return (ResponseEntity<List<ChangeControl>>) validationResponse; // Retourner l'erreur si invalide
		}

		// Récupérer les lots filtrés
		List<ChangeControl> changeControls = changeControlService.getLotsFiltresChange(site, produit);

		// Vérifier si des lots ont été trouvés
		if (changeControls == null || changeControls.isEmpty()) {
			return status(HttpStatus.NOT_FOUND)
					.body(Collections.singletonList(new ChangeControl("No lots found for the provided site and product")));
		}

		// Retourner les lots filtrés
		return ResponseEntity.ok(changeControls);
	}
}
