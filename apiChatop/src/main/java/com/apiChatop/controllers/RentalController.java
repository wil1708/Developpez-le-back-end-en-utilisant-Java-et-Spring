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

    private static final String UPLOAD_DIR = "src/main/resources/static/pictures/";

    @Value("${server.base-url}")
    private String baseUrl;

//    @GetMapping("api/rentals")
//    public ResponseEntity<List<RentalDto>> getAllRentals() {
//        List<RentalDto> rentals = rentalService.findAllRentals();
//        String baseUrl = "http://localhost:8080"; // Adjust this if your base URL is different
//
//        rentals.forEach(rental -> {
//            rental.setPicture(baseUrl + rental.getPicture());
//        });
//        return ResponseEntity.ok(rentals);
//    }

    @GetMapping("/api/rentals")
    public ResponseEntity<Map<String, List<RentalDto>>> getAllRentals() {
        List<RentalDto> rentals = rentalService.findAllRentals();
        String baseUrl = "http://localhost:8080"; // Adjust this if your base URL is different

        rentals.forEach(rental -> rental.setPicture(baseUrl + rental.getPicture()));

        Map<String, List<RentalDto>> response = new HashMap<>();
        response.put("rentals", rentals);

        return ResponseEntity.ok(response);
    }

    @GetMapping("api/rentals/{id}")
    public ResponseEntity<RentalDto> getRentalById(@PathVariable Long id) {
        RentalDto rental = rentalService.findRentalById(id);
        return ResponseEntity.ok(rental);
    }

    @PostMapping("api/rentals")
    public ResponseEntity<JsonResponse> createRental(@RequestHeader("Authorization") String token,
                                                     @RequestParam("name") String name,
                                                     @RequestParam("surface") float surface,
                                                     @RequestParam("price") double price,
                                                     @RequestParam("description") String description,
                                                     @RequestParam("picture") MultipartFile picture) {
        try {
            Path uploadDir = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir);
            }

            // Check if the file is empty
            if (picture.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse("File is empty"));
            }

            // Save the picture to the specified directory
            String fileName = picture.getOriginalFilename();
            Path filePath = uploadDir.resolve(fileName);
            Files.write(filePath, picture.getBytes());

            UserDto userDto = userService.findUserByToken(token);
            Long ownerId = userDto.getId();

            RentalDto rentalDto = new RentalDto();
            rentalDto.setName(name);
            rentalDto.setSurface(surface);
            rentalDto.setPrice(price);
            rentalDto.setDescription(description);
            //rentalDto.setPicture(filePath.toString());
            rentalDto.setPicture("/pictures/" + fileName);

            rentalService.saveOrUpdateRental(rentalDto, ownerId);

            return ResponseEntity.status(HttpStatus.CREATED).body(new JsonResponse("Rental created successfully"));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new JsonResponse("Error saving picture"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new JsonResponse("Error creating rental"));
        }
    }



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
