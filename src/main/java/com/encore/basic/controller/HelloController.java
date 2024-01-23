package com.encore.basic.controller;

import com.encore.basic.domain.Hello;
import com.fasterxml.jackson.core.util.BufferRecycler;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.tomcat.jni.Buffer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Map;

// 모든 요청에 ResponseBody를 붙이고 싶다면, RestController사용
@Controller
// 클래스 차원에서 url경로를 지정하고 싶다면. @RequestMapping 을 클래스 위에 선언하면서 경로 지정
@RequestMapping("hello")
public class HelloController {
    @ResponseBody // data 만을 return 할때
    @GetMapping("string") //localhost:8080 /test 를 가면 test로 이동함
//    @RequestMapping(value = "string",method = RequestMethod.GET) 위애랑 같은 의미
//    responsebod가 없으면 static에 hellow string html 파일 리턴
    public String screen() {
        return "hello String";
    }

    @GetMapping("screen")
    public String helloScreen() {
//        screen.html찾으러 출발
        return "screen";
    }

    @GetMapping("screen-model-param")
//    ? name = hongildong 의 방식으로 호출 ( 파라미터 호출 방식 )
    public String helloScreenModel(Model model, @RequestParam(value = "name") String name) {

//        화면에 data를 넘기고 싶을때는 model객체 사용
        model.addAttribute("myData", name);
        return "screen";
    }

//    pathvariable 방식은 url을 통해 자원의 구조를 명확하게 표현할 수 있어,
//    좀더 retful API 디자인에 적합하다. (

    @GetMapping("screen-model-path/{id}")
    public String helloScreenModelPath(Model model, @PathVariable int id) {
        model.addAttribute("myData", id);
        return "screen";
    }


    @GetMapping("json")
    @ResponseBody
//    responsebod가 없으면 static에 hellow string html 파일 리턴
//    objectmapper에서 직렬화 해줘 json타입으로 나온다
    public Hello helloJson() {
        Hello hello = new Hello();
        hello.setName("송보석");
        hello.setPassword("1234");
        hello.setEmail("fa7271@naver.com");
        System.out.println(hello);
        return hello;
    }

    //    form 태그로 x-www 데이터 처리
    @GetMapping("form-screen")
    public String formScreen() {
        return "hello-form-screen";
    }

    @PostMapping("form-post-handle")
    @ResponseBody
//    form태그를 통한 body의 데이터 형태가
    public String fromPostHandle(@RequestParam(value = "name") String name,
                                 @RequestParam(value = "email") String email,
                                 @RequestParam(value = "password") String password) {
        System.out.println("name = " + name);
        System.out.println("email = " + email);
        System.out.println("password = " + password);
        return "정상처리";
    }

    @PostMapping("/form-post-handle2")
    @ResponseBody
    public String fromPostHandle2(Hello hello) {
        System.out.println(hello);
        return "정상처리";
    }


    //    json데이터 처리
    @GetMapping("json-screen")
    public String jsonScreen() {
        return "hello-json-screen";
    }

    @PostMapping("json-post-handle1")
    @ResponseBody
//    @RequestBody는 json으로 post 요청이 들어왔을 경우, body에서 data를 꺼내기 위해 사용
    public String jsonPostHandle1(@RequestBody Map<String, String> body) {
        System.out.println(body.get("name"));
        System.out.println(body.get("email"));
        System.out.println(body.get("password"));
//        hello 객체 만들어줘야함
        Hello hello = new Hello();
        hello.setName(body.get("name"));
        hello.setEmail(body.get("email"));
        hello.setPassword(body.get("password"));
        return "ok";
    }

    @PostMapping("json-post-handle2")
    @ResponseBody
    public String jsonPostHandle2(@RequestBody JsonNode body) {
        System.out.println(body.get("name").asText());
        System.out.println(body.get("email").asText());
        System.out.println(body.get("password").asText());

        Hello hello = new Hello();
        hello.setName(body.get("name").asText());
        hello.setEmail(body.get("email").asText());
        hello.setPassword(body.get("password").asText());

        return "ok";
    }

    //    💎그냥 객체로 받기 > 이거 사용
//    Spring 에서 Hello 클래스의 인스턴스를 자동 매핑하여 생성
//    form-data 형식, 즉 x-www-url 인코딩 형식의 경우 사용
//    이를 데이터 바인딩이라고 부른다. (Hello 클래스의 Setter 필수)
    @PostMapping("json-post-handle3")
    @ResponseBody
    public String jsonPostHandle3(@RequestBody Hello hello) {
        System.out.println(hello);
        return "ok";
    }

    @PostMapping("httpservlet")
    @ResponseBody
    public String HttpServletTest(HttpServletRequest req) {
//        HttpServletRequest객체에서 header정보 추출
        System.out.println(req.getContentType());
        System.out.println(req.getMethod());

//        session: 로그인(auth) 정보에서 필요한 정보값을 추출할때 많이 사용
        System.out.println(req.getSession());
        System.out.println(req.getHeader("Accept"));

//        HttpServletRequest객체에서 header정보 추출
        System.out.println(req.getParameter("test1"));
        System.out.println(req.getParameter("test2"));
//        req.getReader() 를 통해 BufferedReader로 받아 직접 파싱 // 할 일 없음

        return "OK";
    }

    @GetMapping("/hello-servlet-jsp-get")
    public String helloServletJspGet(Model model) {
        model.addAttribute("myData", "jsp test data");
        return "hello-jsp";
    }

    public void helloBuilderTest() {
        Hello hello = Hello.builder()
                .name("song")
                .email("fa7271@Naver.com")
                .build();
    }
}
