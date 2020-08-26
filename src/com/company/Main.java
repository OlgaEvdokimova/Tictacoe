package com.company;

import java.util.Scanner;

public class Main {
    /**
     * tic tac toe
     */
    public static void main(String[] args) {
        // 2 - empty, 1 - X, 0 - 0;
        int[][] arr = new int[3][3];
        for (int i = 0; i < arr.length; i++) { //заполняем массив 2-ми( т е пустыми клетками)
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] = 2;
            }
        }
        out(arr); // вывод поля
        while (true) {
            inputPerson(arr);               // ход человека
            if (!hasWinFailed(arr)) {       // если не выиграл/проиграл
                checkComp(arr);                  // ход компа
                if (hasWinFailed(arr)) {         // если выграл/проиграл
                    out(arr);                    // вывод поля (конец)
                    break;

                }
            } else {                        // если человек выиграл/проиграл
                out(arr);                    // выводим поле (конец)
                break;
            }
            out(arr);                        // выводим поле и продолжаем

        }

    }

    public static void out(int[][] arr) { //вывод
        System.out.println("|---|---|---|");
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] == 0) {
                    System.out.print("| 0 ");
                } else if (arr[i][j] == 1) {
                    System.out.print("| X ");
                } else if (arr[i][j] == 2) {
                    System.out.print("|   ");
                }
            }
            System.out.println("|");
            System.out.println("|---|---|---|");
        }
    }

    public static void inputPerson(int[][] arr) {   //ввод игрока(1)

        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Input a line");
            int i = sc.nextInt();
            System.out.println("Input a column");
            int j = sc.nextInt();
            if (!filling(arr, i, j)) { // если клетка без начинки(пустая)
                arr[i][j] = 1;
                break;
            }
        }
    }

    static boolean filling(int[][] arr, int i, int j) {
        for (int n = i; n < arr.length; n++) {
            for (int k = j; k < arr.length; k++) {
                if (arr[n][k] == 2) {
                    return false;
                } else if (arr[n][k] == 1 || arr[n][k] == 0) {
                    System.out.println("Клетка занята введите другую");
                    return true;
                }
            }
        }
        return false;
    }

    static void checkComp(int[][] arr) {  //ввод компа (0)
        int quantity = checkOne(arr); // проверяем количество  единиц на поле;
        checkAndStep(arr, quantity); // пытаемся найти единицы и закрыть нулем
    }

    public static void checkAndStep(int[][] arr, int quantity) {  // сначала ищем, куда поставить 0

        // если на поле больше 1 единицы,
        if (quantity >= 2) {

            //  ищем  два 0, чтобы поставить третий ноль и выиграть
            if (checkZeroOrOne(arr, 0)) {  // передаем ноль, потому что будем искать последовательность нолей
                return;
            }

            // если , комп не выграл, ищем, куда поставить 0, чтобы перекрыть единицы и не дать человеку выиграть
            if (checkZeroOrOne(arr, 1)) {  // передаем единицу, потому что будем искать последовательность единиц
                return;
            }


            // если не находим, тогда ищем пустую клетку
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[i].length; j++) {
                    if (arr[i][j] == 2) {
                        stepComp(arr, i, j);
                        return;
                    }
                }
            }

        } else if (quantity == 1) {               // если на поле одна 1
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[i].length; j++) {
                    if (arr[i][j] == 1) {


                        stepComp(arr, i, j);
                    }
                }
            }

        }

    }

    static boolean checkZeroOrOne(int[][] arr, int zeroOrOne) { // ищем, есть ли на поле последовательность 0, чтобы поставить тертий ноль и выиграть
        //проверка горизонтали
        for (int i = 0; i < arr.length; i++) {
            if (arr[i][0] == zeroOrOne && arr[i][1] == zeroOrOne && arr[i][2] == 2) {
                stepComp(arr, i, 2);
                return true;
            }
            if (arr[i][0] == zeroOrOne && arr[i][1] == 2 && arr[i][2] == zeroOrOne) {
                stepComp(arr, i, 1);
                return true;
            }
            if (arr[i][0] == 2 && arr[i][1] == zeroOrOne && arr[i][2] == zeroOrOne) {
                stepComp(arr, i, 0);
                return true;
            }
        }
        //проверка 1 диагонали
        for (int i = 0; i < arr.length; i++) {
            if (arr[0][i] == zeroOrOne && arr[1][i] == zeroOrOne && arr[2][i] == 2) {
                stepComp(arr, 2, i);
                return true;
            }
            if (arr[0][i] == zeroOrOne && arr[1][i] == 2 && arr[2][i] == zeroOrOne) {
                stepComp(arr, 1, i);
                return true;
            }
            if (arr[0][i] == 2 && arr[1][i] == zeroOrOne && arr[2][i] == zeroOrOne) {
                stepComp(arr, 0, i);
                return true;
            }
        }
        //проверка  1 диагонали
        if (arr[0][0] == zeroOrOne && arr[1][1] == zeroOrOne && arr[2][2] == 2) {
            stepComp(arr, 2, 2);
            return true;
        }
        if (arr[0][0] == zeroOrOne && arr[1][1] == 2 && arr[2][2] == zeroOrOne) {
            stepComp(arr, 1, 1);
            return true;
        }
        if (arr[0][0] == 2 && arr[1][1] == zeroOrOne && arr[2][2] == zeroOrOne) {
            stepComp(arr, 0, 0);
            return true;
        }
        //проверка  2 горизонтали
        if (arr[2][0] == zeroOrOne && arr[1][1] == zeroOrOne && arr[0][2] == 2) {
            stepComp(arr, 0, 2);
            return true;
        }
        if (arr[2][0] == zeroOrOne && arr[1][1] == 2 && arr[0][2] == zeroOrOne) {
            stepComp(arr, 1, 1);
            return true;
        }
        if (arr[2][0] == 2 && arr[1][1] == zeroOrOne && arr[0][2] == zeroOrOne) {
            stepComp(arr, 2, 0);
            return true;
        }

        return false;
    }

    static void stepComp(int[][] arr, int n, int k) {   // ввод компа
        int quantity = checkOne(arr);
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (i == n && j == k) {
                    if (quantity != 1) {   // если не одна 1
                        arr[i][j] = 0;
                    } else {                // если одна 1
                        if (j > 0) {        // ходим нулем справа или слева от единицы
                            arr[i][j - 1] = 0;
                        } else {
                            arr[i][j + 1] = 0;
                        }
                    }
                }
            }
        }
    }

    static int checkOne(int[][] arr) {   // ищем кл-во единиц
        int count = 0;

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] == 1) {
                    count++;
                }
            }
        }
        return count;
    }

    static boolean hasWinFailed(int[][] arr) { //проверка выиграл/проиграл или клетки закончились
        //проверка оставшихся клеток
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] == 2) {
                    count++;
                    break;
                }
            }
        }
        //проверка на выигрыш
        for (int i = 0; i < arr.length; i++) {
            if (arr[i][0] == 1 && arr[i][1] == 1 && arr[i][2] == 1) {
                System.out.println("Win");
                return true;
            }
            if (arr[0][i] == 1 && arr[1][i] == 1 && arr[2][i] == 1) {
                System.out.println("Win");
                return true;
            }
           if (checkDiag(arr, 1)){
               return true;
           }

            //проверка на проигрыш
            if (arr[i][0] == 0 && arr[i][1] == 0 && arr[i][2] == 0) {
                System.out.println("Failed");
                return true;
            }
            if (arr[0][i] == 0 && arr[1][i] == 0 && arr[2][i] == 0) {
                System.out.println("Failed");
                return true;
            }
            if (checkDiag(arr, 0)){
                return true;
            }

        }

        //если нет пустых клеток
        if (count == 0) {
            System.out.println("Клетки закончились");
            return true;
        }

        return false;
    }

    static boolean checkDiag(int[][] arr, int zeroOrOne) {
        String win = "Win";
        String failed = "Failed";
        if (arr[0][0] == zeroOrOne && arr[1][1] == zeroOrOne && arr[2][2] == zeroOrOne) {
            if (zeroOrOne == 1) {
                System.out.println(win);
            } else {
                System.out.println(failed);
            }
            return true;
        }

        if (arr[2][0] == zeroOrOne && arr[1][1] == zeroOrOne && arr[0][2] == zeroOrOne) {
            if (zeroOrOne == 1) {
                System.out.println(win);
            } else {
                System.out.println(failed);
            }
            return true;
        }
        return false;
    }

}




