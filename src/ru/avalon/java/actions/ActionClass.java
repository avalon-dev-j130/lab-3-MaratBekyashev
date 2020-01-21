package ru.avalon.java.actions;

public abstract class ActionClass implements Action {
  protected Thread currentThread;
    @Override
    public void start() {
        /*
         * TODO №1 Реализуйте метод start интерфейса Action.
         */
        currentThread = new Thread(this);
        //thread.setName(this.getClass().getCanonicalName());
        System.out.println(currentThread.getName()+ " thread have been started");
        currentThread.start();
    }

    // Прервать текущий поток
    @Override
    public void stop(){
        System.out.println(currentThread.getName()+ " thread will be interrupted");
        currentThread.interrupt();
    }

}
