package dev.rdx.perfumeshop.services;

import dev.rdx.perfumeshop.models.User;
import dev.rdx.perfumeshop.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsersService implements UserDetailsService {
    @Autowired
    private UsersRepository repository;

    public User save(User users) {
        return repository.save(users);
    }

    public User byId(Integer id) {
        return repository.findById(id).get();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User users = repository.findByEmailLikeIgnoreCase(username);

        if (users == null){
            throw new UsernameNotFoundException("User not found");
        }

        return users;
    }
}
