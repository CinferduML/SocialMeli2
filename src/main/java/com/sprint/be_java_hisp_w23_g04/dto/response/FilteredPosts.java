package com.sprint.be_java_hisp_w23_g04.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sprint.be_java_hisp_w23_g04.dto.request.PostDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilteredPosts {
    @JsonProperty("user_id")
    private int userId;
    private List<PostResponseDTO> posts;
}
