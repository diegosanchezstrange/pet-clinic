package guru.springframework.petclinic.services.map;

import guru.springframework.petclinic.model.Vet;
import guru.springframework.petclinic.services.CrudService;
import guru.springframework.petclinic.services.SpecialityService;
import guru.springframework.petclinic.services.VetService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class VetServiceMap extends AbstractMapService<Vet, Long> implements VetService
{
	private final SpecialityService	specialityService;

	public VetServiceMap(SpecialityService specialityService) {
		this.specialityService = specialityService;
	}

	@Override
	public Set<Vet> findAll()
	{
		return (super.findAll());
	}

	@Override
	public Vet findById(Long id)
	{
		return (super.findById(id));
	}

	@Override
	public Vet save(Vet object)
	{
		// Check if specialties are saved, if not save them and give them an ID
		if (object.getSpecialties().size() > 0)
			object.getSpecialties().forEach( specialty ->
			{
				if (specialty.getId() == null)
					specialty.setId(specialityService.save(specialty).getId());
			});
		return (super.save(object));
	}

	@Override
	public void delete(Vet object)
	{
		super.delete(object);
	}

	@Override
	public void deleteById(Long id)
	{
		super.deleteById(id);
	}
}
