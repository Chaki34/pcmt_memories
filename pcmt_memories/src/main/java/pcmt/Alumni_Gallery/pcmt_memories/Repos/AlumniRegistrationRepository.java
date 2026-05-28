package pcmt.Alumni_Gallery.pcmt_memories.Repos;



import org.springframework.data.jpa.repository.JpaRepository;
import pcmt.Alumni_Gallery.pcmt_memories.Entites.AlumniRegistration;


import java.util.UUID;

public interface AlumniRegistrationRepository extends JpaRepository<AlumniRegistration, UUID> {
}