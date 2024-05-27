package org.example;

import java.util.concurrent.BrokenBarrierException;
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
    public void executeTasks(int numberOfTasks) {
        for (int i = 0; i < numberOfTasks; i++) {
            int taskId = i;
            executorService.execute(() -> {
                ComplexTask task = new ComplexTask(taskId);
                task.execute();
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();
    }


}

