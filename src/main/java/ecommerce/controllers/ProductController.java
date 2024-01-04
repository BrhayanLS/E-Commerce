package ecommerce.controllers;

import ecommerce.entities.Message;
import ecommerce.entities.Product;
import ecommerce.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /*@GetMapping("/{productId}")
    public ResponseEntity<Object> getProductById(@PathVariable Long productId) {
        Optional<Product> productOptional = this.productService.getProductById(productId);
        if (productOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productOptional.get(), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getAllProducts() {
        return new ResponseEntity<>(this.productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/related/{category}/{productId}")
    public ResponseEntity<Object> getRelatedProduct(@PathVariable String category, @PathVariable Long ProductId) {
        return new ResponseEntity<>(this.productService.getRelatedProducts(category, ProductId), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Message> createProduct(@Valid @RequestBody Product product, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new ResponseEntity<>(new Message("Revise los campos"), HttpStatus.BAD_REQUEST);
        }
        this.productService.saveProduct(product);
        return new ResponseEntity<>(new Message("Creado Correctamente"), HttpStatus.OK);
    }

    @PutMapping("/update/productId")
    public ResponseEntity<Message> updateProduct(@Valid @RequestBody Product product, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new ResponseEntity<>(new Message("Revise los campos"), HttpStatus.BAD_REQUEST);
        }
        this.productService.saveProduct(product);
        return new ResponseEntity<>(new Message("Actualizado Correctamente"), HttpStatus.OK);
    }*/

    @GetMapping("/list")
    public ResponseEntity<List<Product>> getAll(){
        List<Product> foundProducts = this.productService.getAll();
        return new ResponseEntity<>(foundProducts, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    public ResponseEntity<Message> save(@Valid @RequestBody Product product, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity<>(new Message("Los campos ingresados son incorrectos"), HttpStatus.BAD_REQUEST);
        this.productService.create(product);
        return new ResponseEntity<>(new Message("Producto guardado"), HttpStatus.OK);
    }
}