import java.util.Scanner;

public class Main {

	static int[][] apples = null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int r = sc.nextInt();
		int s = sc.nextInt();
		apples = new int[r][2];
		
		sc.nextLine();
		
		for (int i=0;i<r;i++) {
			String line = sc.nextLine();
			int row = r-i-1;
			apples[row][0] = line.indexOf("J");
			apples[row][1] = line.lastIndexOf("J");
		}
		
		int[] currentPos = {0,0};
		int keyPress = 0;
		while (currentPos[0]<r) {
			keyPress+=getCurrentLineKeyPress(currentPos);
			int[] targetPos = getTargetPosition(currentPos);
			if (targetPos == null) {
				break;
			}
			
			keyPress+=Math.abs(targetPos[0]-currentPos[0])+Math.abs(targetPos[1]-currentPos[1]);
			currentPos = targetPos;
		}
		
		System.out.println(keyPress);
	}
	
	private static int getCurrentLineKeyPress(int[] currentPos) {
		// TODO Auto-generated method stub
		if (currentPos[0]%2==0) {// going to right
			if (apples[currentPos[0]][1] < 0 ) { // there is no "J" in current line.
				return 0;
			} else {
				int step = apples[currentPos[0]][1]-currentPos[1];
				currentPos[1]=apples[currentPos[0]][1];
				return step;
			}
		} else {// going to left
			if (apples[currentPos[0]][0] < 0 ) { // there is no "J" in current line.
				return 0;
			} else {
				int step = currentPos[1]-apples[currentPos[0]][0];
				currentPos[1]=apples[currentPos[0]][0];
				return step;
			}
		}
	}
	
	private static int[] getTargetPosition(int[] currentPos) {
		// TODO Auto-generated method stub
		int row = currentPos[0]+1;
		while (row < apples.length) { // if new row is valid, then try to find a new row that contains "J"
			if (apples[row][0] < 0) {// there is no "J" in this line.
				row++;
			} else { // there is a "J" in this line.
				if (row % 2 == 0) { // going to right
					int[] targetPos = {row, apples[row][0]};
					return targetPos;
				} else {// going to left
					int[] targetPos = {row, apples[row][1]};
					return targetPos;
				}
			}
		}
		
		return null;
	}

}
