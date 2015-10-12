package org.slipb.Writers;

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


public class ImageWriter {

    private static final String FILE_TYPE = "png";
    private static final String FILE_NAME = "QR." + FILE_TYPE;
    private String filePath;

    public ImageWriter(String filePath) {
        this.filePath = filePath + "/" + FILE_NAME;
    }

    public void write(BufferedImage image) throws IOException {
        File file = new File(filePath);
        file.mkdirs();
        ImageIO.write(image, FILE_TYPE, file);
    }
}
