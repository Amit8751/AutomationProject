package com.hm.framework.utilities;

import com.hm.framework.config.Settings;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class LogUtil {

    //File format for the log file name


    private BufferedWriter bufferedWriter = null;
    ZonedDateTime date = ZonedDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyyHHMMSS");
    String fileNameFormat = date.format(formatter);


    //Create Log file

    public  void  CreateLogFile() throws IOException {
        try{
            File dir = new File(Settings.LogPath);
            if(!dir.exists())
                dir.mkdir();
            //Create File
            File logFile = new File(dir+"/" +fileNameFormat +".log");

            FileWriter fileWriter = new FileWriter(logFile.getAbsoluteFile());
            bufferedWriter = new BufferedWriter(fileWriter);

        } catch (Exception e){

        }
    }

    //Write message within a log
    public  void WriteMessage(String message)
    {
       try
       {
           bufferedWriter.write(message);
           bufferedWriter.newLine();
           bufferedWriter.flush();

       } catch (Exception e)
       {

       }
    }
    public void CloseFile() throws IOException {

        bufferedWriter.close();
    }



}
