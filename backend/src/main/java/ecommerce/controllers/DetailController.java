package ecommerce.controllers;

import ecommerce.entities.Detail;
import ecommerce.services.DetailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/saleDetail")
public class DetailController {
    private final DetailService detailService;

    public DetailController(DetailService detailService) {
        this.detailService = detailService;
    }

    @GetMapping("/{saleId}")
    public ResponseEntity<List<Detail>> getDetailBySale(@PathVariable Long saleId){
        return new ResponseEntity<>(this.detailService.getDetailBySale(saleId), HttpStatus.OK);
    }
}
