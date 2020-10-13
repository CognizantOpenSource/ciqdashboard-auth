package com.cognizant.authapi.users.util;

import com.cognizant.authapi.users.beans.Account;
import com.cognizant.authapi.users.beans.Role;
import com.cognizant.authapi.users.dto.AccountDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

/**
 * Created by 784420 on 7/17/2019 7:51 PM
 */
@Component
public class AccUtil {

    public AccountDTO convertToDto(Account account) {
        AccountDTO accountDTO = new AccountDTO();
        BeanUtils.copyProperties(account, accountDTO);
        accountDTO.setRoleIds(
                account.getRoles().stream().map(Role::getName).collect(toList())
        );

        return accountDTO;
    }

    public List<AccountDTO> convertToDtoList(List<Account> accountList) {
        List<AccountDTO> accountDTOList = new ArrayList<>();
        accountList.forEach(account -> accountDTOList.add(convertToDto(account)));
        return accountDTOList;
    }

    public Account convertToEntity(AccountDTO accountDTO, List<Role> roleList) {
        Account account = new Account();
        BeanUtils.copyProperties(accountDTO, account);
        account.setRoles(roleList);
        return account;
    }

    public Account convertToNewEntity(AccountDTO accountDTO) {
        Account account = new Account();
        BeanUtils.copyProperties(accountDTO, account);
        return account;
    }

    public Account mergeToEntity(AccountDTO accountDTO, Account account, List<Role> roleList) {
        BeanUtils.copyProperties(accountDTO, account);
        List<String> ownProjectIds = Optional.ofNullable(account.getOwnProjectIds()).orElseGet(ArrayList::new);
        ownProjectIds.retainAll(account.getProjectIds());
        account.setOwnProjectIds(ownProjectIds);
        account.setRoles(roleList);
        return account;
    }
}
