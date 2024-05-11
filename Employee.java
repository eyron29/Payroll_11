package Project1;

public class Employee {
    String posCode,empName, statCode, depCode, empPos;
    //int posCode;
    double hourlyRate,hoursWorked, presentNum, regPay, otPay, netPay;
    //deduction;

    Employee(String empName, String posCode, String empPos,String depCode, String statCode, double hourlyRate, double hoursWorked, double presentNum,double regPay, double otPay, double netPay){
        this.empName = empName;
        this.posCode = posCode;
        this.empPos = empPos;
        this.depCode = depCode;
        this.statCode = statCode;
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
        this.presentNum = presentNum;
        this.regPay = regPay;
        this.otPay = otPay;
        this.netPay = netPay;
        //this.deduction = deduction;
    }
}
