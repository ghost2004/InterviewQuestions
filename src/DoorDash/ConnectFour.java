package DoorDash;

/*
 * Connect Four is a popular two-player connection game in which the players first choose a color and then take turns dropping one 
 * colored disc from the top into a seven-column, six-row vertically suspended grid. The pieces fall straight down, occupying the 
 * lowest available space within the column. The goal of the Connect Four game is to be the first player to form a horizontal, 
 * vertical, or diagonal line of four of one¡¯s own discs. Connect Four is a solved game. Thus, the first player can always win by 
 * playing the right moves.
 */
import java.io.*;
import java.util.*;
import java.util.stream.*;
public class ConnectFour {
    enum Player {
        Red,
        Yellow
    }
    private int _width;
    private int _height;
    
    private char[][] _grid;
    private int _lastMoveX;
    private int _lastMoveY;
    private int _curHeight[];
    
    public ConnectFour(int width, int height) {
        _width = width;
        _height = height;
        _grid = new char[height][width];
        _curHeight = new int[width];
        Arrays.fill(_curHeight, 0);
        for (int i = 0; i < height; i++) {
            Arrays.fill(_grid[i], '.');
          }

    }
    
    public String toString() {
        return IntStream.range(0,  _width).
               mapToObj(Integer::toString).
               collect(Collectors.joining()) + 
               "\n" +
               Arrays.stream(_grid).
               map(String::new).
               collect(Collectors.joining("\n"));
    }
    
    // get string representation of the row containing the last play of the user
    public String horizontal() {
      return new String(_grid[_lastMoveY]);
    }
    
    // get string representation of the col containing the last play of the user
    public String vertical() {
      StringBuilder sb = new StringBuilder();
      for (int h = 0; h < _lastMoveY; h++) {
        sb.append(_grid[h][_lastMoveX]);
      }
      return sb.toString();
    }
    
    
    // get string representation of the "\" diagonal containing the last play of the user
    public String backslash() {
      StringBuilder sb = new StringBuilder();

      for (int h = _lastMoveY; h >= 0 ; h--) {
        int w = _lastMoveX + _lastMoveY - h;
        if (w < _width)
            sb.append(_grid[h][w]);
      }

      return sb.toString();
    }
    
    // get string representation of the "/" diagonal containing the last play of the user
    public String slash() {
      StringBuilder sb = new StringBuilder();

      for (int h = _lastMoveY; h >= 0 ; h--) {
        int w = _lastMoveX - _lastMoveY + h;
        if (w >= 0)
            sb.append(_grid[h][w]);
      }

      return sb.toString();
    }
    
    // static method checking if a substring is in str
    public static boolean contains(String str, String substring) {
      return str.indexOf(substring) >= 0;
    }

    // now, we create a method checking if last play is a winning play
    public boolean isWinningPlay() {
      if (_lastMoveX == -1) {
        System.err.println("No move has been made yet");
        return false;
      }

      char sym = _grid[_lastMoveY][_lastMoveX];
      // winning streak with the last play symbol
      String streak = String.format("%c%c%c%c", sym, sym, sym, sym);

      // check if streak is in row, col, diagonal or backslash diagonal
      return contains(horizontal(), streak) || 
             contains(vertical(), streak) || 
             contains(slash(), streak) || 
             contains(backslash(), streak);
    }
    
    public boolean play(Player player, int col) throws Exception {
        char c = player.name().charAt(0);
        if (col < 0 || col >= _width) {
            throw new Exception(String.format("column %d is out-of-bound", col ));
        }
        if (_curHeight[col] >= _height) {
            throw new Exception(String.format("column %d is overflow", col ));
        }
        _lastMoveX = col;
        _lastMoveY = _curHeight[col];
        _grid[_curHeight[col]][col] = c;
        _curHeight[col]++;
        return isWinningPlay();
    }
    
    public static void getFile(String path){   
        // get file list where the path has   
        File file = new File(path);   
        // get the folder list   
        File[] array = file.listFiles();   
          
        for(int i=0;i<array.length;i++){   
            if(array[i].isFile()){   
                String fname =  array[i].getName();
                System.out.println(fname);  
                if (fname.endsWith("mp4")) {
                   int idx = fname.indexOf("Home With Kids Season 1 EP. ");
                   String newName = path+"//"+fname.substring(idx, idx+30)+".mp4";
                   System.out.println(newName);
                   array[i].renameTo(new File(newName));
                }
                    

            }else if(array[i].isDirectory()){   
                getFile(array[i].getPath());   
            }   
        }
    }   
    
    public static void main(String args[]) {
        /*
        String path = "Z://Drama//test";   
        getFile(path);   */
        
        ConnectFour f = new ConnectFour(7, 6);
        System.out.println(f.toString());
        
    }

}
