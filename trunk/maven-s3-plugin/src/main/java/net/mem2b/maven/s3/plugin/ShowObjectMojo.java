package net.mem2b.maven.s3.plugin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.maven.plugin.MojoExecutionException;
import org.jets3t.service.S3ServiceException;
import org.jets3t.service.model.S3Bucket;
import org.jets3t.service.model.S3Object;

/**
 * @goal show-object
 */
public class ShowObjectMojo extends AbstractS3Mojo {
	/**
	 * @parameter expression="${bucketName}"
	 * @required
	 */
	protected String bucketName;

	/**
	 * @parameter expression="${objectKey}"
	 * @required
	 */
	protected String objectKey;

	@SuppressWarnings("unchecked")
	public void execute() throws MojoExecutionException {
		try {
			S3Bucket bucket = new S3Bucket(bucketName);
			S3Object object = getS3().getObjectDetails(bucket, objectKey);
			SortedMap<String, String> metadata = new TreeMap<String, String>(object.getMetadataMap());
			for (Map.Entry<String, String> entry : metadata.entrySet()) {
				System.out.println(String.format("%s : %s", entry.getKey(), entry.getValue()));
			}
			object = getS3().getObject(bucket, objectKey);
			InputStream inputStream = object.getDataInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			String line = null;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			throw new MojoExecutionException(e.getMessage());
		} catch (S3ServiceException e) {
			throw new MojoExecutionException(e.getMessage());
		}
	}
}
