package com.charlie.upload;

import java.io.*;

public class StreamUtils {
    /**
     * function: convert InputStream to byte[] array
     * can read a file data content into byte[] array
     *
     * @param is
     * @return
     * @throws Exception
     */
    public static byte[] streamToByteArray(InputStream is) throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();    //create output stream object
        byte[] b = new byte[1024];  //byte[] can store input stream data
        int len;
        while ((len = is.read(b)) != -1) {   //loop for reading input stream data into byte[]
            bos.write(b, 0, len);   //then write th byte[] b data into bos
        }
        byte[] array = bos.toByteArray();   //convert bos to byte[] array
        bos.close();    //close bos resource
        return array;   //return byte[] array which contain all input stream data
    }

    /**
     * function: convert InputStream to String
     * @param is
     * @return
     * @throws Exception
     */
    public static String streamToString(InputStream is) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder builder = new StringBuilder();    //for receive every line from input
        String line;
        while ((line = reader.readLine()) != null) {
            builder.append(line + "\r\n");  //note: \r\n can avoid readLine() method block waiting
        }
        return builder.toString();
    }
}
