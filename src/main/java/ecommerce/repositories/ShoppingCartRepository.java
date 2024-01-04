package ecommerce.repositories;

import ecommerce.entities.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    List<ShoppingCart> findByClient_Id(Long clientId);
    List<ShoppingCart> findByClient_UserName(String userName);
    void deleteByClient_Id(Long clientId);
    Long countByClient_Id(Long id);
}
