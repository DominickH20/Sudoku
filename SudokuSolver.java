import java.io.*;
import java.util.*;

public class SudokuSolver{

  public int[][] puzzle;

  public SudokuSolver(){
    puzzle = new int[9][9];
    setValues(puzzle,"./puzzles/testPuzzle1.csv",9);
  }

  public static void main(String[] args){
    SudokuSolver solver = new SudokuSolver();
    print(solver.puzzle);
    setValues(solver.puzzle,"./puzzles/testPuzzle1Answers.csv",9);
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

  public static void setValues(int[][] array, String path, int length){
    if(array.length != length){
      System.out.println("Array size does not match length");
    }
    try{
      Scanner reader = new Scanner(new File(path));
      int count = 0;
      while(reader.hasNextLine()){
        String line = reader.nextLine();
        String[] nums = line.split(",");
        for(int i=0;i<nums.length;i++){
          array[count][i] = Integer.parseInt(nums[i]);
        }
        count++;
      }
      reader.close();
    }catch(FileNotFoundException e){
      e.printStackTrace();
    }
  }
}
