package com.example.ExpenseTracker.userservice.service;


import com.example.ExpenseTracker.userservice.entities.UserInfo;
import com.example.ExpenseTracker.userservice.entities.UserInfoDTO;
import com.example.ExpenseTracker.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserInfoDTO createOrUpdateUser(UserInfoDTO userInfoDTO){
        Function<UserInfo,UserInfo> updatingUser =  user ->{
         return userRepository.save(userInfoDTO.transformToUserInfo());
        };

        Supplier<UserInfo> createUser = () -> {
            return userRepository.save(userInfoDTO.transformToUserInfo());
        };
            UserInfo userInfo = userRepository.findByUserId(userInfoDTO.getUserId())
                    .map(updatingUser)
                    .orElseGet(createUser);

            return new UserInfoDTO(
                    userInfo.getUserId(),
                    userInfo.getFirstName(),
                    userInfo.getLastName(),
                    userInfo.getPhoneNumber(),
                     userInfo.getEmail(),
                   userInfo.getProfilePic()
            );
    }

    public UserInfoDTO getUser(UserInfoDTO userInfoDto) throws Exception{
        Optional<UserInfo> userInfoDtoOpt = userRepository.findByUserId(userInfoDto.getUserId());
        if(userInfoDtoOpt.isEmpty()){
            throw new Exception("User not found");
        }
        UserInfo userInfo = userInfoDtoOpt.get();
        return new UserInfoDTO(
                userInfo.getUserId(),
                userInfo.getFirstName(),
                userInfo.getLastName(),
                userInfo.getPhoneNumber(),
                userInfo.getEmail(),
                userInfo.getProfilePic()
        );
    }
}

