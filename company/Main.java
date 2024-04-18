package com.company;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть кількість виробників: ");
        int producersCount = scanner.nextInt();

        System.out.print("Введіть кількість покупців: ");
        int consumersCount = scanner.nextInt();

        scanner.close();
        Random random = new Random();
        int initialProduction = random.nextInt(20) +1; // Випадкова початкова кількість продукції
        int initialConsumption = random.nextInt(initialProduction/producersCount) +1; // Випадкова початкова кількість споживаної продукції

        Main main = new Main();
        int storageSize = 15; // Початковий розмір сховища (можна залишити без змін)
        main.starter(storageSize, producersCount, consumersCount, initialProduction, initialConsumption);
    }

    private void starter(int storageSize, int producersCount, int consumersCount, int initialProduction, int initialConsumption) {
        Manager manager = new Manager(storageSize);

        // Створюємо виробників
        for (int i = 0; i < producersCount; i++) {
            Thread producerThread = new Thread(new Producer(initialProduction, manager, i));
            producerThread.start();
        }

        // Створюємо покупців
        for (int i = 0; i < consumersCount; i++) {
            Thread consumerThread = new Thread(new Consumer(initialConsumption, manager, i));
            consumerThread.start();
        }
    }
}
