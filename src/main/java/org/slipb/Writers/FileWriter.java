package org.slipb.Writers;


import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Marshall Bradley (marshallbradley93@gmail.com)
 * ---
 * TODO: Description
 */

public class FileWriter {
    private static final String fileType = "id";

    private String id;
    private String filePath;

    public FileWriter(String id, String filePath) {
        this.id = id;
        this.filePath = filePath + "/raspberry.id";
    }

    public void output() throws IOException {
        PrintWriter out = new PrintWriter(filePath);
        out.print(id);
        out.close();
    }
}
