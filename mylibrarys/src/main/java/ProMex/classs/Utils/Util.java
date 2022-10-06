package ProMex.classs.Utils;

public class Util {

    public static String pizzuhead = "1767E58093290EEF8795F6DDED6B9225";
    public static String pizzudians = "1B42767E701234D07B82E99B2B46B201739C0B15268073C6F5B3BD80356431EDAAB5927FE277DCCF45923B5D27F79F8F0A698004D1A9082701771824CC17E8533026857F898170DBD5F0CDBDBE2B941DEA44DF061FC56CDA3AE9D7A9D08103B5";
    public static String pizzuli = "4B6C48DC91865D8844F7BB4F4F3CE7E5BDFA14583972BFD2527BE761939F941C4BA5F60DB66C1696101195A415A5D2D6C934CB5282BB9625300CE9D6EFAC7641";


    public static String DEc(String aa) {
        String decrypted = "";
        try {
            decrypted = apiii.decrypt(aa);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decrypted;
    }
}

