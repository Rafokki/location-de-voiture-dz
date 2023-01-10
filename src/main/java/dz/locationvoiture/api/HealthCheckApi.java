package dz.locationvoiture.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class HealthCheckApi {


    @GetMapping("health-check")
    public ResponseEntity<Health> healthCheck() {
        return ResponseEntity.ok(new Health("Service UP",HttpStatus.OK ));
    }

    private record Health(String message, HttpStatus httpStatus) {

    }
}
