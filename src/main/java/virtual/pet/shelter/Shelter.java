package virtual.pet.shelter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class Shelter {

	public int getDirty(Cage aCage) {
		return aCage.getDirty();
	}

	public String getName(Cage aCage) {
		String petName = "empty    ";
		if (getPet(aCage) != null) {
			petName = getPet(aCage).getName();
		}
		return petName;
	}

	public String getDescription(Cage aCage) {
		String petDescription = "";
		if (getPet(aCage) != null) {
			petDescription = getPet(aCage).getDescription();
		}
		return petDescription;
	}

	public String getHunger(Cage aCage) {
		String petHunger = "";
		if (getPet(aCage) != null) {
			petHunger = Integer.toString(getPet(aCage).getHunger());
		}
		return petHunger;
	}

	public String getThirst(Cage aCage) {
		String petThirst = "";
		if (getPet(aCage) != null) {
			petThirst = Integer.toString(getPet(aCage).getThirst());
		}
		return petThirst;
	}

	public String getBoredom(Cage aCage) {
		String petBoredom = "";
		if (getPet(aCage) != null) {
			petBoredom = Integer.toString(getPet(aCage).getBoredom());
		}
		return petBoredom;
	}

	public Pet getPet(Cage aCage) {
		return shelterMap.get(aCage);
	}

	public Pet getPet(String petName) {
		Pet returnPet = null;
		for (Pet aPet : shelterMap.values()) {
			if (aPet != null) {
				if (aPet.getName().trim().equalsIgnoreCase(petName)) {
					returnPet = aPet;
				}
			}
		}
		return returnPet;
	}

	HashMap<Cage, Pet> shelterMap = new HashMap<>();

	public ArrayList<Pet> petList() {
		ArrayList<Pet> petList = new ArrayList<>();
		for (Pet aPet : shelterMap.values()) {
			if (aPet != null) {
				petList.add(aPet);
			}
		}
		return petList;
	}

	public void intake(Cage aCage, String name, String description, int hunger, int thirst, int boredom) {
		Pet intakePet = new Pet(name, description, hunger, thirst, boredom);
		shelterMap.put(aCage, intakePet);
	}

	public void adoption(Cage cageToEmpty) {
		shelterMap.replace(cageToEmpty, null);
	}

	public void moveCage(Cage oldCage, Cage newCage) {
		Pet aPet = shelterMap.get(oldCage);
		shelterMap.replace(oldCage, null);
		shelterMap.put(newCage, aPet);
	}

	public void tickAll() {
		// tickAll once at start of App to initialize descriptions
		for (Entry<Cage, Pet> entry : shelterMap.entrySet()) {
			Cage aCage = entry.getKey();
			Pet aPet = entry.getValue();
			if (aPet != null) {
				entry.getValue().tick(aCage.getDirty());
				aCage.tick();
			}
		}
	}

	public void feedAll() {
		for (Pet aPet : shelterMap.values()) {
			if (aPet != null) {
				aPet.feed();
			}
		}
	}

	public void waterAll() {
		for (Pet aPet : shelterMap.values()) {
			if (aPet != null) {
				aPet.water();
			}
		}
	}

	public String clean(Cage aCage) {
		String cleanMessage = "";
		if (shelterMap.get(aCage) == null) {
			cleanMessage = aCage.clean(false);
		} else {
			cleanMessage = aCage.clean(true);
		}
		return cleanMessage;
	}

}