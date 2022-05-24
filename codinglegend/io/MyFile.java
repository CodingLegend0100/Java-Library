package codinglegend.io;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class MyFile {
  File file;

  public MyFile(String filepath){
    file = new File(filepath);
  }

  public MyFile(File f){
    file = f;
  }

  /** Creates an empty file with the specified path 
   * @return true if the file was created
  */
  public boolean create(){
    return create("");
  }

  /** Creates a file with the specified path and writes the text to it
   * @return true if the file was created
  */
  public boolean create(String text) {
    try {
      if (file.createNewFile()){
        overwrite(text);
        return true;
      }
    } catch (IOException e){
    }
    return false;
  }

  /** Deletes the file
   * @return true if the file was deleted
   */
  public boolean delete(){
    try {
      return file.delete();
    } catch (Exception e){
      e.printStackTrace();
    }
    return false;
  }

  /** Checks if the file exists */
  public boolean exists(){
    return file.exists();
  }

  //Attempts to overrite a file's data
  //Returns true if succesful, false if not
  public boolean overwrite(){
    return overwrite("");
  }

  /** Overwrites the files existing data */
  public boolean overwrite(String text){
    try {
      FileWriter fileToWrite = new FileWriter(file);
      fileToWrite.write(text);
      fileToWrite.close();

      return true;
    } catch (IOException e){
      e.printStackTrace();
    }
    return false;
  }

  /** Reads the data from the file
   * @return String the data contained within the file
   * @return null if the file could not be read
   */
  public String read(){
    try {
      String data = "";
      Scanner reader = new Scanner(file);
      while (reader.hasNextLine()){
        data += reader.nextLine()+"\n";
      }
      reader.close();
      return data;
    } catch (FileNotFoundException e){
      e.printStackTrace();
    }
    return null;
  }

  /** Reads the data from the file and splits it into an array of strings for each line
   * @return an array of strings
   * @return null if the file could not be read
  */
  public String[] readStrings(){
    String data = read();
    if (data != null) return data.split("\n");
    return null;
  }

  /** Appends text to the file
   * @param text - the text to be appended to the file
   */
  public boolean write(String text){
    try {
      FileWriter fileToWrite = new FileWriter(file,true);
      fileToWrite.write(text);
      fileToWrite.close();
      return true;
    } catch (IOException e){
      e.printStackTrace();
    }
    return false;
  }

  /** Appends text to the file with a new line character at the end */
  public boolean writeLn(String text){
    return write(text+"\n");
  }

}
