package com.megvii.springboot.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class RestClientController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value="/sendFile")
    public String sendFile() throws URISyntaxException, IOException {
        MultiValueMap<String, Object> multiPartBody = new LinkedMultiValueMap<>();
        multiPartBody.add("file", new ClassPathResource("tmp/test.txt"));
        RequestEntity<MultiValueMap<String, Object>> requestEntity = RequestEntity
                .post(new URI("http://127.0.0.1:8888/uploads/upload1"))
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(multiPartBody);
        ResponseEntity<String> response = restTemplate.exchange(requestEntity, String.class);
        return "sueecess";
    }

    @RequestMapping(value="/receiveFile")
    public void receiveFile(HttpServletResponse response) throws URISyntaxException, IOException {
        // 小文件
        RequestEntity requestEntity = RequestEntity.get(
                new URI("http://127.0.0.1:8080/downLoad.html")).build();
        ResponseEntity<byte[]> responseEntity = restTemplate.exchange(requestEntity, byte[].class);
        byte[] downloadContent = responseEntity.getBody();
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"myframe.html\"");
        response.addHeader("Content-Length", "" + downloadContent.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        IOUtils.write(downloadContent, response.getOutputStream());
    }

    /**
     * 大文件
     * @param response
     * @return
     * @throws URISyntaxException
     */
    @RequestMapping(value="/bigFile")
    public String bigFile(HttpServletResponse response) throws URISyntaxException {
        //大文件
        ResponseExtractor<ResponseEntity<File>> responseExtractor =
                response1 -> {
                    File rcvFile = File.createTempFile("rcvFile", "zip");
                    FileCopyUtils.copy(response1.getBody(), new FileOutputStream(rcvFile));
                    return ResponseEntity.status(response1.getStatusCode()).
                            headers(response1.getHeaders()).body(rcvFile);
                };
        RequestCallback requestCallback = clientHttpRequest -> {

        };
        ResponseEntity<File> fileBody = this.restTemplate.execute(
                new URI("http://127.0.0.1:8080/downLoad.html"),
                HttpMethod.GET,  requestCallback, responseExtractor);
        File file = fileBody.getBody();
        file.renameTo(new File("D:/Users/big.hmtl"));
        return "success";
    }
}
