package TASK1;
// Employee.java
class Employee {
    private int id;
    private String name;
    private double salary;
    private String department;
    private String position;
    
    // Constructor
    public Employee(int id, String name, double salary, String department, String position) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.department = department;
        this.position = position;
    }
    
    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public double getSalary() { return salary; }
    public String getDepartment() { return department; }
    public String getPosition() { return position; }
    
    // Setters
    public void setName(String name) { this.name = name; }
    public void setSalary(double salary) { this.salary = salary; }
    public void setDepartment(String department) { this.department = department; }
    public void setPosition(String position) { this.position = position; }
    
    // Override toString for easy display
    @Override
    public String toString() {
        return String.format("ID: %d | Name: %s | Salary: $%.2f | Department: %s | Position: %s", 
                           id, name, salary, department, position);
    }
}

