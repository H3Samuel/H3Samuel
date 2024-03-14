import 'dart:collection';
import 'dart:math';
import 'dart:core';

class Attendance {
  String date;
  String timeIn;
  String timeOut;
  String employeeName;

  Attendance(this.date, this.timeIn, this.timeOut, this.employeeName);

  String getDate() {
    return date;
  }

  String getTimeIn() {
    return timeIn;
  }

  String getTimeOut() {
    return timeOut;
  }

  String getEmployeeName() {
    return employeeName;
  }
}

class WeeklySummary {
  String employeeName;
  String date;
  int hoursWorked;
  double grossWeeklySalary;
  double netWeeklySalary;
  String lateHours;

  WeeklySummary(this.employeeName, this.date, this.hoursWorked, this.grossWeeklySalary, this.netWeeklySalary, this.lateHours);

  String getDate() {
    return date;
  }

  int getHoursWorked() {
    return hoursWorked;
  }

  String getLateHours() {
    return lateHours;
  }

  double getGrossWeeklySalary() {
    return grossWeeklySalary;
  }

  double getNetWeeklySalary() {
    return netWeeklySalary;
  }
}

class EmployeeData {
  static List<Attendance> getAttendance() {
    return [
      Attendance("01/09/2022", "8:00", "17:00", "Employee1"),
      Attendance("01/09/2022", "8:00", "17:00", "Employee2"),
      // Add other attendance records here
    ];
  }
}

class MAIN {
  static final double HOURLY_RATE = 500.0;
  static final double SSS = 1125.0;
  static final double PHILHEALTH = 750.0;
  static final double GENERIC_DEDUCTIONS = SSS + PHILHEALTH;

  static void main(List<String> args) {
    List<Attendance> employeeAttendance = EmployeeData.getAttendance();
    Map<String, List<WeeklySummary>> weeklySummaries = HashMap();

    for (Attendance attendance in employeeAttendance) {
      processEmployee(attendance, weeklySummaries);
    }

    displayWeeklySummaries(weeklySummaries);
  }

  static void processEmployee(Attendance attendance, Map<String, List<WeeklySummary>> weeklySummaries) {
    String date = attendance.getDate();
    int hoursWorked = calculateHoursWorked(attendance.getTimeIn(), attendance.getTimeOut());
    double grossWeeklySalary = calculateGrossWeeklySalary(hoursWorked, HOURLY_RATE);
    double netWeeklySalary = calculateNetWeeklySalary(grossWeeklySalary, GENERIC_DEDUCTIONS);
    WeeklySummary weeklySummary = WeeklySummary(attendance.getEmployeeName(), date, hoursWorked, grossWeeklySalary, netWeeklySalary, attendance.getTimeIn());

    weeklySummaries.putIfAbsent(attendance.getEmployeeName(), () => []).add(weeklySummary);
  }

  static int calculateHoursWorked(String timeIn, String timeOut) {
    try {
      DateTime inTime = DateTime.parse("2022-01-01 $timeIn:00");
      DateTime outTime = DateTime.parse("2022-01-01 $timeOut:00");
      Duration difference = outTime.difference(inTime);
      return difference.inHours - 1;
    } catch (e) {
      return 0;
    }
  }

  static double calculateGrossWeeklySalary(int hoursWorked, double hourlyRatePHP) {
    int standardHoursPerWeek = 40;
    int overtimeHours = max(hoursWorked - standardHoursPerWeek, 0);
    return ((standardHoursPerWeek - 1) * hourlyRatePHP) + (overtimeHours * hourlyRatePHP * 1.5);
  }

  static double calculateNetWeeklySalary(double grossWeeklySalaryPHP, double deductionsPHP) {
    return grossWeeklySalaryPHP - deductionsPHP;
  }

  static void displayWeeklySummaries(Map<String, List<WeeklySummary>> weeklySummaries) {
    List<Employee> employees = EmployeeData.getEmployees();
    for (Employee employee in employees) {
      print(" EMPLOYEE DETAILS ");
      print(employee);
    }

    for (String key in weeklySummaries.keys) {
      print("*** Employee Name: $key");
      print("========================================");
      for (WeeklySummary weeklySummary in weeklySummaries[key]!) {
        print("* Days Worked:");
        print("- Date Worked: ${weeklySummary.getDate()}");
        print("  - Hours Worked: ${weeklySummary.getHoursWorked()}");
        print("  - Late Hours: ${weeklySummary.getLateHours()}");
        print("  - Total Worked Minus Late: ${weeklySummary.getTotalWorkedMinusLate()}");
        print("  - Total Deductions: ${weeklySummary.getTotalDeductions()}");
        print("");
      }
      int totalHoursWorked = weeklySummaries[key]!.fold(0, (sum, e) => sum + e.getHoursWorked());
      double totalGrossWeeklySalary = weeklySummaries[key]!.fold(0, (sum, e) => sum + e.getGrossWeeklySalary());
      double totalNetWeeklySalary = weeklySummaries[key]!.fold(0, (sum, e) => sum + e.getNetWeeklySalary());
      print("Total Hours Worked: $totalHoursWorked");
      print("Total Gross Weekly Salary: P$totalGrossWeeklySalary");
      print("Total Net Weekly Salary: P$totalNetWeeklySalary");
      print("========================================");
    }
  }
}

class Employee {
  // Define Employee class if necessary
}
