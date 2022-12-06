package com.example.neurodiagnosis.infrastructure.repositories;

import com.example.neurodiagnosis.application.interfaces.repositories.IMriScansRepository;
import com.example.neurodiagnosis.application.service.database.IDatabaseContext;
import com.example.neurodiagnosis.domain.entities.Mri;
import com.example.neurodiagnosis.infrastructure.repositories.base.BaseRepository;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.simple.JSONObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

import java.nio.file.Files;
import java.util.*;


@Named("mriScansRepository")
@SessionScoped
public class MriScansRepository extends BaseRepository
        implements IMriScansRepository, Serializable {
    @Inject
    public MriScansRepository(@Named("DatabaseContextLive") IDatabaseContext databaseContext) {
        super(databaseContext);
    }

    @Override
    public Mri addNewScanEntry(UUID usedId, File photo) throws IOException {
        Mri mri = new Mri();
        mri.setUserId(usedId);
        mri.setId(UUID.randomUUID());

        byte[] bytes = Files.readAllBytes(photo.toPath());
        getMlResults(bytes);
//
//        for(int i=0; i<bytes.length; i++)
//            System.out.println(bytes[i]);
        try {
            mri.setImage(Files.readAllBytes(photo.toPath()));
        }catch (Exception e){
            return null;
        }

        //TODO: MRI posibil remote service?
        try {

            if (!em.getTransaction().isActive()) {
                em.getTransaction().begin();
            }
            em.persist(mri);
            em.getTransaction().commit();
            return mri;

        } catch (Exception e){
            return null;
        }
    }

    @Override
    public void getMlResults(byte[] picture) throws IOException {
        try {
            //picture = scale(picture, 48, 48);
            String encoded = Base64.getEncoder().encodeToString(picture);
            System.out.println(encoded);
            String url1 = "http://127.0.0.1:5000/getAnalysis";

            String body = new JSONObject()
                    .put("imageAsBase64",encoded).toString();
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url1);
            httpPost.setHeader("Content-type", "application/json");
            try {
                StringEntity stringEntity = new StringEntity(body);
                httpPost.getRequestLine();
                httpPost.setEntity(stringEntity);

                httpClient.execute(httpPost);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    public byte[] scale(byte[] fileData, int width, int height) {
        ByteArrayInputStream in = new ByteArrayInputStream(fileData);
        try {
            BufferedImage img = ImageIO.read(in);
            Image scaledImage = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            BufferedImage imageBuff = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            imageBuff.getGraphics().drawImage(scaledImage, 0, 0, new Color(0,0,0), null);

            ByteArrayOutputStream buffer = new ByteArrayOutputStream();

            return buffer.toByteArray();
        } catch (IOException e) {
            System.out.println(e);
        }
        return null;
    }
}
