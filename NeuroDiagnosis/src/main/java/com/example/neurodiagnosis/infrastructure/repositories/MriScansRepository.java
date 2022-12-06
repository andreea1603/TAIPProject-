package com.example.neurodiagnosis.infrastructure.repositories;

import com.example.neurodiagnosis.application.interfaces.repositories.IMriScansRepository;
import com.example.neurodiagnosis.application.service.database.IDatabaseContext;
import com.example.neurodiagnosis.domain.entities.Mri;
import com.example.neurodiagnosis.infrastructure.repositories.base.BaseRepository;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Base64;
import java.util.Scanner;
import java.util.UUID;

import static jdk.internal.net.http.HttpRequestImpl.USER_AGENT;


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

//        byte[] bytes = Files.readAllBytes(photo.toPath());
//        getMlResults(bytes);
//
//        for(int i=0; i<bytes.length; i++)
//            System.out.println(bytes[i]);
//        try {
//            mri.setImage(Files.readAllBytes(photo.toPath()));
//        }catch (Exception e){
//            return null;
//        }

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
        
    }

//    @Override
//    public void getMlResults(byte[] picture) throws IOException {
//        try {
//            picture = scale(picture, 48, 48);
//            String encoded = Base64.getEncoder().encodeToString(picture);
//            System.out.println(encoded);
//            String url1 = "http://127.0.0.1:5000/getAnalysis?photo=" +encoded;
//            URL url = new URL(url1);
//            HttpURLConnection con = (HttpURLConnection) url.openConnection();
//            con.setRequestMethod("GET");
//            con.setRequestProperty("Content-Type", "application/json");
//
//            con.connect();
//            int status = con.getResponseCode();
//
//            System.out.println(status);
//            Reader streamReader = null;
//
//            if (status > 299) {
//                streamReader = new InputStreamReader(con.getErrorStream());
//
//            } else {
//                streamReader = new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8);
//                System.out.println(streamReader);
////                System.out.println(streamReader.read());
////                String result = IOUtils.toString(streamReader);
////                System.out.println(result);
//                Scanner s = new Scanner(streamReader).useDelimiter("\\A");
//                String result1 = s.hasNext() ? s.next() : "";
//                System.out.println(result1);
//            }
//            System.out.println(streamReader);
//
//        }
//        catch (Exception e){
//            System.out.println(e);
//        }
//    }
//    public byte[] scale(byte[] fileData, int width, int height) {
//        ByteArrayInputStream in = new ByteArrayInputStream(fileData);
//        try {
//            BufferedImage img = ImageIO.read(in);
//            Image scaledImage = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
//            BufferedImage imageBuff = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//            imageBuff.getGraphics().drawImage(scaledImage, 0, 0, new Color(0,0,0), null);
//
//            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
//
//            return buffer.toByteArray();
//        } catch (IOException e) {
//            System.out.println(e);
//        }
//        return null;
//    }
}
