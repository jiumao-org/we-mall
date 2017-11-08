package org.jiumao.common.AsynHttp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.concurrent.FutureCallback;
import org.jiumao.common.constants.LoggerName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractListener implements
		FutureCallback<HttpResponse> {

    private static final Logger logger = LoggerFactory
            .getLogger(LoggerName.Server);
	private String url;

	public AbstractListener(String url) {
		this.url = url;
	}

	@Override
	public void cancelled() {
		logger.warn("url :" + url + " cancelled");
	}

	@Override
	public void failed(Exception arg0) {
		logger.warn("url :" + url + " failed");
	}

	@Override
	public void completed(HttpResponse res) {
		StringBuilder sb = new StringBuilder(128);
		Header encode = res.getEntity().getContentEncoding();
		Charset charset = HttpClientUtil.getEncode(null == encode ? null
				: encode.getValue());
		try (BufferedReader in = new BufferedReader(new InputStreamReader(res
				.getEntity().getContent(), charset));) {
			String line;
			while ((line = in.readLine()) != null) {
				sb.append(line);
			}
		} catch (UnsupportedOperationException | IOException e) {
			logger.error(res.getStatusLine() + " url :" + url, e);
		}
		getResource(sb.toString());
	}

	/**
	 * 回调传入数据源字符串，或者为空
	 * @param resource
	 */
	public abstract void getResource(String resource);
}
