package com.sprint.be_java_hisp_w23_g04.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sprint.be_java_hisp_w23_g04.dto.DBUserDTO;
import com.sprint.be_java_hisp_w23_g04.entity.Post;
import com.sprint.be_java_hisp_w23_g04.entity.User;
import com.sprint.be_java_hisp_w23_g04.utils.UserMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class SocialMediaRepositoryImpl implements ISocialMediaRepository {
    private List<User> users = new ArrayList<>();

    public SocialMediaRepositoryImpl() {
        this.users = loadDataBase();
    }

    private List<User> loadDataBase() {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:users.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        TypeReference<List<DBUserDTO>> typeRef = new TypeReference<>() {
        };
        List<DBUserDTO> usersDto = null;
        try {
            usersDto = mapper.readValue(file, typeRef);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return usersDto != null ? usersDto.stream().map(UserMapper::mapUser).collect(Collectors.toList()) : Collections.emptyList();
    }
    
    @Override
    public List<User> findAllUsers() {
        return this.users;
    }

    @Override
    public User findUser(Integer userId) {
        return this.users.stream().filter(u -> Objects.equals(u.getId(), userId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void savePost(User user) {
        users.set(users.indexOf(user), user);
    }
}