package org.example.task.client;

import lombok.Getter;
import lombok.Setter;
import org.example.user.User;

@Getter
@Setter
public class ClientContext {

    private User<?> currentLoggedInUser;

    private State state;

    public ClientContext(State state) {
        this.state = state;
    }

    public enum State {
        ANONYMOUS, AUTHENTICATED, FINISHED
    }
}
