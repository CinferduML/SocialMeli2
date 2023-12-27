package com.sprint.be_java_hisp_w23_g04.dtoNew.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostListDTO {
    @JsonProperty("user_id")
    private Integer userId;
    private List<PostResponseDTO> posts;
}