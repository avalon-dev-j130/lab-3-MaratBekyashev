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
    Action lastAction;
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
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCommand(Commands command) throws IOException {
        switch (command) {
            case copy: // TODO №6 Обработайте команду copy
                System.out.print(">> Input src and dst:");
                Scanner scan = new Scanner(System.in);
                String params = scan.nextLine();
                this.lastAction = new FileCopyAction(params);
                this.lastAction.start();
                break;
            case move: // TODO №7 Обработайте команду move
                this.lastAction = new FileMoveAction();
                this.lastAction.start();
                break;
            case exit:
                close();
                break;
                /*
                 * TODO №9 Обработайте необработанные команды
                 */
            case myCommand1:
                this.lastAction = new MyCommand1Action();
                this.lastAction.start();
                break;
            case stop:

                break;
        }
    }
    
}
