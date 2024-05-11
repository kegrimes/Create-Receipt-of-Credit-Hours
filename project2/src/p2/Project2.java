// Katelyn Grimes
// COP 3330
// November 27, 2022

package p2;

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.ArrayList;

public class Project2 {
	
	static Scanner optionScan = new Scanner(System.in);
	static Scanner Scanner = new Scanner(System.in);
	static Scanner checkScan = new Scanner(System.in);
	
	private static boolean checkID(String id) {
		try {
			if(id.length() == 6) {
				char[] check = id.toCharArray();
				if(Character.isLetter(check[0])) {
					if(Character.isLetter(check[1])) {
						if(Character.isDigit(check[2])) {
							if(Character.isDigit(check[3])) {
								if(Character.isDigit(check[4])) {
									if(Character.isDigit(check[5])) {
										return true;
									}
								}
							}
						}
					}
				}
			}
			throw new IdException(id);
		}
		catch(Exception e) {
			System.out.print(e);
			return false;
		}

	}
	
	private static boolean checkRank(String rank) {
		try {
			if(rank.toLowerCase().equals("professor")) {
				return true;
			}
			if(rank.toLowerCase().equals("adjunct")) {
				return true;
			}
			throw new StringException(rank);
		}
		catch(Exception e){
			System.out.println(e);
			return false;
		}
	}
	
	private static boolean checkDepartment(String department) {
		try {
			if(department.toLowerCase().equals("engineering")) {
				return true;
			}
			if(department.toLowerCase().equals("mathematics")) {
				return true;
			}
			if(department.toLowerCase().equals("sciences")) {
				return true;
			}
			throw new StringException(department);
		}
		catch(Exception e) {
			System.out.println(e);
			return false;
		}
	}
	
	private static boolean checkStatus(String status) {
		try {
			if(status.toLowerCase().equals("p")) {
				return true;
			}
			if(status.toLowerCase().equals("f")) {
				return true;
			}
			throw new StringException(status);
		}
		catch(Exception e) {
			System.out.println(e);
			return false;
		}
	}
	
	private static double checkGPA(double gpa){
		String wrong = null;
		try {
			gpa = checkScan.nextDouble();
		}
		catch(InputMismatchException e) {
			wrong = checkScan.nextLine();
			System.out.printf("\t\t\"%s\" is invalid", wrong);
			return 0.00;
		}
		//safety net exception
		catch(Exception e) {
			wrong = checkScan.nextLine();
			System.out.printf("\t\t\"%s\" is invalid", wrong);
			return 0.00;
		}
		checkScan.nextLine();
		return gpa;
	}
	
	private static int checkHours(int hours) {
		String wrong = null;
		try {
			hours = checkScan.nextInt();
		}
		catch(InputMismatchException e) {
			wrong = checkScan.nextLine();
			System.out.printf("\t\t\"%s\" is invalid", wrong);
			return -1;
		}
		//safety net exception
		catch(Exception e) {
			wrong = checkScan.nextLine();
			System.out.printf("\t\t\"%s\" is invalid", wrong);
			return -1;
		}
		checkScan.nextLine();
		return hours;
	}
		
	public static void faculty(Personnel list) {
		String name;
		String id;
		String rank = null;
		String department = null;
		boolean valid = false;
		
		System.out.printf("\nEnter the faculty info:\n");
		System.out.printf("\tName of the faculty: ");
		name = optionScan.nextLine();
		
		do {
			System.out.printf("\n\tID: ");
			id = optionScan.nextLine();
			valid = checkID(id);
			
			try {
				for(int i = 0; i < list.count; i++) {
					if(list.getList()[i].getId().equals(id)) {
						valid = false;
						throw new Exception();
					}
				}
			}
			catch(Exception e) {
				System.out.print("\tID already exists...\n");
			}
		}while(valid == false);
		
		valid = false;
		do {
			System.out.printf("\n\tRank: ");
			rank = optionScan.nextLine();
			valid = checkRank(rank);
		}while(valid == false);
		
		valid = false;
		do {
			System.out.printf("\n\tDepartment: ");
			department = optionScan.nextLine();
			valid = checkDepartment(department);
		}while(valid == false);
		
		Faculty f = new Faculty(name, id, "f", department, rank);
		list.addFaculty(f);
		
		System.out.printf("\nFactualy added!\n\n");
		
	}
	
