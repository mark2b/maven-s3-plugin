package net.mem2b.maven.s3.plugin;

import org.apache.maven.plugin.MojoExecutionException;
import org.jets3t.service.S3ServiceException;

/**
 * @goal delete-object
 */
public class DeleteObjectMojo extends AbstractS3Mojo {
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

	public void execute() throws MojoExecutionException {
		try {
			getS3().deleteObject(this.bucketName, objectKey);
		} catch (S3ServiceException e) {
			throw new MojoExecutionException(e.getMessage());
		}
	}
}
