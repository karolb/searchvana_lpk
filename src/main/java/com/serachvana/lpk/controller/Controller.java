package com.serachvana.lpk.controller;

import com.serachvana.lpk.Shajt;
//import javneta.util.HashMap;
import jdk.nashorn.internal.ir.ReturnNode;
import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.codec.binary.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
public class Controller {

    private static final Decoder[] DECODERS = new Decoder[] {
            new Base64Decoder(),
            new RuneDecoder(),
            new ThirdDecode(),
            new NullDecoder()
    };

    @PostMapping(path = "/{stuff}", consumes = "application/json", produces = "text/plain")
    public ResponseEntity<String> getAnswerPost(@PathVariable String stuff, @RequestBody Shajt message) {
        String decoded = DECODERS[message.getNumber() - 1].decode(message.getText());

        System.out.println("======");
        System.out.println(message);
        System.out.println("------");
        System.out.println(stuff);
        System.out.println(decoded);
        System.out.println("======");

        return ResponseEntity.ok(decoded);
    }

//    @GetMapping(path = "/", consumes = "application/json;charset=UTF-8", produces = "text/plain")
//    public HttpStatus getAnswerGet(@PathVariable String stuff, @RequestBody Object content) {
//        System.out.println(stuff);
//        return HttpStatus.OK;
//    }

    public interface Decoder {
        public String decode(String value);
    }

    private static class Base64Decoder implements Decoder {
        @Override
        public String decode(String s) {
            String decoded = decodeSingle(s);
            while (!decoded.contains(" ") && !decoded.isEmpty()) {
                decoded = decodeSingle(decoded);
            }
            return decoded;
        }

        public String decodeSingle(String s) {
            return StringUtils.newStringUtf8(Base64.decodeBase64(s));
        }
    }

    public static class ThirdDecode implements Decoder {

