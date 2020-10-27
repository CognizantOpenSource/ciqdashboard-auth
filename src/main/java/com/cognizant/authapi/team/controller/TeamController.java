package com.cognizant.authapi.team.controller;

import com.cognizant.authapi.base.error.InvalidDetailsException;
import com.cognizant.authapi.team.beans.Team;
import com.cognizant.authapi.team.services.TeamService;
import com.cognizant.authapi.users.beans.User;
import com.cognizant.authapi.users.beans.UserPrincipal;
import com.cognizant.authapi.users.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/teams")
@Slf4j
public class TeamController {
    @Autowired
    TeamService service;
    @Autowired
    UserService userService;

    @GetMapping
    public List<Team> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Team get(@PathVariable String id) {
        return service.assertAndGet(id);
    }

    @GetMapping("/search")
    public Team getByName(@RequestParam String name) {
        return service.assertAndGetByName(name);
    }

    @GetMapping("/current-user")
    public List<Team> getTeamsByCurrentUser() {
        String currentUserEmailId = getCurrentUserEmailId();
        return service.findUserBelongsToWhichTeams(currentUserEmailId);
    }

    @GetMapping("/names/current-user")
    public List<String> getTeamNamesByCurrentUser() {
        return getTeamsByCurrentUser().stream().map(Team::getName).collect(Collectors.toList());
    }

    @PostMapping
    @Validated
    public Team add(@Valid @RequestBody Team team) {
        validateUserEmailIds(team.getMembers());
        return service.add(team);
    }

    @PutMapping
    @Validated
    public Team save(@Valid @RequestBody Team team) {
        validateUserEmailIds(team.getMembers());
        return service.update(team);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        service.deleteById(id);
    }

    private void validateUserEmailIds(Set<String> userEmailIds){
        List<User> allUserByEmails = userService.getAllUserByEmails(new ArrayList<>(userEmailIds));
        if (CollectionUtils.isEmpty(allUserByEmails) || userEmailIds.size() != allUserByEmails.size()){
            throw new InvalidDetailsException("Some of User Email Ids are not found");
        }
    }

    private String getCurrentUserEmailId(){
        Object auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (auth instanceof UserPrincipal) {
            return ((UserPrincipal) auth).getEmail();
        } else {
            throw new AuthenticationCredentialsNotFoundException("user not signed in");
        }
    }


}
