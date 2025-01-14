package labshopcompensationme.domain;

import labshopcompensationme.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel="inventories", path="inventories")
public interface InventoryRepository extends PagingAndSortingRepository<Inventory, Long>{

}
