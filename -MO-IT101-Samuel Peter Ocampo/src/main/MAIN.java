/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;


public class MAIN {
    private static final double HOURLY_RATE = 500.0; // Replace with the actual hourly rate
    private static final double SSS = 1125.0; 
    private static final double PHILHEALTH = 750.0; 
    private static final double GENERIC_DEDUCTIONS = SSS + PHILHEALTH; // Replace with the actual generic deductions

    public static void main(String[] args) {
        Attendance[] employeeAttendance = EmployeeData.getAttendance();

        // Map to store weekly summaries per date for each employee
        Map<String, List<WeeklySummary>> weeklySummaries = new HashMap<>();

        // Process and store information for each employee
        for (Attendance attendance : employeeAttendance) {
            processEmployee(attendance, weeklySummaries);
        }

        // Display weekly summaries
        displayWeeklySummaries(weeklySummaries);
    }

    private static void processEmployee(Attendance attendance, Map<String, List<WeeklySummary>> weeklySummaries) {

        // Get the date from the attendance
        String date = attendance.getDate();

        // Calculate hours worked
        int hoursWorked = calculateHoursWorked(attendance.getTimeIn(), attendance.getTimeOut());

        // Calculate gross weekly salary
        double grossWeeklySalary = calculateGrossWeeklySalary(hoursWorked, HOURLY_RATE);

        // Calculate net weekly salary after deductions
        double netWeeklySalary = calculateNetWeeklySalary(grossWeeklySalary, GENERIC_DEDUCTIONS);

        // Create a WeeklySummary object
        WeeklySummary weeklySummary = new WeeklySummary(attendance.getEmployeeName(),date, hoursWorked, grossWeeklySalary, netWeeklySalary,attendance.getTimeIn());

        // Add the WeeklySummary to the map for the specific date
        weeklySummaries.computeIfAbsent(attendance.getEmployeeName(), k -> new ArrayList<>()).add(weeklySummary);

    }

    private static int calculateHoursWorked(String timeIn, String timeOut) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");

            Date inTime = dateFormat.parse(timeIn);
            Date outTime = dateFormat.parse(timeOut);

            // Calculate the time difference in milliseconds
            long timeDifference = outTime.getTime() - inTime.getTime();

            // Convert milliseconds to hours and deduct 1 hour
            return (int) ((timeDifference / (60 * 60 * 1000)) - 1);

        } catch (ParseException e) {
            // Handle the exception (e.g., invalid time format)
            return 0; // Return 0 hours in case of an error
        }
    }

    private static double calculateGrossWeeklySalary(int hoursWorked, double hourlyRatePHP) {
        // Assuming a standard workweek of 40 hours
        int standardHoursPerWeek = 40;

        // Calculate overtime hours (if any)
        int overtimeHours = Math.max(hoursWorked - standardHoursPerWeek, 0);

        // Calculate gross weekly salary including overtime pay and deduct 1 hour
        return ((standardHoursPerWeek - 1) * hourlyRatePHP) + (overtimeHours * hourlyRatePHP * 1.5);
    }

    private static double calculateNetWeeklySalary(double grossWeeklySalaryPHP, double deductionsPHP) {
        // Apply deductions to the gross weekly salary
        return grossWeeklySalaryPHP - deductionsPHP;
    }

    private static void displayWeeklySummaries(Map<String, List<WeeklySummary>> weeklySummaries) {
        
           // Access employee data from the EmployeeData class
        Employee[] employees = EmployeeData.getEmployees();

        // Print employee data
        for (Employee employee : employees) {
            System.out.println( " EMPLOYEE DETAILS " );
            System.out.println(employee);
        }

        // Display weekly summaries
        for (Map.Entry<String, List<WeeklySummary>> entry : weeklySummaries.entrySet()) {
            System.out.println("*** Employee Name: " + entry.getKey());
            System.out.println("========================================");

            for (WeeklySummary weeklySummary : entry.getValue()) {
                System.out.println("* Days Worked:");
                System.out.println("- Date Worked: " + weeklySummary.getDate());
                System.out.println("  - Hours Worked: " + weeklySummary.getHoursWorked());
                System.out.println("  - Late Hours: " + weeklySummary.getLateHours());
                System.out.println("  - Total Worked Minus Late: " + weeklySummary.getTotalWorkedMinusLate());
                System.out.println("  - Total Deductions: " + weeklySummary.getTotalDeductions());
                System.out.println();
            }

            // Calculate and display totals
            int totalHoursWorked = entry.getValue().stream().mapToInt(WeeklySummary::getHoursWorked).sum();
            double totalGrossWeeklySalary = entry.getValue().stream().mapToDouble(WeeklySummary::getGrossWeeklySalary).sum();
            double totalNetWeeklySalary = entry.getValue().stream().mapToDouble(WeeklySummary::getNetWeeklySalary).sum();

            System.out.println("Total Hours Worked: " + totalHoursWorked);
            System.out.println("Total Gross Weekly Salary: P" + totalGrossWeeklySalary);
            System.out.println("Total Net Weekly Salary: P" + totalNetWeeklySalary);

            // Separator for better readability
            System.out.println("========================================");
        }
    }
}

