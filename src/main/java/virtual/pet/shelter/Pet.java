package virtual.pet.shelter;

public class Pet {

	private String name;
	private String description;
	private int hunger;
	private int thirst;
	private int boredom;

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public int getHunger() {
		return hunger;
	}

	public int getThirst() {
		return thirst;
	}

	public int getBoredom() {
		return boredom;
	}

	public Pet(String name, String description, int hunger, int thirst, int boredom) {
		this.name = name;
		this.description = description;
		this.hunger = hunger;
		this.thirst = thirst;
		this.boredom = boredom;
	}

	public void tick(int dirty) {
		hunger += 2;
		thirst += 2;
		if (hunger >= 60 || thirst >= 60) {
			description = " gives a warning growl";
		} else if (hunger >= 40 || thirst >= 40) {
			description = " looks indifferent";
		} else {
			description = " looks eager to play";
		}
		if (dirty >= 70) {
			boredom += 10;
			description = description + " and is covered in filth.";
		} else if (dirty >= 40) {
			boredom += 6;
			description = description + " and is starting to smell.";
		} else {
			boredom += 3;
			description = description + " and smells fresh.";
		}
	}

	public void feed() {
		if (hunger <= 10) {
			hunger = 0;
		} else {
			hunger -= 10;
		}
	}

	public void water() {
		if (thirst <= 10) {
			thirst = 0;
		} else {
			thirst -= 10;
		}
	}

	public String play() {
		String playMessage = null;
		if (hunger >= 60 || thirst >= 60) {
			playMessage = " tried to bite you!";
		} else if (hunger >= 40 || thirst >= 40) {
			boredom -= 15;
			if (boredom < 0) {
				boredom = 0;
			}
			playMessage = " played with you but seemed distracted.";
		} else {
			boredom -= 25;
			if (boredom < 0) {
				boredom = 0;
			}
			playMessage = " had a great time!";
		}
		return playMessage;
	}
}
