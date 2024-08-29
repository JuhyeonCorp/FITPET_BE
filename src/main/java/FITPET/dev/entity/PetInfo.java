package FITPET.dev.entity;

import FITPET.dev.common.base.BaseEntity;
import FITPET.dev.common.enums.PetInfoStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PetInfo extends BaseEntity {
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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PetInfoStatus petInfoStatus;

    @Column(nullable = true, length = 500)
    private String comment;

    public void updateStatus(PetInfoStatus petInfoStatus){
        this.petInfoStatus = petInfoStatus;
    }

}
