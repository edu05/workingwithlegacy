package org.craftedsw.tripservicekata.user;

import java.util.ArrayList;
import java.util.List;

import org.craftedsw.tripservicekata.trip.Trip;

public class User {

	private List<Trip> trips = new ArrayList<Trip>();
	private List<User> friends = new ArrayList<User>();
	
	public List<User> getFriends() {
		return friends;
	}

	public List<Trip> trips() {
		return trips;
	}


	public static final class UserBuilder {
		private List<Trip> trips = new ArrayList<Trip>();
		private List<User> friends = new ArrayList<User>();

		private UserBuilder() {
		}

		public static UserBuilder anUser() {
			return new UserBuilder();
		}

		public UserBuilder withTrips(List<Trip> trips) {
			this.trips = trips;
			return this;
		}

		public UserBuilder withFriends(List<User> friends) {
			this.friends = friends;
			return this;
		}

		public User build() {
			User user = new User();
			user.trips = this.trips;
			user.friends = this.friends;
			return user;
		}
	}
}
