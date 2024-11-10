package gymApp.bbdd.pojos;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class History implements Serializable {

	private static final long serialVersionUID = 1L;

	String id;
	String completedExercises;
	Date date;
	String expectedTime;
	String nameWorkout;
	String totalTime;

	@Override
	public int hashCode() {
		return Objects.hash(completedExercises, date, expectedTime, id, nameWorkout, totalTime);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		History other = (History) obj;
		return Objects.equals(completedExercises, other.completedExercises) && Objects.equals(date, other.date)
				&& Objects.equals(expectedTime, other.expectedTime) && Objects.equals(id, other.id)
				&& Objects.equals(nameWorkout, other.nameWorkout) && Objects.equals(totalTime, other.totalTime);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCompletedExercises() {
		return completedExercises;
	}

	public void setCompletedExercises(String completedExercises) {
		this.completedExercises = completedExercises;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getExpectedTime() {
		return expectedTime;
	}

	public void setExpectedTime(String expectedTime) {
		this.expectedTime = expectedTime;
	}

	public String getNameWorkour() {
		return nameWorkout;
	}

	public void setNameWorkour(String nameWorkour) {
		this.nameWorkout = nameWorkour;
	}

	public String getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(String totalTime) {
		this.totalTime = totalTime;
	}

	@Override
	public String toString() {
		return "Historic [id=" + id + ", completedExercises=" + completedExercises + ", date=" + date
				+ ", expectedTime=" + expectedTime + ", nameWorkour=" + nameWorkout + ", totalTime=" + totalTime + "]";
	}

}
