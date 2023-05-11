package org.example.services;


import org.example.repositories.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.example.entities.Service;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class ServiceService {
    private final ServiceRepository serviceRepository;

    @Autowired
    public ServiceService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public Service add(Service service){
        return serviceRepository.save(service);
    }

    public List<Service> findAll(){
        return serviceRepository.findAll();
    }
    public Optional<Service> findById(Integer id){
        return serviceRepository.findById(id);
    }
    public void deleteById(Integer id){
        serviceRepository.deleteById(id);
    }
}
