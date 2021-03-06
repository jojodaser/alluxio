/*
 * The Alluxio Open Foundation licenses this work under the Apache License, version 2.0
 * (the "License"). You may not use this work except in compliance with the License, which is
 * available at www.apache.org/licenses/LICENSE-2.0
 *
 * This software is distributed on an "AS IS" basis, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied, as more fully set forth in the License.
 *
 * See the NOTICE file distributed with this work for information regarding copyright ownership.
 */

package alluxio.security.authentication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.sasl.SaslClient;
import javax.security.sasl.SaslException;

/**
 * Abstract {@link SaslClientHandler} implementation that maintains {@link SaslClient} instance.
 */
public abstract class AbstractSaslClientHandler implements SaslClientHandler {
  private static final Logger LOG = LoggerFactory.getLogger(AbstractSaslClientHandler.class);

  /** Underlying SaslClient. */
  protected SaslClient mSaslClient;

  @Override
  public SaslClient getSaslClient() {
    return mSaslClient;
  }

  @Override
  public void close() {
    if (mSaslClient != null) {
      try {
        mSaslClient.dispose();
      } catch (SaslException exc) {
        LOG.debug("Failed to close SaslClient.", exc);
      }
    }
  }
}
