package pcmt.Alumni_Gallery.pcmt_memories.Services;



import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pcmt.Alumni_Gallery.pcmt_memories.Entites.AlumniRegistration;
import pcmt.Alumni_Gallery.pcmt_memories.Repos.AlumniRegistrationRepository;


@Service
@RequiredArgsConstructor
public class AlumniRegistrationService {

    private final AlumniRegistrationRepository repository;

    public AlumniRegistration save(AlumniRegistration alumni) {
        return repository.save(alumni);
    }
}
