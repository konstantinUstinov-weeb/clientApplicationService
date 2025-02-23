package prikol2.prikol2.claim.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import prikol2.prikol2.claim.models.ClaimDto;
import prikol2.prikol2.claim.service.ClaimService;

@RestController
@RequestMapping("/user")
public class ClaimController {
    private final ClaimService claimService;

    @Autowired
    public ClaimController(ClaimService claimService) {
        this.claimService = claimService;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ClaimDto createClaim(@RequestBody ClaimDto claimDto) {
        return claimService.createClaim(claimDto);
    }


}
