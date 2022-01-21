package qx.leizige.verification.controller;

import groovy.util.logging.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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
}
