package org.slipb.Writers;


import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Marshall Bradley (marshallbradley93@gmail.com)
 * ---
 * TODO: Description
 */

public class FileWriter {

    private static final String FILE_NAME = "raspberry.id";
    private String filePath;

    public FileWriter(String filePath) {
        this.filePath = filePath + "/" + FILE_NAME;
    }

    public void write(String id) throws IOException {
        PrintWriter out = new PrintWriter(filePath);
        out.print(id);
        out.close();
    }
}
