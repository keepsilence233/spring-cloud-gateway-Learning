package qx.leizige.verification.controller;

import groovy.util.logging.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import qx.leizige.verification.dto.Hello;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping(value = "/verification")
public class VerificationHandler {

    Logger log;

    {
        log = LoggerFactory.getLogger(VerificationHandler.class);
    }

    /* 校验网关过滤器是否添加了请求头 X-Request-red */
    @GetMapping(value = "/addRequestHeader")
    public String addRequestHeader() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        String value = request.getHeader("X-Request-red");
        log.info("X-Request-red = {}", value);
        return value;
    }

    /* 校验网关过滤器是否添加查询参数 red=blue */
    @GetMapping(value = "/addRequestParameter")
    public String addRequestParameter(@RequestParam String red) {
        log.info("red = {}", red);
        return red;
    }


    /* 校验网关过滤器是否替换了X-Request-red请求头的值 */
    @PostMapping(value = "/mapRequestHeader")
    public String mapRequestHeader(@RequestHeader(value = "X-Request-red", required = false) String requestRed) {
        log.info("X-Request-red = {}", requestRed);
        return requestRed;
    }


    /* 校验 prefixPath GatewayFilter factory */
    @GetMapping(value = "/hello")
    public String hello() {
        log.info("hello......");
        return "hello";
    }


    /* 校验redirectTo重定向 */
    @GetMapping(value = "/redirectTo")
    public String redirectTo() {
        return "redirectTo";
    }


    /* 校验 removeRequestHeader */
    @PostMapping(value = "/removeRequestHeader")
    public String removeRequestHeader(@RequestHeader(value = "X-Request-Foo", required = false) String foo) {
        log.info("foo = {}", foo == null ? "null" : foo);
        return foo == null ? "null" : foo;
    }


    /* 校验 setPath */
    @GetMapping(value = "/setPath")
    public String setPath() {
        return "setPath";
    }

    /* 校验 setRequestHeader*/
    @GetMapping(value = "/setRequestHeader")
    public String setRequestHeader(@RequestHeader(value = "X-Request-Red", required = false) String red) {
        log.info("red = {}", red == null ? "red" : red);
        return red == null ? "null" : red;
    }

    /* 校验requestSize */
    @PostMapping(value = "/upload")
    public String requestSize(@RequestParam MultipartFile file) {
        log.info("fileName:{} , fileSize:{}", file.getOriginalFilename(), file.getSize());
        return file.getOriginalFilename();
    }

    /* 校验 modifyRequestBody */
    @PostMapping(value = "/modifyRequestBody")
    public ResponseEntity<Hello> modifyRequestBody(@RequestBody Hello hello) {
        log.info("hello:{} ", hello);
        return ResponseEntity.ok(hello);
    }

    /* 校验 modifyResponseBody*/
    @PostMapping(value = "/modifyResponseBody")
    public ResponseEntity<Hello> modifyResponseBody() {
        return ResponseEntity.ok(new Hello("hello modifyresponsebody"));
    }

}
