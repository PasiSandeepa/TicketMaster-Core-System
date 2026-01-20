package edu.icet.service.impl;

import edu.icet.model.dto.UserDTO;
import edu.icet.model.entity.UserEntity;
import edu.icet.repository.UserRepository;
import edu.icet.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public void saveUser(UserDTO userDto) {
        // ModelMapper මගින් DTO එක Entity එකකට පරිවර්තනය කිරීම
        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);

        // Database එකේ save කිරීම
        userRepository.save(userEntity);
    }
}