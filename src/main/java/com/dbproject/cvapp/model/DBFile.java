package com.dbproject.cvapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="files")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DBFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String fileName;
    private String fileType;

    @Lob
    private byte[] data;

    public DBFile(String fileName, String fileType, byte[] data){
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
    }

}
