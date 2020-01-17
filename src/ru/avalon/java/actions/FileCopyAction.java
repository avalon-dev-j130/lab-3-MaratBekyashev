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
        String[] str = params.split(" ");

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
        inputFile = new File(inPath);
        Collection<String> result = new LinkedList<>();
        String line;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            while((line = reader.readLine()) != null)
            result.add(line);
        }
        catch(IOException e){
            System.out.println(e.getLocalizedMessage());
        }

        // Перекладывание в выходной поток
        outputFile = new File(outPath);
        try {
            writer = new PrintWriter(outputFile);
        }
        catch(FileNotFoundException e){
            System.out.println(e.getLocalizedMessage());
        };
    }

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
