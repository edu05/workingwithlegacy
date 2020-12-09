package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TripServiceTest {

    @Test
    public void shouldThrowExceptionWhenUserIsNotLoggedIn() {
        assertThrows(UserNotLoggedInException.class, () -> {
            new TestableTripService().getTripsByUser(null);
        });
    }

    private static class TestableTripService extends TripService {
        @Override
        protected User getLoggedUser() {
            return null;
        }
    }

}
