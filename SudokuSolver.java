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
    //setValues(solver.puzzle,"./puzzles/testPuzzle1Answers.csv",9);
    //print(solver.puzzle);
    System.out.println(solver.isValid(solver.puzzle));
  }

  public void stateSpaceSearch(int[][] puzzle, StateObj first){
    Stack<StateObj> q = new Stack<StateObj>();
    q.add(first);
    while(!q.isEmpty()){
        StateObj temp = q.pop();
    }
  }

  public boolean isValid(int[][] puzzle){
    int[][] refArray = {{1,0},{2,0},{3,0},{4,0},{5,0},{6,0},{7,0},{8,0},{9,0}};

    //check Rows
    for(int i=0;i<puzzle.length;i++){
      for(int j=0;j<puzzle[i].length;j++){
        if(puzzle[i][j]==0){continue;}
        refArray[puzzle[i][j]-1][1]++;
        if(refArray[puzzle[i][j]-1][1]>1){System.out.println("row" + i + " " + j);return false;}
      }
      for(int j=0;j<refArray.length;j++){
        refArray[j][1]=0;
      }
    }

    //check Columns
    for(int j=0;j<puzzle.length;j++){
      for(int i=0;i<puzzle[j].length;i++){
        if(puzzle[i][j]==0){continue;}
        refArray[puzzle[i][j]-1][1]++;
        if(refArray[puzzle[i][j]-1][1]>1){System.out.println("col" + i + " " + j);return false;}
      }
      for(int i=0;i<refArray.length;i++){
        refArray[i][1]=0;
      }
    }

    //check Squares
    for(int iM=1;iM<=3;iM++){
      for(int jM=1;jM<=3;jM++){
        for(int i=(3*iM-3);i<3*iM;i++){
          for(int j=(3*jM-3);j<3*jM;j++){
            if(puzzle[i][j]==0){continue;}
            refArray[puzzle[i][j]-1][1]++;
            if(refArray[puzzle[i][j]-1][1]>1){System.out.println("sq" + i + " " + j);return false;}
          }
        }
        for(int j=0;j<refArray.length;j++){
          refArray[j][1]=0;
        }
      }
    }
    return true;
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
      System.out.println("Array size does not match length specified");
      return;
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

  public void arrayCopy(int[][] src, int[][] dest){
    if((src.length!=dest.length) || (src[0].length!=dest[0].length))
    for(int i=0;i<src.length;i++){
      for(int j=0;j<src[i].length;j++){
        dest[i][j] = src[i][j];
      }
    }
  }

  private class StateObj{
    int[][] array;

    public StateObj(int[][] source){
      array = new int[source.length][source[0].length];
      arrayCopy(source,array);
    }

    public int[][] getArray(){
      return this.array;
    }
  }
}
