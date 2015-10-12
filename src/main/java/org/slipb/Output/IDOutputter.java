package org.slipb.Output;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Marshall Bradley (marshallbradley93@gmail.com)
 * ---
 * TODO: Description
 */

public class IDOutputter {
    private static final String fileType = "id";

    private String id;
    private String filePath;

    public IDOutputter(String id, String filePath) {
        this.id = id;
        this.filePath = filePath + "/raspberry.id";
    }

    public void output() throws IOException {
        PrintWriter out = new PrintWriter(filePath);
        out.print(id);
        out.close();
    }
}
