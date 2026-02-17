package lab2;

public class PartTime extends Employee {
	
	 private double hours;
	 private double rate;
	 
	 public PartTime() { }

	 public PartTime(String id, String name, String address, String phone,
	                    long sin, String dob, String dept, double rate, double hours) {
	        super(id, name, address, phone, sin, dob, dept);
	        this.hours = hours;
	        this.rate = rate;
	 }
	    
	 public double getHours() { return hours; }
	 public void setHours(double hours) { this.hours = hours; }
	 
	 public double getRate() { return rate; }
	 public void setRate(double rate) { this.rate = rate; }
	 
	 @Override
	    public double getPay() {
	        return rate * hours;
	 }
	 
	 @Override
	    public String toString() {
	        return "PartTime{" +
	                "name='" + getName() + '\'' +
	                ", weeklyPay=" + getPay() +
	                '}';
	 }

}
