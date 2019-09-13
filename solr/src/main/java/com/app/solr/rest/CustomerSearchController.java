package com.app.solr.rest;




import com.app.solr.domain.CustomerDetails;
import com.app.solr.service.CustomerSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("/customer")
public class CustomerSearchController {
    private static final Mono<ResponseEntity<CustomerDetails>> NOT_FOUND = Mono.just(ResponseEntity.notFound().build());

    private final CustomerSearchService customerSearchService;

    @GetMapping("/{id}")
    public Mono<ResponseEntity<CustomerDetails>> get(@PathVariable("id") String id) {
        System.out.println("Request received with id ->"+id);
        Mono<ResponseEntity<CustomerDetails>> mono = null;
        try {
            mono = customerSearchService
                    .findById(id)
                    .map(ResponseEntity::ok)
                    .switchIfEmpty(NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Request completed");
        return mono;
    }
}
