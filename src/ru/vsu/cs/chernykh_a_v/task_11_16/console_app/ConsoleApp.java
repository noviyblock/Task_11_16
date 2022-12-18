package ru.vsu.cs.chernykh_a_v.task_11_16.console_app;

import ru.vsu.cs.chernykh_a_v.task_11_16.main_logic.InputArgs;
import ru.vsu.cs.chernykh_a_v.task_11_16.main_logic.MainLogicClass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleApp{

    static InputArgs inputArgs = new InputArgs();

    //метод чтения из консоли строки и преобразование её в набор строковых аргументов
    public static String[] readConsoleLineParameters(){
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String cmdLine = null;
        try{
            cmdLine=bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert cmdLine != null;
        return cmdLine.split("\\s");
    }

    //парсинг введённых в консоль данных
    public static InputArgs parseCmdArgs(String[] args) {
        String inputFilePathVar, outputFilePathVar;
        if(args.length==2){
            inputFilePathVar = args[0];
            outputFilePathVar = args[1];
        }else{
            inputFilePathVar = args[1];
            outputFilePathVar = args[3];
        }
        inputArgs.setInputFile(inputFilePathVar);
        inputArgs.setOutputFile(outputFilePathVar);
        return inputArgs;
    }

    //запуск решения на основе адресов файлов
    public static void runSolution(String[] pathsTest, int num) throws IOException {
        //метод задания адресов для чтения/записи файлов
        if(num==0){
            //случай когда выполняется консольный (не тестовый ввод)
            inputArgs=parseCmdArgs(pathsTest);
        }else{
            //случай для тестов
            inputArgs.setInputFile(pathsTest[0]);
            inputArgs.setOutputFile(pathsTest[1]);
        }
        //метод чтения файлов по заданным адресам
        String data = MainLogicClass.readAllLinesFromFile(inputArgs.getInputFile());
        String answer = MainLogicClass.deleteCommasFromFileText(data);
        MainLogicClass.writeUpdateDataInFile(answer,inputArgs.getOutputFile());
        printSuccessMessage(num);
    }

    public static void printSuccessMessage(int num){
        if(num==0){
            System.out.println("The main program is done");
        }else{
            System.out.println("Test " + num + " is done");
        }
        System.out.println();
    }

    //запуск тестов для консольного приложения
    public static void runTestConsole() throws IOException {
        Test test = new Test();

        //первый тест
        String[] pathsTest1 = {test.testPath1In, test.testPath1Out};
        runSolution(pathsTest1,1);

        //второй тест
        String[] pathsTest2 = {test.testPath2In, test.testPath2Out};
        runSolution(pathsTest2,2);

        //третий тест
        String[] pathsTest3 = {test.testPath3In, test.testPath3Out};
        runSolution(pathsTest3,3);

        //четвёртый тест
        String[] pathsTest4 = {test.testPath4In, test.testPath4Out};
        runSolution(pathsTest4,4);

        //пятый тест
        String[] pathsTest5 = {test.testPath5In, test.testPath5Out};
        runSolution(pathsTest5,5);

        //шестой тест
        String[] pathsTest6 = {test.testPath6In, test.testPath6Out};
        runSolution(pathsTest6,6);

        //второй тест
        String[] pathsTest7 = {test.testPath7In, test.testPath7Out};
        runSolution(pathsTest7,7);

        //третий тест
        String[] pathsTest8 = {test.testPath8In, test.testPath8Out};
        runSolution(pathsTest8,8);

        //четвёртый тест
        String[] pathsTest9 = {test.testPath9In, test.testPath9Out};
        runSolution(pathsTest9,9);

        //пятый тест
        String[] pathsTest10 = {test.testPath10In, test.testPath10Out};
        runSolution(pathsTest10,10);
    }

    public static void main(String[] args) throws IOException {
        System.out.println();
        //тестовая часть
//        runTestConsole();

        System.out.println();
        System.out.print("Pls, enter your path: ");

        //создание заготовки строки для дальнейшего парсинга
        String[] argsCmd = readConsoleLineParameters();
        //решение
        runSolution(argsCmd,0);
    }
}