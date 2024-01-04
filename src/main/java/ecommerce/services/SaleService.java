package ecommerce.services;

import ecommerce.entities.Detail;
import ecommerce.entities.Sale;
import ecommerce.entities.ShoppingCart;
import ecommerce.repositories.SaleReposirory;
import ecommerce.security.entities.User;
import ecommerce.security.services.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
@Transactional
public class SaleService {

    private final SaleReposirory saleRepository;
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;
    private final DetailService detailService;

    @Autowired
    public SaleService(SaleReposirory saleRepository, UserService userService, ShoppingCartService shoppingCartService,
                       DetailService detailService) {
        this.saleRepository = saleRepository;
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
        this.detailService = detailService;
    }

    public List<Sale> getSalesByClient(String userName) {
        return this.saleRepository.findByClient_UserName(userName);
    }

    public void createSale(String userName) {
        User client = this.userService.getByUserName(userName).get();
        List<ShoppingCart> shoppingCartList = this.shoppingCartService.getListByClient(client.getUserName());
        DecimalFormat decimalFormat = new DecimalFormat("0.00", new DecimalFormatSymbols(Locale.US));
        decimalFormat.setRoundingMode(RoundingMode.DOWN);
        double total = shoppingCartList.stream().mapToDouble(shoppingCartItem -> shoppingCartItem.getProduct().getPrice()
                * shoppingCartItem.getAmount()).sum();
        Sale sale = new Sale(Double.parseDouble(decimalFormat.format(total)), new Date(), client);
        Sale saveSale = this.saleRepository.save(sale);
        for (ShoppingCart shoppingCart : shoppingCartList) {
            Detail detail = new Detail();
            detail.setProduct(shoppingCart.getProduct());
            detail.setAmount(shoppingCart.getAmount());
            detail.setSale(saveSale);
            this.detailService.createDetail(detail);
        }
        this.shoppingCartService.cleanShoppngCart(client.getId());
    }
}