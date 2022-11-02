package recipes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import recipes.models.Users;
import recipes.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public Users get(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void save(Users user) {
        userRepository.save(user);
    }
}
