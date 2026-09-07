package dena.api.common.restserviceproxy;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandler;

import lombok.extern.slf4j.Slf4j;
import r01f.httpclient.HttpConcurrentRetryableRequestExecutor;
import r01f.httpclient.HttpConcurrentRetryableRequestExecutor.HttpRetryableRequestExecResult;
import r01f.objectstreamer.Marshaller;
import r01f.services.interfaces.ProxyForRESTImplementedService;
import r01f.types.url.Host;
import r01f.types.url.Url;
import r01f.types.url.UrlPath;
import r01f.types.url.UrlQueryString;
import r01f.types.url.Urls;

@Slf4j
public abstract class DN00ClientAPIRESTServiceProxyBase
	       implements ProxyForRESTImplementedService {
/////////////////////////////////////////////////////////////////////////////////////////
//	FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * An HTTP request executor that can retry failed requests (e.g. due to network issues)
	 * BEWARE! this executor should be REUSED across all the REST service proxies 
	 * 		   because it keeps a pool of connections and reuses them for subsequent requests
	 */
	protected final HttpConcurrentRetryableRequestExecutor _httpReqExecutor;
	/**
	 * Model objects marshaller used to serialize/deserialize model objects to/from JSON
	 */
	protected final Marshaller _modelObjectsMarshaller;
	/**
	 * The base URL of the REST service, which is used to construct the full endpoint URLs for the service's resources
	 */
	protected final Url _baseUrl;
/////////////////////////////////////////////////////////////////////////////////////////
//	CONSTRUCTOR
/////////////////////////////////////////////////////////////////////////////////////////	
	protected DN00ClientAPIRESTServiceProxyBase(final HttpConcurrentRetryableRequestExecutor httpReqExecutor,
												final Marshaller modelObjectsMarshaller,
												final Url baseUrl) {
		_httpReqExecutor = httpReqExecutor;
		_modelObjectsMarshaller = modelObjectsMarshaller;
		_baseUrl = baseUrl;
	}
	public DN00ClientAPIRESTServiceProxyBase(final HttpConcurrentRetryableRequestExecutor httpReqExecutor,
											 final Marshaller modelObjectsMarshaller,
											 final Host remoteHost,final UrlPath basePath) {
		this(httpReqExecutor,
			 modelObjectsMarshaller,
			 Urls.join(remoteHost,basePath));
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Composes the complete REST endpoint URI for a path
	 * @param path
	 * @return
	 */
	protected Url _composeURIFor(final UrlPath path) {
		return Urls.join(_baseUrl,
						 path);
	}
	/**
	 * Composes the complete REST endpoint URI for a path
	 * @param path
	 * @param qryString
	 * @return
	 */
	protected Url _composeURIFor(final UrlPath path,
								 final UrlQueryString qryString) {
		return Urls.join(_baseUrl,
						 path,
						 qryString);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	EXECUTE REQUEST final 
/////////////////////////////////////////////////////////////////////////////////////////	
	protected String _executeJsonPOSTRequest(final Url url,
											 final String jsonBody) throws IOException {
		return _executeJsonPOSTRequest(url,
									   jsonBody,
									   HttpResponse.BodyHandlers.ofString(),String.class);
	}
	protected <T> T _executeJsonPOSTRequest(final Url url,
											 final String jsonBody,
											 final BodyHandler<T> responseBodyHandler,final Class<T> responseType) throws IOException {
		try {
			HttpRequest req = HttpRequest.newBuilder()
										 .uri(url.asUriNoThrow())
	 	                    			 .header("Content-Type","application/json")
	 	                    			 .header("Accept","application/json")
	 	                    			 .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
	 	                    			 .build();
			HttpRetryableRequestExecResult<T> result = _httpReqExecutor.returningResultOfType(responseType)
															   		   .forRequest(req)
															   		   .withDefaultTimeouts()
															   		   .retring(2)
															   		   .withDefaultIdempotencyKey()
															   		   .sendAndhandleResponseWith(responseBodyHandler);
			log.info("POST {} -> HTTP {} ({} attempt(s))",
	 	    		 url,
	 				 result.getResponse().statusCode(),result.getNumberOfAttempts());
			return result.getResponse()
						 .body();
		} catch (InterruptedException interruptedEx) {
			// When the Interrupted exception is cached, the [interrupt flag] is cleared (set to FALSE)
			// Clean up resources if needed...
			
			// Restore the flag to TRUE so callers/frameworks know an interrupt occurred
			// (if the flag is NOT restored, thread pools -like ExecutorService- may fail to shut down properly)
			Thread.currentThread()
				  .interrupt();
			log.error("Error creating pull from admin bespoke job: {}",
					  interruptedEx.getMessage(),interruptedEx);
			
			throw new IOException("The thread executing the HTTP Request was interrupted calling the REST service at " + url + ": " + interruptedEx.getMessage());
		}
	}
	protected String _executeJSONGETRequest(final Url url) throws IOException {
		return _executeJSONGETRequest(url,
									  HttpResponse.BodyHandlers.ofString(),String.class);
	}
	protected <T> T _executeJSONGETRequest(final Url url,
										   final BodyHandler<T> responseBodyHandler,final Class<T> responseType) throws IOException {
		try {
			HttpRequest req = HttpRequest.newBuilder()
										 .uri(url.asUriNoThrow())
	 	                    			 .header("Content-Type","application/json")
	 	                    			 .header("Accept","application/json")
	 	                    			 .GET()
	 	                    			 .build();
			HttpRetryableRequestExecResult<T> result = _httpReqExecutor.returningResultOfType(responseType)
															   		   .forRequest(req)
															   		   .withDefaultTimeouts()
															   		   .retring(2)
															   		   .withDefaultIdempotencyKey()
															   		   .sendAndhandleResponseWith(responseBodyHandler);
			log.info("{} {} -> HTTP {} ({} attempt(s))",
	 	    		 req.method(),req.uri(),
	 				 result.getResponse().statusCode(),result.getNumberOfAttempts());
			return result.getResponse()
						 .body();
		} catch (InterruptedException interruptedEx) {
			// When the Interrupted exception is cached, the [interrupt flag] is cleared (set to FALSE)
			// Clean up resources if needed...
			
			// Restore the flag to TRUE so callers/frameworks know an interrupt occurred
			// (if the flag is NOT restored, thread pools -like ExecutorService- may fail to shut down properly)
			Thread.currentThread()
				  .interrupt();
			log.error("Error creating pull from admin bespoke job: {}",
					  interruptedEx.getMessage(),interruptedEx);
			
			throw new IOException("The thread executing the HTTP Request was interrupted calling the REST service at " + url + ": " + interruptedEx.getMessage());
		}
	}
}
