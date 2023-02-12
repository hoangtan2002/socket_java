import java.io.*;
import java.net.URL;
import java.net.MalformedURLException;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class download_page{
    public static void DownloadPage(String urlPage, String outputFileName){
        try{
            //create URL object 
            URL siteURL = new URL(urlPage);
            //create write and read buffer;
            BufferedReader reader = 
                new BufferedReader(new InputStreamReader(siteURL.openStream()));
            BufferedWriter writer = 
                new BufferedWriter(new FileWriter(outputFileName));
            String line;
            while((line = reader.readLine()) != null){
                writer.write(line);
            } 
            reader.close();
            writer.close();
            System.out.println("The site has been downloaded successfully");
        }
        catch(MalformedURLException mue){
            System.out.println("Invalid URL");
        }
        catch (IOException ie) {
            System.out.println("Site too big to download");
        }
    }
    public static String[] getURLfromFile(String fileName){
        File inputFile = new File(fileName);
        String urlString[] = new String[10];
        try{
            int i = 0;
            Scanner urlScanner = new Scanner(inputFile);
            while(urlScanner.hasNextLine()){
                urlString[i] = urlScanner.nextLine();
                i=i+1;
            }
            urlScanner.close();
        }
        catch(FileNotFoundException e){
            System.out.println("File not found!");
        }
        return urlString;
    }
    public static void main(String args[]){
        String[] url = getURLfromFile("./input.txt");
        String baseFileName = "download_";
        String downloadFileName;
        for(int i=0; i<url.length; i++){
            if(url[i]!=null){
                downloadFileName = baseFileName + Integer.toString(i) + ".html";
                DownloadPage(url[i], downloadFileName);
            }
        }
    }
}