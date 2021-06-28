package org.example.user;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class IntegerIDUser extends User<Integer> {

    @Builder
    public IntegerIDUser(Integer integer, String username) {
        super(integer, username);
    }
}
