package com.cognizant.authapi.users.services;

import com.cognizant.authapi.base.error.UserExistsException;
import com.cognizant.authapi.base.error.UserNotFoundException;
import com.cognizant.authapi.users.beans.Account;
import com.cognizant.authapi.users.beans.User;
import com.cognizant.authapi.users.dto.UserDTO;
import com.cognizant.authapi.users.repos.UserRepository;
import com.cognizant.authapi.users.util.UserUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by 784420 on 7/22/2019 4:47 PM
 */
@Service
@Slf4j
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private AccountService accountService;
    private UserUtil userUtil;

    /**
     * Getting all user details from db
     *
     * @return all user details
     */
    public List<User> getAllUsers() {
        log.info("Getting all the Users from DB .....!");
        return userRepository.findAll();
    }

    /**
     * @param userId id of teh user which has to fetch from db
     * @return user details of provided user id
     */
    public Optional<User> getUser(String userId) {
        log.info("Getting User based on the Id. id is : " + userId);
        return userRepository.findById(userId);
    }

    /**
     * Adding new user based on the user details
     *
     * @param user user details which has to store in DB
     * @return user details post storing in teh db
     */
    public User addNewUser(@RequestBody User user) {
        log.info("Saving the Project data: " + user.toString());
        return userRepository.insert(user);
    }

    /**
     * getting user details based on email id
     *
     * @param email email id to fetch user details from DB
     * @return
     */
    public Optional<User> getUserByEmail(String email) {
        log.info("Getting User based on the email. EmailId is : " + email);
        return userRepository.findByEmail(email);
    }


    /**
     * Updating user details based on the provided details
     *
     * @param user details of the user
     * @return post updating return user details
     */
    public User updateUser(User user) {
        log.info("Updating the User data: " + user.toString());
        return userRepository.save(user);
    }


    /**
     * Getting List of Users by List of User ID's
     *
     * @param userIdList List of user id's
     * @return List of user details
     */
    public List<User> getUserList(List<String> userIdList) {
        log.info("Getting User List based on the Ids. id list is : " + userIdList.toString());
        return (List<User>) userRepository.findAllById(userIdList);
    }


    /**
     * Updating list of users
     *
     * @param users list user details which has to update
     * @return list of users
     */
    public List<User> updateUserList(List<User> users) {
        return userRepository.saveAll(users);
    }


    /**
     * deleting list of users based on the user id's
     *
     * @param userIds list of user id's
     */
    public void deleteUserList(List<String> userIds) {
        List<User> userList = getUserList(userIds);

        List<String> accountIds = userList.stream().map(User::getAccount).filter(Objects::nonNull).map(Account::getId).collect(Collectors.toList());
        if (accountIds != null)
            accountService.removeIds(accountIds);

        userRepository.deleteByIdIn(userIds);
    }

    /**
     * deleting user based on the user email id
     *
     * @param email email id of user
     */
    public void deleteUserByEmail(String email) {
        getUserByEmail(email).ifPresent(user -> {
            Account account = user.getAccount();
            if (account != null && !StringUtils.isEmpty(account.getId())) {
                accountService.removeById(account.getId());
            }
            accountService.removeByUserId(user.getEmail());
            userRepository.delete(user);
        });
    }

    /**
     * signing up or creating new user based on the user details provided
     *
     * @param userDTO user details to signing up
     * @return user details post saving in the database
     */
    public User signUpUser(UserDTO userDTO) {
        if (!getUserByEmail(userDTO.getEmail()).isPresent()) {
            Account account = accountService.addNewAccount(
                    accountService.generateAccount(userDTO.getEmail())
            );
            User entity = userUtil.convertToNewEntity(userDTO, account);
            return addNewUser(entity);
        } else {
            throw new UserExistsException("Email", userDTO.getEmail());
        }
    }

    public UserDTO generateUserDTO(String email) {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(email);
        userDTO.setFirstName(email.substring(0, email.indexOf('@')));
        userDTO.setOrg("Leap Collector");

        return userDTO;
    }

    public User assertAndGetUser(String userId) {
        Optional<User> optional = getUser(userId);
        if (!optional.isPresent()) {
            throw new UserNotFoundException(userId);
        } else {
            return optional.get();
        }
    }
}
