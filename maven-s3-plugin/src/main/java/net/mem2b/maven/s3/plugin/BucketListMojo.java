package net.mem2b.maven.s3.plugin;

import org.apache.maven.plugin.MojoExecutionException;
import org.jets3t.service.S3ServiceException;
import org.jets3t.service.model.S3Bucket;

/**
 * Print bucket list
 * @goal bucket-list
 */
public class BucketListMojo extends AbstractS3Mojo {

	public void execute() throws MojoExecutionException {
		S3Bucket[] buckets;
		try {
			buckets = getS3().listAllBuckets();
			for (S3Bucket bucket : buckets) {
				System.out.println(bucket.getName());
			}
		} catch (S3ServiceException e) {
			throw new MojoExecutionException(e.getMessage());
		}
	}
}
