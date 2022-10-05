package com.dbproject.cvapp.service;

import com.dbproject.cvapp.exception.FileStorageException;
import com.dbproject.cvapp.exception.MyFileNotFoundException;
import com.dbproject.cvapp.model.DBFile;
import com.dbproject.cvapp.repository.DBFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DBFileStorageService {
    private final DBFileRepository dbFileRepository;

    public DBFile storeFile(MultipartFile file) throws FileStorageException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try{
            if(fileName.contains("..")){
                throw new FileStorageException(fileName);
            }
            DBFile dbFile= new DBFile(fileName, file.getContentType(), file.getBytes());
            return dbFileRepository.save(dbFile);
        } catch (IOException ex){
            throw new FileStorageException(fileName);

        }
    }

    public DBFile getFile(Integer fileId) throws MyFileNotFoundException {
        Optional<DBFile> optionalDBFile = dbFileRepository.findById(fileId);
        if(optionalDBFile.isEmpty()) throw new MyFileNotFoundException(fileId);
        else return optionalDBFile.get();
    }
}
