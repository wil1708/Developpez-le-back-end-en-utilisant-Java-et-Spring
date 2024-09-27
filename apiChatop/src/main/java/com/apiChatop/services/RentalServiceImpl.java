package com.apiChatop.services;

import com.apiChatop.dtos.RentalDto;
import com.apiChatop.entities.Rental;
import com.apiChatop.entities.User;
import com.apiChatop.mapper.DtoMapper;
import com.apiChatop.repositories.RentalRepository;
import com.apiChatop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Transactional
@Service
public class RentalServiceImpl implements RentalService {
    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private UserRepository userRepository;

    public List<RentalDto> findAllRentals() {
        List<Rental> rentals = rentalRepository.findAll();
        return rentals.stream()
                .map(DtoMapper.INSTANCE::rentalToRentalDto)
                .collect(Collectors.toList());
    }

    public RentalDto findRentalById(Long id) {
        Optional<Rental> rental = rentalRepository.findById(id);
        return rental.map(DtoMapper.INSTANCE::rentalToRentalDto)
                .orElseThrow(() -> new RuntimeException("Rental not found"));
    }

    @Override
    public void saveOrUpdateRental(RentalDto rentalDto, Long ownerId) {
        User owner = userRepository.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("Owner not found"));

        Rental rental = DtoMapper.INSTANCE.rentalDtoToRental(rentalDto);
        rental.setOwner(owner);

        Rental savedRental = rentalRepository.save(rental);
        DtoMapper.INSTANCE.rentalToRentalDto(savedRental);
    }

    @Override
    public void updateRental(RentalDto rentalDto) {
        Rental rental = rentalRepository.findById(rentalDto.getId())
                .orElseThrow(() -> new RuntimeException("Rental not found"));

        rental.setName(rentalDto.getName());
        rental.setSurface(rentalDto.getSurface());
        rental.setPrice(rentalDto.getPrice());
        rental.setDescription(rentalDto.getDescription());
        // Assuming picture is not updated in this method

        rentalRepository.save(rental);
    }
}
