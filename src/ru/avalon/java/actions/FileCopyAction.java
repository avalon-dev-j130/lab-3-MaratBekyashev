package ru.avalon.java.actions;

import org.omg.CORBA.portable.InputStream;

import java.io.*;
import java.util.Collection;
import java.util.LinkedList;


/**
 * Действие, которое копирует файлы в пределах дискового
 * пространства.
 */
public class FileCopyAction implements Action {
    private String inPath;
    private String outPath;
    private File inputFile;
    private File outputFile;
    Collection<String> lines;
    PrintWriter writer;

    public FileCopyAction(String params) {
        String[] str = params.split("\\s+");

        String inPath = str[0];
        String outPath = str[1];

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
                this.lines.add(line);
                Thread.sleep(1);
            }
        }
        catch(IOException e){
            System.out.println(e.getLocalizedMessage());
        }
        catch(InterruptedException ex){
            System.out.println("Process "+ this.getClass().getCanonicalName()+ " aborted at read");
            return;
        }

        // Перекладывание в выходной поток
        this.outputFile = new File(this.outPath);
        try  {
            this.writer = new PrintWriter(this.outputFile);
            for (String str: lines){
                this.writer.println(str);
                Thread.sleep(1);
            }
            this.writer.flush();
            this.writer.close();
            System.out.println("File copied");
        }
        catch(FileNotFoundException ex){
            System.out.println(ex.getLocalizedMessage());
        }
        catch(InterruptedException ex){
            System.out.println("Process "+ this.getClass().getCanonicalName()+ " aborted at write");
        }
    };


    @Override
    public void close() throws Exception {
        /*
         * TODO №3 Реализуйте метод close класса FileCopyAction
         */
       if (this.writer != null) {
           this.writer.close();
           this.writer = null;
       }
    }

}
