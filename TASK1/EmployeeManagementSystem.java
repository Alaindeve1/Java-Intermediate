package TASK1;

// EmployeeManagementSystem.java
import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeManagementSystem {
    private ArrayList<Employee> employees;
    private Scanner scanner;
    private int nextId;
    
    public EmployeeManagementSystem() {
        employees = new ArrayList<>();
        scanner = new Scanner(System.in);
        nextId = 1;
    }
    
    // CREATE - Add new employee
    public void addEmployee() {
        System.out.println("\n=== ADD NEW EMPLOYEE ===");
        
        System.out.print("Enter employee name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter salary: $");
        double salary = scanner.nextDouble();
        scanner.nextLine(); // consume newline
        
        System.out.print("Enter department: ");
        String department = scanner.nextLine();
        
        System.out.print("Enter position: ");
        String position = scanner.nextLine();
        
        Employee newEmployee = new Employee(nextId++, name, salary, department, position);
        employees.add(newEmployee);
        
        System.out.println("✅ Employee added successfully!");
        System.out.println("Employee Details: " + newEmployee);
    }
    
    // READ - View all employees
    public void viewAllEmployees() {
        System.out.println("\n=== ALL EMPLOYEES ===");
        
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }
        
        System.out.println("Total Employees: " + employees.size());
        System.out.println("-".repeat(80));
        
        for (Employee emp : employees) {
            System.out.println(emp);
        }
    }
    
    // READ - View specific employee by ID
    public void viewEmployeeById() {
        System.out.print("Enter employee ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline
        
        Employee employee = findEmployeeById(id);
        if (employee != null) {
            System.out.println("\n=== EMPLOYEE DETAILS ===");
            System.out.println(employee);
        } else {
            System.out.println("❌ Employee with ID " + id + " not found.");
        }
    }
    
    // UPDATE - Modify employee details
    public void updateEmployee() {
        System.out.print("Enter employee ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline
        
        Employee employee = findEmployeeById(id);
        if (employee == null) {
            System.out.println("❌ Employee with ID " + id + " not found.");
            return;
        }
        
        System.out.println("\n=== UPDATE EMPLOYEE ===");
        System.out.println("Current Details: " + employee);
        System.out.println("\nWhat would you like to update?");
        System.out.println("1. Name");
        System.out.println("2. Salary");
        System.out.println("3. Department");
        System.out.println("4. Position");
        System.out.println("5. Update All");
        
        System.out.print("Choose option (1-5): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline
        
        switch (choice) {
            case 1:
                System.out.print("Enter new name: ");
                employee.setName(scanner.nextLine());
                break;
            case 2:
                System.out.print("Enter new salary: $");
                employee.setSalary(scanner.nextDouble());
                scanner.nextLine();
                break;
            case 3:
                System.out.print("Enter new department: ");
                employee.setDepartment(scanner.nextLine());
                break;
            case 4:
                System.out.print("Enter new position: ");
                employee.setPosition(scanner.nextLine());
                break;
            case 5:
                System.out.print("Enter new name: ");
                employee.setName(scanner.nextLine());
                System.out.print("Enter new salary: $");
                employee.setSalary(scanner.nextDouble());
                scanner.nextLine();
                System.out.print("Enter new department: ");
                employee.setDepartment(scanner.nextLine());
                System.out.print("Enter new position: ");
                employee.setPosition(scanner.nextLine());
                break;
            default:
                System.out.println("❌ Invalid choice.");
                return;
        }
        
        System.out.println("✅ Employee updated successfully!");
        System.out.println("Updated Details: " + employee);
    }
    
    // DELETE - Remove employee
    public void deleteEmployee() {
        System.out.print("Enter employee ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline
        
        Employee employee = findEmployeeById(id);
        if (employee == null) {
            System.out.println("❌ Employee with ID " + id + " not found.");
            return;
        }
        
        System.out.println("Employee to delete: " + employee);
        System.out.print("Are you sure you want to delete this employee? (y/n): ");
        String confirmation = scanner.nextLine().toLowerCase();
        
        if (confirmation.equals("y") || confirmation.equals("yes")) {
            employees.remove(employee);
            System.out.println("✅ Employee deleted successfully!");
        } else {
            System.out.println("❌ Deletion cancelled.");
        }
    }
    
    // Helper method to find employee by ID
    private Employee findEmployeeById(int id) {
        for (Employee emp : employees) {
            if (emp.getId() == id) {
                return emp;
            }
        }
        return null;
    }
    
    // Display menu
    private void displayMenu() {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("    EMPLOYEE MANAGEMENT SYSTEM");
        System.out.println("=".repeat(40));
        System.out.println("1. Add Employee (CREATE)");
        System.out.println("2. View All Employees (READ)");
        System.out.println("3. View Employee by ID (READ)");
        System.out.println("4. Update Employee (UPDATE)");
        System.out.println("5. Delete Employee (DELETE)");
        System.out.println("6. Exit");
        System.out.println("=".repeat(40));
        System.out.print("Choose an option (1-6): ");
    }
    
    // Main program loop
    public void run() {
        System.out.println("🎉 Welcome to Employee Management System!");
        
        while (true) {
            displayMenu();
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            
            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    viewAllEmployees();
                    break;
                case 3:
                    viewEmployeeById();
                    break;
                case 4:
                    updateEmployee();
                    break;
                case 5:
                    deleteEmployee();
                    break;
                case 6:
                    System.out.println("👋 Thank you for using Employee Management System!");
                    return;
                default:
                    System.out.println("❌ Invalid choice. Please try again.");
            }
            
            // Pause before showing menu again
            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();
        }
    }
    
    // Main method
    public static void main(String[] args) {
        EmployeeManagementSystem ems = new EmployeeManagementSystem();
        ems.run();
    }
}