	public static void student(Personnel list) {
		String name;
		String id;
		double gpa = 0.00;
		int hours = -1;
		boolean valid = false;
		
		System.out.printf("\n\nEnter the student info: ");
		System.out.printf("\n\tName of Student: ");
		name = optionScan.nextLine();
		
		do {
			System.out.printf("\n\tID: ");
			id = optionScan.nextLine();
			valid = checkID(id);
			
			try {
				for(int i = 0; i < list.count; i++) {
					if(list.getList()[i].getId().equals(id)) {
						valid = false;
						throw new Exception();
					}
				}
			}
			catch(Exception e) {
				System.out.print("\tID already exists...\n");
			}
		}while(valid == false);
		
		do {
			System.out.printf("\n\tGpa: ");
			gpa = checkGPA(gpa);
		}while(gpa == 0.00);
		
		do {
			System.out.printf("\n\tCredit hours: ");
			hours = checkHours(hours);
		}while(hours == -1);
		
		Student s = new Student(name, id, "s", gpa, hours);
		list.addStudent(s);
		
		System.out.printf("\nStudent added!\n");
	}
	
	public static void printStudent(Personnel list) {
		String id;
		
		System.out.printf("\n\nEnter the Student's id: ");
		id = optionScan.nextLine();
		
		for(int i = 0; i < list.count; i++) {
			if(list.getList()[i].getType() == "s") {
				if(list.getList()[i].getId().equals(id)) {
					System.out.printf("Here is the tuition invoice for %s : ", list.getList()[i].getName());
					list.getList()[i].print();
					return;
				}
			}
		}
		System.out.printf("\nNo Student matched!\n\n");
	}
	
	public static void printFaculty(Personnel list) {
		String id;
		
		System.out.printf("\n\nEnter the Faculty's id: ");
		id = optionScan.nextLine();
		
		for(int i = 0; i < list.count; i++) {
			if(list.getList()[i].getType() == "f") {
				if(list.getList()[i].getId().equals(id)) {
					list.getList()[i].print();
					return;
				}
			}
		}
		System.out.printf("\nNo Faculty matched!\n\n");
	}
	
	public static void staffMember(Personnel list) {
		String name;
		String id;
		String department;
		String status;
		boolean valid = false;
		
		System.out.printf("\nEnter the staff member's info:\n");
		System.out.printf("\tName of the staff member: ");
		name = optionScan.nextLine();
		
		do {
			System.out.printf("\n\tEnter the id: ");
			id = optionScan.nextLine();
			valid = checkID(id);
			
			try {
				for(int i = 0; i < list.count; i++) {
					if(list.getList()[i].getId().equals(id)) {
						valid = false;
						throw new Exception();
					}
				}
			}
			catch(Exception e) {
				System.out.print("\tID already exists...\n");
			}
		}while(valid == false);
		
		valid = false;
		do {
			System.out.printf("\n\tDepartment: ");
			department = optionScan.nextLine();
			valid = checkDepartment(department);
		}while(valid == false);
		
		valid = false;
		do {
			System.out.printf("\n\tStatus, Enter P for Part Time, or Enter F for Full Time: ");
			status = optionScan.nextLine();
			valid = checkStatus(status);
		}while(valid == false);
		
		Staff sf = new Staff(name, id, "sf", department, status);
		list.addStaff(sf);
		
		System.out.printf("\n\nStaff member added!\n\n");
	}
	
	public static void printStaff(Personnel list) {
		String id;
		
		System.out.printf("\n\nEnter the Staff's id: ");
		id = optionScan.nextLine();
		
		for(int i = 0; i < list.count; i++) {
			if(list.getList()[i].getType() == "sf") {
				if(list.getList()[i].getId().equals(id)) {
					list.getList()[i].print();
					return;
				}
			}
		}
		System.out.printf("\nNo Staff member matched!\n\n");

	}

