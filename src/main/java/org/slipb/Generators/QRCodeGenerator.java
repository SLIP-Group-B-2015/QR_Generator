package org.slipb.Generators;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.util.Hashtable;

/**
 * Created by Marshall Bradley (marshallbradley93@gmail.com)
 * ---
 * TODO: Description
 */

public class QRCodeGenerator {

    private static final int size = 33;
    private static QRCodeWriter qrCodeWriter = new QRCodeWriter();
    private String id;

    public QRCodeGenerator(String id) {
        this.id = id;
    }

    public BitMatrix getMatrix() throws WriterException {
        Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<>();
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        return qrCodeWriter.encode(id, BarcodeFormat.QR_CODE, size, size, hintMap);
    }
}
