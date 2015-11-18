package com.webapp.rest;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;

public class AWS {

	//Access Key ID: AKIAIZM62YVO4GGIHORQ
	//Secret Access Key: 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
	private AWSCredentials credentials = new BasicAWSCredentials("AKIAIZM62YVO4GGIHORQ", "rYy5WmWfAeXzjaF1pBAW/ma2D75K+w14sdKve0Ut");
	private AmazonS3 s3Client = new AmazonS3Client(credentials);
	String VideoCategory;
	List<String> ListOfItems = new ArrayList<String>();
	
	public List<String> ListVideos(String VideoCategory){
		
		String Prefix = VideoCategory + "/";
		
		ObjectListing objectListing = s3Client.listObjects(new ListObjectsRequest()
                .withBucketName("shank7485")
                .withPrefix(Prefix)
                .withDelimiter("/"));
		
        for (S3ObjectSummary objectSummary : objectListing.getObjectSummaries()) {
            //System.out.println(objectSummary.getKey());
            String[] parts = objectSummary.getKey().split("/");
            try {
            ListOfItems.add(parts[1]); 
            } catch (IndexOutOfBoundsException e){
            	//Do nothing.
            }
        }
        return ListOfItems;
	}
	
}
