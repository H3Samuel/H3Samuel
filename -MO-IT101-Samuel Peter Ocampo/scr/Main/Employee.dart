class Employee {
  int employeeNumber;
  final String lastName;
  final String firstName;
  String birthday;
  String address;
  String phoneNumber;
  String sssNumber;
  String philhealthNumber;
  String tinNumber;
  String pagibigNumber;
  String status;
  String position;
  String immediateSupervisor;
  double basicSalary;
  double riceSubsidy;
  double phoneAllowance;
  double clothingAllowance;
  double grossSemiMonthlyRate;
  double hourlyRate;

  // Constructor
  Employee(
    this.employeeNumber,
    this.lastName,
    this.firstName,
    this.birthday,
    this.address,
    this.phoneNumber,
    this.sssNumber,
    this.philhealthNumber,
    this.tinNumber,
    this.pagibigNumber,
    this.status,
    this.position,
    this.immediateSupervisor,
    this.basicSalary,
    this.riceSubsidy,
    this.phoneAllowance,
    this.clothingAllowance,
    this.grossSemiMonthlyRate,
    this.hourlyRate,
  );

  // Getters for calculated values
  int get dailyHours => 8;
  int get workdaysPerWeek => 5;
  int get weeklyWorkdays => dailyHours * workdaysPerWeek;
  int get weeklyHoursWorked => weeklyWorkdays;

  // Override toString() for better printing of object details
  @override
  String toString() {
    return '''
      employeeNumber: $employeeNumber,
      lastName: $lastName,
      firstName: $firstName,
      birthday: $birthday,
      address: $address,
      phoneNumber: $phoneNumber,
      sssNumber: $sssNumber,
      philhealthNumber: $philhealthNumber,
      tinNumber: $tinNumber,
      pagibigNumber: $pagibigNumber,
      status: $status,
      position: $position,
      immediateSupervisor: $immediateSupervisor,
      basicSalary: $basicSalary,
      riceSubsidy: $riceSubsidy,
      phoneAllowance: $phoneAllowance,
      clothingAllowance: $clothingAllowance,
      grossSemiMonthlyRate: $grossSemiMonthlyRate,
      hourlyRate: $hourlyRate,
      Number of Hours worked: $weeklyWorkdays,
      Gross weekly Salary: ${weeklyHoursWorked * hourlyRate},
    ''';
  }
}
