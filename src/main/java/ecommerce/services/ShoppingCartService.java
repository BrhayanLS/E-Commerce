package ecommerce.services;

import ecommerce.entities.ShoppingCart;
import ecommerce.repositories.ShoppingCartRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;

    @Autowired
    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    public List<ShoppingCart> getListByClient(String userName){
        return this.shoppingCartRepository.findByClient_UserName(userName);
    }

    public void cleanShoppngCart(Long clientId){
        this.shoppingCartRepository.deleteByClient_Id(clientId);
    }

    public void removeProduct(Long id){
        this.shoppingCartRepository.deleteById(id);
    }

    public void addProduct(ShoppingCart shoppingCart){
        this.shoppingCartRepository.save(shoppingCart);
    }

    public Long getCountByClient(Long clientId){
        return this.shoppingCartRepository.countByClient_Id(clientId);
    }
}
