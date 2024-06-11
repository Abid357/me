import java.util.Calendar;
import java.util.Date;

public class Route {

	private String location1;
	private String location2;
	private double distance;
	private Date duration;

	/*
	 * converts distance into either meters (if < 1 km) or kilometers
	 */
	public static double convertDistance(double distanceInMeters) {
		return distanceInMeters / 1000;
	}

	/*
	 * converts duration from seconds into hours, minutes and seconds
	 */
	public static Date convertDuration(double duration) {
		int hours = (int) duration / 3600;
		int minutes = (int) (duration - (hours * 3600) / 60);
		int seconds = (int) (duration - (hours * 3600) - (minutes * 60));
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR, hours);
		cal.set(Calendar.MINUTE, minutes);
		cal.set(Calendar.SECOND, seconds);
		return cal.getTime();
	}

	public String getLocation1() {
		return location1;
	}

	public void setLocation1(String location1) {
		this.location1 = location1;
	}

	public String getLocation2() {
		return location2;
	}

	public void setLocation2(String location2) {
		this.location2 = location2;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public Date getDuration() {
		return duration;
	}

	public void setDuration(Date duration) {
		this.duration = duration;
	}

	public Route(String location1, String location2, double distance, Date duration) {
		super();
		this.location1 = location1;
		this.location2 = location2;
		this.distance = distance;
		this.duration = duration;
	}

    @Override	public String toString() {		return "Route [location1: " + location1 + ", location2: " + location2 + ", distance: " + distance				+ ", duration: " + duration + "]";	}
}
