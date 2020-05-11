package entities;

import entities.enums.WorkLevel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Worker {
    private String name;
    private WorkLevel level;
    private Double baseSalary;

    // IMPORTANTE! Colocar associações
    // COMPOSIÇÃO DE OBJETOS
    // Worker tem 1 Department
    private Department department;
    // Worker tem X contratos. Criar como lista
    private List<HourContract> contracts = new ArrayList<>(); // necessário inicializar a lista

    public Worker() {
    }

    // Ao criar o construtor, criar SEM os atributos do tipo LISTA
    public Worker(String name, WorkLevel level, Double baseSalary, Department department) {
        this.name = name;
        this.level = level;
        this.baseSalary = baseSalary;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WorkLevel getLevel() {
        return level;
    }

    public void setLevel(WorkLevel level) {
        this.level = level;
    }

    public Double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(Double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<HourContract> getContracts() {
        return contracts;
    }

    // Add os métodos

    public void addContract(HourContract contract) {
        // usar o comando de add p/ lista
        contracts.add(contract);
    }

    public void removeContract(HourContract contract) {
        contracts.remove(contract);
    }

    public double income(int year, int month) {
        double sum = baseSalary;
        // Vou usar o Calendar para recuperar YEAR e MONTH
        Calendar cal = Calendar.getInstance(); // declarei variável Calendar e instanciei
        // Fazer um for each para percorrer a lista, e capturar os valores
        for(HourContract c : contracts) {
            cal.setTime(c.getDate()); //peguei a data do contrato e defini como sendo a data do calendário
            int c_year = cal.get(Calendar.YEAR);
            int c_month = cal.get(Calendar.MONTH);
            if(c_year == year && c_month == month) {
                sum += c.totalValue();
            }
        }
        return sum;
    }
}
