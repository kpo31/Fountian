
//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           (Fountain.java)
// Files:           (Fountain.java)
// Course:          (CS 300 Spring 2019)
//
// Author:          (Mihir Khatri)
// Email:           (mkhatri@wisc.edu)
// Lecturer's Name: (Mouna Kacem)
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    (name of your pair programming partner)
// Partner Email:   (email address of your programming partner)
// Partner Lecturer's Name: (name of your partner's lecturer)
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates, 
// strangers, and others do.  If you received no outside help from either type
//  of source, then please explicitly indicate NONE.
//
// Persons:         (identify each person and describe their help in detail)
// Online Sources:  (identify each URL and describe their assistance in detail)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import java.util.Random;

public class Fountain {
	private static Random randGen = new Random();
	private static Particle[] particles;
	private static int positionX; // middle of the screen (left to right): 400
	private static int positionY; // middle of the screen (top to bottom): 300
	private static int startColor; // blue: Utility.color(23,141,235)
	private static int endColor; // lighter blue: Utility.color(23,200,255)

	/**
	 * initializing all the static variables.
	 */
	public static void setup() {

		particles = new Particle[5000];
		testUpdateParticle();
		testRemoveOldParticles();
		positionX = 400;
		positionY = 300;
		startColor = Utility.color(29, 341, 135);
		endColor = Utility.color(55, 400, 355);

		for(int i = 0; i < particles.length; i++) {//sets all the particles to 0 to ensure no errors
			particles[i] = null;
		}
	}

	/**
	 * calls update particles in loop, makes new particles and removes the older
	 * ones.
	 */

	public static void update() {

		Utility.background(Utility.color(235, 213, 186));
		for (int i = 0; i < particles.length; i++) {
			if (particles[i] != null) {// if there is no particle calls update particle to make a new one
				updateParticle(i);
			}
		}
		createNewParticles(10);
		removeOldParticles(80);
	}

	/**
	 * updates the age position and everything else that needs to be updated.
	 * 
	 * @param index - the index of the particle to be updated
	 */
	private static void updateParticle(int index) {

		if (particles[index] != null) {// if there is a particle at the index updates the particle
			Utility.fill(particles[index].getColor());// makes the color of the particle
			particles[index].setVelocityY(particles[index].getVelocityY() + 0.3f);// sets its velocity
			Utility.circle(particles[index].getPositionX(), particles[index].getPositionY(), // sets the circle with the
																								// particles values
					particles[index].getSize());
			particles[index].setPositionX(particles[index].getPositionX() + particles[index].getVelocityX());// moves
																												// the
																												// particle
																												// in X
																												// and Y
			particles[index].setPositionY(particles[index].getPositionY() + particles[index].getVelocityY());
			particles[index].setAge(particles[index].getAge() + 1);// updates the age
		}
	}

	/**
	 * creates the particles in the number wanted
	 * 
	 * @param number - number of particles to be created
	 */
	private static void createNewParticles(int number) {
		if (number < 5000) {// checks if the array is full of particles
			int counter = 0;
			for (int i = 0; i < particles.length; i++) {
				if (counter == number) {// makes until 10 particles are made
					break;
				}
				if (particles[i] == null) {// if there is no particles makes a new one
					particles[i] = new Particle(positionX - 3 + randGen.nextFloat() * 6,
							positionY - 3 + randGen.nextFloat() * 6, randGen.nextFloat() * (7) + 4,
							Utility.lerpColor(startColor, endColor, randGen.nextFloat()));// new color, position and
																							// velocity
					Utility.circle(particles[i].getPositionX(), particles[i].getPositionY(), particles[i].getSize());
					particles[i].setAge(randGen.nextInt(41));// sets age transparency and velocity
					particles[i].setTransparency(randGen.nextInt(96) + 32);
					particles[i].setVelocityX(randGen.nextFloat() * (2) - 1);
					particles[i].setVelocityY(randGen.nextFloat() * (5) - 10);
					counter++;
				}
			}
		}
	}

	/**
	 * makes the fountain run forever
	 * 
	 * @param maxAge - age after particles were to be deleted
	 */
	private static void removeOldParticles(int maxAge) {
		for (int i = 0; i < particles.length; i++) {
			if (particles[i] != null) {// checks for particle
				if (particles[i].getAge() > maxAge) {
					particles[i] = null;// if old then delete
				}
			}
		}

	}

	/**
	 * moves the fountain around with the mouse
	 * 
	 * @param moveX - positionX
	 * @param moveY - positionY
	 */
	public static void mousePressed(int moveX, int moveY) {
		positionX = moveX;// sets the particles position to the mouse coordinate
		positionY = moveY;
	}

	/**
	 * takes screenshot
	 * 
	 * @param c - keyboard input
	 */
	public static void keyPressed(char c) {
		if (c == 'p') {
			Utility.save("screenShot.png");// takes screenshot with s
		}
	}

	/**
	 * Creates a single particle at position (3,3) with velocity (-1,-2). Then
	 * checks whether calling updateParticle() on this particle's index correctly
	 * results in changing its position to (2,1.3).
	 * 
	 * @return true when no defect is found, and false otherwise
	 */
	private static boolean testUpdateParticle() {
		boolean testResult = false;
		particles[0] = new Particle(3f, 3f, randGen.nextFloat(), randGen.nextInt());// sets the origin size color and
																					// velocity
		particles[0].setVelocityY(-2f);
		particles[0].setVelocityX(-1f);

		updateParticle(0);

		if (particles[0].getPositionX() == 2f && particles[0].getPositionY() == 1.3f) {//checks for the condition
			testResult = true;
		}
		if (testResult == false) {
			System.out.println("FAILED");
		}
		return testResult; // TODO: implement this test
	}

	/**
	 * Calls removeOldParticles(6) on an array with three particles (two of which
	 * have ages over six and another that does not). Then checks whether the old
	 * particles were removed and the young particle was left alone.
	 * 
	 * @return true when no defect is found, and false otherwise
	 */
	private static boolean testRemoveOldParticles() {
		boolean testResult = false;
		particles[0] = new Particle(3f, 3f, randGen.nextFloat(), randGen.nextInt());//3 particles with origin size and color
		particles[1] = new Particle(2f, 2f, randGen.nextFloat(), randGen.nextInt());
		particles[2] = new Particle(1f, 5f, randGen.nextFloat(), randGen.nextInt());
		particles[0].setAge(9);//sets the age
		particles[1].setAge(12);
		particles[2].setAge(2);
		removeOldParticles(6);

		if (particles[0] == null && particles[1] == null) {//checks if particles are removed
			testResult = true;
		}
		if (testResult == false) {
			System.out.println("FAILED");
		}

		return testResult; // TODO: implement this test
	}

	public static void main(String args[]) {
		Utility.runApplication();

	}

}
