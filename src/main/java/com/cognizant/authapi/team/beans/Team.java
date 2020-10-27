package com.cognizant.authapi.team.beans;

import com.cognizant.authapi.base.beans.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@Document(collection = "teams")
@EqualsAndHashCode(callSuper = false)
public class Team extends BaseModel {
    @Id
    private String id;
    @Indexed(unique = true)
    @NotBlank(message = "Name should not be empty/null")
    @Size(min = 3, message = "Name should have at least 3 Characters")
    private String name;
    @NotEmpty(message = "Members list should not be empty/null")
    @Size(min = 2, message = "Members list should have at least 2 members")
    private Set<String> members;
}
