package org.example.soap.services;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import org.example.soap.dao.FileDAO;
import org.example.soap.models.File;
import org.example.soap.models.FileListWrapper;

@WebService(serviceName = "FileRead")
public class FileRead {

    FileDAO dao = new FileDAO();
    /**
     * Web service operation
     * @return
     */
    @WebMethod(operationName = "getFileUUID")
    public File getFile(@WebParam(name = "uuid") String uuid) {
        File dto = new File();
        dto.setId(1L);
        dto.setUuid("1");
        dto.setNamefile("hola oscar");
        dto.setHash("25");
        dto.setMetadata("yhnahs");
        return dao.findFile(uuid);
    }

    @WebMethod(operationName = "getFileList")
    public FileListWrapper getFileList() {
        return new FileListWrapper(dao.listFile());
    }
}
