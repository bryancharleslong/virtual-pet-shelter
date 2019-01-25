package virtual.pet.shelter;

public class Cage {

	private int dirty;

	public Cage(int dirty) {
		this.dirty = dirty;
	}

	public int getDirty() {
		return dirty;
	}

	public void tick() {
		dirty += 5;
	}

	public String clean(boolean isOccupied) {
		String cleanMessage = "error";
		if (isOccupied) {
			if (dirty <= 12) {
				dirty = 0;
			} else {
				dirty -= 12;
			}
			cleanMessage = "There is only so much you can clean an occupied cage.";
		} else {
			dirty = 0;
			cleanMessage = "You thouroughly clean the empty cage.";
		}
		return cleanMessage;
	}

}
