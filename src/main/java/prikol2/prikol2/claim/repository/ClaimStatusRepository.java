package prikol2.prikol2.claim.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import prikol2.prikol2.claim.models.ClaimStatus;

public interface ClaimStatusRepository extends JpaRepository<ClaimStatus, Long> {

    ClaimStatus findClaimStatusByCode(String code);
}
