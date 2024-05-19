package org.example.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InReader {
    BufferedReader bfn;

    public InReader(){
        bfn = new BufferedReader(
                new InputStreamReader(System.in));
    }

    public String readLine() throws IOException {
        return bfn.readLine();
    }

    public int read() throws IOException {
        String str = readLine();
        return Integer.parseInt(str);
    }

    public String[] readNextLines(int n) throws IOException {
        String[] res = new String[n];
        for(int i=0;i<n;i++){
            res[i] = readLine();
        }
        return res;
    }
}
