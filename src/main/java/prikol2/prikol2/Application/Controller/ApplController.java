package prikol2.prikol2.Application.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import prikol2.prikol2.Application.models.ApplicationDto;
import prikol2.prikol2.Application.service.ApplicationService;

@RestController
@RequestMapping("/user")
public class ApplController {
    private final ApplicationService applicationService;

    @Autowired
    public ApplController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ApplicationDto createApplication(@RequestBody ApplicationDto applicationDto) {
        return applicationService.createApplication(applicationDto);
    }


}