	public static void report(Personnel list) {
		String answer = null;
		int sort = 0;
		boolean valid = false;
		
		do {
			System.out.printf("\nWould you like to create the report?(Y/N): ");
			try {
				answer = Scanner.nextLine();
				if(answer.toLowerCase().equals("y")) {
					valid = true;
				}
				else if(answer.toLowerCase().equals("n")) {
					valid = true;
				}
				else {
					throw new Exception();
				}
			}
			catch(Exception e) {
				System.out.println("Invalid entry! Must be Y/N.");
			}
		}while(valid == false);
		
		valid = false;
		if(answer.toLowerCase().equals("y")) {
			do {
				try {
					System.out.printf("\nWould you like to sort your students by (1) gpa or (2) credit hours: ");
					sort = Scanner.nextInt();
					if(sort == 1) {
						valid = true;
					}
					else if(sort == 2) {
						valid = true;
					}
					else {
						throw new InputMismatchException();
					}
				}
				catch(InputMismatchException e) {
					System.out.println("Invalid entry! Must be 1 or 2.");
				}
				Scanner.nextLine();
			}while(valid == false);
		}
		
		if(answer.toLowerCase().equals("n")){
			System.out.println("\nGoodbye!");
		}
		else {
			ArrayList<Student> slist = new ArrayList<>();
			slist = sortStudents(list, slist, sort);
			if(sort == 1) {
				printReport(slist,list, sort);
				System.out.print("\nReport created and saved on your hard drive!\nGoodbye!");
			}
			if(sort == 2) {
				printReport(slist,list, sort);
				System.out.print("\nReport created and saved on your hard drive!\nGoodbye!");
			}
		}

	}
	
	public static ArrayList<Student> sortStudents(Personnel list, ArrayList<Student> slist, int sortOption) {
		for(int i = 0; i < list.count; i++) {
			if(list.getList()[i].getType() == "s") {
				slist.add((Student) list.getList()[i]);
			}
		}
		
		if(sortOption == 1) {
			Collections.sort(slist, new SortbyGPA());
		}
		if(sortOption == 2) {
			Collections.sort(slist, new SortbyHours());
		}
		
		return slist;
	}
	
	public static void printReport(ArrayList<Student> slist, Personnel list, int sortOption) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter("report.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		
		writer.print("\t\tReport created on ");
		writer.print(format.format(date));
		writer.print("\n\t    ********************************\n\n");
		writer.print("Faculty Members\n*********************\n");
		
		int fcount = 1;
		String department = null;
		String rank = null;
		//prints Faculty to report.txt
		for(int i = 0; i < list.count; i++) {
			if(list.getList()[i].getType() == "f") {
				writer.printf("\t%d. %s\n", fcount, list.getList()[i].getName());
				writer.printf("\tID: %s\n", list.getList()[i].getId());
				department = list.getList()[i].department();
				rank = list.getList()[i].extra();
				writer.printf("\t%s, %s\n", rank, department);
				fcount++;
			}
		}
		
		//prints Staff to report.txt
		writer.print("\n\nStaff Members\n*********************\n");
		int sfcount = 1;
		department = null;
		String status = null;
		for(int k = 0; k < list.count; k++) {
			if(list.getList()[k].getType() == "sf") {
				writer.printf("\t%d. %s\n", sfcount, list.getList()[k].getName());
				writer.printf("\tID: %s\n", list.getList()[k].getId());
				department = list.getList()[k].department();
				status = list.getList()[k].extra();
				if(status.toLowerCase().equals("f")) {
					status = "Full Time";
				}
				if(status.toLowerCase().equals("p")) {
					status = "Part Time";
				}
				writer.printf("\t%s, %s\n", department, status);
				sfcount++;
			}
		}
		
		//prints Students to report.txt
		int scount = 1;
		writer.print("\n\nStudents ");
		if(sortOption == 1) {
			writer.print("(Sorted by gpa)");
		}
		if(sortOption == 2) {
			writer.print("(Sorted by credit hours)");
		}
		writer.print("\n*********************\n");
		for(int j = 0; j < slist.size(); j++) {
			writer.printf("\t%d. %s\n", scount, slist.get(j).getName());
			writer.printf("\tID: %s\n", slist.get(j).getId());
			writer.printf("\tGpa: %.2f\n", slist.get(j).getGpa());
			writer.printf("\tCredit Hours: %d\n\n", slist.get(j).getHours());
			scount++;
		}
		
		writer.close();
	}
	
