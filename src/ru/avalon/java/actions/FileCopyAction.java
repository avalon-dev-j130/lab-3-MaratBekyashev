package ru.avalon.java.actions;

import org.omg.CORBA.portable.InputStream;

import java.io.*;
import java.util.Collection;
import java.util.LinkedList;


/**
 * Действие, которое копирует файлы в пределах дискового
 * пространства.
 */
public class FileCopyAction extends ActionClass implements Action {
    private String inPath;
    private String outPath;
    private File inputFile;
    private File outputFile;
    Collection<String> lines;
    PrintWriter writer;

    public FileCopyAction(String params) {
        String[] str = params.split("\\s+");

        String inPath = "d:\\users\\old.txt";//str[0];
        String outPath = "d:\\users\\new.txt"; //str[1];

        this.inPath = inPath;
        this.outPath = outPath;
    }

    @Override
    public void run()  {
        /*
         * TODO №2 Реализуйте метод run класса FileCopyAction
         */
        // Чтение файла
        this.inputFile = new File(inPath);
        this.lines = new LinkedList<>();
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(this.inputFile))){
            while((line = reader.readLine()) != null){
                try{
                    this.lines.add(line);
                    Thread.sleep(2000);
                }
                catch(InterruptedException ex){
                    System.out.println("Process "+ this.getClass().getCanonicalName()+ " aborted at read");
                    break;
                }
            }
        }
        catch(IOException e){
            System.out.println(e.getLocalizedMessage());
        }

        // Перекладывание в выходной поток
        this.outputFile = new File(this.outPath);
        try  {
            this.writer = new PrintWriter(this.outputFile);
            for (String str: lines){
                try{
                    this.writer.println(str);
                    Thread.sleep(1);
                }
                catch(InterruptedException ex){
                    System.out.println("Process "+ this.getClass().getCanonicalName()+ " aborted at write");
                    return;
                }

            }
            this.writer.flush();
            this.writer.close();
            System.out.println("File copied");
        }
        catch(FileNotFoundException ex){
            System.out.println(ex.getLocalizedMessage());
        }
    };

    @Override
    public void close()  {
        /*
         * TODO №3 Реализуйте метод close класса FileCopyAction
         */
       if (this.writer != null) {
           this.writer.close();
           this.writer = null;
       }
    }
}
