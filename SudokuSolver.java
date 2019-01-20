import java.io.*;
import java.util.*;

public class SudokuSolver{

  public int[][] puzzle;

  public SudokuSolver(){
    puzzle = new int[9][9];
    setValues(puzzle,"./puzzles/testPuzzle3.csv",9);
  }

  public static void main(String[] args){
    SudokuSolver solver = new SudokuSolver();
    print(solver.puzzle);
    solver.stateSpaceSearch(solver.puzzle);
    //solver.recursiveBackTracking(solver.puzzle);
  }

  public void stateSpaceSearch(int[][] puzzle){
    StateObj first = new StateObj(puzzle);
    Stack<StateObj> q = new Stack<StateObj>();
    q.push(first);
    while(!q.isEmpty()){
        StateObj temp = q.pop();
        int[] pos = findNextEmpty(temp.array);
        if(pos!=null){
          for(int i=0;i<9;i++){
            int[][] arr = new int[9][9];
            arrayCopy(temp.array,arr);
            arr[pos[0]][pos[1]] = i+1;
            if(isValid(arr)){
              StateObj next = new StateObj(arr);
              q.push(next);
            }
          }
        }else{
          if(isValid(temp.array)){
            print(temp.array);
          }
        }
    }
  }

  public void recursiveBackTracking(int[][] puzzle){
    int[][] arr = new int[9][9];
    boolean[][] bool = new boolean[9][9];
    arrayCopy(puzzle,arr);
    for(int i=0;i<9;i++){
      for(int j=0;j<9;j++){
        if(arr[i][j]!=0){
          bool[i][j]=true;
        }
      }
    }
    solveAt(arr,bool,0,0,1);
  }

  public void solveAt(int[][] puzzle, boolean[][] bool, int i,int j, int val){
    if(bool[i][j]){
      if(i==8&&j==8){
        if(isValid(puzzle)){
          print(puzzle);
          return;
        }
      }else if(j==8){
        solveAt(puzzle,bool,i+1,0,1);
      }else{
        solveAt(puzzle,bool,i,j+1,1);
      }
    }else{
      puzzle[i][j] = val;
      if(isValid(puzzle)){
        if(i==8&&j==8){
          print(puzzle);
          return;
        }else if(j==8){
          solveAt(puzzle,bool,i+1,0,1);
        }else{
          solveAt(puzzle,bool,i,j+1,1);
        }
      }else{
        puzzle[i][j] = 0;
        if(val==9){
          int[] pos = findPriorIncrementableValue(puzzle,bool,i,j);
          if((i==0&&j==0) || pos==null){
            if(pos==null){
              System.out.println("null");
            }
            System.out.println("Failed");
            return;
          }else{
            solveAt(puzzle,bool,pos[0],pos[1],pos[2]+1);
          }
        }else{
          solveAt(puzzle,bool,i,j,val+1);
        }
      }
    }
  }

  public int[] findPriorIncrementableValue(int[][] puzzle, boolean[][] bool, int i, int j){
    for(int k=i;k>=0;k--){
      for(int l=8;l>=0;l--){
        if(k==i && l>j){continue;}
        if(!bool[k][l] && (puzzle[k][l]!=9) && (k!=i || j!=l)){
          int[] arr = new int[3];
          arr[0]=k;arr[1]=l;arr[2]=puzzle[k][l];
          return arr;
        }
        if(!bool[k][l]){puzzle[k][l]=0;}
      }
    }
    return null;
  }

  public int[] findNextEmpty(int[][] puzzle){
    for(int i=0;i<puzzle.length;i++){
      for(int j=0;j<puzzle[i].length;j++){
        if(puzzle[i][j]==0){
          int[] arr = new int[2];
          arr[0]=i; arr[1]=j;
          return arr;
        }
      }
    }
    return null;
  }

  public boolean isValid(int[][] puzzle){
    int[][] refArray = {{1,0},{2,0},{3,0},{4,0},{5,0},{6,0},{7,0},{8,0},{9,0}};

    //check Rows
    for(int i=0;i<puzzle.length;i++){
      for(int j=0;j<puzzle[i].length;j++){
        if(puzzle[i][j]==0){continue;}
        refArray[puzzle[i][j]-1][1]++;
        if(refArray[puzzle[i][j]-1][1]>1){return false;}
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
        if(refArray[puzzle[i][j]-1][1]>1){return false;}
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
            if(refArray[puzzle[i][j]-1][1]>1){return false;}
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
    System.out.print("\n");
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
    if((src.length==dest.length) && (src[0].length==dest[0].length)){
      for(int i=0;i<src.length;i++){
        for(int j=0;j<src[i].length;j++){
          dest[i][j] = src[i][j];
        }
      }
    }
  }

  private class StateObj{
    int[][] array;

    public StateObj(int[][] source){
      this.array = new int[source.length][source[0].length];
      arrayCopy(source,this.array);
    }

    public int[][] getArray(){
      return this.array;
    }
  }
}
