package ru.avalon.java;

import ru.avalon.java.console.ConsoleUI;
import ru.avalon.java.actions.*;
import java.io.IOException;
import java.util.Scanner;

/**
 * Лабораторная работа №3
 * <p>
 * Курс: "Программирование на платформе Java. Разработка
 * многоуровневых приложений"
 * <p>
 * Тема: "Потоки исполнения (Threads) и многозадачность" 
 *
 * @author Daniel Alpatov <danial.alpatov@gmail.com>
 */
public class Lab3 extends ConsoleUI<Commands> {
    private Action lastAction;
    private String commandParams;
    private Scanner scan;
    /**
     * Точка входа в приложение.
     * 
     * @param args 
     */
    public static void main(String[] args) {
        new Lab3().run();
    }
    /**
     * Конструктор класса.
     * <p>
     * Инициализирует экземпляр базового типа с использоавнием перечисления {@link Commands}.
     */
    Lab3() {
        super(Commands.class);
        scan = new Scanner(System.in);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCommand(Commands command) throws IOException {
        switch (command) {
            case copy: // TODO №6 Обработайте команду copy
                System.out.print(">> Input src and dst:");
                this.commandParams = scan.nextLine();
                this.lastAction = new FileCopyAction(this.commandParams);
                this.lastAction.start();
                break;
            case move: // TODO №7 Обработайте команду move
                System.out.print("  >> Please enter filename to move and new name:");
                this.commandParams = scan.nextLine();
                this.lastAction = new FileMoveAction(this.commandParams);
                this.lastAction.start();
                break;
            case exit:
                close();
                break;
                /*
                 * TODO №9 Обработайте необработанные команды
                 */
            case myCmd:
                this.lastAction = new MyCmdAction();
                this.lastAction.start();
                break;
            case stop:
                // реализовать логику по прерыванию последнего запущенного потока
                this.lastAction.stop();
                break;
            default:
                System.out.println("Wrong command entered");
        }
    }
    
}
