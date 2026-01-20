package edu.icet.controller;

import edu.icet.model.dto.UserDTO;
import edu.icet.model.entity.UserEntity;
import edu.icet.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDto) {

        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);

        UserEntity savedUser = userRepository.save(userEntity);


        return ResponseEntity.ok(modelMapper.map(savedUser, UserDTO.class));
    }
}