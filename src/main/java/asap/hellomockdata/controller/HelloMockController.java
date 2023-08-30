package asap.hellomockdata.controller;

import asap.hellomockdata.service.CreateMockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloMockController {

    private final CreateMockService createMockService;

    public HelloMockController(final CreateMockService createMockService) {
        this.createMockService = createMockService;
    }

    @PostMapping("/carts")
    public ResponseEntity<Void> createCartsMock() {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/products")
    public ResponseEntity<Void> createProductsMock(
        @RequestParam final int size
    ) {
        createMockService.createProductsMock(size);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/payments")
    public ResponseEntity<Void> createPaymentsMock() {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/orders")
    public ResponseEntity<Void> createOrdersMock(
        @RequestParam final int size,
        @RequestParam final Long userId
    ) {
        createMockService.createOrdersMock(size, userId);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/users")
    public ResponseEntity<Void> createUsersMock(
        @RequestParam final int size
    ) {
        createMockService.createUsersMock(size);

        return ResponseEntity.ok().build();
    }
}
