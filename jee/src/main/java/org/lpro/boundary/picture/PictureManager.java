/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lpro.boundary.picture;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import javax.inject.Inject;

import org.lpro.control.RandomToken;
import org.lpro.entity.Picture;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.core.MultivaluedMap;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.lpro.boundary.serie.SerieManager;

/**
 *
 * @author morgan
 */
public class PictureManager {
    
    @PersistenceContext
    EntityManager em;
    
    @Inject
    SerieManager sm;
    
    public Picture save(Picture p){
        p.setId(UUID.randomUUID().toString());
        return this.em.merge(p);
    }
    
    public boolean upload(MultipartFormDataInput input, String serie_id){
        File img = new File("/opt/jboss/wildfly/standalone/tmp/img/"+serie_id+"/");
        if (!img.exists()) {
            try {
                img.mkdir();
            } catch(SecurityException se) { }
        }
        
        Set<Picture> pics = this.sm.findById(serie_id).getPicture();
        List<String> pics_name = new ArrayList();
        for (Picture p : pics) {
            pics_name.add(p.getUrl());
        }

        Map<String, List<InputPart>> formulaire = input.getFormDataMap();
        List<InputPart> inputParts = formulaire.get("file");
        
        for(int i= 0; i < inputParts.size(); i++){
            MultivaluedMap<String, String> headers = inputParts.get(i).getHeaders();
            String filename = getFileName(headers, pics_name.get(i).replaceFirst("[.][^.]+$", ""));
            
            try {
                InputStream is = inputParts.get(i).getBody(InputStream.class,null);
                byte[] bytes = PictureManager.toByteArray(is);
                System.out.println(filename);
                writeFile(bytes,"/opt/jboss/wildfly/standalone/tmp/img/"+serie_id+"/"+filename);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        
        return true;
    }
    
    public static byte[] toByteArray(InputStream is) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            byte[] b = new byte[4096];
            int n = 0;
            while ((n = is.read(b)) != -1) {
                output.write(b, 0, n);
            }
            return output.toByteArray();
        } finally {
            output.close();
        }
    }
    
    private void writeFile(byte[] contenu, String filename) throws IOException {
        
        File file = new File(filename);
        
        FileOutputStream fop = new FileOutputStream(file);

        fop.write(contenu);
        fop.flush();
        fop.close();
    }

    private String getFileName(MultivaluedMap<String, String> headers, String s) {

        String[] contenuHeader = headers.getFirst("Content-Disposition").split(";");

        for (String filename : contenuHeader) {
            if ((filename.trim().startsWith("filename"))) {
                String[] name = filename.split("=");
                String tmp = name[1].trim().replaceAll("\"", "");
                String ext = tmp.substring(tmp.lastIndexOf("."), tmp.length());
                String nom = s;

                return nom + ext;
            }
        }

        return "inconnu";
    }
}
