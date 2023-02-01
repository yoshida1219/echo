package com.example.echo.api;

import com.amazonaws.auth.AWSCredentials;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;

public class S3Upload {
    public void S3uploader(String foldername){
        S3Upload myf = new S3Upload();
        AmazonS3 s3client = myf.authS3();

        File file = new File("C:/Users/202127/Desktop/temp/ぼっちちゃん2.jpg");
        String key = foldername + "/sample03.png";
        myf.uploadFile(s3client, key, file);

    }

    public void uploadFile(AmazonS3 s3client, String key ,File file){
        
        s3client.putObject(new PutObjectRequest("skpacket", key, file));
        
    }

    public void downloadFile(AmazonS3 s3client)throws IOException{

        S3Object object = s3client.getObject(new GetObjectRequest("skpacket", "thumbnail/image.png"));

        FileOutputStream fos = new FileOutputStream(new File("C:/Users/202152/Desktop/api_test/demo/src/main/java/com/example/demo/api/delight.jpg"));
        IOUtils.copy(object.getObjectContent(), fos);
        String url = s3client.getUrl("skpacket", "thumbnail/image.png").toString();
        System.out.println(url);
        fos.close();

    }

    public AmazonS3 authS3(){
        key k = new key();
        AWSCredentials credentials = new BasicAWSCredentials(k.getAWSAccess(), k.getAWSSecret());

        AmazonS3 s3client = AmazonS3ClientBuilder.standard()
             .withCredentials(new AWSStaticCredentialsProvider(credentials))
             .withRegion("ap-northeast-1")
             .build();
        return s3client;
    }
}
