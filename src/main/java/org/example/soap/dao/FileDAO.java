package org.example.soap.dao;

import org.example.soap.models.File;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FileDAO {

    Conexion conexion;

    public FileDAO() {
        conexion = new Conexion();
    }

    public File findFile(String uuid) {
        Connection accessBD = conexion.getConnection();
        File fileDTO = null;
        try {
            PreparedStatement ps = accessBD.prepareStatement(
                    "SELECT id, uuid, namefile, hash, metadata, archivo FROM file WHERE uuid = ?");
            ps.setString(1, uuid);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                fileDTO = new File();
                fileDTO.setId(rs.getObject("id", Long.class));
                fileDTO.setUuid(rs.getString("uuid"));
                fileDTO.setNamefile(rs.getString("namefile"));
                fileDTO.setHash(rs.getString("hash"));
                fileDTO.setMetadata(rs.getString("metadata"));
                fileDTO.setArchivo(rs.getString("archivo")); // Usa getBytes para blobs
            }
        } catch (Exception e) {
            System.out.println("ERROR " + e.getMessage());
        }
        return fileDTO;
    }

    public List<File> listFile() {
        Connection accessBD = conexion.getConnection();
        List<File> fileList = new ArrayList<>();
        try {
            PreparedStatement ps = accessBD.prepareStatement(
                    "SELECT id, uuid, namefile, hash, metadata, archivo FROM file LIMIT 100");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                File fileDTO = new File();
                fileDTO.setId(rs.getObject("id", Long.class));
                fileDTO.setUuid(rs.getString("uuid"));
                fileDTO.setNamefile(rs.getString("namefile"));
                fileDTO.setHash(rs.getString("hash"));
                fileDTO.setMetadata(rs.getString("metadata"));
                fileDTO.setArchivo(rs.getString("archivo"));
                fileList.add(fileDTO);
            }
        } catch (Exception e) {
            System.out.println("ERROR " + e.getMessage());
        }
        return fileList;
    }
}
