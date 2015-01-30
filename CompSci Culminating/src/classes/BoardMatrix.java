package classes;
public class BoardMatrix {
    static int[][] board = new int[17][13];
    public static void init(){
        for(int x = 0; x < 9; x++){
           for(int y = 0; y < 7; y++){
               board[x][y] = 0;
           } 
        }
    }
}