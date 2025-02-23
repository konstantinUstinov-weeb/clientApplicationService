package prikol2.prikol2.claim.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import prikol2.prikol2.claim.models.Claim;
import java.util.Optional;

public interface ClaimRepositoryImpl extends JpaRepository<Claim, Long> {
    Claim findClaimById(Long id);

    Optional<Claim> getClaimByNumber(String number);
}
