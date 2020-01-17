package ru.avalon.java.actions;

import java.io.File;
import java.security.InvalidParameterException;

/**
 * Действие, которое перемещает файлы в пределах дискового
 * пространства.
 */
public class FileMoveAction implements Action {
    private String srcFileName;
    private String newFileName;

    private File inputFile;
    private File outputFile;

    // Constructor
    public FileMoveAction(String params) {
        String[] tmp = params.split("\\s+");
        if (tmp.length > 2)
            throw new InvalidParameterException("Неверный формат параметров команды MOVE. Example move src_file dst_file");
        this.srcFileName = tmp[0];
        this.newFileName = tmp[1];
    }

    @Override
    public void run() {
        /*
         * TODO №4 Реализуйте метод run класса FileMoveAction
         */
        this.inputFile = new File(this.srcFileName);
        this.outputFile = new File(this.newFileName);


        if (this.inputFile.exists()){
           this.inputFile.renameTo(this.outputFile);
        }
        else
            System.out.println("Source filename does not exist");
    }

    @Override
    public void close() throws Exception {
        /*
         * TODO №5 Реализуйте метод close класса FileMoveAction
         */

    }

}
