package net.mem2b.maven.s3.plugin;

import org.apache.maven.plugin.AbstractMojo;
import org.jets3t.service.S3Service;
import org.jets3t.service.S3ServiceException;
import org.jets3t.service.impl.rest.httpclient.RestS3Service;
import org.jets3t.service.security.AWSCredentials;

/**
 * @requiresProject false
 */
abstract class AbstractS3Mojo extends AbstractMojo {
	/**
	 * @parameter expression="${accessKey}"
	 * @required
	 */
	protected String accessKey;
	/**
	 * @parameter expression="${secretKey}"
	 * @required
	 */
	protected String secretKey;

	protected S3Service getS3() throws S3ServiceException {
		AWSCredentials credentials = new AWSCredentials(accessKey, secretKey);
		S3Service s3 = new RestS3Service(credentials);
		return s3;
	}
}
