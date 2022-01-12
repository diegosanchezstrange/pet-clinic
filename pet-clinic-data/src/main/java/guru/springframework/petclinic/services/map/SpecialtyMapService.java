package guru.springframework.petclinic.services.map;

import guru.springframework.petclinic.model.Specialty;
import guru.springframework.petclinic.services.SpecialitesService;

import java.util.Set;

public class SpecialtyMapService extends AbstractMapService<Specialty, Long> implements SpecialitesService {
    @Override
    public Set<Specialty> findAll() {
        return (super.findAll());
    }

    @Override
    public Specialty findById(Long id) {
        return (super.findById(id));
    }

    @Override
    public Specialty save(Specialty object) {
        return (super.save(object));
    }

    @Override
    public void delete(Specialty object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
