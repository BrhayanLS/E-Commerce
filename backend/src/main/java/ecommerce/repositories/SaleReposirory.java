package ecommerce.repositories;

import ecommerce.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SaleReposirory extends JpaRepository<Sale, Long> {
    List<Sale> findByClient_UserName(String userName);
}
