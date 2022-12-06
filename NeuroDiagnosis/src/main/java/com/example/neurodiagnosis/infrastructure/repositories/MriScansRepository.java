package com.example.neurodiagnosis.infrastructure.repositories;

import com.example.neurodiagnosis.application.interfaces.repositories.IMriScansRepository;
import com.example.neurodiagnosis.application.service.database.IDatabaseContext;
import com.example.neurodiagnosis.domain.entities.Mri;
import com.example.neurodiagnosis.domain.entities.User;
import com.example.neurodiagnosis.infrastructure.repositories.base.BaseRepository;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;

import java.nio.file.Files;
import java.util.*;

import static org.postgresql.core.Oid.JSON;


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
        String mriResults = getMlResults(bytes);

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

            UserRepository userRepository = new UserRepository(getDatabaseContext());

            Optional<User> myUser = Optional.of(em.find(User.class, usedId));

            try {

                myUser.ifPresent(user -> user.setMriScanResult(mriResults));
                EntityManager emUser = userRepository.getEntityManager();
                emUser.getTransaction().begin();
                emUser.persist(myUser.get());
                emUser.getTransaction().commit();
            }
            catch (Exception e){
                System.out.println(e);
            }
            return mri;

        } catch (Exception e){
            return null;
        }
    }

    @Override
    public String getMlResults(byte[] picture) throws IOException {
        try {
            String encoded = Base64.getEncoder().encodeToString(picture);
            System.out.println(encoded);

            String url1 = "http://127.0.0.1:5000/getAnalysis";


            JSONObject body = new JSONObject();
            body.put("imageAsBase64", encoded);
            System.out.println(body);
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url1);
            httpPost.setHeader("Content-type", "application/json");
            try {
                StringEntity stringEntity = new StringEntity(body.toString());
                httpPost.getRequestLine();
                httpPost.setEntity(stringEntity);

                var response =httpClient.execute(httpPost);
                var statistics = EntityUtils.toString(response.getEntity());
                return statistics;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
        catch (Exception e){
            System.out.println(e);
        }
        return null;
    }
}
