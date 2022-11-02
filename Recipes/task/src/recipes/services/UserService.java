package recipes.services;

import recipes.models.Users;

import javax.transaction.Transactional;

public interface UserService {
    @Transactional
    Users get(String email);

    @Transactional
    void save(Users user);
}
