package org.example.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User<ID> {

    private ID id;

    private String username;
}
