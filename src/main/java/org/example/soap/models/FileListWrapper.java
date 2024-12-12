package org.example.soap.models;


import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "Files")
public class FileListWrapper {
    private List<File> fileList;
    private File file;

    public FileListWrapper() {}

    public FileListWrapper(List<File> fileList) {
        this.fileList = fileList;
    }

    public FileListWrapper(File fileDTO){
        this.file = fileDTO;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public List<File> getFileList() {
        return fileList;
    }

    public void setFileList(List<File> fileList) {
        this.fileList = fileList;
    }
}
