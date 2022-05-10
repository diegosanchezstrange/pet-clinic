package guru.springframework.petclinic.services.springdatajpa;

import guru.springframework.petclinic.model.Owner;
import guru.springframework.petclinic.repositories.OwnerRepository;
import guru.springframework.petclinic.repositories.PetRepository;
import guru.springframework.petclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {

    public static final String  LAST_NAME = "Smith";
    public static final Long    FIRST_ID = 1L;
    @Mock
    OwnerRepository             ownerRepository;
    @Mock
    PetRepository               petRepository;
    @Mock
    PetTypeRepository           petTypeRepository;

    @InjectMocks
    OwnerSDJpaService           ownerSDJpaService;

    Owner                       returnedOwner;

    @BeforeEach
    void setUp()
    {
        this.returnedOwner = Owner.builder().id(FIRST_ID).firstName("Pedro").lastName(LAST_NAME).build();
    }

    @Test
    void findAll()
    {
        Set<Owner> returnedOwners = new HashSet<>();
        returnedOwners.add(Owner.builder().id(1L).build());
        returnedOwners.add(Owner.builder().id(2L).build());

        when(ownerRepository.findAll()).thenReturn(returnedOwners);
        Set<Owner> owners = ownerSDJpaService.findAll();

        assertNotNull(owners);
        assertEquals(2, owners.size());
    }

    @Test
    void findById()
    {
        when(ownerRepository.findById(FIRST_ID)).thenReturn(Optional.of(returnedOwner));

        Owner owner = ownerSDJpaService.findById(FIRST_ID);

        assertNotNull(owner);
        assertEquals(FIRST_ID, owner.getId());
    }

    @Test
    void findByIdNotFound()
    {
        when(ownerRepository.findById(FIRST_ID)).thenReturn(Optional.empty());

        Owner owner = ownerSDJpaService.findById(FIRST_ID);

        assertNull(owner);
    }

    @Test
    void save()
    {
        when(ownerRepository.save(returnedOwner)).thenReturn(returnedOwner);
        Owner owner = ownerSDJpaService.save(returnedOwner);
        assertNotNull(owner);
        verify(ownerRepository).save(returnedOwner);
    }

    @Test
    void delete()
    {
        ownerSDJpaService.delete(returnedOwner);
        verify(ownerRepository).delete(returnedOwner);
    }

    @Test
    void deleteById()
    {
        ownerSDJpaService.deleteById(FIRST_ID);
        verify(ownerRepository).deleteById(FIRST_ID);
    }

    @Test
    void findByLastName()
    {
        when(ownerRepository.findByLastName(any())).thenReturn(returnedOwner);
        Owner smith = ownerSDJpaService.findByLastName(LAST_NAME);
        assertEquals(LAST_NAME, smith.getLastName());
        verify(ownerRepository).findByLastName(LAST_NAME);
    }
}