package Project1;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class EmployeeList {
    static Scanner inp = new Scanner(System.in);

    static ArrayList<Employee> emp = new ArrayList<>();

    static ArrayList<String> fileList = new ArrayList<String>();
    static String posCode, depCode, empName, empPos;
    static double hoursWorked, regPay, otPay, netPay;

    static double hourlyRate, taxRate;
    static double regHours = 176.02;
    static char subCode;
    static String ans ="";

    static String posCode1;

    static String fileName = "", fileName1 = "";

    static int choice, option;

    static String remName;

    static int x;

    //index to remove
    static int i;

    static boolean isValidInput = false;

    static boolean isValidInput1 = false;

    static boolean isValidInput2 = false;

    static String statCode;

    static double presentNum;

    static double otHours;

    public static void main(String[] args) throws InvalidInputException{

        do{
            menu();
            System.out.print("Back to Main Menu? ");
            ans = inp.nextLine();
        }while(ans.equalsIgnoreCase("YES"));
        //members();

    }//main
    public static void menu(){
        System.out.println("\n------------------------------------------------------------------");
        System.out.println("•••••••••••••••••••EMPLOYEE PAYROLL SYSTEM ••••••••••••••••••••••••");
        System.out.println("------------------------------------------------------------------");
        System.out.println("******************************************************************");
        System.out.println("           Enter Choice from the Following Options:");
        System.out.println("******************************************************************");
        System.out.println("------------------------------------------------------------------");
        System.out.println("\t [1] - Add Employee Record");
        System.out.println("\t [2] - Display Employee Record");
        System.out.println("\t [3] - Export Record to a File");
        System.out.println("\t [4] - Check for Existing File/s");
        System.out.println("\t [5] - Remove Employee Record/s");
        System.out.println("------------------------------------------------------------------");

        try{
            System.out.print("Enter Choice: ");
            choice = inp.nextInt();
            if(choice <= 0  || choice > 5){
                throw new InvalidInputException("Invalid option");

            }
        }catch(InvalidInputException e){
            System.out.println("NOTE: Enter a valid option");
            System.out.println("------------------------------------------------------------------");
        }catch(InputMismatchException e){
            System.out.println("Input Mismatch Exception: Enter a Valid Number");
            System.out.println("------------------------------------------------------------------");
        }finally {
            inp.nextLine();
        }
        switch(choice){
            case 1:{
                do{
                    System.out.println("------------------------------------------------------------------");
                    addRecord();
                    inp.nextLine();
                    System.out.print("Add another employee? ");
                    ans = inp.nextLine();
                    //System.out.println("------------------------------------------------------------------");
                }while(ans.equalsIgnoreCase("YES"));
                break;
            }
            case 2:{
                printRecord();
                break;
            }
            case 3:{
                if(emp.isEmpty()){
                    System.out.println("NO EMPLOYEE RECORD FOUND...");
                    System.out.println("------------------------------------------------------------------");
                } else {
                    System.out.print("Enter a File Name: ");
                    fileName = inp.nextLine();
                    fileName1 = "C:/Users/Owner/Desktop/Payroll_Records/" + fileName + ".txt";
                    fileList.add(fileName);
                    writeFile(fileName1);
                    System.out.println("\n--FILE IS SUCCESSFULLY CREATED--");
                    System.out.println("\tFile Location: " + fileName1);
                    System.out.println("------------------------------------------------------------------");
                }//else
                break;
            }
            case 4:{
                checkFile();
                break;
            }
            case 5:{
                removeRecord();
                break;
            }
            default:{
                break;
            }
        }

    }//menu

    public static void addRecord(){

        while(!isValidInput){
            System.out.print("Employee Code (000X) : ");
            posCode = inp.nextLine();

            if(posCode.equals("011A") || posCode.equals("011B")
                    || posCode.equals("011C") || posCode.equals("011D")){
                isValidInput = true;
            }else{
                System.out.println("INVALID...");
                System.out.println();
            }
        }

        int j = posCode.length();
        posCode1 = posCode.substring(0,j-1);
        subCode = posCode.charAt(3);

        while(!isValidInput2){
            System.out.print("Status (SWD/SOD) : ");
            statCode = inp.nextLine();

            if(statCode.equals("SWD") || statCode.equals("SOD")){
                isValidInput2 = true;
            }else{
                System.out.println("INVALID...");
                System.out.println();
            }
        }

        System.out.print("Employee Name    : ");
        empName = inp.nextLine();

        while(!isValidInput1){
            System.out.print("Hours Worked     : ");
            hoursWorked = inp.nextDouble();

            if(!(hoursWorked > 0.0)){
                System.out.println("INVALID...");
                System.out.println();
                inp.nextLine();
            }else{
                isValidInput1 = true;
            }
        }

        //Di yata accurate if ganito?
        presentNum = hoursWorked / 8.00;

        switch(subCode){
            case 'A':{
                hourlyRate = 290.00;
                empPos = "Senior Programmer";
                break;
            }
            case 'B': {
                hourlyRate = 148.00;
                empPos = "Junior Programmer";
                break;
            }
            case 'C': {
                hourlyRate = 159.00;
                empPos = "System Analyst";
                break;
            }
            case 'D': {
                hourlyRate = 165.00;
                empPos = "Data Analyst";
                break;
            }
            default:{
                break;
            }
        }
        depCode = "Management Information Systems";
        computeSalary(hoursWorked, hourlyRate);

        isValidInput = false;
        isValidInput1 = false;
        isValidInput2 = false;

        emp.add(new Employee(empName,posCode,empPos, depCode,statCode,hourlyRate,hoursWorked, presentNum, regPay,otPay,netPay));
        System.out.println("------------------------------------------------------------------");

    }//addRecord

    public static double computeSalary(double hoursWorked, double hourlyRate){
        //try lang, di pa accurate 
        if(hoursWorked > regHours){
            //with Overtime Pay (SWD/SOD)
            if(statCode.equals("SWD")){ //SWD: Single With Dependent
                regPay = hoursWorked * hourlyRate;
                otHours = hoursWorked - regHours;
                otPay =  otHours * hourlyRate * 1.5;
                taxRate = regPay * 0.05;
                netPay = (regPay + otPay) - taxRate;
            }
        }
        return netPay;
    }//computeSalary

    public static void printRecord(){
        if(emp.isEmpty()){
            System.out.println("NO EMPLOYEE RECORD FOUND...");
            System.out.println("------------------------------------------------------------------");
        }
        else{
            System.out.println("------------------------------------------------------------------");
            System.out.println("                   EMPLOYEE PAYROLL RECORD                        ");
            System.out.println("------------------------------------------------------------------");
            for(x =0; x<emp.size(); x++){
                System.out.println("EMPLOYEE NAME         : " + emp.get(x).empName);
                System.out.println("POSITION CODE         : " + emp.get(x).posCode);
                System.out.println("POSITION              : " + emp.get(x).empPos);
                System.out.println("DEPARTMENT            : " + emp.get(x).depCode);
                System.out.println("STATUS                : " + emp.get(x).statCode);
                System.out.println("PAY PER HOUR          : " + emp.get(x).hourlyRate);
                System.out.println("HOURS WORKED          : " + emp.get(x).hoursWorked);
                System.out.println("PRESENT DAYS          : " + emp.get(x).presentNum);
                System.out.println("REGULAR PAY           : " + emp.get(x).regPay);
                System.out.println("OVERTIME PAY          : " + emp.get(x).otPay);
                System.out.println("TAX/DEDUCTIONS        :");
                System.out.println("NET PAY               : " + emp.get(x).netPay);
                System.out.println("------------------------------------------------------------------");
            }
            //System.out.println("------------------------------------------------------------------");
        }
    }//printRecord

    public static String writeFile(String str){
        //C:\\Users\\Owner\\Desktop\\Payroll_Records\\fileName.txt
        //C:/Users/Owner/Desktop/Payroll_Records/fileName.txt

        try{
            FileWriter writer = new FileWriter(str);
            writer.write("------------------------------------------------------------------");
            writer.write("\n                   EMPLOYEE PAYROLL RECORD                        ");
            writer.write("\n------------------------------------------------------------------");
            for(x=0; x<emp.size(); x++){
                writer.write("\nEMPLOYEE NAME         : " + emp.get(x).empName);
                writer.write("\nPOSITION CODE         : " + emp.get(x).posCode);
                writer.write("\nPOSITION              : " + emp.get(x).empPos);
                writer.write("\nDEPARTMENT            : " + emp.get(x).depCode);
                writer.write("\nPOSITION              : " + emp.get(x).empPos);
                writer.write("\nPAY PER HOUR          : " + emp.get(x).hourlyRate);
                writer.write("\nHOURS WORKED          : " + emp.get(x).hoursWorked);
                writer.write("\nPRESENT DAYS          : " + emp.get(x).presentNum);
                writer.write("\nREGULAR PAY           : " + emp.get(x).regPay);
                writer.write("\nOVERTIME PAY          : " + emp.get(x).otPay);
                writer.write("\nTAX/DEDUCTIONS        : ");
                writer.write("\nNET PAY               : " + emp.get(x).netPay);
                writer.write("\n------------------------------------------------------------------");
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return str;
    }//writeFile

    public static void checkFile(){
        /*
        05/11/24
        File Options: Display Content or Delete the recent File created
         */

        if(fileList.isEmpty()){
            System.out.println("------------------------------------------------------------------");
            System.out.println("ERROR: LIST IS EMPTY...");
            System.out.println("NOTE: Create a new file first");
            System.out.println("HINT: Select Option # 3");
        }
        else {
            System.out.println("------------------------------------------------------------------");
            System.out.println("FILE LIST RECORD:");
            for (x = 0; x < fileList.size(); x++) {
                System.out.println("\t" + (x + 1) + ". " + fileList.get(x));
            }
        }
        System.out.println("------------------------------------------------------------------");

        //since the file is already created from createFile() method
        //this does not create a new file, it just checks if the fileName exist already
        File file = new File(fileName1);

        //check if the file exist
        if(file.exists()){
            //return the path of file
            System.out.println("--MOST RECENT FILE--");
            System.out.println("\tFile Location: " + file.getAbsolutePath());
            System.out.println("------------------------------------------------------------------");
            fileOption();
        }
        else {
            System.out.println("No File Found...");
            System.out.println("------------------------------------------------------------------");
        }

    }//checkFile

    public static void removeRecord(){
        //Status: Not yet finish

        if(emp.isEmpty()){
            System.out.println("No Employee Record Found...");
            System.out.println("------------------------------------------------------------------");
        } else {
            do{
                System.out.println("\n------------------------------------------------------------------");
                System.out.println("                  Existing Employees Record                        ");
                System.out.println("------------------------------------------------------------------");
                System.out.println("\tNUMBER   \tNAME \t         POSITION");
                for(x=0; x<emp.size(); x++){
                    System.out.println("\t" + (x+1) + ". \t" + emp.get(x).empName
                            + "\t:     " + emp.get(x).empPos);
                }

                System.out.print("Employee to Remove: ");
                remName = inp.nextLine();

                if(remName.isEmpty()){
                    System.out.println("Cannot be empty..");
                } else {
                    for(x = 0; x < emp.size(); x++){
                        if(remName.equals(emp.get(x).empName)){
                            i = x;
                        }
                    }
                    emp.remove(i);
                }

                inp.nextLine();
                System.out.print("Try again? ");
                ans = inp.nextLine();

            }while(ans.equalsIgnoreCase("YES"));
        }
    }//removeRecord

    public static void fileOption(){
        //Requirement: after deleteFile(), exit na dapat
        //Reason: after deleteFIle(), di na available yung readFile(), exception if nag-select pa

        do{
            System.out.println("\n------------------------------------------------------------------");
            System.out.println("             Select from the Following Options                      ");
            System.out.println("------------------------------------------------------------------");
            System.out.println("\t [1] - Display Content");
            System.out.println("\t [2] - Delete File");
            System.out.println("------------------------------------------------------------------");
            System.out.print("Enter Option: ");
            option = inp.nextInt();
            inp.nextLine();

            switch(option){
                case 1:{
                    readFile();
                    break;
                }
                case 2:{
                    deleteFile();
                    break;
                }
                default:{
                    break;
                }
            }

            System.out.print("Try Again? ");
            ans = inp.nextLine();
        }while(ans.equalsIgnoreCase("YES"));

    }

    public static void readFile(){

        //Edit: Yung by scanner yung reader
        //Scanner read = new Scanner(fileHere);

        try {
            FileReader reader = new FileReader(fileName1);
            int data = reader.read();

            while(data != -1 ){
                System.out.print((char) data);
                data = reader.read();
            }
            reader.close();
            System.out.println();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteFile(){

        File file = new File(fileName1);

        if(file.exists()){
            file.delete();
        }
    }//deleteFile


}//class
