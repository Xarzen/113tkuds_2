public class TicTacToeBoard {
    private char[][] board;
    private static final int SIZE = 3;
    private static final char EMPTY = ' ';
    private static final char PLAYER_X = 'X';
    private static final char PLAYER_O = 'O';
    
    public TicTacToeBoard() {
        board = new char[SIZE][SIZE];
        initializeBoard();
    }
    
    public void initializeBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = EMPTY;
            }
        }
    }
    
    public void displayBoard() {
        System.out.println("\n現在棋盤狀態：");
        System.out.println("   0   1   2");
        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(" " + board[i][j] + " ");
                if (j < SIZE - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (i < SIZE - 1) {
                System.out.println("  -----------");
            }
        }
        System.out.println();
    }
    
    public boolean isValidMove(int row, int col) {
        if (row < 0 || row >= SIZE || col < 0 || col >= SIZE) {
            System.out.println("錯誤：位置超出棋盤範圍！");
            return false;
        }
        
        if (board[row][col] != EMPTY) {
            System.out.println("錯誤：該位置已被佔用！");
            return false;
        }
        
        return true;
    }
    
    public boolean placePiece(int row, int col, char player) {
        if (!isValidMove(row, col)) {
            return false;
        }
        
        if (player != PLAYER_X && player != PLAYER_O) {
            System.out.println("錯誤：無效的玩家符號！");
            return false;
        }
        
        board[row][col] = player;
        System.out.printf("玩家 %c 在位置 (%d, %d) 放置棋子\n", player, row, col);
        return true;
    }
    
    public char checkWinner() {
        for (int i = 0; i < SIZE; i++) {
            if (board[i][0] != EMPTY && 
                board[i][0] == board[i][1] && 
                board[i][1] == board[i][2]) {
                return board[i][0];
            }
        }
        
        for (int j = 0; j < SIZE; j++) {
            if (board[0][j] != EMPTY && 
                board[0][j] == board[1][j] && 
                board[1][j] == board[2][j]) {
                return board[0][j];
            }
        }
        
        if (board[0][0] != EMPTY && 
            board[0][0] == board[1][1] && 
            board[1][1] == board[2][2]) {
            return board[0][0];
        }
        
        if (board[0][2] != EMPTY && 
            board[0][2] == board[1][1] && 
            board[1][1] == board[2][0]) {
            return board[0][2];
        }
        
        return EMPTY;
    }
    
    public boolean isBoardFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public boolean isGameOver() {
        return checkWinner() != EMPTY || isBoardFull();
    }
    
    public String getGameStatus() {
        char winner = checkWinner();
        if (winner != EMPTY) {
            return "遊戲結束！玩家 " + winner + " 獲勝！";
        } else if (isBoardFull()) {
            return "遊戲結束！平手！";
        } else {
            return "遊戲進行中...";
        }
    }
    
    public void printGameStatistics() {
        int xCount = 0, oCount = 0, emptyCount = 0;
        
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == PLAYER_X) {
                    xCount++;
                } else if (board[i][j] == PLAYER_O) {
                    oCount++;
                } else {
                    emptyCount++;
                }
            }
        }
        
        System.out.println("=== 遊戲統計 ===");
        System.out.println("X 的棋子數量: " + xCount);
        System.out.println("O 的棋子數量: " + oCount);
        System.out.println("空白位置: " + emptyCount);
        System.out.println("總步數: " + (xCount + oCount));
    }
    
    public void resetGame() {
        initializeBoard();
        System.out.println("遊戲已重置！");
    }
    
    public static void simulateGame() {
        TicTacToeBoard game = new TicTacToeBoard();
        
        System.out.println("=== 井字遊戲模擬 ===");
        game.displayBoard();
        
        int[][] moves = {
            {1, 1, PLAYER_X},
            {0, 0, PLAYER_O},
            {0, 1, PLAYER_X},
            {2, 1, PLAYER_O},
            {2, 0, PLAYER_X},
            {0, 2, PLAYER_O},
            {1, 0, PLAYER_X},
            {1, 2, PLAYER_O},
            {2, 2, PLAYER_X}
        };
        
        for (int[] move : moves) {
            int row = move[0];
            int col = move[1];
            char player = (char) move[2];
            
            if (game.placePiece(row, col, player)) {
                game.displayBoard();
                System.out.println("狀態: " + game.getGameStatus());
                
                if (game.isGameOver()) {
                    break;
                }
            }
            
            System.out.println("按 Enter 繼續下一步...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        
        game.printGameStatistics();
    }
    
    public static void main(String[] args) {
        System.out.println("=== 井字遊戲棋盤測試 ===");
        
        // 基本功能測試
        TicTacToeBoard testBoard = new TicTacToeBoard();
        
        System.out.println("1. 初始棋盤:");
        testBoard.displayBoard();
        
        System.out.println("2. 測試放置棋子:");
        testBoard.placePiece(1, 1, PLAYER_X);
        testBoard.placePiece(0, 0, PLAYER_O);
        testBoard.displayBoard();
        
        System.out.println("3. 測試無效移動:");
        testBoard.placePiece(1, 1, PLAYER_X);  // 位置已佔用
        testBoard.placePiece(-1, 0, PLAYER_O); // 超出範圍
        testBoard.placePiece(1, 3, PLAYER_X);  // 超出範圍
        
        System.out.println("4. 測試獲勝檢查:");
        // 讓 X 獲勝（對角線）
        testBoard.placePiece(0, 2, PLAYER_X);
        testBoard.placePiece(2, 2, PLAYER_X);
        testBoard.displayBoard();
        System.out.println("獲勝者: " + testBoard.checkWinner());
        System.out.println("遊戲狀態: " + testBoard.getGameStatus());
        
        testBoard.printGameStatistics();
        
        System.out.println("\n" + "=".repeat(50));
        
        // 模擬完整遊戲
        simulateGame();
    }
}
