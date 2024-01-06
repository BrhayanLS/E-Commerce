package ecommerce.security.services;

import ecommerce.security.entities.User;
import ecommerce.security.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getByUserName(String userName){
        return this.userRepository.findByUserName(userName);
    }

    public boolean existsByUserName(String userName){
        return this.userRepository.existsByUserName(userName);
    }

    public void save (User user){
        this.userRepository.save(user);
    }
}
