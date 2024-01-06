package ecommerce.controllers;

import ecommerce.entities.Message;
import ecommerce.entities.ShoppingCart;
import ecommerce.services.ShoppingCartService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shoppingList")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping()
    public ResponseEntity<List<ShoppingCart>> getListByClient(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String userName = userDetails.getUsername();
        return new ResponseEntity<>(this.shoppingCartService.getListByClient(userName), HttpStatus.OK);
    }

    @GetMapping("/count/{clientId}")
    public ResponseEntity<Long> countByClient(@PathVariable Long clientId){
        return new ResponseEntity<>(this.shoppingCartService.getCountByClient(clientId), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Message> addProduct(@Valid @RequestBody ShoppingCart shoppingCart,
                                              BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new ResponseEntity<>(new Message("Revise los campos"), HttpStatus.BAD_REQUEST);
        }
        this.shoppingCartService.addProduct(shoppingCart);
        return new ResponseEntity<>(new Message("Producto agregado"), HttpStatus.OK);
    }

    @DeleteMapping("/clean/{itemId}")
    public ResponseEntity<Message> removeProduct(@PathVariable Long itemId){
        this.shoppingCartService.removeProduct(itemId);
        return new ResponseEntity<>(new Message("Eliminado"), HttpStatus.OK);
    }
}
