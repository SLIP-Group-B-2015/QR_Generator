package org.slipb;

import java.awt.image.BufferedImage;
import java.io.IOException;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import org.slipb.Generators.IDGenerator;
import org.slipb.Generators.QRCodeGenerator;
import org.slipb.Writers.FileWriter;
import org.slipb.Writers.ImageWriter;

/**
 * Created by Marshall Bradley (marshallbradley93@gmail.com)
 *
 * TODO: Description
 */

public class Main {

    private static final String GENERATING = "Generating ";
    private static final String CODES = " QR codes...";

    public static void main(String[] args) {

        int codes = Integer.parseInt(args[0]);
        String outputFolder = args[1];
        System.out.println(GENERATING + codes + CODES);

        // main execution loop
        for (int i = 0; i < codes; i++) {

            String id = IDGenerator.toString(IDGenerator.generateID());
            System.out.println("UUID " + (i + 1) + " is " + id);
            String filePath = outputFolder + "/" + (i + 1);

            try {
                QRCodeGenerator qrCodeGenerator = new QRCodeGenerator(id);
                BufferedImage image = qrCodeGenerator.getImage();

                ImageWriter imageWriter = new ImageWriter(filePath);
                imageWriter.write(image);

                FileWriter fileWriter = new FileWriter(filePath);
                fileWriter.write(id);

            } catch (IOException ex) {
                ex.printStackTrace();
            }

            System.out.println("QR Code " + (i + 1) + " generated and saved!");
        }
    }
}
