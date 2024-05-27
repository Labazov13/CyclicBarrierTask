package org.example;

public class ComplexTask {

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
