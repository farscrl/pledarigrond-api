package ch.pledarigrond.api.services.impl;

import ch.pledarigrond.api.services.S3BackupService;
import ch.pledarigrond.common.config.PgEnvironment;
import ch.pledarigrond.common.data.common.Language;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.PutObjectResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class S3BackupServiceImpl implements S3BackupService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final PgEnvironment pgEnvironment;

    private final AmazonS3 s3Client;

    public S3BackupServiceImpl(@Autowired PgEnvironment pgEnvironment) {
        this.pgEnvironment = pgEnvironment;
        AWSCredentials credentials = new BasicAWSCredentials(pgEnvironment.getS3AccessKey(), pgEnvironment.getS3SecretKey());
        s3Client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(pgEnvironment.getS3Endpoint(), ""))
                .build();

        logger.info("Available S3 buckets: ");
        List<Bucket> buckets = s3Client.listBuckets();
        for(Bucket bucket : buckets) {
            logger.info("  * " + bucket.getName());
        }
    }

    @Override
    public void uploadFile(String dbName, File file) {
        PutObjectResult result = s3Client.putObject(pgEnvironment.getS3Bucket(), dbName + "/" +file.getName(), file);
        logger.info("Uploaded file to S3: " + file.getName());
    }
}
