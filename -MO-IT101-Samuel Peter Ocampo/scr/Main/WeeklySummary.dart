import 'dart:core';
import 'dart:math';
import 'dart:convert';

class WeeklySummary {
  late String date;
  late int hoursWorked;
  late double grossWeeklySalary;
  late double netWeeklySalary;
  final String employeeName;
  final String timeIn;
  static const double SSS_CONTRIBUTION_RATE = 0.03;
  static const double PHILHEALTH_CONTRIBUTION_RATE = 0.03;
  static const double PAGIBIG_EMPLOYEE_CONTRIBUTION_RATE = 0.01;
  static const double PAGIBIG_EMPLOYER_CONTRIBUTION_RATE = 0.02;
  static const double TAX_RATE_1 = 0.2;
  static const double TAX_RATE_2 = 0.25;
  static const double TAX_RATE_3 = 0.3;
  static const double TAX_RATE_4 = 0.32;
  static const double TAX_RATE_5 = 0.35;

  WeeklySummary(this.employeeName, this.date, this.hoursWorked, this.grossWeeklySalary, this.netWeeklySalary, this.timeIn);

  String getDate() {
    return date;
  }

  int getHoursWorked() {
    return hoursWorked;
  }

  double getGrossWeeklySalary() {
    return grossWeeklySalary;
  }

  double getNetWeeklySalary() {
    return netWeeklySalary;
  }

  String getEmployeeName() {
    return employeeName;
  }

  String getTimeIn() {
    return timeIn;
  }

  double getSSSDeduction() {
    double monthlyBasicSalary = getGrossWeeklySalary() * 4;

    if (monthlyBasicSalary < 3250) {
      return 135.00;
    } else if (monthlyBasicSalary <= 25250) {
      return 1125.00;
    } else {
      return 1125.00;
    }
  }

  double getPhilHealthDeduction() {
    double monthlyBasicSalary = getGrossWeeklySalary() * 4;

    if (monthlyBasicSalary <= 10000) {
      return 300.00;
    } else if (monthlyBasicSalary <= 59999.99) {
      return monthlyBasicSalary * PHILHEALTH_CONTRIBUTION_RATE;
    } else {
      return 1800.00;
    }
  }

  double getPagIBIGDeduction() {
    double monthlyBasicSalary = getGrossWeeklySalary() * 4;
    double pagIBIGDeduction = monthlyBasicSalary * PAGIBIG_EMPLOYEE_CONTRIBUTION_RATE;
    return pagIBIGDeduction;
  }

  double getWithholdingTax() {
    double monthlyRate = getGrossWeeklySalary() * 4;

    if (monthlyRate <= 20832) {
      return 0.00;
    } else if (monthlyRate <= 33333) {
      return (monthlyRate - 20832) * TAX_RATE_1;
    } else if (monthlyRate <= 66667) {
      return 2500 + (monthlyRate - 33333) * TAX_RATE_2;
    } else if (monthlyRate <= 166667) {
      return 10833 + (monthlyRate - 66667) * TAX_RATE_3;
    } else if (monthlyRate <= 666667) {
      return 40833.33 + (monthlyRate - 166667) * TAX_RATE_4;
    } else {
      return 200833.33 + (monthlyRate - 666667) * TAX_RATE_5;
    }
  }

  @override
  String toString() {
    return "WeeklySummary{" +
        "employeeName='" + employeeName + '\'' +
        ", date='" + date + '\'' +
        ", hoursWorked=" + hoursWorked +
        ", grossWeeklySalary=" + grossWeeklySalary +
        ", netWeeklySalary=" + netWeeklySalary +
        ", timeIn='" + timeIn + '\'' +
        ", SSS Deduction=" + getSSSDeduction() +
        ", PhilHealth Deduction=" + getPhilHealthDeduction() +
        ", Withholding Tax=" + getWithholdingTax() +
        ", Total Deductions=" + getTotalDeductions() +
        '}';
  }

  int getLateHours() {
    // working hours start at 8:00 AM
    final dateFormat = DateFormat("HH:mm");

    try {
      final expectedTimeIn = dateFormat.parse("08:00");
      final actualTimeIn = dateFormat.parse(this.timeIn);
      final timeDifference = actualTimeIn.millisecondsSinceEpoch - expectedTimeIn.millisecondsSinceEpoch;
      final lateHours = (timeDifference / (60 * 60 * 1000)).round();
      return max(lateHours, 0);
    } catch (e) {
      return 0;
    }
  }

  int getTotalWorkedMinusLate() {
    return this.hoursWorked - getLateHours();
  }

  double getTotalDeductions() {
    double totalDeductions = 0.0;

    totalDeductions += getSSSDeduction();
    totalDeductions += getPhilHealthDeduction();
    totalDeductions += getPagIBIGDeduction();
    totalDeductions += getWithholdingTax();

    return totalDeductions;
  }
}
