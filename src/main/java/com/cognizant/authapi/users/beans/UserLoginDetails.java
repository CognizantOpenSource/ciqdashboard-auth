package com.cognizant.authapi.users.beans;

import com.cognizant.authapi.base.beans.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Data
@Document(collection = "userLoginDetails")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class UserLoginDetails extends BaseModel {
    @Id
    private String userEmailId;
    private LoginStatus lastLoginStatus = LoginStatus.SUCCESS;
    private AtomicInteger failureCount = new AtomicInteger();
    private Date lastSuccessLoginTime = new Date();
    private List<Date> failureLoginTimes = new ArrayList<>();

    public UserLoginDetails increaseFailureDetails(){
        this.setLastLoginStatus(UserLoginDetails.LoginStatus.FAILURE);
        this.getFailureCount().incrementAndGet();
        this.getFailureLoginTimes().add(new Date());
        return this;
    }

    public UserLoginDetails(String userEmailId) {
        this.userEmailId = userEmailId;
    }

    public static UserLoginDetails getFirstFailure(String userEmailId){
        UserLoginDetails userLoginDetails = new UserLoginDetails(userEmailId);
        userLoginDetails.setLastLoginStatus(LoginStatus.FAILURE);
        userLoginDetails.setFailureCount(new AtomicInteger(1));
        userLoginDetails.setFailureLoginTimes(Collections.singletonList(new Date()));
        return userLoginDetails;
    }

    public enum LoginStatus{
        SUCCESS, FAILURE
    }
}
