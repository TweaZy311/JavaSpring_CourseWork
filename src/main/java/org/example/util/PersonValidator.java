package org.example.util;

import org.example.entities.User;
import org.example.services.PeopleDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {
    private final PeopleDetailsService peopleDetailsService;

    @Autowired
    public PersonValidator(PeopleDetailsService peopleDetailsService) {
        this.peopleDetailsService = peopleDetailsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User)target;

        try {
            peopleDetailsService.loadUserByUsername(user.getUsername());
        } catch(UsernameNotFoundException ignored) {
            return;
        }

        errors.rejectValue("username", "", "There is already person with that username");
    }
}
