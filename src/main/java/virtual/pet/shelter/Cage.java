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
			dirty-=23;
			if(dirty<=10) {
				dirty = 10;
			}
			cleanMessage = "The cage looks cleaner, but there is only \nso much you can do with an occupied cage.";
		} else {
			dirty = 0;
			cleanMessage = "You thouroughly clean the empty cage.";
		}
		return cleanMessage;
	}

}
