package virtual.pet.shelter;

import java.util.HashMap;
import java.util.Map.Entry;

public class Shelter {

	public Pet getPet(Cage aCage) {
		return shelterMap.get(aCage);
	}

	public Cage getCage(String petName) {
		Cage aCage = null;
		for (Entry<Cage, Pet> entry : shelterMap.entrySet()) {
			if (entry.getValue().getName().equalsIgnoreCase(petName)) {
				aCage = entry.getKey();
			}
		}
		return aCage;
	}

	public int getDirty(Cage aCage) {
		return aCage.getDirty();
	}

	public String getName(Cage aCage) {
		return getPet(aCage).getName();
	}

	public String getDescription(Cage aCage) {
		return getPet(aCage).getDescription();
	}

	public int getHunger(Cage aCage) {
		return  getPet(aCage).getHunger();
	}

	public int getThirst(Cage aCage) {
		return getPet(aCage).getThirst();
	}

	public int getBoredom(Cage aCage) {
		return getPet(aCage).getBoredom();
	}

	HashMap<Cage, Pet> shelterMap = new HashMap<>();

	public void intake(Cage aCage, String name, String description, int hunger, int thirst, int boredom) {
		// check for duplicate names
		Pet intakePet = new Pet(name, description, hunger, thirst, boredom);
		shelterMap.put(aCage, intakePet);
	}

	public void adoption(String name) {
		Cage cageToEmpty = getCage(name);
		shelterMap.replace(cageToEmpty, null);
	}

	public void moveCage(Cage oldCage, Cage newCage) {
		Pet aPet = shelterMap.get(oldCage);
		shelterMap.replace(oldCage, null);
		shelterMap.put(newCage, aPet);
	}

	public void tickAll() {
		for (Entry<Cage, Pet> entry : shelterMap.entrySet()) {
			if (entry.getValue() != null) {
				entry.getValue().tick(entry.getKey().getDirty());
				entry.getKey().tick();
			}
		}

	}
}