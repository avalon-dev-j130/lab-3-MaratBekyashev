package ru.avalon.java.console;

import ru.avalon.java.Commands;

import java.io.IOException;

/**  Класс описывает текстовый человеко-машинный интерфейс.
 * 
 * @author Daniel Alpatov <danial.alpatov@gmail.com>
 * @param <E> тип данных, описывающий возможные команды. Дожен быть перечислением
 */
public class ConsoleUI<E extends Enum<E>> extends EnumReader<E> implements Runnable {
    /** Флаг, указывающий на то, должен ли интерфейс продолжать обрабатывать команды.
     * Переменная должна содержать значение false, чтобы интрефейс продолжал получать команды из потомка.
     */
    private boolean exit;

    protected Commands lastCommand;
    /** Основной конструктор класса.
     * @param cls описатель перечисления, которое отражает набор команд, обрабатываемых интерфейсом
     */
    public ConsoleUI(Class<E> cls) {
        super(System.in, cls);
    }

    /** Выполняет обработку следующей команды из потока.
     * Следующая команда, содержащаяся в потоке, связанным с текущим объектом, передаётся на обработку в метод onCommand.
     */
    protected void processCommand() {
        try {
            System.out.print("> ");
            onCommand(next());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    //Алгоритм обработки команд.
    @Override
    public void run() {
        while (!exit)
            processCommand();

    }

    /** Метод жизненного цикла класса.
     * <p>
     * Вызывается, когда от пользозвателя была получена следующая команда.
     * @param command экземпляр перечисления E. Описывает пользовательскую команду.

     * @throws IOException в случае ощибки ввода вывода
     */
    protected void onCommand(E command) throws IOException {
      // переопределяется в класе Lab3 , пустышка здесь
    }

    /**
     * {@inheritDoc}
     * @throws IOException 
     */
    @Override
    public void close() throws IOException {
        exit = true;
    }
}
