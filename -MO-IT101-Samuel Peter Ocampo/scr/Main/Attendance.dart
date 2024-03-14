class Attendance {
  int employeeNumber;
  final String lastName;
  final String firstName;
  String date;
  String timeIn;
  String timeOut;

  Attendance(this.employeeNumber, this.lastName, this.firstName, this.date, this.timeIn, this.timeOut);

  @override
  String toString() {
    return '''
      employeeNumber: $employeeNumber,
      lastName: $lastName,
      firstName: $firstName,
      date: $date,
      timeIn: $timeIn,
      timeOut: $timeOut,
    ''';
  }

  String getTimeIn() {
    return timeIn;
  }

  String getTimeOut() {
    return timeOut;
  }

  String getDate() {
    return date;
  }

  String getEmployeeName() {
    return '$firstName $lastName';
  }
}
