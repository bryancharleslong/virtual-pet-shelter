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

}
