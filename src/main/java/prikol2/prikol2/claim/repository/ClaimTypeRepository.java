package prikol2.prikol2.claim.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import prikol2.prikol2.claim.models.ClaimType;

public interface ClaimTypeRepository extends JpaRepository<ClaimType, Long> {
    ClaimType findClaimTypeByCode(String code);
}
