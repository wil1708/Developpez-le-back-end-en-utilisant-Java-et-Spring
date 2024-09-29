package com.apiChatop.controllers;

import com.apiChatop.dtos.JsonResponse;
import com.apiChatop.dtos.RentalDto;
import com.apiChatop.dtos.UserDto;
import com.apiChatop.services.RentalService;
import com.apiChatop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class RentalController {

    @Autowired
    private RentalService rentalService;

    @Autowired
    private UserService userService;

    /**
     * Chemin relatif pour la sauvegarde des images lors de la création d'un Rental
     */
    private static final String UPLOAD_DIR = "apiChatop/src/main/resources/static/pictures";

    /**
     * Correspond à http://localhost:8080 (voir application.properties)
     */
    @Value("${server.base-url}")
    private String baseUrl;

    /**
     * Méthode d'afficher une liste de rentals
     * @return un statut réponse 200
     */
    @GetMapping("/api/rentals")
    public ResponseEntity<Map<String, List<RentalDto>>> getAllRentals() {
        List<RentalDto> rentals = rentalService.findAllRentals();
        String baseUrl = "http://localhost:8080"; // Adjust this if your base URL is different

        rentals.forEach(rental -> rental.setPicture(baseUrl + rental.getPicture()));

        Map<String, List<RentalDto>> response = new HashMap<>();
        response.put("rentals", rentals);

        return ResponseEntity.ok(response);
    }

    /**
     * Méthode pour obtenir un rental par son id
     * @param id
     * @return un statut réponse 200 et le rental trouvé
     */
    @GetMapping("api/rentals/{id}")
    public ResponseEntity<RentalDto> getRentalById(@PathVariable Long id) {
        RentalDto rental = rentalService.findRentalById(id);
        String baseUrl = "http://localhost:8080"; // Adjust this if your base URL is different

        // Set the full URL for the picture
        rental.setPicture(baseUrl + rental.getPicture());

        return ResponseEntity.ok(rental);
    }

    /**
     * Méthode de création d'un rental en base de donnée en vérifiant que la requête comporte bien une picture, et la sauvegarde dans l'API
     * @param token
     * @param name
     * @param surface
     * @param price
     * @param description
     * @param picture
     * @return un statut réponse 201
     */
    @PostMapping("api/rentals")
    public ResponseEntity<JsonResponse> createRental(@RequestHeader("Authorization") String token,
                                                     @RequestParam(value = "name", required = false) String name,
                                                     @RequestParam(value = "surface", required = false) Float surface,
                                                     @RequestParam(value = "price", required = false) Double price,
                                                     @RequestParam(value = "description", required = false) String description,
                                                     @RequestParam(value = "picture", required = false) MultipartFile picture) {
        // Check for missing parameters
        if (token == null || token.isBlank() ||
                name == null || name.isBlank() ||
                surface == null ||
                price == null ||
                description == null || description.isBlank() ||
                picture == null || picture.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("Missing parameters"));
        }


        try {
            // Construct the absolute path
            Path uploadDir = Paths.get(System.getProperty("user.dir"), "apiChatop", "src", "main", "resources", "static", "pictures");

            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir);
            }

            // Save the picture to the specified directory
            String fileName = Paths.get(picture.getOriginalFilename()).getFileName().toString(); // Ensure safe file name
            Path filePath = uploadDir.resolve(fileName);
            picture.transferTo(filePath.toFile());

            UserDto userDto = userService.findUserByToken(token);
            Long ownerId = userDto.getId();

            RentalDto rentalDto = new RentalDto();
            rentalDto.setName(name);
            rentalDto.setSurface(surface);
            rentalDto.setPrice(price);
            rentalDto.setDescription(description);
            rentalDto.setPicture("/pictures/" + fileName);

            rentalService.saveOrUpdateRental(rentalDto, ownerId);

            return ResponseEntity.status(HttpStatus.CREATED).body(new JsonResponse("Rental created successfully"));

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new JsonResponse("Error saving picture"));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new JsonResponse("Error creating rental"));
        }
    }



    /**
     * Méthode de modification d'un rental (name, surface, price ou description), après avoir vérifié que la requête provient bien du possesseur du rental
     * @param token
     * @param id
     * @param name
     * @param surface
     * @param price
     * @param description
     * @return un statut réponse 200
     */
    @PutMapping("api/rentals/{id}")
    public ResponseEntity<String> updateRental(@RequestHeader("Authorization") String token,
                                               @PathVariable Long id,
                                               @RequestParam("name") String name,
                                               @RequestParam("surface") float surface,
                                               @RequestParam("price") double price,
                                               @RequestParam("description") String description) {
        try {
            RentalDto existingRental = rentalService.findRentalById(id);
            if (existingRental == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Rental not found");
            }

            UserDto userDto = userService.findUserByToken(token);
            Long ownerId = userDto.getId();

            if (!existingRental.getOwner_id().equals(ownerId)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are not authorized to update this rental");
            }

            RentalDto rental = rentalService.findRentalById(id);
            rental.setName(name);
            rental.setSurface(surface);
            rental.setPrice(price);
            rental.setDescription(description);
            rentalService.updateRental(rental);

            return ResponseEntity.ok("Rental updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating rental");
        }
    }



}
