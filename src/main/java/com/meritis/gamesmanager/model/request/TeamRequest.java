package com.meritis.gamesmanager.model.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Size;

@Data
public class TeamRequest {

    @Size(min = 3, max = 3, message = "Le shortName doit contenir exactement 3 caractères")
    private String shortName;

    @Length(min = 3, message = "Le fullName doit contenir au moins 3 caractères")
    private String fullName;

}
