package org.slipb.Generators;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Hashtable;

/**
 * Created by Marshall Bradley (marshallbradley93@gmail.com)
 * ---
 * TODO: Description
 */

public class QRCodeGenerator {

    private static final int SIZE = 33;

    private String id;
    private BitMatrix bitMatrix;
    private BufferedImage image;

    public QRCodeGenerator(String id) {
        this.id = id;
        try {
            generateMatrix();
            generateImage();
        } catch (WriterException ex) {
            ex.printStackTrace();
        }
    }

    public BufferedImage getImage() {
        return this.image;
    }

    private void generateMatrix() throws WriterException {
        Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<>();
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        this.bitMatrix = qrCodeWriter.encode(id, BarcodeFormat.QR_CODE, SIZE, SIZE, hintMap);
    }

    private void generateImage() {

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

        this.image = image;
    }
}
