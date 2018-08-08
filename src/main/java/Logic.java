import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Logic {

   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd",new Locale("RU"));

    public void addNewEmployee(){
        Scanner scannerWR = new Scanner(System.in);
        System.out.println("Input data in format: id; last-name; name; surname; Birthday(yyyy-MM-dd); position; sub-division; " +
                "room number; officialTelefon; eMail; salary; date of hiring(yyyy-MM-dd); notes");
        System.out.println("Input 'break' to finish");
          Writer writer = null;
        try {
              writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("E:/employee.txt"),true)));
        } catch (FileNotFoundException e) {
            System.out.println("can't create a File");
        }
        String s;
        while (!(s = scannerWR.nextLine()).equals("break")){
            try {

                String[] parts = s.split("; ");
                Employee employee = createEmployee(parts);

                Employee.employees.add(employee);
                writer.write(employee.toString() + "\r\n");

            }catch (Exception e){
                System.out.println("incorrect input format");
            }
        }
        closeStreams(writer);
    }

    public void closeStreams(Writer writer){
        try {
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println("Error. Can't close file");
        }
    }

    public Employee createEmployee(String[] parts){
        Employee employee = new Employee();
        employee.setID(Integer.parseInt(parts[0]));
        employee.setLastName(parts[1]);
        employee.setName(parts[2]);
        employee.setSurname(parts[3]);
        employee.setBirthDay(LocalDate.parse(parts[4],formatter));
        employee.setPosition(parts[5]);
        employee.setSubDivision(parts[6]);
        employee.setRoomNumber(Integer.parseInt(parts[7]));
        employee.setOfficialTelefon(Integer.parseInt(parts[8]));
        employee.seteMail(parts[9]);
        employee.setSalary(Integer.parseInt(parts[10]));
        employee.setDateOfHiring(LocalDate.parse(parts[11],formatter));
        employee.setNotes(parts[12]);
        return employee;
    }

    public Set<Employee> readEmployeesFromFile(){
        List<String> lines = new ArrayList<>();
        try {
            Path path = Paths.get("E:employee.txt");
            lines = Files.readAllLines(path, Charset.defaultCharset());
        } catch (IOException e) {
            System.out.println("can't read a File");
        }
        for (String str : lines){
            String[] tempStr = str.split("; ");
            Employee employee = createEmployee(tempStr);
            Employee.employees.add(employee);
        }
        return Employee.employees;
    }

    public void writeNewSetOfEmployee(Set<Employee> newSetOfEmployee){
        Writer writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("E:/employee.txt"))));
        } catch (FileNotFoundException e) {
            System.out.println("can't create a File");
        }
        for (Employee e : newSetOfEmployee){
            try {
                writer.write(e.toString() +"\r\n");
            } catch (IOException e1) {
                System.out.println("can't write a data");
            }
        }
        try {
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println("Error. Can't close file");
        }
    }


    public void viewEmployeeData() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("input full name of employee (last-name; name; surname) to which you want see a data");
        System.out.println("Input 'break' to finish");

        String s;
        while (!(s = scanner.nextLine()).equals("break")) {

            String[] parts = s.split("; ");

            Iterator SetIterator = readEmployeesFromFile().iterator();
            while (SetIterator.hasNext()) {
                Employee employee = (Employee) SetIterator.next();
                if (parts[0].equals(employee.getLastName()) && parts[1].equals(employee.getName())
                        && parts[2].equals(employee.getSurname())) {
                    System.out.println(employee.toString());
                }
            }
        }
       // scanner.close();
    }

    public void editExistingEmployee(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("input full name of employee (last-name; name; surname) to which you want to change a data");
        System.out.println("Input 'break' to finish");
        // пропустити це можна через флаг true false в параметрі метода

        Set<Employee> newSetOfEmployee = new LinkedHashSet<>();
        String s;
        while (!(s = scanner.nextLine()).equals("break")) {

            String[] parts = s.split("; ");

            Iterator SetIterator = readEmployeesFromFile().iterator();
            boolean searchFlag = false;
            while (SetIterator.hasNext()) {
                Employee employee = (Employee) SetIterator.next();
                if (parts[0].equals(employee.getLastName()) && parts[1].equals(employee.getName())
                        && parts[2].equals(employee.getSurname())) {
                    searchFlag = true;
                    System.out.println(employee.toString());
                    System.out.println("what field do you wanna change? (type number)");
                    System.out.println("1: ID");
                    System.out.println("2: last-name");
                    System.out.println("3: name");
                    System.out.println("4: surname");
                    System.out.println("5: Birthday");
                    System.out.println("6: position");
                    System.out.println("7: sub-division");
                    System.out.println("8: room number");
                    System.out.println("9: official Telefon");
                    System.out.println("10: eMail");
                    System.out.println("11: salary");
                    System.out.println("12: date of hiring");
                    System.out.println("13: notes");
                    System.out.println("14: <exit>");

                    while (true){
                        int numberOfField = Integer.parseInt(scanner.nextLine());
                        if (numberOfField==14){
                            System.out.println("changes commit");
                            break;
                        }
                        switch (numberOfField){
                            case 1 :
                                System.out.println("set ID ");
                                employee.setID(Integer.parseInt(scanner.nextLine()));
                                break;
                            case 2:
                                System.out.println("Set last-name");
                                employee.setLastName(scanner.nextLine());
                                break;
                            case 3:
                                System.out.println("Set name");
                                employee.setName(scanner.nextLine());
                                break;
                            case 4:
                                System.out.println("Set surname");
                                employee.setSurname(scanner.nextLine());
                                break;
                            case 5:
                                System.out.println("Set Birthday");
                                employee.setBirthDay(LocalDate.parse(scanner.nextLine(),formatter));
                                break;
                            case 6:
                                System.out.println("Set position");
                                employee.setPosition(scanner.nextLine());
                                break;
                            case 7:
                                System.out.println("set sub-division");
                                employee.setSubDivision(scanner.nextLine());
                                break;
                            case 8:
                                System.out.println("set room number");
                                employee.setRoomNumber(Integer.parseInt(scanner.nextLine()));
                                break;
                            case 9:
                                System.out.println("set official Telefon");
                                employee.setOfficialTelefon(Integer.parseInt(scanner.nextLine()));
                                break;
                            case 10:
                                System.out.println("set eMali");
                                employee.seteMail(scanner.nextLine());
                                break;
                            case 11:
                                System.out.println("set salary");
                                employee.setSalary(Integer.parseInt(scanner.nextLine()));
                                break;
                            case 12:
                                System.out.println("set date of hiring");
                                employee.setDateOfHiring(LocalDate.parse(scanner.nextLine(),formatter));
                                break;
                            case 13:
                                System.out.println("set notes");
                                employee.setNotes(scanner.nextLine());
                                break;
                        }
                    }
                }

                newSetOfEmployee.add(employee);
            }
            if (!searchFlag){
                System.out.println("incorrect input or didn't find this employee");
            }
        }
        writeNewSetOfEmployee(newSetOfEmployee);
    }

    public void deleteExistingEmployee(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("input full name of employee (last-name; name; surname) to which you want to delete");
        System.out.println("Input 'break' to finish");
        // пропустити це можна через флаг true false в параметрі метода

        Set<Employee> newSetOfEmployee = new LinkedHashSet<>();
        String s;
        newSetOfEmployee.addAll(readEmployeesFromFile());
        while (!(s = scanner.nextLine()).equals("break")) {

            String[] parts = s.split("; ");

            Iterator SetIterator = readEmployeesFromFile().iterator();
            boolean searchFlag = false;
            while (SetIterator.hasNext()) {
                Employee employee = (Employee) SetIterator.next();
                if (parts[0].equals(employee.getLastName()) && parts[1].equals(employee.getName())
                        && parts[2].equals(employee.getSurname())) {
                    searchFlag = true;
                    System.out.println(employee.toString());
                    System.out.println("Are You sure you want to remove this employee?");
                    System.out.println("1: Yes");
                    System.out.println("2: No");
                    int answer = Integer.parseInt(scanner.nextLine());
                    switch (answer){
                        case 1: newSetOfEmployee.remove(employee);
                            System.out.println("employee has been removed");
                            break;
                        case 2 : break;
                    }
                }
            }
            if (!searchFlag){
                System.out.println("incorrect input or didn't find this employee");
            }
        }
        writeNewSetOfEmployee(newSetOfEmployee);

    }
}
