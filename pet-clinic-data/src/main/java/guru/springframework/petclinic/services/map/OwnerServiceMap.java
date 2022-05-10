package guru.springframework.petclinic.services.map;

import guru.springframework.petclinic.model.Owner;
import guru.springframework.petclinic.services.OwnerService;
import guru.springframework.petclinic.services.PetService;
import guru.springframework.petclinic.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default", "map"})
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService
{
	private PetService		petService;
	private PetTypeService	petTypeService;

	public OwnerServiceMap(PetService petService, PetTypeService petTypeService) {
		this.petService = petService;
		this.petTypeService = petTypeService;
	}

	@Override
	public Set<Owner> findAll()
	{
		return (super.findAll());
	}

	@Override
	public Owner findById(Long id)
	{
		return (super.findById(id));
	}

	@Override
	public Owner save(Owner object)
	{
		if (object == null)
			return (null);
		if (object.getPets() != null)
		{
			object.getPets().forEach( pet ->
			{
				if (pet.getPetType() == null)
					throw new RuntimeException("Pet Type is required");
				if (pet.getPetType().getId() == null)
					pet.setPetType(petTypeService.save(pet.getPetType()));
				if (pet.getId() == null)
					pet.setId(petService.save(pet).getId());
			});
		}
		return (super.save(object));
	}

	@Override
	public void delete(Owner object)
	{
		super.delete(object);
	}

	@Override
	public void deleteById(Long id)
	{
		super.deleteById(id);
	}

	@Override
	public Owner findByLastName(String lastName) {
		return (super.findAll()
				.stream()
				.filter(owner -> owner.getLastName().equalsIgnoreCase(lastName))
				.findFirst()
				.orElse(null));
	}
}
