package com.sprint.be_java_hisp_w23_g04.controller;

import com.sprint.be_java_hisp_w23_g04.dto.request.PostDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sprint.be_java_hisp_w23_g04.service.ISocialMediaService;
import com.sprint.be_java_hisp_w23_g04.service.SocialMediaServiceImpl;

@RestController
public class SocialMeliController {
    private final ISocialMediaService socialMediaService;

    public SocialMeliController(SocialMediaServiceImpl socialMediaService) {
        this.socialMediaService = socialMediaService;
    }

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        return new ResponseEntity<>(socialMediaService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping("/users/{userId}/follow/{userIdToFollow}")
    public ResponseEntity<?> followSellerUser(@PathVariable Integer userId, @PathVariable Integer userIdToFollow) {
        return new ResponseEntity<>(socialMediaService.followSellerUser(userId, userIdToFollow), HttpStatus.OK);
    }

    @GetMapping("/users/{userId}/followers/count")
    public ResponseEntity<?> getFollowers(@PathVariable int userId) {
        return new ResponseEntity<>(socialMediaService.followersCount(userId), HttpStatus.OK);
    }

    @GetMapping("/users/{userId}/followers/list")
    public ResponseEntity<?> getAllFollowersByUserId(@PathVariable int userId,
                                                     @RequestParam(defaultValue = "name_asc") String order) {
        return new ResponseEntity<>(this.socialMediaService.getFollowersByUserId(userId, order), HttpStatus.OK);
    }

    @GetMapping("/users/{userId}/followed/list")
    public ResponseEntity<?> getFollowedByUserId(@PathVariable Integer userId,
                                                 @RequestParam(defaultValue = "name_asc") String order) {
        return new ResponseEntity<>(socialMediaService.getFollowedByUserId(userId, order), HttpStatus.OK);
    }

    @PostMapping("/products/post")
    public ResponseEntity<?> savePost(@RequestBody PostDTO post){
        socialMediaService.savePost(post);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/users/{userId}/unfollow/{userIdToUnfollow}")
    public ResponseEntity<?> unfollowUser(@PathVariable int userId, @PathVariable int userIdToUnfollow){
        return new ResponseEntity<>(socialMediaService.unfollowUser(userId, userIdToUnfollow), HttpStatus.ACCEPTED);
    }
}
