package com.omnia.docclassifier.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;

import static java.util.Objects.nonNull;

@Slf4j
public class HttpUtils {

    public static <T> T basicAuthGet(WebClient client, String url, String user, String password, Class resultClass) throws URISyntaxException {
        Flux<T> response = client
                .get()
                .uri(new URI(url))
                .headers(headers -> headers.setBasicAuth(user, password))
                .retrieve()
                .bodyToFlux(resultClass)
                .onErrorResume(e -> {
                    Exception ex = (Exception) e;
                    return Flux.error(ex);
                });
        return (T) response.blockFirst();
    }

    /**
     * HTTP GET with an OAuth bearer token
     * @param client - WebClient
     * @param url - String
     * @param token - String, OAuth bearer token
     * @param resultClass - Class, type of returned data
     * @param <T> - type of returned data
     * @return <T>
     * @throws URISyntaxException
     */
    public static <T> T tokenGet(WebClient client, String url, String token, Class resultClass) throws URISyntaxException {
        Flux<T> response = client
                .get()
                .uri(new URI(url))
                .headers(headers -> headers.setBearerAuth(token))
                .retrieve()
                .bodyToFlux(resultClass)
                .onErrorResume(e -> {
                    Exception ex = (Exception) e;
                    return Flux.error(ex);
                });
        return (T) response.blockFirst();
    }

    /**
     * POST a JSON payload with basic authentication
     * @param client - WebClient
     * @param url - String
     * @param user - String
     * @param password - String
     * @param bodyJson - String
     * @param resultClass - Class
     * @param <T>
     * @return
     * @throws URISyntaxException
     */
    public static <T> T basicAuthJsonPost(WebClient client, String url, String user, String password, String bodyJson, Class resultClass) throws URISyntaxException {

        Mono<T> response = (Mono<T>) client
                .post()
                .uri(new URI(url))
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(bodyJson))
                .headers(headers -> headers.setBasicAuth(user, password))
                .retrieve()
                .bodyToMono(resultClass).checkpoint("at bodyToFlux", true)
                .onErrorResume(e -> {
                    Exception ex = (Exception) e;
                    return Mono.error(ex);
                });
        return (T) response.block();

    }

    public static <T> T jsonPost(WebClient client, String url, String bodyJson, Class resultClass) throws URISyntaxException {

        Mono<T> response = (Mono<T>) client
                .post()
                .uri(new URI(url))
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(bodyJson))
                .retrieve()
                .bodyToMono(resultClass).checkpoint("at bodyToFlux", true)
                .onErrorResume(e -> {
                    Exception ex = (Exception) e;
                    return Mono.error(ex);
                });
        return (T) response.block();
    }

    public static <T> T tokenJsonPost(WebClient client, String url, String bodyJson, String token, Class resultClass) throws URISyntaxException {

        Mono<T> response = (Mono<T>) client
                .post()
                .uri(new URI(url))
                .contentType(MediaType.APPLICATION_JSON)
                .headers(header -> header.setBearerAuth(token))
                .body(BodyInserters.fromValue(bodyJson))
                .retrieve()
                .bodyToMono(resultClass).checkpoint("at bodyToFlux", true)
                .onErrorResume(e -> {
                    Exception ex = (Exception) e;
                    return Mono.error(ex);
                });
        return (T) response.block();
    }

    public static <T> T deleteWithHeader(WebClient client, String url, String hdrKey, String hdrValue, Class resultClass) throws URISyntaxException {

        Mono<T> response = (Mono<T>) client
                .delete()
                .uri(new URI(url))
                .header(hdrKey, hdrValue)
                .retrieve()
                .bodyToMono(resultClass).checkpoint("at bodyToFlux", true)
                .onErrorResume(e -> {
                    Exception ex = (Exception) e;
                    return Mono.error(ex);
                });

        return (T) response.block();
    }

    public static <T> T getWithHeader(WebClient client, String url, String hdrKey, String hdrValue, Class resultClass)
            throws URISyntaxException, Exception {
        Mono<T> response = (Mono<T>) client
                .get()
                .uri(new URI(url))
                .header(hdrKey, hdrValue)
                .retrieve()
                .bodyToMono(resultClass).checkpoint("getWithHeader bodyToFlux", false)
                .onErrorResume(e -> {
                    Exception ex = (Exception) e;
                    return Mono.error(ex);
                });
        return (T) response.block();
    }

    public static <T> T putWithHeader(WebClient client, String url, String bodyJson, String hdrKey, String hdrValue, Class resultClass) throws URISyntaxException {

        Mono<T> response = (Mono<T>) client
                .put()
                .uri(new URI(url))
                .header(hdrKey, hdrValue)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(bodyJson))
                .retrieve()
                .bodyToMono(resultClass).checkpoint("at bodyToFlux", true)
                .onErrorResume(e -> {
                        Exception ex = (Exception) e;
                        return Mono.error(ex);
                    });

        return (T) response.block();
    }
    /**
     * delete with basic auth
     * @param client - WebClient instance
     * @param url - String
     * @param user - String
     * @param password - String
     * @throws URISyntaxException
     */
    public static void basicAuthDelete(WebClient client, String url, String user, String password) throws URISyntaxException {
        Mono<ClientResponse> resp = client.delete()
                .uri(new URI(url))
                .headers(headers -> headers.setBasicAuth(user, password))
                .exchange(); //.retrieve();

        ClientResponse clientResponse = resp.block();
        HttpUtils.log.info(LogUtils.formatMessage(String.format("URL: %s, response code: %s", url, clientResponse.rawStatusCode())));
    }

    public static void tokenDelete(WebClient client, String url, String token) throws URISyntaxException {
        Mono<ClientResponse> resp = client.delete()
                .uri(new URI(url))
                .headers(headers -> headers.setBearerAuth(token))
                .exchange(); //.retrieve();

        ClientResponse clientResponse = resp.block();
        HttpUtils.log.info(LogUtils.formatMessage(String.format("tokenDelete - URL: %s, response code: %s", url, clientResponse.rawStatusCode())));
    }

    /**
     * helper to parse the XForwarded header
     * @param header - String
     * @return String
     */
    public static String parseXForwardedHeader(String header) {
        return header.split(" *, *")[0];
    }

    /**
     * helper function to extract the client IP address from an HTTP
     * request.
     * @param request - HttpServletRequest
     * @return String
     */
    public static String getClientIpAddress(HttpServletRequest request) {
        String clientIp;
        String clientXForwardedForIp = request.getHeader("x-forwarded-for");
        if (nonNull(clientXForwardedForIp)) {
            clientIp = parseXForwardedHeader(clientXForwardedForIp);
        }
        else {
            clientIp = request.getRemoteAddr();
        }
        return clientIp;
    }
}
