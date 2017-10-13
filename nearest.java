

import java.util.Arrays;
import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		System.out.print("N = ");
		int N = keyboard.nextInt();
		if (N > 100) {
			System.out.println("Invalid value!");
			System.exit(0);
		} else
			System.out.println("The booster distances are found!");
		int[][] locations = createLocations(); /* x,y,fee */
		int[][] mylocation = createMyLocation(); /* x,y */
		int[][] distance_fee = distance_fee(locations,
				mylocation); /* distance,fee,x,y */
		int[][] validValues = new int[validArraySize(
				distance_fee)][4];/* fee <= 200 */

		int[][] remainings = new int[validArraySize2(
				distance_fee)][4];/* fee >200 */

		validValues = usedArray(distance_fee, validValues.length);
		remainings = unusedArray(distance_fee, remainings.length);
		java.util.Arrays.sort(validValues, java.util.Comparator.comparingInt(a -> a[1]));/* sorting */
		java.util.Arrays.sort(remainings, java.util.Comparator.comparingInt(a -> a[0]));/* sorting */

		for (int i = 0; i < validValues.length; i++) {
			int local1 = getArrayIndex(locations, validValues[i][2], validValues[i][3], validValues[i][1]);
			System.out.println((i + 1) + "th" + " " + "nearest distance calculated with the" + " " + local1
					+ "th generated coordinate is" + " " + validValues[i][0]);
			System.out.println("Coordinates of touristic place is" + " " + "(" + validValues[i][2] + ","
					+ validValues[i][3] + ")" + "," + "location fee is" + " " + validValues[i][1]);
			System.out.println();
		}

		for (int i = 0; i < (N - validValues.length); i++) {
			int local2 = getArrayIndex(locations, remainings[i][2], remainings[i][3], remainings[i][1]);
			System.out.println((i + validValues.length + 1) + "th" + " " + "nearest distance calculated with the" + " "
					+ local2 + "th coordinate is" + " " + remainings[i][0]);
			System.out.println("Coordinates of touristic place is" + " " + "(" + remainings[i][2] + ","
					+ remainings[i][3] + ")" + "," + "location fee is" + " " + remainings[i][1]);
			System.out.println();

		}

	}

	// The method creates random fee and locations.
	public static int[][] createLocations() {
		int[][] locations = new int[100][3];

		for (int count1 = 0; count1 < 100; count1++)
			for (int count2 = 0; count2 < 2; count2++)
				locations[count1][count2] = (int) (Math.random() * 2000 - 1000);

		for (int count3 = 0; count3 < 100; count3++)
			for (int count4 = 2; count4 < 3; count4++)
				locations[count3][count4] = (int) (Math.random() * 60);

		return locations;

	}

	// The method creates my location.
	public static int[][] createMyLocation() {
		int mylocation[][] = new int[1][2];
		mylocation[0][0] = (int) (Math.random() * 2000 - 1000);
		mylocation[0][1] = (int) (Math.random() * 2000 - 1000);
		return mylocation;

	}

	// The method has distance and fee values.
	public static int[][] distance_fee(int array1[][], int array2[][]) {
		int[][] result = new int[100][4];

		for (int count = 0; count < 100; count++)
			result[count][0] = (int) Math
					.sqrt(Math.pow(array2[0][0] - array1[count][0], 2) + Math.pow(array2[0][1] - array1[count][1], 2));

		for (int count2 = 0; count2 < 100; count2++)
			result[count2][1] = array1[count2][2];
		for (int count3 = 0; count3 < 100; count3++)
			result[count3][2] = array1[count3][0];
		for (int count4 = 0; count4 < 100; count4++)
			result[count4][3] = array1[count4][1];
		return result;

	}

	// value of fees are smaller than 200
	public static int validArraySize(int array[][]) {
		int counter = 0;
		for (int i = 0; i < 100; i++) {
			if (array[i][0] <= 200)
				counter++;
		}
		return counter;
	}

	// value of fees are greater than 200
	public static int validArraySize2(int array[][]) {
		int counter = 0;
		for (int i = 0; i < 100; i++) {
			if (array[i][0] > 200)
				counter++;
		}
		return counter;
	}

	// fees are smaller than 200
	public static int[][] usedArray(int array[][], int size) {
		int[][] array2 = new int[size][4];
		for (int count = 0; count < 100; count++) {
			if (array[count][0] <= 200) {
				array2[size - 1][0] = array[count][0];
				array2[size - 1][1] = array[count][1];
				array2[size - 1][2] = array[count][2];
				array2[size - 1][3] = array[count][3];

				size--;
			}
		}

		return array2;
	}

	// fees are greater than 200
	public static int[][] unusedArray(int array[][], int size) {
		int[][] array2 = new int[size][4];
		for (int count = 0; count < 100; count++) {
			if (array[count][0] > 200) {
				array2[size - 1][0] = array[count][0];
				array2[size - 1][1] = array[count][1];
				array2[size - 1][2] = array[count][2];
				array2[size - 1][3] = array[count][3];

				size--;
			}
		}

		return array2;
	}

	// finding index of elements (x,y,fee)
	public static int getArrayIndex(int[][] array, int a, int b, int c) {

		int k = 0;
		for (int i = 0; i < array.length; i++) {

			if (array[i][0] == a && array[i][1] == b && array[i][2] == c) {
				k = i;
				break;
			}
		}
		return k;
	}

}
