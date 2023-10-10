package com.tondeuse.tondeusekata.domaine;

import lombok.*;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Tondeuse {

    private int x;
    private int y;
    private Orientation orientation;
    private String instruction;

}
