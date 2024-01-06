package ecommerce.repositories;

import ecommerce.entities.Detail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetailRepository extends JpaRepository<Detail, Long> {
    List<Detail> findBySale_Id(Long saleId);
}
