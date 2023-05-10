package com.epam.mjc.nio;
import java.io.IOException;

public class FileBadFormatException extends IOException {
    public FileBadFormatException(String a) {
        super(a);
    }
}
