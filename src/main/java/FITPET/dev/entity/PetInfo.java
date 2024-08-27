package FITPET.dev.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PetInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long petInfoId;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "pet_id", nullable = true)
    private Pet pet;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false, length = 20)
    private String phoneNum;

    @Column(nullable = false)
    private LocalDateTime createdAt;

}