	public static void main(String [] args) {
		String option = "7";
		Scanner Scan = new Scanner(System.in);
		Personnel list = new Personnel();
		
		System.out.printf("\t\tWelcome to my Personnel Management Program\n\n\n");
		
		do {
			optionScan.reset();
			System.out.printf("Choose one of the options:\n\n");
			System.out.printf("1- Enter the information of a faculty\n");
			System.out.printf("2- Enter the information of a student\n");
			System.out.printf("3- Print tuition invoice for a student\n");
			System.out.printf("4- Print faculty information\n");
			System.out.printf("5- Enter the information of a staff member\n");
			System.out.printf("6- Print the information of a staff member\n");
			System.out.printf("7- Exit Program\n");
			System.out.printf("\tEnter your selection: ");
			option = Scan.nextLine();
			
			switch(option) {
			case "1":
				faculty(list);
				break;
			case "2":
				student(list);
				break;
			case "3":
				printStudent(list);
				break;
			case "4":
				printFaculty(list);
				break;
			case "5":
				staffMember(list);
				break;
			case "6":
				printStaff(list);
				break;
			case "7":
				report(list);
				return;
			default:
				System.out.printf("\n\nInvalid entry - please try again\n\n");
				continue;
			}
			
		}while(option != "7");
		
		Scan.close();
		optionScan.close();
		
	}
}

abstract class Person{
	private String name;
	private String id;
	private String type;
		
	//Setters/Getters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	//Constructor
	public Person(String name, String id, String type) {
		super();
		this.name = name;
		this.id = id;
		this.type = type;
	}
	
	//Default Constructor
	public Person() {
		this.name = "NO NAME";
		this.id = "NO ID";
		this.type = "n/a";
	}
	
	@Override 
	public String toString() {
		return name+"\t\t\t"+id+"\n\n";
	}
	
	public abstract void print();
	public abstract String department();
	public abstract String extra();
	
}

abstract class Employee extends Person{
	protected String department;
	
	public abstract String department();
	public abstract String extra();

	//Setters/Getters
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
	//Constructor
	public Employee(String name, String id, String type, String department) {
		super(name, id, type);
		this.department = department;
	}
	
	//Default Constructor
	public Employee() {
		this.department = "NO DEPARTMENT";
	}
	
	@Override
	public String toString() {
		return super.toString()+department+" Department, ";
	}
	
}

class Student extends Person{
	protected double gpa;
	protected int hours;
	
	//Setters/Getters
	public double getGpa() {
		return gpa;
	}
	public void setGpa(double gpa) {
		this.gpa = gpa;
	}
	public int getHours() {
		return hours;
	}
	public void setHours(int hours) {
		this.hours = hours;
	}
	
	//Constructor
	public Student(String name, String id, String type, double gpa, int hours) {
		super(name, id, type);
		this.gpa = gpa;
		this.hours = hours;
	}
	
	public double calculateTotal(double gpa, int hours) {
		double total = 0;
		double discount = 0;
				
		if(gpa >= 3.85) {
			//discount applied
			total = (hours*236.45)+52;
			discount = total*0.25;
			total = total-discount;
			return total;
		}
		else {
			//no discount
			total = (hours*236.45)+52;
			return total;
		}
	}
	
	public double discount(double gpa, int hours) {
		double discount = 0;
		
		if(gpa < 3.85) {
			return discount;
		}
		else {
			discount = ((hours*236.45)+52)*0.25;
			return discount;
		}
	}
	
	//toString
	@Override
	public String toString() {
		DecimalFormat df = new DecimalFormat("0.00");
		return super.toString()+"\n"+"Credit Hours:"+hours+" ($236.45/credit hour)"+"\nFees: $52\n\nTotal payment: $"+df.format(calculateTotal(gpa, hours))+"\t\t($"+df.format(discount(gpa, hours))+" discount applied)";
	}
	
