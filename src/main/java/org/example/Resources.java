package org.example;
//
//import java.io.FileInputStream;
//import java.io.InputStream;
//import java.security.KeyStore;
//import javax.net.ssl.HttpsURLConnection;
//import javax.net.ssl.SSLContext;
//import javax.net.ssl.TrustManagerFactory;
//import java.net.URL;
//
//public class Resources{
//
//    public static void main(String[] args) throws Exception {
//        ;
//        // Load the trust store from the resources folder
//               InputStream trustStoreStream = Resources.class.getResourceAsStream("/betfair-akm-client.jks");
//                // Load the trust store
//                char[] password = "howm@nybets".toCharArray(); // Password to access the trust store
//                KeyStore trustStore = KeyStore.getInstance("JKS");
//                trustStore.load(trustStoreStream, password);
//
//                // Initialize a TrustManagerFactory with the trust store
//                TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
//                trustManagerFactory.init(trustStore);
//
//                // Create an SSL context
//                SSLContext sslContext = SSLContext.getInstance("TLS");
//                sslContext.init(null, trustManagerFactory.getTrustManagers(), null);
//
//                // Use the SSL context to create a HTTPS connection
//                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) new URL("https://identitysso.nxt.com.betfair/api/login").openConnection();
//                httpsURLConnection.setSSLSocketFactory(sslContext.getSocketFactory());
//
//                // Perform the connection
//                httpsURLConnection.connect();
//
//                // Additional operations on the connection if needed
//            }
//        }