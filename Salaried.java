package lab2;

public class Salaried extends Employee {

    private double salary; // yearly salary from file

    public Salaried() { }

    public Salaried(String id, String name, String address, String phone,
                    long sin, String dob, String dept, double salary) {
        super(id, name, address, phone, sin, dob, dept);
        this.salary = salary;
    }

    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }

    @Override
    public double getPay() {
        return salary / 52.0; // weekly pay
    }

    @Override
    public String toString() {
        return "Salaried{" +
                "name='" + getName() + '\'' +
                ", weeklyPay=" + getPay() +
                '}';
    }
}
