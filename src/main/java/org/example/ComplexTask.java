package org.example;

public class ComplexTask {
    private int taskId;

    public ComplexTask(int taskId) {
        this.taskId = taskId;
    }

    public void execute() {
        try {
            System.out.println("Выполняю подзадачу сложной задачи...");
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + " выполнил подзадачу!");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
