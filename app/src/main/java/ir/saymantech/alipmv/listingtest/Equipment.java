package ir.saymantech.alipmv.listingtest;

/**
 * Created by Ali on 01/11/2015.
 */
public class Equipment {
    private int Description;
    private int Picture;
    private int QRcode;

    public Equipment(int description, int picture, int qrcode) {
        Description = description;
        Picture = picture;
        QRcode = qrcode;
    }

    public int getDescription() {
        return Description;
    }

    public void setDescription(int description) {
        Description = description;
    }

    public int getPicture() {
        return Picture;
    }

    public void setPicture(int picture) {
        Picture = picture;
    }

    public int getQRcode() {
        return QRcode;
    }

    public void setQRcode(int QRcode) {
        this.QRcode = QRcode;
    }
}
