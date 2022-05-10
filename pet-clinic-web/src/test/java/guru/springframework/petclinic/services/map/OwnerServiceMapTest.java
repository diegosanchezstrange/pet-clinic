package guru.springframework.petclinic.services.map;

import guru.springframework.petclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerServiceMapTest {

    OwnerServiceMap ownerServiceMap;

    final Long      idOwner = 1L;
    final String    lastNameOwner = "Strange";

    @BeforeEach
    void setUp()
    {
        ownerServiceMap = new OwnerServiceMap(new PetServiceMap(), new PetTypeMapService());

        ownerServiceMap.save(Owner.builder().id(idOwner).lastName(lastNameOwner).build());
    }

    @Test
    void findAll()
    {
        Set<Owner> ownerSet = ownerServiceMap.findAll();

        assertEquals(1, ownerSet.size());
    }

    @Test
    void findById()
    {
        Owner owner = ownerServiceMap.findById(idOwner);

        assertEquals(idOwner, owner.getId());
    }

    @Test
    void saveWithId()
    {
        Long    newId = 2L;
        Owner   newOwner = Owner.builder().id(newId).build();
        Owner   savedOwner = ownerServiceMap.save(newOwner);

        assertEquals(newId, savedOwner.getId());
    }

    @Test
    void saveWithoutId()
    {
        Owner   newOwner = Owner.builder().build();
        Owner   savedOwner = ownerServiceMap.save(newOwner);

        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());
    }

    @Test
    void delete()
    {
        ownerServiceMap.delete(ownerServiceMap.findById(idOwner));

        assertEquals(0, ownerServiceMap.findAll().size());
    }

    @Test
    void deleteById()
    {
        ownerServiceMap.deleteById(idOwner);

        assertEquals(0, ownerServiceMap.findAll().size());
    }

    @Test
    void findByLastName()
    {
        Owner owner = ownerServiceMap.findByLastName(lastNameOwner);

        assertNotNull(owner);
        assertNotNull(owner.getId());
    }

    @Test
    void findByLastNameNotFound()
    {
        Owner owner = ownerServiceMap.findByLastName("Sanchez");

        assertNull(owner);
    }
}