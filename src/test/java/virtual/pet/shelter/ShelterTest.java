package virtual.pet.shelter;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

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
	public void tickAllShouldIncreaseAllHungerBy2() {
		shelterUnderTest.intake(cageA, "namea", "descriptiona", 10, 11, 12);
		shelterUnderTest.intake(cageB, "nameb", "descriptionb", 13, 14, 15);
		shelterUnderTest.tickAll();
		assertEquals("12", shelterUnderTest.getHunger(cageA));
		assertEquals("15", shelterUnderTest.getHunger(cageB));
	}

	@Test
	public void tickAllShouldIncreaseAllThirstBy2() {
		shelterUnderTest.intake(cageA, "namea", "descriptiona", 10, 11, 12);
		shelterUnderTest.intake(cageB, "nameb", "descriptionb", 13, 14, 15);
		shelterUnderTest.tickAll();
		assertEquals("13", shelterUnderTest.getThirst(cageA));
		assertEquals("16", shelterUnderTest.getThirst(cageB));
	}

	@Test 
	public void tickAllShouldIncreaseBoredomInCageABCby10or6or3() {
		shelterUnderTest.intake(cageA, "namea", "descriptiona", 10, 11, 12);
		shelterUnderTest.intake(cageB, "nameb", "descriptionb", 13, 14, 15);
		shelterUnderTest.intake(cageC, "namec", "descriptionc", 16, 17, 18);
		shelterUnderTest.tickAll();
		assertEquals("22", shelterUnderTest.getBoredom(cageA));
		assertEquals("21", shelterUnderTest.getBoredom(cageB));
		assertEquals("21", shelterUnderTest.getBoredom(cageC));
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
		assertEquals("70",shelterUnderTest.getHunger(cageA));
		assertEquals("3",shelterUnderTest.getHunger(cageB));
		assertEquals("6",shelterUnderTest.getHunger(cageC));
	}
	
	@Test
	public void feedAllShouldDecreaseHungerToMinimum0() {
		shelterUnderTest.intake(cageA, "namea", "descriptiona", 5, 11, 12);
		shelterUnderTest.feedAll();
		assertEquals("0",shelterUnderTest.getHunger(cageA));
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
	public void waterAllShouldDecreaseHungerToMinimum0() {
		shelterUnderTest.intake(cageA, "namea", "descriptiona", 50, 1, 12);
		shelterUnderTest.waterAll();
		assertEquals("0",shelterUnderTest.getThirst(cageA));
	}
	
	@Test
	public void playWillDecreaseBoredomBy12IfNotHungryOrThirsty() {
		shelterUnderTest.intake(cageC, "namec", "descriptionc", 16, 17, 40);
		String testMessage = shelterUnderTest.getPet(cageC).play();
		assertEquals(15,shelterUnderTest.getPet(cageC).getBoredom());
		assertTrue(testMessage.contains("great time"));
	}
	
	@Test
	public void playWillDecreaseBoredomBy15IfHungryOrThirsty() {
		shelterUnderTest.intake(cageA, "namea", "descriptiona", 55, 10, 20);
		shelterUnderTest.intake(cageB, "nameb", "descriptionb", 23, 50, 15);
		shelterUnderTest.getPet(cageA).play();
		String testMessage = shelterUnderTest.getPet(cageB).play();
		assertEquals(5,shelterUnderTest.getPet(cageA).getBoredom());
		assertEquals(0,shelterUnderTest.getPet(cageB).getBoredom());
		assertTrue(testMessage.contains("distracted"));
	}
	
	@Test
	public void playWillDecreaseBoredomby0IfVeryHungryOrThirsty() {
		shelterUnderTest.intake(cageA, "namea", "descriptiona", 75, 10, 20);
		shelterUnderTest.intake(cageB, "nameb", "descriptionb", 13, 80, 15);
		shelterUnderTest.getPet(cageA).play();
		String testMessage = shelterUnderTest.getPet(cageB).play();
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
	public void cleaningOccupiedCageDecreasesDirtyBy23() {
		shelterUnderTest.intake(cageA, "namea", "descriptiona", 75, 10, 20);
		String testMessage = shelterUnderTest.clean(cageA);
		assertEquals(57,shelterUnderTest.getDirty(cageA));
		assertTrue(testMessage.contains("occupied cage"));
	}
	
	@Test
	public void cleaningOccupiedCageDecreasesDirtyBy23ToMinimum10() {
		shelterUnderTest.intake(cageC, "namec", "descriptionc", 75, 10, 20);
		String testMessage = shelterUnderTest.clean(cageC);
		assertEquals(10,shelterUnderTest.getDirty(cageC));
		assertTrue(testMessage.contains("occupied cage"));
	}
	
	@Test
	public void getPetReturnsPetObjectGivenPetName() {
		shelterUnderTest.intake(cageA, "namea", "descriptiona", 75, 10, 20);
		shelterUnderTest.intake(cageB, "nameb", "descriptionb", 13, 80, 15);
		Pet testPet = shelterUnderTest.getPet("namea");
		assertEquals(shelterUnderTest.getPet(cageA),testPet);
	}
	
	@Test
	public void petListReturnsCollectionOfPetsOfSize2() {
		shelterUnderTest.intake(cageA, "namea", "descriptiona", 75, 10, 20);
		shelterUnderTest.intake(cageB, "nameb", "descriptionb", 13, 80, 15);
		Collection<Pet> testList = shelterUnderTest.petList();
		assertThat(testList.size(), is (2));
	}
}
