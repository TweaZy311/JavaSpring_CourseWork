package org.example.services;

import org.example.entities.Application;
import org.example.repositories.ApplicationRepository;
import org.example.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final UserRepository userRepository;

    @Autowired
    public ApplicationService(ApplicationRepository applicationRepository, UserRepository userRepository) {
        this.applicationRepository = applicationRepository;
        this.userRepository = userRepository;
    }

    public List<Application> findAll(){
        return applicationRepository.findAll();
    }
    public void saveApplication(Application application){
        applicationRepository.save(application);
    }
}
