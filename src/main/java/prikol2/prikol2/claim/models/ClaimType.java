package prikol2.prikol2.claim.models;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "claim_type")
public class ClaimType {
    @Id
    @Column(name = "code", length = 7)
    private String code;

    @Column(name = "name", nullable = false, length = 15)
    private String name;
}
