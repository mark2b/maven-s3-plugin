package net.mem2b.maven.s3.plugin;

import org.apache.maven.plugin.MojoExecutionException;
import org.jets3t.service.S3ServiceException;
import org.jets3t.service.model.S3Bucket;
import org.jets3t.service.model.S3Object;

/**
 * @goal object-list
 */
public class ObjectListMojo extends AbstractS3Mojo {
	/**
	 * @parameter expression="${bucketName}"
	 * @required
	 */
	protected String bucketName;

	public void execute() throws MojoExecutionException {
		try {
			S3Bucket bucket = new S3Bucket(this.bucketName);
			S3Object[] objects = getS3().listObjects(bucket);
			for (S3Object object : objects) {
				System.out.println(object.getKey());
			}
		} catch (S3ServiceException e) {
			throw new MojoExecutionException(e.getMessage());
		}
	}
}
