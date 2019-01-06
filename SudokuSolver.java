public class SudokuSolver{

  public int[][] puzzle;

  public SudokuSolver(){
    puzzle = new int[9][9];
    setTestValues(puzzle);
  }

  public static void main(String[] args){
    SudokuSolver solver = new SudokuSolver();
    print(solver.puzzle);
  }

  public static void print(int[][] puzzle){
    for(int i=0;i<puzzle.length;i++){
      for(int j=0;j<puzzle[i].length;j++){
        if(j%3==0 && j!=0){System.out.print("| ");}
        System.out.print(puzzle[i][j] + " ");
      }
      System.out.print("\n");
      if((i+1)%3==0 && i!=8){
        for(int k=0;k<21;k++){
          System.out.print("-");
        }
        System.out.print("\n");
      }
    }
  }

  public static void setTestValues(int[][] puzzle) {
		puzzle[0][0] = 1;
		puzzle[0][1] = 8;
		puzzle[0][2] = 0;
		puzzle[0][3] = 0;
		puzzle[0][4] = 3;
		puzzle[0][5] = 0;
		puzzle[0][6] = 0;
		puzzle[0][7] = 9;
		puzzle[0][8] = 2;

		puzzle[1][0] = 2;
		puzzle[1][1] = 7;
		puzzle[1][2] = 0;
		puzzle[1][3] = 0;
		puzzle[1][4] = 6;
		puzzle[1][5] = 9;
		puzzle[1][6] = 0;
		puzzle[1][7] = 0;
		puzzle[1][8] = 0;

		puzzle[2][0] = 0;
		puzzle[2][1] = 0;
		puzzle[2][2] = 9;
		puzzle[2][3] = 0;
		puzzle[2][4] = 0;
		puzzle[2][5] = 0;
		puzzle[2][6] = 0;
		puzzle[2][7] = 6;
		puzzle[2][8] = 0;

		puzzle[3][0] = 0;
		puzzle[3][1] = 2;
		puzzle[3][2] = 1;
		puzzle[3][3] = 3;
		puzzle[3][4] = 9;
		puzzle[3][5] = 0;
		puzzle[3][6] = 7;
		puzzle[3][7] = 4;
		puzzle[3][8] = 5;

		puzzle[4][0] = 4;
		puzzle[4][1] = 5;
		puzzle[4][2] = 0;
		puzzle[4][3] = 0;
		puzzle[4][4] = 0;
		puzzle[4][5] = 0;
		puzzle[4][6] = 0;
		puzzle[4][7] = 1;
		puzzle[4][8] = 6;

		puzzle[5][0] = 6;
		puzzle[5][1] = 9;
		puzzle[5][2] = 7;
		puzzle[5][3] = 0;
		puzzle[5][4] = 1;
		puzzle[5][5] = 5;
		puzzle[5][6] = 2;
		puzzle[5][7] = 3;
		puzzle[5][8] = 0;

		puzzle[6][0] = 0;
		puzzle[6][1] = 6;
		puzzle[6][2] = 0;
		puzzle[6][3] = 0;
		puzzle[6][4] = 0;
		puzzle[6][5] = 0;
		puzzle[6][6] = 3;
		puzzle[6][7] = 0;
		puzzle[6][8] = 0;

		puzzle[7][0] = 0;
		puzzle[7][1] = 0;
		puzzle[7][2] = 0;
		puzzle[7][3] = 6;
		puzzle[7][4] = 4;
		puzzle[7][5] = 0;
		puzzle[7][6] = 0;
		puzzle[7][7] = 2;
		puzzle[7][8] = 9;

		puzzle[8][0] = 7;
		puzzle[8][1] = 4;
		puzzle[8][2] = 0;
		puzzle[8][3] = 0;
		puzzle[8][4] = 2;
		puzzle[8][5] = 0;
		puzzle[8][6] = 0;
		puzzle[8][7] = 5;
		puzzle[8][8] = 1;
	}

  public static void setTestAnswer(int[][] puzzle) {
		puzzle[0][0] = 1;
		puzzle[0][1] = 8;
		puzzle[0][2] = 6;
		puzzle[0][3] = 7;
		puzzle[0][4] = 3;
		puzzle[0][5] = 4;
		puzzle[0][6] = 5;
		puzzle[0][7] = 9;
		puzzle[0][8] = 2;

		puzzle[1][0] = 2;
		puzzle[1][1] = 7;
		puzzle[1][2] = 4;
		puzzle[1][3] = 5;
		puzzle[1][4] = 6;
		puzzle[1][5] = 9;
		puzzle[1][6] = 1;
		puzzle[1][7] = 8;
		puzzle[1][8] = 3;

		puzzle[2][0] = 5;
		puzzle[2][1] = 3;
		puzzle[2][2] = 9;
		puzzle[2][3] = 2;
		puzzle[2][4] = 8;
		puzzle[2][5] = 1;
		puzzle[2][6] = 4;
		puzzle[2][7] = 6;
		puzzle[2][8] = 7;

		puzzle[3][0] = 8;
		puzzle[3][1] = 2;
		puzzle[3][2] = 1;
		puzzle[3][3] = 3;
		puzzle[3][4] = 9;
		puzzle[3][5] = 6;
		puzzle[3][6] = 7;
		puzzle[3][7] = 4;
		puzzle[3][8] = 5;

		puzzle[4][0] = 4;
		puzzle[4][1] = 5;
		puzzle[4][2] = 3;
		puzzle[4][3] = 8;
		puzzle[4][4] = 7;
		puzzle[4][5] = 2;
		puzzle[4][6] = 9;
		puzzle[4][7] = 1;
		puzzle[4][8] = 6;

		puzzle[5][0] = 6;
		puzzle[5][1] = 9;
		puzzle[5][2] = 7;
		puzzle[5][3] = 4;
		puzzle[5][4] = 1;
		puzzle[5][5] = 5;
		puzzle[5][6] = 2;
		puzzle[5][7] = 3;
		puzzle[5][8] = 8;

		puzzle[6][0] = 9;
		puzzle[6][1] = 6;
		puzzle[6][2] = 2;
		puzzle[6][3] = 1;
		puzzle[6][4] = 5;
		puzzle[6][5] = 8;
		puzzle[6][6] = 3;
		puzzle[6][7] = 7;
		puzzle[6][8] = 4;

		puzzle[7][0] = 3;
		puzzle[7][1] = 1;
		puzzle[7][2] = 5;
		puzzle[7][3] = 6;
		puzzle[7][4] = 4;
		puzzle[7][5] = 7;
		puzzle[7][6] = 8;
		puzzle[7][7] = 2;
		puzzle[7][8] = 9;

		puzzle[8][0] = 7;
		puzzle[8][1] = 4;
		puzzle[8][2] = 8;
		puzzle[8][3] = 9;
		puzzle[8][4] = 2;
		puzzle[8][5] = 3;
		puzzle[8][6] = 6;
		puzzle[8][7] = 5;
		puzzle[8][8] = 1;
	}

}
