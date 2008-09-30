package net.mem2b.maven.s3.plugin;

import org.apache.maven.plugin.MojoExecutionException;
import org.jets3t.service.S3ServiceException;

/**
 * @goal delete-bucket
 */
public class DeleteBucketMojo extends AbstractS3Mojo {
	/**
	 * @parameter expression="${bucketName}"
	 * @required
	 */
	protected String bucketName;

	public void execute() throws MojoExecutionException {
		try {
			getS3().deleteBucket(bucketName);
		} catch (S3ServiceException e) {
			throw new MojoExecutionException(e.getMessage());
		}
	}
}
