package org.slipb.Output;

import com.google.zxing.common.BitMatrix;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Marshall Bradley (marshallbradley93@gmail.com)
 *
 * TODO: Description
 */


public class QRCodeOutputter {

    private static final String fileType = "png";

    private BitMatrix bitMatrix;
    private File file;

    public QRCodeOutputter(BitMatrix bitMatrix, String filePath) {
        this.bitMatrix = bitMatrix;
        this.file = new File(filePath + "/QR.png");
    }

    public BufferedImage generateImage() {

        int matrixWidth = bitMatrix.getWidth();
        BufferedImage image = new BufferedImage(matrixWidth, matrixWidth, BufferedImage.TYPE_INT_RGB);
        image.createGraphics();

        Graphics2D graphics = (Graphics2D) image.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, matrixWidth, matrixWidth);
        graphics.setColor(Color.BLACK);

        for (int i = 0; i < matrixWidth; i++) {
            for (int j = 0; j < matrixWidth; j++) {
                if (bitMatrix.get(i, j)) {
                    graphics.fillRect(i, j, 1, 1);
                }
            }
        }

        return image;
    }

    public void output(BufferedImage image) throws IOException {
        file.mkdirs();
        ImageIO.write(image, fileType, file);
    }
}
