package net.mem2b.maven.s3.plugin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.maven.plugin.MojoExecutionException;
import org.jets3t.service.S3ServiceException;
import org.jets3t.service.model.S3Bucket;
import org.jets3t.service.model.S3Object;

/**
 * @goal get-object
 */
public class GetObjectMojo extends AbstractS3Mojo {
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

	/**
	 * @parameter expression="${outputFile}"
	 * @required
	 */
	protected File outputFile;

	public void execute() throws MojoExecutionException {
		try {
			S3Bucket bucket = new S3Bucket(bucketName);
			S3Object object = getS3().getObject(bucket, objectKey);
			InputStream inputStream = object.getDataInputStream();
			FileOutputStream outputStream = new FileOutputStream(outputFile);
			IOUtils.copy(inputStream, outputStream);
			outputStream.flush();
			inputStream.close();
			outputStream.close();
		} catch (IOException e) {
			throw new MojoExecutionException(e.getMessage());
		} catch (S3ServiceException e) {
			throw new MojoExecutionException(e.getMessage());
		}
	}
}
