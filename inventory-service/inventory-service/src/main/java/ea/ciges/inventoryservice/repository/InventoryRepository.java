package ea.ciges.inventoryservice.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ea.ciges.inventoryservice.model.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

	Optional<Inventory> findBySkuCode(String skuCode);
}