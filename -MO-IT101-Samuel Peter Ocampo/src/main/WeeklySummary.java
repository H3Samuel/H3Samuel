package main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WeeklySummary {
    private String date;
    private int hoursWorked;
    private double grossWeeklySalary;
    private double netWeeklySalary;
    private final String employeeName;
    private final String timeIn;
    //private static final double SSS_CONTRIBUTION_RATE = 0.03;
    private static final double PHILHEALTH_CONTRIBUTION_RATE = 0.03;
    private static final double PAGIBIG_EMPLOYEE_CONTRIBUTION_RATE = 0.01;
    //private static final double PAGIBIG_EMPLOYER_CONTRIBUTION_RATE = 0.02;
    private static final double TAX_RATE_1 = 0.2;
    private static final double TAX_RATE_2 = 0.25;
    private static final double TAX_RATE_3 = 0.3;
    private static final double TAX_RATE_4 = 0.32;
    private static final double TAX_RATE_5 = 0.35;

    public WeeklySummary(String employeeName, String date, int hoursWorked, double grossWeeklySalary, double netWeeklySalary, String timeIn) {
        this.employeeName = employeeName;
        this.date = date;
        this.hoursWorked = hoursWorked;
        this.grossWeeklySalary = grossWeeklySalary;
        this.netWeeklySalary = netWeeklySalary;
        this.timeIn = timeIn; 
    }

    public String getDate() {
        return date;
    }

    public int getHoursWorked() {
        return hoursWorked;
    }

    public double getGrossWeeklySalary() {
        return grossWeeklySalary;
    }

    public double getNetWeeklySalary() {
        return netWeeklySalary;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public String getTimeIn() {
        return timeIn;
    }

    public double getSSSDeduction() {
        double grossSalary = getGrossWeeklySalary();
        return calculateSSSDeduction(grossSalary);
    }

    public static double calculateSSSDeduction(double monthlyBasicSalary) {
    if (monthlyBasicSalary < 3250) {
        return 135.00;
    } else if (monthlyBasicSalary <= 3750) {
        return 157.50;
    } else if (monthlyBasicSalary <= 4250) {
        return 180.00;
    } else if (monthlyBasicSalary <= 4750) {
        return 202.50;
    } else if (monthlyBasicSalary <= 5250) {
        return 225.00;
    } else if (monthlyBasicSalary <= 5750) {
        return 247.50;
    } else if (monthlyBasicSalary <= 6250) {
        return 270.00;
    } else if (monthlyBasicSalary <= 6750) {
        return 292.50;
    } else if (monthlyBasicSalary <= 7250) {
        return 315.00;
    } else if (monthlyBasicSalary <= 7750) {
        return 337.50;
    } else if (monthlyBasicSalary <= 8250) {
        return 360.00;
    } else if (monthlyBasicSalary <= 8750) {
        return 382.50;
    } else if (monthlyBasicSalary <= 9250) {
        return 405.00;
    } else if (monthlyBasicSalary <= 9750) {
        return 427.50;
    } else if (monthlyBasicSalary <= 10250) {
        return 450.00;
    } else if (monthlyBasicSalary <= 10750) {
        return 472.50;
    } else if (monthlyBasicSalary <= 11250) {
        return 495.00;
    } else if (monthlyBasicSalary <= 11750) {
        return 517.50;
    } else if (monthlyBasicSalary <= 12250) {
        return 540.00;
    } else if (monthlyBasicSalary <= 12750) {
        return 562.50;
    } else if (monthlyBasicSalary <= 13250) {
        return 585.00;
    } else if (monthlyBasicSalary <= 13750) {
        return 607.50;
    } else if (monthlyBasicSalary <= 14250) {
        return 630.00;
    } else if (monthlyBasicSalary <= 14750) {
        return 652.50;
    } else if (monthlyBasicSalary <= 15250) {
        return 675.00;
    } else if (monthlyBasicSalary <= 15750) {
        return 697.50;
    } else if (monthlyBasicSalary <= 16250) {
        return 720.00;
    } else if (monthlyBasicSalary <= 16750) {
        return 742.50;
    } else if (monthlyBasicSalary <= 17250) {
        return 765.00;
    } else if (monthlyBasicSalary <= 17750) {
        return 787.50;
    } else if (monthlyBasicSalary <= 18250) {
        return 810.00;
    } else if (monthlyBasicSalary <= 18750) {
        return 832.50;
    } else if (monthlyBasicSalary <= 19250) {
        return 855.00;
    } else if (monthlyBasicSalary <= 19750) {
        return 877.50;
    } else if (monthlyBasicSalary <= 20250) {
        return 900.00;
    } else if (monthlyBasicSalary <= 20750) {
        return 922.50;
    } else if (monthlyBasicSalary <= 21250) {
        return 945.00;
    } else if (monthlyBasicSalary <= 21750) {
        return 967.50;
    } else if (monthlyBasicSalary <= 22250) {
        return 990.00;
    } else if (monthlyBasicSalary <= 22750) {
        return 1012.50;
    } else if (monthlyBasicSalary <= 23250) {
        return 1035.00;
    } else if (monthlyBasicSalary <= 23750) {
        return 1057.50;
    } else if (monthlyBasicSalary <= 24250) {
        return 1080.00;
    } else if (monthlyBasicSalary <= 24750) {
        return 1102.50;
    } else if (monthlyBasicSalary <= 25250) {
        return 1125.00;
    } else {
        return 1125.00; 
    }
}

    public double getPhilHealthDeduction() {
        double monthlyBasicSalary = getGrossWeeklySalary() * 4; 

        if (monthlyBasicSalary <= 10000) {
            return 300.00;
        } else if (monthlyBasicSalary <= 59999.99) {
            return monthlyBasicSalary * PHILHEALTH_CONTRIBUTION_RATE;
        } else {
            return 1800.00; 
        }
    }

    public double getPagIBIGDeduction() {
        double monthlyBasicSalary = getGrossWeeklySalary() * 4; 
        double pagIBIGDeduction = monthlyBasicSalary * PAGIBIG_EMPLOYEE_CONTRIBUTION_RATE;
        return pagIBIGDeduction;
    }

    public double getWithholdingTax() {
        double monthlyRate = getGrossWeeklySalary() * 4; 

        if (monthlyRate <= 20832) {
            return 0.00;
        } else if (monthlyRate > 20832 && monthlyRate <= 33333) {
            return (monthlyRate - 20832) * TAX_RATE_1;
        } else if (monthlyRate > 33333 && monthlyRate <= 66667) {
            return 2500 + (monthlyRate - 33333) * TAX_RATE_2;
        } else if (monthlyRate > 66667 && monthlyRate <= 166667) {
            return 10833 + (monthlyRate - 66667) * TAX_RATE_3;
        } else if (monthlyRate > 166667 && monthlyRate <= 666667) {
            return 40833.33 + (monthlyRate - 166667) * TAX_RATE_4;
        } else if (monthlyRate > 666667) {
            return 200833.33 + (monthlyRate - 666667) * TAX_RATE_5;
        } else {
            return 0.00;
        }
    }

    @Override
    public String toString() {
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

    public int getLateHours() {
        // working hours start at 8:00 AM
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");

        try {
            Date expectedTimeIn = dateFormat.parse("08:00");
            Date actualTimeIn = dateFormat.parse(this.timeIn);
            long timeDifference = actualTimeIn.getTime() - expectedTimeIn.getTime();
            int lateHours = (int) (timeDifference / (60 * 60 * 1000));
            return Math.max(lateHours, 0);
        } catch (ParseException e) {
            return 0;
        }
    }

    public int getTotalWorkedMinusLate() {
        return this.hoursWorked - getLateHours();
    }

    public double getTotalDeductions() {
        double totalDeductions = 0.0;

        totalDeductions += getSSSDeduction();
        totalDeductions += getPhilHealthDeduction();
        totalDeductions += getPagIBIGDeduction();
        totalDeductions += getWithholdingTax();

        return totalDeductions;
    }
}
