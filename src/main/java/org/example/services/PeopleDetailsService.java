package org.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.example.entities.User;
import org.example.repositories.UserRepository;
import org.example.security.PersonDetails;

@Service
public class PeopleDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public PeopleDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null)
            throw new UsernameNotFoundException("User not found!");

        return new PersonDetails(user);
    }

//    public boolean addUser(Person person){
//        Person personFromDb = peopleRepository.findByUsername(person.getUsername());
//
//        if (personFromDb != null){
//            return false;
//        }
//
//        personFromDb.setRole("ROLE_USER");
//
//    }
}
