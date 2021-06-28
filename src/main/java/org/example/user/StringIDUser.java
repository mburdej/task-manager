package org.example.user;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class StringIDUser extends User<String> {

    @Builder
    public StringIDUser(String id, String username) {
        super(id, username);
    }
}
