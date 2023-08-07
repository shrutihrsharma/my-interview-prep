package org.example.deutsche;

import java.util.*;
import java.util.stream.Collectors;

public class Employee {
    private String name;
    private Integer id;
    private List<String> skillSets;

    public Employee() {}

    public Employee(String name, Integer id, List<String> skillSets) {
        this.name = name;
        this.id = id;
        this.skillSets = skillSets;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<String> getSkillSets() {
        return skillSets;
    }

    public void setSkillSets(List<String> skillSets) {
        this.skillSets = skillSets;
    }
    // 1. Create a sample POJO and add some member variables and construct it the way you use it.
    // 2. What is the rationale behind creating a POJO with private instance variables and parameterised constructor?
    // 3. Given a list of employees, group them by their skill sets i.e. employees with one or more common skill set should be grouped together.

    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(new Employee("Shruti",1, Arrays.asList("Java", "Angular", "Spring", "Excel")),
                new Employee("Kalpesh", 2, Arrays.asList("Apriso", "Excel", "JavaScript")),
                new Employee("Neha", 3, Arrays.asList("Angular", "BI")));

        groupBySkillSet1(employees);
        groupBySkillSet2(employees);
        groupBySkillSet3(employees);
    }

    private static void groupBySkillSet1(List<Employee> employees) {
        if(!employees.isEmpty()) {
            // Create a HashSet of SkillSets
            Set<String> allPossibleSkillSets = new HashSet<>();
            for(Employee e: employees) {
                allPossibleSkillSets.addAll(e.getSkillSets());
            }

            // Create a HashMap where key will be Skill set and value will be list of employee names.
            Map<String, List<String>> groupedBySkillSet = new HashMap<>();
            for(String skill: allPossibleSkillSets) {
                List<String> employeesBySkill = employees.stream().filter(e -> e.getSkillSets().contains(skill)).map(Employee::getName).toList();
                groupedBySkillSet.put(skill, employeesBySkill);
            }
            System.out.println(groupedBySkillSet);
        }
    }

    private static void groupBySkillSet2(List<Employee> employees) {
        if(!employees.isEmpty()) {
            // Create a HashMap where key will be Skill set and value will be list of employee names.
            Map<String, List<String>> groupedBySkillSet = new HashMap<>();
            for(Employee e: employees) {
                for (String skill: e.getSkillSets()) {
                    List<String> employeesBySkillSet = groupedBySkillSet.getOrDefault(skill, new ArrayList<>());
                    employeesBySkillSet.add(e.getName());
                    groupedBySkillSet.put(skill, employeesBySkillSet);
                }
            }

            System.out.println(groupedBySkillSet);
        }
    }

    private static void groupBySkillSet3(List<Employee> employees) {

            // Create a HashMap where key will be Skill set and value will be list of employee names.
            Map<String, List<String>> groupedBySkillSet = employees.stream().flatMap(
                    e -> e.getSkillSets().stream().map(
                            s -> new AbstractMap.SimpleEntry<>(e.getName(), s)))
                            .collect(Collectors.groupingBy(
                                    AbstractMap.SimpleEntry::getValue,
                                    Collectors.mapping(AbstractMap.SimpleEntry::getKey, Collectors.toList())));
            System.out.println(groupedBySkillSet);
        }
}


