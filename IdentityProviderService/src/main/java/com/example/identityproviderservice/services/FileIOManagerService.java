package com.example.identityproviderservice.services;

import com.example.identityproviderservice.interfaces.FileIOManagerServiceInterface;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Service
public class FileIOManagerService implements FileIOManagerServiceInterface
{

    @Override
    public byte[] readFile(String filename) throws IOException
    {
        return Files.readAllBytes(new File(filename).toPath());
    }

    @Override
    public void writeFile(String filename, byte[] bytes) throws IOException
    {
        File file = new File(filename);
        File fileParent = file.getParentFile();
        if (!file.exists()) {
            if (!fileParent.exists()) {
                fileParent.mkdirs();
            }
            file.createNewFile();
        }
        Files.write(file.toPath(), bytes);
    }

    @Override
    public boolean deleteFile(String filename) throws IOException
    {
        return Files.deleteIfExists(new File(filename).toPath());
    }
}