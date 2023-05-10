package com.epam.mjc.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;


public class FileReader {

    public Profile getDataFromFile(File file) {
        Profile newProfile = new Profile();

        try (FileInputStream fis = new FileInputStream(file)){
            int i;
            StringBuilder sb = new StringBuilder();
            while ((i =fis.read()) != -1){
            sb.append((char)i);
            }
            String data = sb.toString();
            newProfile = createProfile(data);
        } catch (IOException e){
            e.printStackTrace();
        }

        return newProfile;
    }

    public Map<String, String> getProfileMap(String data) {
        Map<String, String> profileMap = new LinkedHashMap<>();
        String[] dataArray = data.split("\r\n");
            for (String str: dataArray){
                String [] profileString = str.split(": ");
                profileMap.put(profileString[0],profileString[1]);
            }
            return profileMap;
    }

    public Profile createProfile(String data) throws FileBadFormatException {
        Profile newProfile = new Profile();
        Map<String, String> profileMap = getProfileMap(data);
        for(Map.Entry<String, String> entry: profileMap.entrySet()){
            switch (entry.getKey()){
                case "Name":
                    newProfile.setName(entry.getValue());
                    break;
                case "Age":
                    newProfile.setAge(Integer.parseInt(entry.getValue()));
                    break;
                case "Email":
                    newProfile.setEmail(entry.getValue());
                    break;
                case "Phone":
                    newProfile.setPhone(Long.parseLong(entry.getValue()));
                    break;
                default:
                    throw new FileBadFormatException("The data in the file has a bad format" + entry.getKey() );
            }
        }
        return newProfile;
    }
}

