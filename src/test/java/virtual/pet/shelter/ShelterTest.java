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
	public void getPetShouldRetrunNullifCageIsEmpty() {
		shelterUnderTest.intake(cageA, "namea", "descriptiona", 10, 11, 12);
		Pet petTest = shelterUnderTest.getPet(cageB);
		assertNull(petTest);
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
		shelterUnderTest.adoption(cageA);
		assertNull(shelterUnderTest.getPet(cageA));
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
	public void getNameInCageShouldReturnEmptyIfEmptyCage() {
		shelterUnderTest.intake(cageA, "namea", "descriptiona", 10, 11, 12);
		String testName = shelterUnderTest.getName(cageB).trim();
		assertTrue(testName.equals("empty"));
	}

	@Test
	public void getDescriptionShouldReturnPetDescriptionInCageA() {
		shelterUnderTest.intake(cageA, "namea", "descriptiona", 10, 11, 12);
		String testDescription = shelterUnderTest.getDescription(cageA);
		assertEquals("descriptiona", testDescription);
	}
	@Test
	public void getDescriptionShouldReturnNothingIfEmptyCage() {
		String testDescription = shelterUnderTest.getDescription(cageB);
		assertEquals("",testDescription);
	}

	@Test
	public void getHungerShouldReturnPetHungerInCageA() {
		shelterUnderTest.intake(cageA, "namea", "descriptiona", 10, 11, 12);
		String testHunger = shelterUnderTest.getHunger(cageA);
		assertEquals("10", testHunger);
	}

	@Test
	public void getThirstShouldReturnPetThirstInCageA() {
		shelterUnderTest.intake(cageA, "namea", "descriptiona", 10, 11, 12);
		String testThirst = shelterUnderTest.getThirst(cageA);
		assertEquals("11", testThirst);
	}

	@Test
	public void getBoredomShouldReturnPetBoredomInCageA() {
		shelterUnderTest.intake(cageA, "namea", "descriptiona", 10, 11, 12);
		String testBoredom = shelterUnderTest.getBoredom(cageA);
		assertEquals("12", testBoredom);
	}

	@Test
	public void tickAllShouldIncreaseAllHungerBy3() {
		shelterUnderTest.intake(cageA, "namea", "descriptiona", 10, 11, 12);
		shelterUnderTest.intake(cageB, "nameb", "descriptionb", 13, 14, 15);
		shelterUnderTest.tickAll();
		assertEquals("13", shelterUnderTest.getHunger(cageA));
		assertEquals("16", shelterUnderTest.getHunger(cageB));
	}

	@Test
	public void tickAllShouldIncreaseAllThirstBy3() {
		shelterUnderTest.intake(cageA, "namea", "descriptiona", 10, 11, 12);
		shelterUnderTest.intake(cageB, "nameb", "descriptionb", 13, 14, 15);
		shelterUnderTest.tickAll();
		assertEquals("14", shelterUnderTest.getThirst(cageA));
		assertEquals("17", shelterUnderTest.getThirst(cageB));
	}

	@Test 
	public void tickAllShouldIncreaseBoredomInCageABCby15or10or5() {
		shelterUnderTest.intake(cageA, "namea", "descriptiona", 10, 11, 12);
		shelterUnderTest.intake(cageB, "nameb", "descriptionb", 13, 14, 15);
		shelterUnderTest.intake(cageC, "namec", "descriptionc", 16, 17, 18);
		shelterUnderTest.tickAll();
		assertEquals("27", shelterUnderTest.getBoredom(cageA));
		assertEquals("25", shelterUnderTest.getBoredom(cageB));
		assertEquals("23", shelterUnderTest.getBoredom(cageC));
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
	
	@Test
	public void feedAllShouldDecreaseAllHungerBy10() {
		shelterUnderTest.intake(cageA, "namea", "descriptiona", 80, 11, 12);
		shelterUnderTest.intake(cageB, "nameb", "descriptionb", 13, 50, 15);
		shelterUnderTest.intake(cageC, "namec", "descriptionc", 16, 17, 18);
		shelterUnderTest.feedAll();
		assertEquals(70,shelterUnderTest.getPet(cageA).getHunger());
		assertEquals(3,shelterUnderTest.getPet(cageB).getHunger());
		assertEquals(6,shelterUnderTest.getPet(cageC).getHunger());
	}
	
	@Test
	public void waterAllShouldDecreaseAllThirstBy10() {
		shelterUnderTest.intake(cageA, "namea", "descriptiona", 80, 11, 12);
		shelterUnderTest.intake(cageB, "nameb", "descriptionb", 13, 50, 15);
		shelterUnderTest.intake(cageC, "namec", "descriptionc", 16, 17, 18);
		shelterUnderTest.waterAll();
		assertEquals(1,shelterUnderTest.getPet(cageA).getThirst());
		assertEquals(40,shelterUnderTest.getPet(cageB).getThirst());
		assertEquals(7,shelterUnderTest.getPet(cageC).getThirst());
	}
	
	@Test
	public void playWillDecreaseBoredomBy8IfNotHungryOrThirsty() {
		shelterUnderTest.intake(cageC, "namec", "descriptionc", 16, 17, 18);
		String testMessage = shelterUnderTest.play(cageC);
		assertEquals(10,shelterUnderTest.getPet(cageC).getBoredom());
		assertTrue(testMessage.contains("great time"));
	}
	
	@Test
	public void playWillDecreaseBoredomBy4IfHungryOrThirsty() {
		shelterUnderTest.intake(cageA, "namea", "descriptiona", 55, 10, 20);
		shelterUnderTest.intake(cageB, "nameb", "descriptionb", 13, 50, 15);
		shelterUnderTest.play(cageA);
		String testMessage = shelterUnderTest.play(cageB);
		assertEquals(16,shelterUnderTest.getPet(cageA).getBoredom());
		assertEquals(11,shelterUnderTest.getPet(cageB).getBoredom());
		assertTrue(testMessage.contains("distracted"));
	}
	
	@Test
	public void playWillDecreaseBoredomby0IfVeryHungryOrThirsty() {
		shelterUnderTest.intake(cageA, "namea", "descriptiona", 75, 10, 20);
		shelterUnderTest.intake(cageB, "nameb", "descriptionb", 13, 80, 15);
		shelterUnderTest.play(cageA);
		String testMessage = shelterUnderTest.play(cageB);
		assertEquals(20,shelterUnderTest.getPet(cageA).getBoredom());
		assertEquals(15,shelterUnderTest.getPet(cageB).getBoredom());
		assertTrue(testMessage.contains("bite"));
	}
	
	@Test
	public void cleaningEmptyCageDecreasesDirtyto0() {
		shelterUnderTest.intake(cageA, "namea", "descriptiona", 75, 10, 20);
		String testMessage = shelterUnderTest.clean(cageB);
		assertEquals(0,shelterUnderTest.getDirty(cageB));
		assertTrue(testMessage.contains("thouroughly clean"));
	}
	
	@Test
	public void cleaningOccupiedCageDecreasesDirtyBy12() {
		shelterUnderTest.intake(cageA, "namea", "descriptiona", 75, 10, 20);
		String testMessage = shelterUnderTest.clean(cageA);
		assertEquals(68,shelterUnderTest.getDirty(cageA));
		assertTrue(testMessage.contains("occupied cage"));
	}
	
	@Test
	public void cleaningOccupiedCageDecreasesDirtyBy12ToMinimum0() {
		shelterUnderTest.intake(cageC, "namec", "descriptionc", 75, 10, 20);
		String testMessage = shelterUnderTest.clean(cageC);
		assertEquals(0,shelterUnderTest.getDirty(cageC));
		assertTrue(testMessage.contains("occupied cage"));
	}
}
