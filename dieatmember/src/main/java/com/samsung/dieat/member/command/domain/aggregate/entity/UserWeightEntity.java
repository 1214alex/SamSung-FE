package com.samsung.dieat.member.command.domain.aggregate.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name="tbl_weight")
public class UserWeightEntity {

    @EmbeddedId
    private UserWeightId userWeightId;

    @Column(name = "cur_weight")
    private int curWeight;



}
