package org.example;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Cllllll {
}
class ComplexTask1 {
    public void execute() {
        // Реализация выполнения части сложной задачи
        try {
            System.out.println(Thread.currentThread().getName() + " is executing a part of the complex task.");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

class ComplexTaskExecutor1 {
    private final ExecutorService executorService;
    private final CyclicBarrier barrier;

    public ComplexTaskExecutor1(int numberOfTasks) {
        this.executorService = Executors.newFixedThreadPool(numberOfTasks);
        this.barrier = new CyclicBarrier(numberOfTasks, () -> {
            System.out.println("All tasks have completed. Combining results...");
        });
    }

    public void executeTasks(int numberOfTasks) {
        for (int i = 0; i < numberOfTasks; i++) {
            executorService.submit(() -> {
                ComplexTask1 complexTask = new ComplexTask1();
                complexTask.execute();
                try {
                    barrier.await(); // Ожидание завершения всех потоков
                } catch (Exception e) {
                    Thread.currentThread().interrupt();
                }
            });
        }
        executorService.shutdown();
    }
}

class TestComplexTaskExecutor1 {

    public static void main(String[] args) {
        ComplexTaskExecutor1 taskExecutor = new ComplexTaskExecutor1(5); // Количество задач для выполнения

        taskExecutor.executeTasks(5);
    }
}
