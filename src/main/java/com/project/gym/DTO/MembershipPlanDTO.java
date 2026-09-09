package com.project.gym.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MembershipPlanDTO {
    private int id;
    private int duration;
    private String name;
    private BigDecimal price;
}
