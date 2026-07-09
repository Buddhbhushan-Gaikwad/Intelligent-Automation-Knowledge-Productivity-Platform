package com.example.Intelligent.Automation.Knowledge.Productivity.Platform.mapper;

import com.example.Intelligent.Automation.Knowledge.Productivity.Platform.dto.request.UserRequestDTO;
import com.example.Intelligent.Automation.Knowledge.Productivity.Platform.dto.response.UserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.Intelligent.Automation.Knowledge.Productivity.Platform.entity.User;

import java.util.List;

@Component
public class UserMapper {

    private User user;

    public User toEntity(UserRequestDTO dto) {

        if (dto == null) {
            return null;
        }

        User user = new User();

        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());

        return user;
    }

    public UserResponseDTO toResponseDTO(User user) {

        if (user == null) {
            return null;
        }

        UserResponseDTO dto = new UserResponseDTO();

        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setRoles(user.getRoles());

        dto.setJournalCount(
                user.getJournalEntries() == null
                        ? 0
                        : user.getJournalEntries().size()
        );

        dto.setCreatedAt(user.getCreatedAt());

        return dto;
    }

    public List<UserResponseDTO> toResponseDTOList(List<User> users) {

        return users.stream()
                .map(this::toResponseDTO)
                .toList();
    }
}
