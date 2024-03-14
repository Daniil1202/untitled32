import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;


public class Main {

    public static void main(String[] args) {
        List<Department> departments = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            departments.add(new Department("Departnment №" + i));

        }

        List<Person> people = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            people.add(new Person("Person № " + i,
                    ThreadLocalRandom.current().nextInt(20, 61),
                    ThreadLocalRandom.current().nextInt(20_000, 95_000) * 1.0,
                    departments.get(ThreadLocalRandom.current().nextInt(departments.size()))
            ));

        }
    }

        /**
         * В каждом департаменте найти самого взрослого сотрудника.
         * Вывести на консоль мапипнг department -> personName
         * Map<Department, Person>
         */

        public Map<Streams.Department, Streams.Person> printDepartmentOldestPerson (List < Streams.Person > people) {
            return people.stream().collect(Collectors.toMap(Streams.Person::getDepartment,
                    it -> it, (first, second) -> {
                        if (first.getAge() > second.getAge()) {
                            return first;
                        } else {
                            return second;
                        }
                    }));
        }

        /**
         * Найти 10 первых сотрудников, младше 30 лет, у которых зарплата выше 50_000
         */
        public List<Streams.Person> findFirstPersons (List<Streams.Person > people) {
            people.stream().filter(it -> it.getAge() < 30).
                    filter(it -> it.getSalary() > 50_000).limit(10).forEach(System.out::println);
        }



    /**
     * Найти депаратмент, чья суммарная зарплата всех сотрудников максимальна
     */

    public Optional<Department> maxsumSelaryPersonFromDepartment(List<Person> people) {
        return people.stream().
                collect(Collectors.groupingBy(Person::getDepartment, Collectors.
                        summingDouble(Person::getSalary))).entrySet().
                stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey);

    }

    /**
     * Вывести на консоль отсортированные (по алфавиту) имена персонов
     */
    public void printNamesOrdered(List<Person> people) {
        people.stream()
                .map(person -> person.getName())
                .sorted()
                .forEach(System.out::println);
    }



    public static class Person {
        private String name;
        private int age;
        private double salary;
        Department department;

        public Person(String name, int age, double salary, Department department) {
            this.name = name;
            this.age = age;
            this.salary = salary;
            this.department = department;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public double getSalary() {
            return salary;
        }

        public Department getDepartment() {
            return department;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", salary=" + salary +
                    ", department=" + department +
                    '}';
        }
    }

    public static class Department {
        private String name;

        public Department(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "Department{" +
                    "name='" + name + '\'' +
                    '}';
        }

    }


}
