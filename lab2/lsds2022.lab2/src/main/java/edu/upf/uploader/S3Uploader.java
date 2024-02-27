package edu.upf.uploader;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;
import java.io.File;
import java.util.List;

public class S3Uploader implements Uploader {
    private String bucketName;
    private String prefix;
    private String profileName;

    public S3Uploader(String bucketName, String prefix, String profileName){
        this.bucketName = bucketName;
        this.prefix = prefix;
        this.profileName = profileName;
    }
    @Override
    public void upload(List<String> files) {
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new ProfileCredentialsProvider(profileName))
                .build();

        for (String filePath : files) {
            File file = new File(filePath);
            String key = prefix + "/" + file.getName();

            if (!s3Client.doesBucketExistV2(bucketName)) {
                System.out.println("Bucket " + bucketName + " does not exist.");
                return;
            }

            s3Client.putObject(new PutObjectRequest(bucketName, key, file));
            System.out.println("Uploaded file " + file.getName() + " to S3 bucket " + bucketName + " with key " + key);
        }
    }
}
