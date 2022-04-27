package guru.springframework.petclinic.services.springdatajpa;

import guru.springframework.petclinic.model.Owner;
import guru.springframework.petclinic.repositories.OwnerRepository;
import guru.springframework.petclinic.repositories.PetRepository;
import guru.springframework.petclinic.repositories.PetTypeRepository;
import guru.springframework.petclinic.services.OwnerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class OwnerSDJpaService implements OwnerService {

    private final OwnerRepository   ownerRepository;
    private final PetRepository     petRepository;
    private final PetTypeRepository petTypeRepository;

    public OwnerSDJpaService(OwnerRepository ownerRepository, PetRepository petRepository, PetTypeRepository petTypeRepository) {
        this.ownerRepository = ownerRepository;
        this.petRepository = petRepository;
        this.petTypeRepository = petTypeRepository;
    }

    @Override
    public Set<Owner> findAll() {
        Set<Owner> ownerSet = new HashSet();

        ownerRepository.findAll().forEach(ownerSet::add);
        return (ownerSet);
    }

    @Override
    public Owner findById(Long aLong) {
        return (ownerRepository.findById(aLong).orElse(null));
    }

    @Override
    public Owner save(Owner object) {
        return (ownerRepository.save(object));
    }

    @Override
    public void delete(Owner object) {
        ownerRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        ownerRepository.deleteById(aLong);
    }

    @Override
    public Owner findByLastName(String lastName) {
        return (ownerRepository.findByLastName(lastName));
    }
}