	@Override
	public void print() {
		System.out.printf("\n----------------------------------------------------------------\n\n");
		System.out.printf(toString());
		System.out.printf("\n\n----------------------------------------------------------------\n\n");
	}
	
	@Override
	public String department() {
		String dummy = null;
		return dummy;
	}
	
	@Override
	public String extra() {
		String dummy = null;
		return dummy;
	}
	
}

class Faculty extends Employee{
	protected String rank;

	//Getters/Setters
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}

	//Constructor
	public Faculty(String name, String id, String type, String department, String rank) {
		super(name, id, type, department);
		this.rank = rank;
	}
	
	@Override
	public String toString() {
		return super.toString()+rank;
	}
	
	@Override
	public void print() {
		System.out.printf("\n----------------------------------------------------------------\n\n");
		System.out.printf(toString());
		System.out.printf("\n\n----------------------------------------------------------------\n\n");
	}
	
	@Override
	public String department() {
		return department;
	}
	
	@Override
	public String extra() {
		return rank;
	}
	
}

class Staff extends Employee{
	private String status;

	//Getters/Setters
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	//Constructor
	public Staff(String name, String id, String type, String department, String status) {
		super(name, id, type, department);
		this.status = status;
	}
	
	public String status(String status) {
		String full = "Full Time";
		String part = "Part Time";
		String wrong = "N/a";
		
		if(status.equals("f")) {
			return full;
		}
		if(status.equals("p")) {
			return part;
		}
		return wrong;
	}
	
	@Override
	public String toString() {
		return super.toString()+status(status);
	}
	
	@Override
	public void print() {
		System.out.printf("\n----------------------------------------------------------------\n\n");
		System.out.printf(toString());
		System.out.printf("\n\n----------------------------------------------------------------\n\n");
	}
	
	@Override
	public String department() {
		return department;
	}
	
	@Override
	public String extra() {
		return status;
	}
	
}

class Personnel{
	private Person[] list;
	public int count;
	
	//Getters/Setters
	public Person[] getList() {
		return list;
	}
	public void setList(Person[] list) {
		this.list = list;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	//Constructors
	public Personnel() {
		list = new Person [100];
		count = 0;
	}
	
	public void addStudent(Student s) {
		this.list[count] = s;
		count++;
	}
	
	public void addFaculty(Faculty f) {
		this.list[count] = f;
		count++;
	}
	
	public void addStaff(Staff sf) {
		this.list[count] = sf;
		count++;
	}
}

class SortbyGPA implements Comparator<Student>{
	public int compare(Student a, Student b) {
		if(a.getGpa() == b.getGpa()) {
			return 0;
		}
		if(a.getGpa() < b.getGpa()) {
			return 1;
		}
		return -1;
	}
}

class SortbyHours implements Comparator<Student>{
	public int compare(Student a, Student b) {
		if(a.getHours() == b.getHours()) {
			return 0;
		}
		if(a.getHours() < b.getHours()) {
			return 1;
		}
		return -1;
	}
}

class IdException extends Exception{
	//I had a warning that said I needed this 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	
	//Getters/Setters
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	//Constructor
	public IdException(String id) {
		super();
		this.id = id;
	}
	
	public String toString() {
		return "\tInvalid ID format. Must be LetterLetterDigitDigitDigitDigit\n";
	}
}

class StringException extends Exception{
	//I had a warning that said I needed this 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String string;

	//Getters/Setters
	public String getString() {
		return string;
	}
	public void setEntry(String string) {
		this.string = string;
	}
	
	//Constructor
	public StringException(String string) {
		super();
		this.string = string;
	}
	
	public String toString() {
		return "\t\t\""+ string +"\" is invalid";
	}
}

class DoubleException extends Exception{
	//I had a warning that said it needed this
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private double d;
	
	//Getters/Setters
	public double getD() {
		return d;
	}
	public void setD(double d) {
		this.d = d;
	}
	
	//Constructor
	public DoubleException(double d) {
		super();
		this.d = d;
	}
	
	public String toString() {
		return "\t\t\""+d+"\" is invalid";
	}
}

