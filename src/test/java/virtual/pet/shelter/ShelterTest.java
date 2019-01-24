package virtual.pet.shelter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ShelterTest {
	Shelter shelterUnderTest = new Shelter();
	Cage cageA = new Cage(80);
	Cage cageB = new Cage(50);
	Cage cageC = new Cage(10);

	@Test
	public void intakeShouldCreatePetAndPutInCage() {
		shelterUnderTest.intake(cageA, "namea", "descriptiona", 10, 11, 12);
		String nameUnderTest = shelterUnderTest.getPet(cageA).getName();
		assertEquals("namea", nameUnderTest);
	}

	@Test
	public void moveCageAToCageBShouldRemoveFromCageAandPutIntoCageB() {
		shelterUnderTest.intake(cageA, "namea", "descriptiona", 10, 11, 12);
		shelterUnderTest.moveCage(cageA, cageB);
		assertNull(shelterUnderTest.getPet(cageA));
		assertEquals("namea", shelterUnderTest.getPet(cageB).getName());
	}

	@Test
	public void getCageShouldReturnCageAHousingPetName() {
		shelterUnderTest.intake(cageA, "namea", "descriptiona", 10, 11, 12);
		Cage testCage = shelterUnderTest.getCage("namea");
		assertEquals(cageA, testCage);
	}

	@Test
	public void getCageShouldReturnNullIfNameNotInCage() {
		shelterUnderTest.intake(cageA, "namea", "descriptiona", 10, 11, 12);
		Cage testCage = shelterUnderTest.getCage("nameX");
		assertNull(testCage);
	}

	@Test
	public void petAdoptionShouldRemovePetFromShelterMap() {
		shelterUnderTest.intake(cageA, "namea", "descriptiona", 10, 11, 12);
		shelterUnderTest.adoption("namea");
		assertNull(shelterUnderTest.getPet(cageA));
	}

	@Test
	public void getPetShouldRetrunNullifCageIsEmpty() {
		shelterUnderTest.intake(cageA, "namea", "descriptiona", 10, 11, 12);
		Pet petTest = shelterUnderTest.getPet(cageB);
		assertNull(petTest);
	}

	@Test
	public void getDirtyShouldReturnDirtyOf80() {
		int dirty = shelterUnderTest.getDirty(cageA);
		assertEquals(80, dirty);
	}

	@Test
	public void getNameInCageShouldReturnPetNameA() {
		shelterUnderTest.intake(cageA, "namea", "descriptiona", 10, 11, 12);
		String testName = shelterUnderTest.getName(cageA);
		assertEquals("namea", testName);
	}

	@Test
	public void getDescriptionShouldReturnPetDescriptionInCageA() {
		shelterUnderTest.intake(cageA, "namea", "descriptiona", 10, 11, 12);
		String testDescription = shelterUnderTest.getDescription(cageA);
		assertEquals("descriptiona", testDescription);
	}

	@Test
	public void getHungerShouldReturnPetHungerInCageA() {
		shelterUnderTest.intake(cageA, "namea", "descriptiona", 10, 11, 12);
		int testHunger = shelterUnderTest.getHunger(cageA);
		assertEquals(10, testHunger);
	}

	@Test
	public void getThirstShouldReturnPetThirstInCageA() {
		shelterUnderTest.intake(cageA, "namea", "descriptiona", 10, 11, 12);
		int testThirst = shelterUnderTest.getThirst(cageA);
		assertEquals(11, testThirst);
	}

	@Test
	public void getBoredomShouldReturnPetBoredomInCageA() {
		shelterUnderTest.intake(cageA, "namea", "descriptiona", 10, 11, 12);
		int testBoredom = shelterUnderTest.getBoredom(cageA);
		assertEquals(12, testBoredom);
	}

	@Test
	public void tickAllShouldIncreaseAllHungerBy3() {
		shelterUnderTest.intake(cageA, "namea", "descriptiona", 10, 11, 12);
		shelterUnderTest.intake(cageB, "nameb", "descriptionb", 13, 14, 15);
		shelterUnderTest.tickAll();
		assertEquals(13, shelterUnderTest.getHunger(cageA));
		assertEquals(16, shelterUnderTest.getHunger(cageB));
	}

	@Test
	public void tickAllShouldIncreaseAllThirstBy3() {
		shelterUnderTest.intake(cageA, "namea", "descriptiona", 10, 11, 12);
		shelterUnderTest.intake(cageB, "nameb", "descriptionb", 13, 14, 15);
		shelterUnderTest.tickAll();
		assertEquals(14, shelterUnderTest.getThirst(cageA));
		assertEquals(17, shelterUnderTest.getThirst(cageB));
	}

	@Test 
	public void tickAllShouldIncreaseBoredomInCageABCby15or10or5() {
		shelterUnderTest.intake(cageA, "namea", "descriptiona", 10, 11, 12);
		shelterUnderTest.intake(cageB, "nameb", "descriptionb", 13, 14, 15);
		shelterUnderTest.intake(cageC, "namec", "descriptionc", 16, 17, 18);
		shelterUnderTest.tickAll();
		assertEquals(27, shelterUnderTest.getBoredom(cageA));
		assertEquals(25, shelterUnderTest.getBoredom(cageB));
		assertEquals(23, shelterUnderTest.getBoredom(cageC));
	}

	@Test
	public void tickAllShouldIncreaseDirtyBy5IfCageIsOccupied() {
		shelterUnderTest.intake(cageA, "namea", "descriptiona", 10, 11, 12);
		shelterUnderTest.intake(cageB, "nameb", "descriptionb", 13, 14, 15);
		shelterUnderTest.tickAll();
		assertEquals(85, shelterUnderTest.getDirty(cageA));
		assertEquals(55, shelterUnderTest.getDirty(cageB));
		assertEquals(10, shelterUnderTest.getDirty(cageC));
	}
	
	@Test
	public void tickAllChangesDescriptionsBasedOnPetAndCage() {
		shelterUnderTest.intake(cageA, "namea", "descriptiona", 80, 11, 12);
		shelterUnderTest.intake(cageB, "nameb", "descriptionb", 13, 50, 15);
		shelterUnderTest.intake(cageC, "namec", "descriptionc", 16, 17, 18);
		shelterUnderTest.tickAll();
		assertTrue(shelterUnderTest.getPet(cageA).getDescription().contains("filth"));
		assertTrue(shelterUnderTest.getPet(cageA).getDescription().contains("growl"));
		assertTrue(shelterUnderTest.getPet(cageB).getDescription().contains("smell"));
		assertTrue(shelterUnderTest.getPet(cageB).getDescription().contains("indifferent"));
		assertTrue(shelterUnderTest.getPet(cageC).getDescription().contains("fresh"));
		assertTrue(shelterUnderTest.getPet(cageC).getDescription().contains("eager"));
	}
}
