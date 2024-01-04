package ecommerce.services;

import ecommerce.entities.Product;
import ecommerce.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@Transactional
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /*public void saveProduct(Product product){
        this.productRepository.save(product);
    }

    public List<Product> getAllProducts(){
        return this.productRepository.findAll();
    }

    public List<Product> getRelatedProducts(String category, Long productId){
        List<Product> productList = this.productRepository.findByCategoryAndIdNot(category, productId);
        List<Product> randomProducts = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 2; i++) {
            int ramdomIndex = random.nextInt(productList.size());
            randomProducts.add(productList.get(ramdomIndex));
            productList.remove(ramdomIndex);
        }
        return randomProducts;
    }

    public Optional<Product> getProductById(Long id){
        return  this.productRepository.findById(id);
    }

    public List<Product> getBestPriceProducts(){
        return this.productRepository.findFirst5ByOrderByPriceAsc();
    }*/

    public List<Product> getAll(){
        return this.productRepository.findAll();
    }
    public void create(Product product){
        this.productRepository.save(product);
    }
}
