package org.example;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.System.out;

public class ComplexTaskExecutor {
    private final ExecutorService executorService;
    private final CyclicBarrier cyclicBarrier;

    public ComplexTaskExecutor(int countTask) {
        executorService = Executors.newFixedThreadPool(countTask);
        cyclicBarrier = new CyclicBarrier(countTask, () -> out.println("Все задачи выполнены, комбинирую результат..."));
    }

    public synchronized void executeTasks(int countTask) {
        if (!executorService.isShutdown()) {
            for (int i = 0; i < countTask; i++) {
                executorService.submit(() -> {
                    ComplexTask complexTask = new ComplexTask();
                    complexTask.execute();
                    try {
                        cyclicBarrier.await();
                    } catch (Exception e) {
                        Thread.currentThread().interrupt();
                    }
                });
            }
            executorService.shutdown();
        } else {
            System.out.println("Cannot execute tasks. Executor is already shut down.");
        }
    }
}

