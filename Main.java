import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ComplexMatrix A = null;
        ComplexMatrix B = null;
        while (true) {
            System.out.println("1. Создать/Ввести матрицу A");
            System.out.println("2. Создать/Ввести матрицу B");
            System.out.println("3. Сложение (A+B)");
            System.out.println("4. Умножение (A*B)");
            System.out.println("5. Деление (A/B)");
            System.out.println("6. Транспонирование A");
            System.out.println("7. Определитель A");
            System.out.println("8. Показать матрицу A");
            System.out.println("9. Показать матрицу B");
            System.out.println("0. Выход");
            System.out.print("Выберите пункт: ");

            try {
                int choice = sc.nextInt();
                if (choice == 0) {
                    System.out.println("Выход из программы...");
                    break;
                }

                switch (choice) {
                    case 1:
                        A = inputMatrix(sc, "A");
                        break;
                    case 2:
                        B = inputMatrix(sc, "B");
                        break;
                    case 3:
                        checkMatrices(A, B);
                        System.out.println("Результат сложения:");
                        A.add(B).print();
                        break;
                    case 4:
                        checkMatrices(A, B);
                        System.out.println("Результат умножения:");
                        A.multiply(B).print();
                        break;
                    case 5:
                        checkMatrices(A, B);
                        System.out.println("Результат деления:");
                        A.divide(B).print();
                        break;
                    case 6:
                        if (A == null) { System.out.println("Сначала введите матрицу А!"); break; }
                        System.out.println("Транспонированная матрица A:");
                        A.transpose().print();
                        break;
                    case 7:
                        if (A == null) { System.out.println("Сначала введите матрицу А!"); break; }
                        System.out.println("Определитель матрицы A: " + A.determinant());
                        break;
                    case 8:
                        if (A == null) { System.out.println("Сначала введите матрицу А!"); break; }
                        System.out.println("Матрица A:");
                        A.print();
                        break;
                    case 9:
                        if (B == null) { System.out.println("Сначала введите матрицу B!"); break; }
                        System.out.println("Матрица B:");
                        B.print();
                        break;
                    default:
                        System.out.println("Нет такого пункта меню.");
                }
            } catch (RuntimeException e) {
                System.out.println("Ошибка: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Ошибка ввода. Проверьте, что вы вводите числа.");
                sc.nextLine();
            }
        }
    }

    private static ComplexMatrix inputMatrix(Scanner sc, String name) {
        System.out.print("Введите количество строк для матрицы " + name + ": ");
        int r = sc.nextInt();
        System.out.print("Введите количество столбцов для матрицы " + name + ": ");
        int c = sc.nextInt();

        ComplexMatrix m = new ComplexMatrix(r, c);
        System.out.println("Введите элементы матрицы (сначала действительную часть, потом мнимую):");
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                System.out.printf("Элемент [%d][%d](R): ", i, j);
                double re = sc.nextDouble();
                System.out.printf("Элемент [%d][%d](I): ", i, j);
                double im = sc.nextDouble();
                m.data[i][j] = new Complex(re, im);
            }
        }
        System.out.println("Матрица " + name + " успешно введена.");
        return m;
    }

    private static void checkMatrices(ComplexMatrix a, ComplexMatrix b) {
        if (a == null || b == null) {
            throw new RuntimeException("Сначала введите обе матрицы (пункты 1 и 2)!");
        }
    }
}