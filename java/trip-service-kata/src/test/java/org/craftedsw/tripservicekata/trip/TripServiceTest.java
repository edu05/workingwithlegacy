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

    private static User loggedInUser;

    @Test
    public void shouldThrowExceptionWhenUserIsNotLoggedIn() {
        loggedInUser = null;
        assertThrows(UserNotLoggedInException.class, () -> {
            new TestableTripService().getTripsByUser(null);
        });
    }

    @Test
    void shouldReturnEmptryTripsListWhenUserIsLoggedInButIsNotFriends() {
        User laExDeEdu = new User();
        loggedInUser = new User(); //representa a Edu
        List<Trip> tripsDeLaExDeEdu = new TestableTripService().getTripsByUser(laExDeEdu);

        assertThat(tripsDeLaExDeEdu.size(), is(0));
    }

    private static class TestableTripService extends TripService {
        @Override
        protected User getLoggedUser() {
            return loggedInUser;
        }
    }

}
