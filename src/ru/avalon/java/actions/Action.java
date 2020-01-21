package ru.avalon.java.actions;

/**
 * Абстрактное представление о действии, как функциональном
 * элементе приложения.
 * <p>
 * Действие является потоковым объектом, что позволяет
 * исполнять несколько действий параллельно и не блокировать
 * основной поток исполнения.
 */
public interface Action extends Runnable, AutoCloseable {
    //Thread innerThread = new Thread();
    // Запускает потоковый объект на исполнение в отдельном потоке исполнения.
    default void start() {
        /*
         * TODO №1 Реализуйте метод start интерфейса Action.
         */
        Thread thread = new Thread(this);
        thread.setName(this.getClass().getCanonicalName());
        thread.start();

        //innerThread.setName(this.getClass().getCanonicalName());
        //innerThread.start();

    }

    // Прервать текущий поток
     default void stop(){
        Thread th = Thread.currentThread();
        System.out.println(th.getName()+ " thread will be interrupted");
        th.interrupt();
    }

     default void close() {
         System.out.println("Some resources should be closed here");
     }

}
