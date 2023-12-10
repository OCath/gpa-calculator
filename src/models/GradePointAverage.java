package models;
public class GradePointAverage{
    private static double gradePoint = 0;
    private static double totalQualityPoint = 0;
    private static double totalGradeUnit = 0;

    public static double getGradePointAverage(String[][] gradedUnitProfile) {
        for (int i = 0; i < gradedUnitProfile.length; i++) {
            double courseUnit = Double.parseDouble(gradedUnitProfile[i][1]);
            double gradeUnit = Double.parseDouble(gradedUnitProfile[i][3]);
            totalQualityPoint += courseUnit * gradeUnit;
            totalGradeUnit += courseUnit;
        }

        gradePoint = totalQualityPoint / totalGradeUnit;
        return gradePoint;
    }
}