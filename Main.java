package lab2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) {
        List<Employee> employees = readEmployeesFromRes("res/employees.txt");

        if (employees.isEmpty()) {
            System.out.println("No employees loaded. Check res/employees.txt path and file contents.");
            return;
        }
        
        double avgPay = averageWeeklyPay(employees);
        String highestWagesName = highestPaidWagesEmployee(employees);
        String lowestSalariedName = lowestPaidSalariedEmployee(employees);

        double pctSalaried = percentageOfType(employees, Salaried.class);
        double pctWages = percentageOfType(employees, Wages.class);
        double pctPartTime = percentageOfType(employees, PartTime.class);

        System.out.println("The average pay for all employees is: " + avgPay);
        System.out.println("The Wages employee with the highest pay is: " + highestWagesName);
        System.out.println("The Salaried employee with the lowest pay is: " + lowestSalariedName);
        System.out.println("Percentage of Salaried employees is: " + pctSalaried + "%");
        System.out.println("Percentage of Wages employees is: " + pctWages + "%");
        System.out.println("Percentage of Part Time employees is: " + pctPartTime + "%");
    }
	
	 // reads the file and creates an objects

    private static List<Employee> readEmployeesFromRes(String relativePath) {
        List<Employee> employees = new ArrayList<>();

        File file = new File(relativePath);
        if (!file.exists()) {
            System.out.println("File not found: " + file.getAbsolutePath());
            return employees;
        }
        
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                // SPLIT BY COLON (:) — this matches your employees.txt
                String[] parts = line.split("\\s*:\\s*");

                if (parts.length < 8) continue;

                String id = parts[0].trim();
                String name = parts[1].trim();
                String address = parts[2].trim();
                String phone = parts[3].trim();
                long sin = Long.parseLong(parts[4].trim());
                String dob = parts[5].trim();
                String dept = parts[6].trim();

       char firstDigit = id.charAt(0);

       if (firstDigit >= '0' && firstDigit <= '4') {
    	   
           double salary = Double.parseDouble(parts[7]);
           employees.add(new Salaried(id, name, address, phone, sin, dob, dept, salary));
       } else if (firstDigit >= '5' && firstDigit <= '7') {
           
    	   if (parts.length < 9) continue;
           double rate = Double.parseDouble(parts[7]);
           double hours = Double.parseDouble(parts[8]);
           employees.add(new Wages(id, name, address, phone, sin, dob, dept, rate, hours));
       } else if (firstDigit >= '8' && firstDigit <= '9') {
           
           if (parts.length < 9) continue;
           double rate = Double.parseDouble(parts[7]);
           double hours = Double.parseDouble(parts[8]);
           employees.add(new PartTime(id, name, address, phone, sin, dob, dept, rate, hours));
       }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error reading employees file: " + e.getMessage());
        }

        return employees;
    }

    private static double averageWeeklyPay(List<Employee> employees) {
        double total = 0.0;
        for (Employee e : employees) {
            total += e.getPay();
        }
        return total / employees.size();
    }

    private static String highestPaidWagesEmployee(List<Employee> employees) {
        Wages best = null;
        for (Employee e : employees) {
            if (e instanceof Wages w) {
                if (best == null || w.getPay() > best.getPay()) {
                    best = w;
                }
            }
        }
        return (best == null) ? "N/A" : best.getName();
    }

    private static String lowestPaidSalariedEmployee(List<Employee> employees) {
        Salaried lowest = null;
        for (Employee e : employees) {
            if (e instanceof Salaried s) {
                if (lowest == null || s.getPay() < lowest.getPay()) {
                    lowest = s;
                }
            }
        }
        return (lowest == null) ? "N/A" : lowest.getName();
    }

    private static double percentageOfType(List<Employee> employees, Class<?> type) {
        if (employees.isEmpty()) return 0.0;

        int count = 0;
        for (Employee e : employees) {
            if (type.isInstance(e)) count++;
        }
        return (count * 100.0) / employees.size();
    }
}
