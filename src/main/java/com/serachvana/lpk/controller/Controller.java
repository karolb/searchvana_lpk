package com.serachvana.lpk.controller;

import com.serachvana.lpk.Shajt;
import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.codec.binary.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    @PostMapping(path = "/{stuff}", consumes = "application/json", produces = "text/plain")
    public ResponseEntity<String> getAnswerPost(@PathVariable String stuff, @RequestBody Shajt message) {
        String decoded = superDecode(message.getText());
        String decodedStuff = superDecode(stuff);

        System.out.println(decodedStuff);
        System.out.println(decoded);
        System.out.println(message);
        System.out.println("------");

        return ResponseEntity.ok(decoded);
    }

//    @GetMapping(path = "/", consumes = "application/json;charset=UTF-8", produces = "text/plain")
//    public HttpStatus getAnswerGet(@PathVariable String stuff, @RequestBody Object content) {
//        System.out.println(stuff);
//        return HttpStatus.OK;
//    }

    private String superDecode(String s) {
        String decoded = decode(s);
        while (!decoded.contains(" ") && !decoded.isEmpty()) {
            decoded = decode(decoded);
        }
        return decoded;
    }

    public String decode(String s) {
        return StringUtils.newStringUtf8(Base64.decodeBase64(s));
    }
}
