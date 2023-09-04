package com.pharamdrive.controller;

import com.pharamdrive.models.File;
import com.pharamdrive.repository.FileRepository;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
@RestController(value = "/")
public class fileController {
private final FileRepository fileRepository;

    public fileController(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @PostMapping(value="addFile")
    public File createFile (MultipartFile file) throws IOException {
        File file1=new File();
        file1.setFileBinary(new Binary(BsonBinarySubType.BINARY, file.getBytes()));
       return fileRepository.save(file1);



    }

}
