package ecommerce.services;

import ecommerce.entities.Detail;
import ecommerce.repositories.DetailRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class DetailService {

    private final DetailRepository detailRepository;

    @Autowired
    public DetailService(DetailRepository detailRepository) {
        this.detailRepository = detailRepository;
    }

    public void createDetail(Detail detail){
        this.detailRepository.save(detail);
    }

    public List<Detail> getDetailBySale(Long saleId){
        return  this.detailRepository.findBySale_Id(saleId);
    }
}
