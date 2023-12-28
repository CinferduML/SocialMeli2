package com.sprint.be_java_hisp_w23_g04.utils;

import com.sprint.be_java_hisp_w23_g04.entity.User;
import com.sprint.be_java_hisp_w23_g04.exception.BadRequestException;
import com.sprint.be_java_hisp_w23_g04.exception.NoContentException;
import com.sprint.be_java_hisp_w23_g04.exception.NotFoundException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Verifications {

    /**
     * Checks if a user exists and throws NotFoundException if not.
     *
     * @param user The user to check for existence.
     * @param id   The user's ID, used for the error message if the user doesn't exist.
     * @throws NotFoundException if the user is null.
     */
    public static void verifyUserExist(User user, Integer id) {
        if (user == null) {
            throw new NotFoundException("No se encontró usuario con el id " + id + ".");
        }
    }

    public static void verifyUserExist(User user) {
        if (user == null) {
            throw new NotFoundException("No se encontró usuario con el id proporcionado.");
        }
    }

    /**
     * Validates that a given list is not empty. Throws NoContentException if the list is empty.
     *
     * @param list The list to check for content.
     * @throws NoContentException if the list is empty.
     */
    public static void validateEmptyResponseList(List<?> list) {
        if (list.isEmpty()) {
            throw new NoContentException();
        }
    }

    public static void verifyUserHasFollowedSellers(com.sprint.be_java_hisp_w23_g04.entity.User user) {
        if(user.getFollowedId().isEmpty()){
            throw new NotFoundException("El usuario indicado actualmente no sigue a ningún vendedor");
        }
    }

    /**
     * Validates that a given user is a Seller.
     *
     * @param seller The User to validate.
     * @throws BadRequestException if the user is not a Seller.
     */
    public static void verifyUserIsSeller(User seller){
        if (!isSeller(seller)) {
            throw new BadRequestException("El id de usuario vendedor proporcionado no es valido.");
        }
    }

    private static boolean isSeller(User user) {
        return !user.getPostsId().isEmpty();
    }

    /**
     * Validates if the given User follows Seller.
     *
     * @param user The User to validate.
     * @param seller The Seller to validate.
     * @throws BadRequestException if the User already follows Seller.
     */
    public static void verifyUserFollowsSeller(User user, User seller){
        if (userAlreadyFollowsSeller(user, seller)) {
            throw new BadRequestException("El usuario con id:" + user.getId() + " ya sigue al vendedor con id:" + seller.getId());
        }
    }
    private static boolean userAlreadyFollowsSeller(User user, User seller) {
        Optional<Integer> userSellerId =  user.getFollowedId().stream()
                .filter(id -> Objects.equals(id, seller.getId()))
                .findFirst();

        return userSellerId.isPresent();
    }

    /**
     * Validates if the given Users are not the same.
     *
     * @param user The User to validate.
     * @param seller The Seller to validate.
     * @throws BadRequestException if the User and Seller are the same
     */
    public static void verifyDistinctsUser(User user, User seller){
        if (Objects.equals(user.getId(), seller.getId())) {
            throw new BadRequestException("El usuario y vendedor con id:"+ seller.getId() +" no pueden ser el mismo.");
        }
    }
}
