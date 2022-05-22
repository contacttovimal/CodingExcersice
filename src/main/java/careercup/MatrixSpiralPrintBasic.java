package careercup;

public class MatrixSpiralPrintBasic {

	public enum Direction {
		RIGHT, DOWN, LEFT, UP;
	}
	
	public static void printMatrixSpiral(char[][] charArray) {
		
		int xMax = charArray[0].length;
		int yMax = charArray.length;
		int xMin = 0;
		int yMin = 0;
		int xPoint = -1;
		int yPoint = 0;
		int totalCharCnt = xMax*yMax;
		int writeCharCnt = 0;
		
		Direction dir = Direction.RIGHT;
		
		while(writeCharCnt < totalCharCnt) {
			
			switch(dir) {
			
				case RIGHT:
					xPoint++;
					System.out.print(charArray[yPoint][xPoint]);
					if(xPoint == xMax-1) {
						yMin++;
						dir = Direction.DOWN;
					}
					break;
					
				case DOWN:
					yPoint++;
					System.out.print(charArray[yPoint][xPoint]);
					if(yPoint == yMax-1) {
						xMax--;
						dir = Direction.LEFT;
					}
					break;
					
				case LEFT:
					xPoint--;
					System.out.print(charArray[yPoint][xPoint]);
					if(xPoint == xMin) {
						yMax--;
						dir = Direction.UP;
					}
					break;
					
				case UP:
					yPoint--;
					System.out.print(charArray[yPoint][xPoint]);
					if(yPoint == yMin) {
						xMin++;
						dir = Direction.RIGHT;
					}
					break;
			}
			
			writeCharCnt++;
		}
		
	}
	
	public static void main(String[] args) {

		final char[][] charArray = 
			{
				{'i',	'l', 	'o', 	'v', 	'e' },
				{'d',	'i', 	'n', 	't', 	'e' },
				{'n', 	'e', 	'w', 	'e', 	'p' }, 
				{'a', 	'i', 	'v', 	'r', 	'i' }, 
				{'m',	'a', 	'x', 	'e', 	'c' } 
			};

		/*
		final char[][] charArray = 
			{
				{'a',	'b', 	'c', 	'd'},
				{'l',	'm', 	'n', 	'e'},
				{'k', 	'p', 	'o', 	'f'}, 
				{'j', 	'i', 	'h', 	'g'} 
			};
		*/

		/*
		final char[][] charArray = 
			{
				{'1',	'2'},
				{'8',	'3'},
				{'7', 	'4'}, 
				{'6', 	'5'} 
			};
		*/

		/*
		final char[][] charArray = 
			{
				{'1',	'2',	'3',	'4'} 
			};
		*/
		
		printMatrixSpiral(charArray);
		
	}

	
}