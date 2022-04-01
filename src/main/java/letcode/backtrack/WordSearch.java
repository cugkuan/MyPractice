package letcode.backtrack;

/**
 * * 给定一个m x n 二维字符网格board 和一个字符串单词word 。如果word 存在于网格中，返回 true ；否则，返回 false 。
 * <p>
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-search
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 本题目才是关键的关键，真正的理解回溯，
 * */

public class WordSearch {

    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        // 记录被访问的情况
        boolean[][] visitor  = new boolean[m][n];
        for (int i = 0; i < m;i++){
            for (int j = 0;j < n;j++){
                visitor[i][j] = false;
            }
        }
        for (int i = 0;i < m;i++){
            for (int j = 0; j < n;j++){
              boolean result = backTrack(board,visitor,m,n,word,i,j,0);
              if (result){
                  return true;
              }
            }
        }
        return false;
    }
    private boolean backTrack(char[][] board, boolean[][] visitor,int m,int n,String word, int i, int j, int k) {
        System.out.print(board[i][j]);
        if (word.charAt(k) != board[i][j]){
            return false;
        }else if (k == word.length() -1){
            return true;
        }

        visitor[i][j] = true;
        // 访问四个方向
        // 左边
        boolean result;
        if (j +1 < n && visitor[i][j+1] == false){
            result =  backTrack(board,visitor,m,n,word,i,j+1,k+1);
           if (result){
               return true;
           }
        }
        // 右边
        if (j -1 >= 0  && visitor[i][j-1] == false){
             result = backTrack(board,visitor,m,n,word,i,j-1,k+1);
             if (result){
                 return true;
             }
        }
        // 下边
        if (i+1 < m && visitor[i+1][j] == false){
            result = backTrack(board,visitor,m,n,word,i+1,j,k+1);
            if (result){
                return true;
            }
        }
        // 上边
        if (i- 1 >= 0 && visitor[i-1][j] == false){
            result = backTrack(board,visitor,m,n,word,i-1,j,k+1);
            if (result){
                return true;
            }
        }
        visitor[i][j] = false;
        return false;
    }

    public static void main(String[] args) {

        WordSearch search = new WordSearch();

//        char[][] board = new char[][]{{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
//        String word = "ABCCED";

        char[][] board = new char[][]{{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        String word = "SEE";

        System.out.print(search.exist(board, word));

    }
}
