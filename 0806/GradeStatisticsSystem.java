import java.util.Arrays;

public class GradeStatisticsSystem {
    
    public static double calculateAverage(int[] grades) {
        if (grades == null || grades.length == 0) {
            return 0.0;
        }
        
        int sum = 0;
        for (int grade : grades) {
            sum += grade;
        }
        return (double) sum / grades.length;
    }
    
    public static int findMaxGrade(int[] grades) {
        if (grades == null || grades.length == 0) {
            return -1;
        }
        
        int max = grades[0];
        for (int grade : grades) {
            if (grade > max) {
                max = grade;
            }
        }
        return max;
    }
    
    public static int findMinGrade(int[] grades) {
        if (grades == null || grades.length == 0) {
            return -1;
        }
        
        int min = grades[0];
        for (int grade : grades) {
            if (grade < min) {
                min = grade;
            }
        }
        return min;
    }
    
    public static char getGradeLetter(int score) {
        if (score >= 90) return 'A';
        else if (score >= 80) return 'B';
        else if (score >= 70) return 'C';
        else if (score >= 60) return 'D';
        else return 'F';
    }
    
    public static int[] countGradeDistribution(int[] grades) {
        int[] distribution = new int[5];
        
        for (int grade : grades) {
            char letter = getGradeLetter(grade);
            switch (letter) {
                case 'A': distribution[0]++; break;
                case 'B': distribution[1]++; break;
                case 'C': distribution[2]++; break;
                case 'D': distribution[3]++; break;
                case 'F': distribution[4]++; break;
            }
        }
        return distribution;
    }
    
    public static int countAboveAverage(int[] grades) {
        double average = calculateAverage(grades);
        int count = 0;
        
        for (int grade : grades) {
            if (grade > average) {
                count++;
            }
        }
        return count;
    }
    
    public static void printGradeReport(int[] grades) {
        System.out.println("=== 成績統計報表 ===");
        System.out.println("原始成績：" + Arrays.toString(grades));
        System.out.println();
        
        double average = calculateAverage(grades);
        int maxGrade = findMaxGrade(grades);
        int minGrade = findMinGrade(grades);
        
        System.out.printf("學生總數：%d 人\n", grades.length);
        System.out.printf("平均分數：%.2f 分\n", average);
        System.out.printf("最高分數：%d 分\n", maxGrade);
        System.out.printf("最低分數：%d 分\n", minGrade);
        System.out.println();
        
        int[] distribution = countGradeDistribution(grades);
        String[] gradeLetters = {"A", "B", "C", "D", "F"};
        String[] gradeRanges = {"90-100", "80-89", "70-79", "60-69", "0-59"};
        
        System.out.println("等第分布：");
        for (int i = 0; i < distribution.length; i++) {
            double percentage = (double) distribution[i] / grades.length * 100;
            System.out.printf("  %s 等 (%s 分): %d 人 (%.1f%%)\n", 
                            gradeLetters[i], gradeRanges[i], distribution[i], percentage);
        }
        System.out.println();
        
        int aboveAverage = countAboveAverage(grades);
        double aboveAveragePercentage = (double) aboveAverage / grades.length * 100;
        System.out.printf("高於平均分：%d 人 (%.1f%%)\n", aboveAverage, aboveAveragePercentage);
        
        System.out.println("\n詳細成績清單：");
        for (int i = 0; i < grades.length; i++) {
            char letter = getGradeLetter(grades[i]);
            String status = grades[i] > average ? "高於平均" : "低於平均";
            System.out.printf("學生 %2d: %3d 分 (%c 等) - %s\n", 
                            i + 1, grades[i], letter, status);
        }
    }
    
    public static void main(String[] args) {
        int[] grades = {85, 92, 78, 96, 87, 73, 89, 94, 82, 90};
        
        printGradeReport(grades);
        
        System.out.println("\n=== 個別功能測試 ===");
        System.out.println("平均分數：" + calculateAverage(grades));
        System.out.println("最高分數：" + findMaxGrade(grades));
        System.out.println("最低分數：" + findMinGrade(grades));
        System.out.println("高於平均分人數：" + countAboveAverage(grades));
    }
}
