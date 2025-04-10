package com.samsung.dieat.member.command.domain.aggregate.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.util.Date;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserWeightId {

    @Column(name = "weight_dt")
    private Date weightDt;

    @Column(name = "user_code")
    private int userCode;

}
