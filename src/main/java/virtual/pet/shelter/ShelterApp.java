package virtual.pet.shelter;

import java.util.ArrayList;
import java.util.Scanner;

public class ShelterApp {

	public static void main(String[] args) {
		System.out.println("Welcome to the Pet Shelter!");
		System.out.println("This is a small sheleter with only five cages.");
		Shelter myShelter = new Shelter();
		Cage cageA = new Cage(20);
		Cage cageB = new Cage(10);
		Cage cageC = new Cage(15);
		Cage cageD = new Cage(10);
		Cage cageE = new Cage(0);
		myShelter.intake(cageA, "PuppyCat", "", 20, 10, 10);
		myShelter.intake(cageB, "Rover   ", "", 12, 26, 18);
		Scanner input = new Scanner(System.in);
		String menuChoice;
		do {
			myShelter.tickAll();
			System.out.println("\nPet Status:");
			System.out.println("Cage:\t|Name:\t\t|Hunger:\t|Thirst:\t|Boredom:");
			System.out.println("-------------------------------------------------------------------------");
			System.out.println("A\t|" + myShelter.getName(cageA) + "\t|" + myShelter.getHunger(cageA) + "\t\t|"
					+ myShelter.getThirst(cageA) + "\t\t|" + myShelter.getBoredom(cageA));
			System.out.println("B\t|" + myShelter.getName(cageB) + "\t|" + myShelter.getHunger(cageB) + "\t\t|"
					+ myShelter.getThirst(cageB) + "\t\t|" + myShelter.getBoredom(cageB));
			System.out.println("C\t|" + myShelter.getName(cageC) + "\t|" + myShelter.getHunger(cageC) + "\t\t|"
					+ myShelter.getThirst(cageC) + "\t\t|" + myShelter.getBoredom(cageC));
			System.out.println("D\t|" + myShelter.getName(cageD) + "\t|" + myShelter.getHunger(cageD) + "\t\t|"
					+ myShelter.getThirst(cageD) + "\t\t|" + myShelter.getBoredom(cageD));
			System.out.println("E\t|" + myShelter.getName(cageE) + "\t|" + myShelter.getHunger(cageE) + "\t\t|"
					+ myShelter.getThirst(cageE) + "\t\t|" + myShelter.getBoredom(cageE));
			System.out.println("\nWhat would you like to do?");
			System.out.println("[1]. [Adopt]out a pet");
			System.out.println("[2]. New pet [intake]");
			System.out.println("[3]. [Move] a pet to a different cage");
			System.out.println("[4]. [Feed] all the pets");
			System.out.println("[5]. [Water] all the pets");
			System.out.println("[6]. [Play] with a pet");
			System.out.println("[7]. [Clean] a cage");
			System.out.println("[8]. [Quit]");
			boolean exitMenu = false;
			menuChoice = input.nextLine();

			if (menuChoice.equals("1") || menuChoice.equalsIgnoreCase("adopt")) {
				do {
					System.out.println("Which pet would you like to adopt?");
					if (myShelter.getPet(cageA) != null) {
						System.out.println("Cage [A]: [" + myShelter.getName(cageA).trim() + "]");
					}
					if (myShelter.getPet(cageB) != null) {
						System.out.println("Cage [B]: [" + myShelter.getName(cageB).trim() + "]");
					}
					if (myShelter.getPet(cageC) != null) {
						System.out.println("Cage [C]: [" + myShelter.getName(cageC).trim() + "]");
					}
					if (myShelter.getPet(cageC) != null) {
						System.out.println("Cage [D]: [" + myShelter.getName(cageD).trim() + "]");
					}
					if (myShelter.getPet(cageE) != null) {
						System.out.println("Cage [E]: [" + myShelter.getName(cageE).trim() + "]");
					}
					menuChoice = input.nextLine();
					if (menuChoice.equalsIgnoreCase("A")
							|| menuChoice.equalsIgnoreCase(myShelter.getName(cageA).trim())) {
						myShelter.adoption(cageA);
						exitMenu = true;
					} else if (menuChoice.equalsIgnoreCase("B")
							|| menuChoice.equalsIgnoreCase(myShelter.getName(cageB).trim())) {
						myShelter.adoption(cageB);
						exitMenu = true;
					} else if (menuChoice.equalsIgnoreCase("C")
							|| menuChoice.equalsIgnoreCase(myShelter.getName(cageC).trim())) {
						myShelter.adoption(cageC);
						exitMenu = true;
					} else if (menuChoice.equalsIgnoreCase("D")
							|| menuChoice.equalsIgnoreCase(myShelter.getName(cageD).trim())) {
						myShelter.adoption(cageD);
						exitMenu = true;
					} else if (menuChoice.equalsIgnoreCase("E")
							|| menuChoice.equalsIgnoreCase(myShelter.getName(cageE).trim())) {
						myShelter.adoption(cageE);
						exitMenu = true;
					}
				} while (!exitMenu);
			}

			if (menuChoice.equals("2") || menuChoice.equalsIgnoreCase("intake")) {
				Cage intakeCage = null;
				if (myShelter.getName(cageA).trim().equals("empty")) {
					intakeCage = cageA;
				} else if (myShelter.getName(cageB).trim().equals("empty")) {
					intakeCage = cageB;
				} else if (myShelter.getName(cageC).trim().equals("empty")) {
					intakeCage = cageC;
				} else if (myShelter.getName(cageD).trim().equals("empty")) {
					intakeCage = cageD;
				} else if (myShelter.getName(cageE).trim().equals("empty")) {
					intakeCage = cageE;
				} else {
					System.out.println("All cages are full");
					exitMenu = true;
				}
				if (!exitMenu) {
					String intakeName = null;
					while (!exitMenu) {
						exitMenu = false;
						System.out.println("What is the name of the pet?");
						intakeName = input.nextLine();
						if (intakeName.length() >= 15) {
							System.out.println("Pet name should be fourteen characters or less.");
						} else if (intakeName.length() <= 1) {
							System.out.println("Pet name should be at least two characters.");
						} else if (intakeName.trim().equalsIgnoreCase(myShelter.getName(cageA).trim())
								|| intakeName.trim().equalsIgnoreCase(myShelter.getName(cageB).trim())
								|| intakeName.trim().equalsIgnoreCase(myShelter.getName(cageC).trim())
								|| intakeName.trim().equalsIgnoreCase(myShelter.getName(cageD).trim())
								|| intakeName.trim().equalsIgnoreCase(myShelter.getName(cageE).trim())) {
							System.out.println("Choose a pet name not already used.");
						} else {
							// extra spaces stored in name for menu formatting
							// trim() is used when retrieving names
							while (intakeName.length() <= 6) {
								intakeName = intakeName + " ";
							}
							exitMenu = true;
						}
					}
					exitMenu = false;
					while (!exitMenu) {
						System.out.println("Does the pet look healthy? ([y]/[n])");
						String intakeHealth = input.nextLine();
						int intakeHunger = 0;
						int intakeThirst = 0;
						int intakeBoredom = 0;
						if (intakeHealth.equalsIgnoreCase("Y")) {
							intakeHunger = 20;
							intakeThirst = 20;
							intakeBoredom = 10;
							exitMenu = true;
						} else if (intakeHealth.equalsIgnoreCase("N")) {
							intakeHunger = 60;
							intakeThirst = 60;
							intakeBoredom = 50;
							exitMenu = true;
						} else {
							System.out.println("Please enter [y] or [n]");
						}
						myShelter.intake(intakeCage, intakeName, "", intakeHunger, intakeThirst, intakeBoredom);
					}
				}
			}

			if (menuChoice.equals("3") || menuChoice.equalsIgnoreCase("move")) {
				Cage oldCage = null;
				do {
					System.out.println("Which pet would you like to move?");
					if (myShelter.getPet(cageA) != null) {
						System.out.println("Cage [A]: [" + myShelter.getName(cageA).trim() + "]");
					}
					if (myShelter.getPet(cageB) != null) {
						System.out.println("Cage [B]: [" + myShelter.getName(cageB).trim() + "]");
					}
					if (myShelter.getPet(cageC) != null) {
						System.out.println("Cage [C]: [" + myShelter.getName(cageC).trim() + "]");
					}
					if (myShelter.getPet(cageD) != null) {
						System.out.println("Cage [D]: [" + myShelter.getName(cageD).trim() + "]");
					}
					if (myShelter.getPet(cageE) != null) {
						System.out.println("Cage [E]: [" + myShelter.getName(cageE).trim() + "]");
					}
					menuChoice = input.nextLine();
					if (menuChoice.equalsIgnoreCase("A")
							|| menuChoice.equalsIgnoreCase(myShelter.getName(cageA).trim())) {
						oldCage = cageA;
						exitMenu = true;
					} else if (menuChoice.equalsIgnoreCase("B")
							|| menuChoice.equalsIgnoreCase(myShelter.getName(cageB).trim())) {
						oldCage = cageB;
						exitMenu = true;
					} else if (menuChoice.equalsIgnoreCase("C")
							|| menuChoice.equalsIgnoreCase(myShelter.getName(cageC).trim())) {
						oldCage = cageC;
						exitMenu = true;
					} else if (menuChoice.equalsIgnoreCase("D")
							|| menuChoice.equalsIgnoreCase(myShelter.getName(cageD).trim())) {
						oldCage = cageD;
						exitMenu = true;
					} else if (menuChoice.equalsIgnoreCase("E")
							|| menuChoice.equalsIgnoreCase(myShelter.getName(cageE).trim())) {
						oldCage = cageE;
						exitMenu = true;
					}
				} while (!exitMenu);
				if (!(myShelter.getPet(cageA) == null || myShelter.getPet(cageB) == null
						|| myShelter.getPet(cageC) == null || myShelter.getPet(cageD) == null
						|| myShelter.getPet(cageE) == null)) {
					System.out.println("All cages are full.");
					exitMenu = true;
				} else {
					do {
						System.out.println(
								"Into which cage do you want to move " + myShelter.getName(oldCage).trim() + "?");
						if (myShelter.getPet(cageA) == null) {
							System.out.println("Cage [A]");
						}
						if (myShelter.getPet(cageB) == null) {
							System.out.println("Cage [B]");
						}
						if (myShelter.getPet(cageC) == null) {
							System.out.println("Cage [C]");
						}
						if (myShelter.getPet(cageD) == null) {
							System.out.println("Cage [D]");
						}
						if (myShelter.getPet(cageE) == null) {
							System.out.println("Cage [E]");
						}
						menuChoice = input.nextLine();
						exitMenu = false;
						if (menuChoice.equalsIgnoreCase("A") && myShelter.getPet(cageA) == null) {
							myShelter.moveCage(oldCage, cageA);
							exitMenu = true;
						} else if (menuChoice.equalsIgnoreCase("B") && myShelter.getPet(cageB) == null) {
							myShelter.moveCage(oldCage, cageB);
							exitMenu = true;
						} else if (menuChoice.equalsIgnoreCase("C") && myShelter.getPet(cageC) == null) {
							myShelter.moveCage(oldCage, cageC);
							exitMenu = true;
						} else if (menuChoice.equalsIgnoreCase("D") && myShelter.getPet(cageD) == null) {
							myShelter.moveCage(oldCage, cageD);
							exitMenu = true;
						} else if (menuChoice.equalsIgnoreCase("E") && myShelter.getPet(cageE) == null) {
							myShelter.moveCage(oldCage, cageE);
							exitMenu = true;
						}

					} while (!exitMenu);

				}
			}

			if (menuChoice.equals("4") || menuChoice.equalsIgnoreCase("feed")) {
				myShelter.feedAll();
				System.out.println("You feed all the pets in the shelter.");
			}

			if (menuChoice.contentEquals("5") || menuChoice.equalsIgnoreCase("water")) {
				myShelter.waterAll();
				System.out.println("You water all the pets in the shelter.");
			}

			if (menuChoice.contentEquals("6") || menuChoice.equalsIgnoreCase("play")) {
				ArrayList<Pet> petList = myShelter.petList();
				if (petList.size() == 0) {
					System.out.println("There are no pets to play with.");
					exitMenu = true;
				} else {
					do {
						System.out.println("Which pet would you like to play with?");
						for (Pet aPet : petList) {
							System.out.println("[" + aPet.getName().trim() + "] " + aPet.getDescription());
						}
						menuChoice = input.nextLine();

						if (myShelter.getPet(menuChoice) != null) {
							System.out.println(
									myShelter.getPet(menuChoice).getName().trim() + myShelter.getPet(menuChoice).play());
							exitMenu = true;
						}

					} while (!exitMenu);
				}

			}
			if (menuChoice.equals("7") || menuChoice.equalsIgnoreCase("clean")) {
				do {
					System.out.println("Which cage would you like to clean?");
					System.out.println("Cage:\t|Dirtiness:\t|Pet:");
					System.out.println("----------------------------------");
					System.out.println("[A]\t|" + cageA.getDirty() + "\t\t|" + myShelter.getName(cageA));
					System.out.println("[B]\t|" + cageB.getDirty() + "\t\t|" + myShelter.getName(cageB));
					System.out.println("[C]\t|" + cageC.getDirty() + "\t\t|" + myShelter.getName(cageC));
					System.out.println("[D]\t|" + cageD.getDirty() + "\t\t|" + myShelter.getName(cageD));
					System.out.println("[E]\t|" + cageE.getDirty() + "\t\t|" + myShelter.getName(cageE));
					menuChoice = input.nextLine();
					if (menuChoice.equalsIgnoreCase("A")) {
						System.out.println(cageA.clean(myShelter.getPet(cageA) != null));
						exitMenu = true;
					} else if (menuChoice.equalsIgnoreCase("B")) {
						System.out.println(cageB.clean(myShelter.getPet(cageB) != null));
						exitMenu = true;
					} else if (menuChoice.equalsIgnoreCase("C")) {
						System.out.println(cageC.clean(myShelter.getPet(cageC) != null));
						exitMenu = true;
					} else if (menuChoice.equalsIgnoreCase("D")) {
						System.out.println(cageD.clean(myShelter.getPet(cageD) != null));
						exitMenu = true;
					} else if (menuChoice.equalsIgnoreCase("E")) {
						System.out.println(cageE.clean(myShelter.getPet(cageE) != null));
						exitMenu = true;
					}
				} while (!exitMenu);
			}

		} while (!menuChoice.equals("8") && !menuChoice.equalsIgnoreCase("quit"));
		System.out.println("Goodbye.");
		input.close();
	}
}
