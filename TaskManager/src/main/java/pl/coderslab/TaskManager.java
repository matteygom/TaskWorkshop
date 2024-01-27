package pl.coderslab;

import javax.imageio.IIOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class TaskManager {

    public static void main(String[] args) {

        menuDisplay();
        scanChoice();
    }

    public static void menuDisplay() {

        System.out.println("Please select an option:");
        String[] options = {"add", "remove", "list", "exit"};


        for (String option : options) {

            System.out.println(option);
        }
    }

    public static void scanChoice() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter choice:");

        while (scanner.hasNextLine()) {

            String choice = scanner.nextLine();

            switch (choice) {

                case "exit":
                    System.exit(0);

                case "list":
                    listTasks();
                    break;

                case "remove":
                    removeTask();
                    break;

                case "add":
                    addTask();
                    break;

                default:
                    System.out.println("Please type correct value");

            }
        }

    }

    public static void listTasks() {

        File file = new File("/Users/matteygom/TaskManager/src/main/java/pl/coderslab/tasks.csv");

        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {

                System.out.println(scanner.nextLine());
            }

        } catch (FileNotFoundException exception) {

            System.out.println("No file found");

        }
        menuDisplay();
    }

    public static void addTask() {

        File file = new File("/Users/matteygom/TaskManager/src/main/java/pl/coderslab/tasks.csv");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter task description:");


        String description = scanner.nextLine();

        System.out.println("Please enter task date:");

        String date = scanner.nextLine();

        System.out.println("Please enter task priority:");


        String priority = scanner.nextLine();


        try (FileWriter fileWriter = new FileWriter(file, true)) {

            String lineToWrite = description + ", " + date + ", " + priority;

            fileWriter.write("\n" + lineToWrite);

        } catch (IOException exception) {
            System.out.println("File error");


        }menuDisplay();

    }

    public static void removeTask() {

        File file = new File("/Users/matteygom/TaskManager/src/main/java/pl/coderslab/tasks.csv");

        String[][] contentTable = new String[1][1];

        int counter = 0;
        int counter2 = 0;

        try {
            Scanner scanner = new Scanner(file);


            while (scanner.hasNextLine()) {

                String nextLine = scanner.nextLine();
                contentTable[counter] = new String[1]; // Dopytać Michała
                contentTable[counter][0] = nextLine;
                counter++;

                contentTable = Arrays.copyOf(contentTable, contentTable.length + 1); // albo counter +1
            }
            contentTable = Arrays.copyOf(contentTable, contentTable.length - 1);

            //KONCZY SIE WCZYTYWANIE DO UTWORZONEJ TABELI

////            for (String[] oneLine : contentTable) {
////                for (String element : oneLine) {
////                    System.out.println(counter2 + 1 + " " + element);
////                    counter2++;
////                }
////
//            }
                for (String[] oneline : contentTable){

                    System.out.println(counter2 + 1 + " " + oneline[0]);
                    counter2++;
                }

        } catch (FileNotFoundException exception) {
            System.out.println("File not found");

        } // KONCZY SIE WYSWIETLANIE TABELI

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Please enter the number of the task you want to remove");

            String newNextLine = scanner.nextLine();

            if (newNextLine.matches("[0-9]+")) {

                int taskToRemove = Integer.parseInt(newNextLine);

                if (taskToRemove == 0) {
                    break;
                }

                if (taskToRemove < 1 || taskToRemove > counter2) {  // MNIEJSZY OD 0 LUB WIEKSZY NIZ WSZYSTKIE POZYCJE W TABELI

                    System.out.println("Invalid input");

                } else {

                    contentTable[taskToRemove - 1][0] = "Skip";
                    System.out.println("Task removed succesfully");

                    break;

                }
            } else {
                System.out.println("Invalid input");

            }
        }
            try (FileWriter fileWriter = new FileWriter(file)) {

                for (int y = 0; y < contentTable.length; y++) {

                    String lineToWrite = contentTable[y][0].trim();

                    if (lineToWrite.equals("Skip")) {

                        continue;
                    }

                    fileWriter.write(lineToWrite + "\n");

                }
            } catch (IOException exception) {

                System.out.println("File not found");
            }

        }
        }

