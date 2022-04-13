package ea.ciges.inventoryservice.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ea.ciges.inventoryservice.model.Inventory;
import ea.ciges.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
@Slf4j
public class InventoryRestController {

	private final InventoryRepository inventoryRepository;

	@GetMapping("/{skuCode}")
	Boolean isInStock(@PathVariable String skuCode) {
		log.info("Comprobando si quedan productos con skucode - " + skuCode);
		Inventory inventory = inventoryRepository.findBySkuCode(skuCode)
				.orElseThrow(() -> new RuntimeException("No pueden encontrarse productos con sku code " + skuCode));
		return inventory.getStock() > 0;
	}
}