package gymApp.bbdd.pojos;

import java.util.Arrays;
import java.util.Objects;

public class Ejercicio extends Workout {
	String description;
	String image;
	String nameExercise;
	int rest;
	int[] sets;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getNameExercise() {
		return nameExercise;
	}

	public void setNameExercise(String nameExercise) {
		this.nameExercise = nameExercise;
	}

	public int getRest() {
		return rest;
	}

	public void setRest(int rest) {
		this.rest = rest;
	}

	public int[] getSets() {
		return sets;
	}

	public void setSets(int[] sets) {
		this.sets = sets;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Arrays.hashCode(sets);
		result = prime * result + Objects.hash(description, image, nameExercise, rest);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ejercicio other = (Ejercicio) obj;
		return Objects.equals(description, other.description) && Objects.equals(image, other.image)
				&& Objects.equals(nameExercise, other.nameExercise) && rest == other.rest
				&& Arrays.equals(sets, other.sets);
	}

	@Override
	public String toString() {
		return "Ejercicio [description=" + description + ", image=" + image + ", nameExercise=" + nameExercise
				+ ", rest=" + rest + ", sets=" + Arrays.toString(sets) + "]";
	}

}
