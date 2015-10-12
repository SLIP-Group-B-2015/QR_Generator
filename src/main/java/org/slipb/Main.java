package org.slipb;

import java.awt.image.BufferedImage;
import java.io.IOException;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import org.slipb.Generators.IDGenerator;
import org.slipb.Generators.QRCodeGenerator;
import org.slipb.Output.IDOutputter;
import org.slipb.Output.QRCodeOutputter;

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
        String outputFilePath = args[1];
        System.out.println(GENERATING + codes + CODES);

        // main execution loop
        for (int i = 0; i < codes; i++) {

            String id = (new IDGenerator()).toString();
            System.out.println("UUID " + (i+1) + " is " + id);
            String filePath = outputFilePath + "/" + (i+1);

            try {
                BitMatrix bitMatrix = (new QRCodeGenerator(id)).getMatrix();

                QRCodeOutputter qrCodeOutputter = new QRCodeOutputter(bitMatrix, filePath);
                BufferedImage image = qrCodeOutputter.generateImage();
                qrCodeOutputter.output(image);

                IDOutputter idOutputter = new IDOutputter(id, filePath);
                idOutputter.output();

            } catch (WriterException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            System.out.println("QR Code " + (i + 1) + " generated and saved!");
        }
    }
}
