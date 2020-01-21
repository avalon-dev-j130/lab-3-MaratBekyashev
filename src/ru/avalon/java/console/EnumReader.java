package ru.avalon.java.console;

import java.io.*;

/**Модель потокового объекта, позволяющего читать из преданного потока экземпляры перечисления, описанного типом Е.
 * 
 * @author Daniel Alpatov <danial.alpatov@gmail.com>
 * @param <E> перечисление, объекты которого будут вычитаны из потока.
 */
public class EnumReader<E extends Enum<E>> implements Closeable {
    /** Читатель потока
     */
    private BufferedReader in;

    /**Описатель типа перечисления, описанного параметром E шаблона
     */
    private Class<E> cls;

    /**Основной констуктор класса.
     * @param stream поток, в котором ожидаются экземпляры перечисления.
     * @param cls описатель типа перечисления, описанного параметром шаблона Е
     */
    public EnumReader(InputStream stream, Class<E> cls) {
        this.cls = cls;
        this.in  = new BufferedReader(new InputStreamReader(stream));
    }
    /** Возвращает следующий экземпляр перечисления из потока.
     * @return экземпляр типа <E>
     * @throws IOException в случае ошибки ввода/вывода
     */
    public final E next() throws IOException {
        try {
            return E.valueOf(this.cls, this.in.readLine());
        } catch (IllegalArgumentException e) {
            throw new IOException(e.getMessage(), e);
        }
    }

    /** ОСвобождает ресурсы, связанные с экземпляром класса.
     * @throws IOException в случае ошибки ввода/вывода
     */
    @Override
    public void close() throws IOException {
        this.in.close();
        this.in = null;
        this.cls = null;
    }

}
