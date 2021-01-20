/*
package com.cognizant.authapi.users.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.Instant;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordAudit {
    private String userId;
    private String oldPassword;
    private String newPassword;

    @JsonIgnore
    @CreatedBy
    private String createdUser;
    @JsonIgnore
    @CreatedDate
    private Date createdDate;
    @JsonIgnore
    @LastModifiedBy
    private String lastModifiedUser;
    @JsonIgnore
    @LastModifiedDate
    private Instant lastModifiedDate;

    public void setOldPassword(CharSequence oldPassword) {
        this.oldPassword = new BCryptPasswordEncoder().encode(oldPassword);
    }

    public void setNewPassword(CharSequence newPassword) {
        this.newPassword = new BCryptPasswordEncoder().encode(newPassword);
    }
}
*/
