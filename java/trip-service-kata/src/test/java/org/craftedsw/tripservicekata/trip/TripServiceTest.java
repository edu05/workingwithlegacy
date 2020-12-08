package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.craftedsw.tripservicekata.user.User.UserBuilder.anUser;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TripServiceTest {

    private static User loggedInUser;
    private static List<Trip> trips;

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

    @Test
    void shouldReturnTripsWhenUserAndLoggedInUserAreFriends() {
        loggedInUser = new User(); //representa a Edu
        Trip tripToSanFran = new Trip();
        Trip tripToThailand = new Trip();
        trips = List.of(tripToSanFran, tripToThailand);
        User javi = anUser()
                .withFriends(List.of(loggedInUser))
                .build();

        List<Trip> tripsDeJavi = new TestableTripService().getTripsByUser(javi);

        assertThat(tripsDeJavi.size(), is(2));
        assertThat(tripsDeJavi.get(0), is(tripToSanFran));
        assertThat(tripsDeJavi.get(1), is(tripToThailand));
    }

    private static class TestableTripService extends TripService {

        @Override
        protected User getLoggedUser() {
            return loggedInUser;
        }

        @Override
        protected List<Trip> findTripsByUser(User user) {
            return trips;
        }
    }

}
