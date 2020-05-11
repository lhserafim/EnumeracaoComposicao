package application;

import entities.Department;
import entities.HourContract;
import entities.Worker;
import entities.enums.WorkLevel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class Program {
    public static void main(String args[]) throws ParseException {
        // %s string ; %f flutuante ; %d inteiro ; %n quebra de linha; %.2f formatacao float
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.print("Enter department's name:");
        String departmentName = sc.nextLine();
        System.out.println("Enter worker data:");
        System.out.print("Name:");
        String workerName = sc.nextLine();
        System.out.print("Level:");
        String workerLevel = sc.nextLine();
        System.out.print("Base salary:");
        double baseSalary = sc.nextDouble();

        // Instanciar o trabalhador
        // Instanciar classe enum recebendo string WorkLevel.valueOf(workerLevel)
        // Instanciar o Departamento como Departament
        Worker worker = new Worker(workerName, WorkLevel.valueOf(workerLevel), baseSalary, new Department(departmentName));

        System.out.print("How many contracts to this worker?");
        int n = sc.nextInt();
        for(int i=1;i<=n;i++) {
            System.out.printf("Enter contract #%d data:",i);
            System.out.print("Date (DD/MM/YYYY)");
            // Utilizar o SimpleDateFormat, para formatar a data
            // Parse requer exceção
            Date contractDate = sdf.parse(sc.next());
            System.out.print("Value per hour:");
            double valuePerHour = sc.nextDouble();
            System.out.print("Duration (hours):");
            int hours = sc.nextInt();
            // Instanciar horas
            HourContract contract = new HourContract(contractDate, valuePerHour, hours);
            // Associar horas ao trabalhador. O worker já foi instanciado anteriormente
            worker.addContract(contract);
        }
        System.out.print("Enter month and year to calculate income (MM/YYYY):");
        // Usando o "SUBSTRING"
        String monthAndYear = sc.next();
        int month = Integer.parseInt(monthAndYear.substring(0,2)); // posicao 0 a 2
        int year = Integer.parseInt(monthAndYear.substring(3)); //de 3 até final
        System.out.print("Name: " + worker.getName());
        System.out.print("Department: " + worker.getDepartment().getName()); //Acessar o departamento pelo objeto worker. COMPOSIÇÃO DE OBJETOS
        System.out.print("Income for " + monthAndYear + ": " + String.format("%.2f",worker.income(month,year)));

        sc.close();
    }
}