        @Override
        public String decode(String value) {
            String decodes = decodeSingle(value);
            StringBuilder sb = new StringBuilder();
            byte[] array = new byte[decodes.length()];
            for (int i=0; i<decodes.length(); i++) {
                array[i] = (byte) (decodes.charAt(i) + ('a' - '0'));
            }
            String dec = null;
            try {
                dec = new String(array, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return dec;
        }
        public String decodeSingle(String s) {
            return StringUtils.newStringUtf8(Base64.decodeBase64(s));
        }

    }

    public static class RuneDecoder implements Decoder {

        private static final Map<Character, Character> CHAR_MAP = new HashMap<>();
        private static final Map<String, String> REVERSE_MAP = new HashMap<>();

        static {
//            REVERSE_MAP.put("f", Runic.fehu.toString(),
//            REVERSE_MAP.put("u", Runic.uruz.toString()
//            REVERSE_MAP.put("þ", Runic.thurisaz,
//            REVERSE_MAP.put("a", Runic.ansuz,
//            REVERSE_MAP.put("r", Runic.raido,
//            REVERSE_MAP.put("k", Runic.kaunan,
//            REVERSE_MAP.put("c", Runic.kaunan,
//            REVERSE_MAP.put("g", Runic.gebo,
//            REVERSE_MAP.put("w", Runic.wunjo,
//            REVERSE_MAP.put("h", Runic.hagalaz,
//            REVERSE_MAP.put("n", Runic.naudiz,
//            REVERSE_MAP.put("i", Runic.isaz,
//            REVERSE_MAP.put("j", Runic.jera,
//            REVERSE_MAP.put("y", Runic.jera,
//            REVERSE_MAP.put("æ", Runic.eihwaz,
//            REVERSE_MAP.put("ï", Runic.eihwaz,
//            REVERSE_MAP.put("p", Runic.perth,
//            REVERSE_MAP.put("z", Runic.algiz,
//            REVERSE_MAP.put("s", Runic.sowilo,
//            REVERSE_MAP.put("t", Runic.tiwaz,
//            REVERSE_MAP.put("b", Runic.berkanan,
//            REVERSE_MAP.put("e", Runic.ehwaz,
//            REVERSE_MAP.put("m", Runic.mannaz,
//            REVERSE_MAP.put("l", Runic.laguz,
//            REVERSE_MAP.put("ŋ", Runic.ingwaz,
//            REVERSE_MAP.put("o", Runic.othala,
//            REVERSE_MAP.put("d", Runic.dagaz,
//            REVERSE_MAP.put("v", Runic.fehu,
//            REVERSE_MAP.put("ð", Runic.thurisaz,
//            REVERSE_MAP.put("x", Runic.kaunan + Runic.sowilo,
//            REVERSE_MAP.put("ch: Runic.gebo,
//            REVERSE_MAP.put("ij: Runic.ehwaz,
//            REVERSE_MAP.put("cc: Runic.kaunan + Runic.sowilo,
//            REVERSE_MAP.put("th: Runic.thurisaz,
//            REVERSE_MAP.put("eau: Runic.othala,
//            REVERSE_MAP.put("chr: Runic.hagalaz + Runic.raido,
//            REVERSE_MAP.put("ing: Runic.ingwaz,
//            REVERSE_MAP.put("chl: Runic.hagalaz + Runic.laguz,
//            REVERSE_MAP.put("ng: Runic.ingwaz,
//            REVERSE_MAP.put("chj: Runic.hagalaz + Runic.jera,
//            REVERSE_MAP.put("nk: Runic.ingwaz + Runic.kaunan,
//            REVERSE_MAP.put("chw: Runic.hagalaz + Runic.wunjo,
//            REVERSE_MAP.put("ei: Runic.sowilo,
//            REVERSE_MAP.put("ø: Runic.othala + Runic.ehwaz,
//            REVERSE_MAP.put("å: Runic.ansuz + Runic.ansuz,
//            REVERSE_MAP.put("q: Runic.kaunan,



            CHAR_MAP.put('ᚫ', 'a');
            CHAR_MAP.put('ᚤ', 'y');
            CHAR_MAP.put('ᚳ', 'k');
//            CHAR_MAP.put("ᚲ", "x");
//            CHAR_MAP.put("ᙟ", "x");
//            CHAR_MAP.put("ᚢ", "x");
//            CHAR_MAP.put("ᚠ", "x");
//            CHAR_MAP.put("ᚨ", "x");
//            CHAR_MAP.put("ᚭ", "x");
//            CHAR_MAP.put("ᚱ", "x");
            CHAR_MAP.put('ᚴ', 'k');
//            CHAR_MAP.put("ᚥ", "x");

            CHAR_MAP.put('ᚠ', 'f');
            CHAR_MAP.put('ᚢ', 'u');
            CHAR_MAP.put('ᚦ', 'þ');
            CHAR_MAP.put('ᚨ', 'a');
            CHAR_MAP.put('ᚱ', 'r');
            CHAR_MAP.put('ᚲ', 'k');
            CHAR_MAP.put('ᚷ', 'g');
            CHAR_MAP.put('ᚹ', 'w');
//            CHAR_MAP.put('ᚺ ᚻ', 'h');
            CHAR_MAP.put('ᚾ', 'n');
            CHAR_MAP.put('ᛁ', 'i');
            CHAR_MAP.put('ᛃ', 'j');
            CHAR_MAP.put('ᛇ', 'ï');
            CHAR_MAP.put('ᛈ', 'p');
            CHAR_MAP.put('ᛉ', 'z');
            CHAR_MAP.put('ᛊ', 's');
            CHAR_MAP.put('ᛋ', 's');
            CHAR_MAP.put('ᛏ', 't');
            CHAR_MAP.put('ᛒ', 'b');
            CHAR_MAP.put('ᛖ', 'e');
            CHAR_MAP.put('ᛗ', 'm');
            CHAR_MAP.put('ᛚ', 'l');
//            CHAR_MAP.put("ᛜ ᛝ", "ŋ");
            CHAR_MAP.put('ᛟ', 'o');
            CHAR_MAP.put('ᛞ', 'd');
        }

        @Override
        public String decode(String value) {
//            Set<Character> missing = new HashSet<>();

            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < value.length(); i++) {
                int result = (int) value.charAt(i) - (int) 'ᚠ' + (int) 'a';
                builder.append((char) result);
//                if (translated == null) {
//                    missing.add(c);
////                    System.out.printf("Could not find: %s\n", c);
//                } else {
//                    builder.append(translated);
//                }
            }

//            System.out.printf("Miissing %d characters", missing.size());

            return builder.toString();
        }


//        enum Runic {
//                fehu("ᚠ"),
//                uruz("ᚢ"),
//                thurisaz("ᚦ"),
//                ansuz("ᚨ"),
//                raido("ᚱ"),
//                kaunan("ᚲ"),
//                gebo("ᚷ"),
//                wunjo("ᚹ"),
//                hagalaz("ᚺ"),
//                naudiz("ᚾ"),
//                isaz("ᛁ"),
//                jera("ᛃ"),
//                eihwaz("ᛇ"),
//                perth("ᛈ"),
//                algiz("ᛉ"),
//                sowilo("ᛋ"),
//                tiwaz("ᛏ"),
//                berkanan("ᛒ"),
//                ehwaz("ᛖ"),
//                mannaz("ᛗ"),
//                laguz("ᛚ"),
//                ingwaz("ᛜ"),
//                othala("ᛟ"),
//                dagaz("ᛞ");
//
//            private final String c;
//
//            Runic(String c) {
//                this.c = c;
//            }
//
//
//            @Override
//            public String toString() {
//                return c;
//            }
//        }


    }

    private static class NullDecoder implements Decoder {

        @Override
        public String decode(String value) {
            return value;
        }
    }

}
