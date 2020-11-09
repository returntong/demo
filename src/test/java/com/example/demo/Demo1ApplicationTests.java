package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@SpringBootTest
class Demo1ApplicationTests {

    @Value("${my.secret:${random.uuid}}")
    private String string;


    @Test
    void contextLoads2() {

    }
    //https://tcc.taobao.com/cc/json/mobile_tel_segment.htm

    @Test
    void contextLoads() {
        WebClient webClient = WebClient.builder()
                .baseUrl("http://jsonplaceholder.typicode.com") //用于测试的接口地址
                .build();

        Mono<String> mono = webClient
                .get()  //GET 请求
                .uri("/posts/1")  // 请求路径,注意为了制造异常，这里是错的
                .retrieve()  //获取请求结果
                .bodyToMono(String.class)  //用Mono接收单个非集合对象数据
                .doOnError(Exception.class, err -> {  //处理异常
                    System.out.println(LocalDateTime.now() + "---发生错误：" + err.getMessage());
                });
        //第一个参数仍然表示重试3次 第二个参数表示按指数增长的时间间隔重试，第一次重试间隔5秒，第二次间隔10秒（5 x2），第三次间隔20秒（5x2x2）
        //.retryBackoff(3, Duration.ofSeconds(5));

        System.out.println(mono.block()); //block()阻塞式获取响应结果
    }


    @Test
    void contextLoads1() throws InterruptedException {
        String s = null;
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("tel", "15333065057");
        // 发送请求
        WebClient.create("https://tcc.taobao.com/cc/json/mobile_tel_segment.htm")
                .post() // 发送POST 请求
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)  //表单数据类型
                .body(BodyInserters.fromFormData(map))  //表单数据
                .retrieve()// 获取响应体
                .bodyToMono(String.class)
                .subscribe(string ->
                        System.out.println(string)
                );//响应数据类型转换
        //为了避免测试用例主线程执行完成，导致看不到异步处理结果
        Thread.sleep(500);

    }

    @Test
    void contextLoads3() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        //设置请求头
        HttpHeaders headers = new HttpHeaders();
        //设置参数
        LinkedMultiValueMap<String, Object> body = new LinkedMultiValueMap();

        body.add("tel", "18866526522");
        HttpEntity<LinkedMultiValueMap<String, Object>> entity = new HttpEntity(body, headers);
        //请求接口，并接收返回的信息
        String s = restTemplate.exchange("http://jsonplaceholder.typicode.com/posts", HttpMethod.POST, entity, String.class).getBody();

        System.out.println(s);
    }

}
