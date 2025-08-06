public class MatrixCalculator {
    
    public static void printMatrix(int[][] matrix, String title) {
        System.out.println(title + ":");
        if (matrix == null) {
            System.out.println("矩陣為 null");
            return;
        }
        
        for (int[] row : matrix) {
            for (int value : row) {
                System.out.printf("%4d ", value);
            }
            System.out.println();
        }
        System.out.println();
    }
    
    public static boolean canAdd(int[][] matrix1, int[][] matrix2) {
        if (matrix1 == null || matrix2 == null) return false;
        if (matrix1.length != matrix2.length) return false;
        if (matrix1.length == 0) return true;
        return matrix1[0].length == matrix2[0].length;
    }
    
    public static int[][] addMatrix(int[][] matrix1, int[][] matrix2) {
        if (!canAdd(matrix1, matrix2)) {
            System.out.println("錯誤：矩陣維度不匹配，無法相加");
            return null;
        }
        
        int rows = matrix1.length;
        int cols = matrix1[0].length;
        int[][] result = new int[rows][cols];
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        }
        
        return result;
    }
    
    public static boolean canMultiply(int[][] matrix1, int[][] matrix2) {
        if (matrix1 == null || matrix2 == null) return false;
        if (matrix1.length == 0 || matrix2.length == 0) return false;
        return matrix1[0].length == matrix2.length;
    }
    
    public static int[][] multiplyMatrix(int[][] matrix1, int[][] matrix2) {
        if (!canMultiply(matrix1, matrix2)) {
            System.out.println("錯誤：矩陣維度不匹配，無法相乘");
            return null;
        }
        
        int rows1 = matrix1.length;
        int cols1 = matrix1[0].length;
        int cols2 = matrix2[0].length;
        
        int[][] result = new int[rows1][cols2];
        
        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < cols2; j++) {
                for (int k = 0; k < cols1; k++) {
                    result[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
        
        return result;
    }
    
    public static int[][] transposeMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return null;
        }
        
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] transposed = new int[cols][rows];
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                transposed[j][i] = matrix[i][j];
            }
        }
        
        return transposed;
    }
    
    public static int findMaxValue(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            throw new IllegalArgumentException("矩陣不能為空");
        }
        
        int max = matrix[0][0];
        for (int[] row : matrix) {
            for (int value : row) {
                if (value > max) {
                    max = value;
                }
            }
        }
        return max;
    }
    
    public static int findMinValue(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            throw new IllegalArgumentException("矩陣不能為空");
        }
        
        int min = matrix[0][0];
        for (int[] row : matrix) {
            for (int value : row) {
                if (value < min) {
                    min = value;
                }
            }
        }
        return min;
    }
    
    public static void printMatrixInfo(int[][] matrix, String name) {
        if (matrix == null) {
            System.out.println(name + ": null");
            return;
        }
        
        int rows = matrix.length;
        int cols = rows > 0 ? matrix[0].length : 0;
        
        System.out.printf("%s: %d×%d 矩陣\n", name, rows, cols);
        if (rows > 0 && cols > 0) {
            System.out.printf("  最大值: %d\n", findMaxValue(matrix));
            System.out.printf("  最小值: %d\n", findMinValue(matrix));
        }
    }
    
    public static void main(String[] args) {
        // 測試矩陣
        int[][] matrix1 = {
            {1, 2, 3},
            {4, 5, 6}
        };
        
        int[][] matrix2 = {
            {7, 8, 9},
            {10, 11, 12}
        };
        
        int[][] matrix3 = {
            {1, 2},
            {3, 4},
            {5, 6}
        };
        
        System.out.println("=== 矩陣運算器測試 ===\n");
        
        printMatrix(matrix1, "矩陣 A");
        printMatrix(matrix2, "矩陣 B");
        printMatrix(matrix3, "矩陣 C");
        
        printMatrixInfo(matrix1, "矩陣 A");
        printMatrixInfo(matrix2, "矩陣 B");
        printMatrixInfo(matrix3, "矩陣 C");
        System.out.println();
        
        System.out.println("=== 矩陣加法測試 ===");
        int[][] sum = addMatrix(matrix1, matrix2);
        printMatrix(sum, "A + B");
        
        System.out.println("=== 矩陣乘法測試 ===");
        int[][] product = multiplyMatrix(matrix1, matrix3);
        printMatrix(product, "A × C");
        
        System.out.println("=== 矩陣轉置測試 ===");
        int[][] transposed1 = transposeMatrix(matrix1);
        printMatrix(transposed1, "A 的轉置");
        
        int[][] transposed3 = transposeMatrix(matrix3);
        printMatrix(transposed3, "C 的轉置");
        
        System.out.println("=== 錯誤處理測試 ===");
        addMatrix(matrix1, matrix3);
        multiplyMatrix(matrix2, matrix1);
    }
}
