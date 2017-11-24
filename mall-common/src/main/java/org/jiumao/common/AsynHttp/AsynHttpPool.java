package org.jiumao.common.AsynHttp;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.CodingErrorAction;
import java.util.Arrays;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;

import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CookieStore;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.MessageConstraints;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.DnsResolver;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.impl.DefaultHttpResponseFactory;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.conn.SystemDefaultDnsResolver;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.codecs.DefaultHttpRequestWriterFactory;
import org.apache.http.impl.nio.codecs.DefaultHttpResponseParser;
import org.apache.http.impl.nio.codecs.DefaultHttpResponseParserFactory;
import org.apache.http.impl.nio.conn.ManagedNHttpClientConnectionFactory;
import org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager;
import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicLineParser;
import org.apache.http.message.LineParser;
import org.apache.http.nio.NHttpMessageParser;
import org.apache.http.nio.NHttpMessageParserFactory;
import org.apache.http.nio.NHttpMessageWriterFactory;
import org.apache.http.nio.conn.ManagedNHttpClientConnection;
import org.apache.http.nio.conn.NHttpClientConnectionManager;
import org.apache.http.nio.conn.NHttpConnectionFactory;
import org.apache.http.nio.conn.NoopIOSessionStrategy;
import org.apache.http.nio.conn.SchemeIOSessionStrategy;
import org.apache.http.nio.conn.ssl.SSLIOSessionStrategy;
import org.apache.http.nio.reactor.ConnectingIOReactor;
import org.apache.http.nio.reactor.IOReactorException;
import org.apache.http.nio.reactor.SessionInputBuffer;
import org.apache.http.nio.util.HeapByteBufferAllocator;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.CharArrayBuffer;

/**
 * @see {@linkplain http://hc.apache.org/httpcomponents-asyncclient-dev/}
 * <P>使用参考 {@link AsynHttpClient} 
 * @author ppf@jiumao.org
 * @date 2017年9月5日
 */
public final class AsynHttpPool {
	private static NHttpClientConnectionManager connManager = null;
	private static CredentialsProvider credentialsProvider = null;
	private static RequestConfig defaultRequestConfig = null;
	private static NHttpMessageParserFactory<HttpResponse> responseParserFactory;
	private static NHttpMessageWriterFactory<HttpRequest> requestWriterFactory;
	private static NHttpConnectionFactory<ManagedNHttpClientConnection> connFactory;
	private static CookieStore cookieStore = new BasicCookieStore();;

	static {

		responseParserFactory = new DefaultHttpResponseParserFactory() {
			@Override
			public NHttpMessageParser<HttpResponse> create(
					final SessionInputBuffer buffer,
					final MessageConstraints constraints) {
				LineParser lineParser = new BasicLineParser() {

					@Override
					public Header parseHeader(final CharArrayBuffer buffer) {
						try {
							return super.parseHeader(buffer);
						} catch (ParseException ex) {
							return new BasicHeader(buffer.toString(), null);
						}
					}
				};
				return new DefaultHttpResponseParser(buffer, lineParser,
						DefaultHttpResponseFactory.INSTANCE, constraints);
			}
		};
		requestWriterFactory = new DefaultHttpRequestWriterFactory();

		connFactory = new ManagedNHttpClientConnectionFactory(
				requestWriterFactory, responseParserFactory,
				HeapByteBufferAllocator.INSTANCE);

		SSLContext sslcontext = SSLContexts.createSystemDefault();
		HostnameVerifier hostnameVerifier = new DefaultHostnameVerifier();

		Registry<SchemeIOSessionStrategy> sessionStrategyRegistry = RegistryBuilder
				.<SchemeIOSessionStrategy> create()
				.register("http", NoopIOSessionStrategy.INSTANCE)
				.register("https",
						new SSLIOSessionStrategy(sslcontext, hostnameVerifier))
				.build();

		DnsResolver dnsResolver = new SystemDefaultDnsResolver() {
			@Override
			public InetAddress[] resolve(final String host)
					throws UnknownHostException {
				if (host.equalsIgnoreCase("localhost")) {
					return new InetAddress[] { InetAddress
							.getByAddress(new byte[] { 127, 0, 0, 1 }) };
				} else {
					return super.resolve(host);
				}
			}

		};

		// Create I/O reactor configuration
		IOReactorConfig ioReactorConfig = IOReactorConfig.custom()
				.setIoThreadCount(Runtime.getRuntime().availableProcessors())
				.setConnectTimeout(30000).setSoTimeout(30000).build();

		// Create a custom I/O reactort
		ConnectingIOReactor ioReactor;
		try {
			ioReactor = new DefaultConnectingIOReactor(ioReactorConfig);
		} catch (IOReactorException e) {
			throw new RuntimeException(e);
		}

		// Create a connection manager with custom configuration.
		PoolingNHttpClientConnectionManager connManager = new PoolingNHttpClientConnectionManager(
				ioReactor, connFactory, sessionStrategyRegistry, dnsResolver);

		// Create message constraints
		MessageConstraints messageConstraints = MessageConstraints.custom()
				.setMaxHeaderCount(200).setMaxLineLength(2000).build();
		// Create connection configuration
		ConnectionConfig connectionConfig = ConnectionConfig.custom()
				.setMalformedInputAction(CodingErrorAction.IGNORE)
				.setUnmappableInputAction(CodingErrorAction.IGNORE)
				.setCharset(Consts.UTF_8)
				.setMessageConstraints(messageConstraints).build();
		connManager.setDefaultConnectionConfig(connectionConfig);
		connManager.setMaxTotal(100);
		connManager.setDefaultMaxPerRoute(10);
		connManager.setMaxPerRoute(new HttpRoute(new HttpHost("somehost", 80)),
				20);

		credentialsProvider = new BasicCredentialsProvider();
		defaultRequestConfig = RequestConfig
				.custom()
				.setCookieSpec(CookieSpecs.DEFAULT)
				.setExpectContinueEnabled(true)
				.setTargetPreferredAuthSchemes(
						Arrays.asList(AuthSchemes.NTLM, AuthSchemes.DIGEST))
				.setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC))
				.build();

	}

	public static void setBasicAuth(String host, int port, String userName,
			String password) {
		credentialsProvider.setCredentials(new AuthScope(host, port),
				new UsernamePasswordCredentials(userName, password));
	}

	public static RequestConfig createRequestConfig(HttpHost proxy) {
		return RequestConfig.copy(defaultRequestConfig).setSocketTimeout(5000)
				.setConnectTimeout(5000).setConnectionRequestTimeout(5000)
				.setProxy(proxy).build();
	}

	public static void createHttpContext() {
		HttpClientContext localContext = HttpClientContext.create();
		localContext.setCookieStore(cookieStore);
		localContext.setCredentialsProvider(credentialsProvider);
	}

	public static CloseableHttpAsyncClient create(RequestConfig requestConfig) {
		HttpAsyncClientBuilder builder = HttpAsyncClients.custom();
		builder.setConnectionManager(connManager)
				.setDefaultCookieStore(cookieStore)
				.setDefaultCredentialsProvider(credentialsProvider);
		
		if (null != requestConfig) {
			return builder.setDefaultRequestConfig(requestConfig).build();
		} else {
			return builder.setDefaultRequestConfig(defaultRequestConfig)
					.build();
		}
	}

}
