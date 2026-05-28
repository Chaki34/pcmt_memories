package pcmt.Alumni_Gallery.pcmt_memories.Entites;



import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "alumni_registration")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlumniRegistration {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(unique = true, nullable = false)
    private String email;

    private Integer batchYear;

    private String currentCompany;

    private String department;


    private LocalDateTime createdAt = LocalDateTime.now();
}
