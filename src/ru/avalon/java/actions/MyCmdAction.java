package ru.avalon.java.actions;

public class MyCmdAction extends ActionClass {

    @Override
    public void run(){
        for (int i = 0; ; i++){
           try{
              System.out.println(this.currentThread.getName() + " - " + i);
              Thread.sleep(2000);
            }
            catch (InterruptedException e){
                    System.out.println("InterruptedException fired! Infinite loop aborted");
                    break;
            }
        }
        System.out.println("Thread finished");
     }


}

