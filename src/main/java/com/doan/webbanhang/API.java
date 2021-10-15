package com.doan.webbanhang;

import com.doan.webbanhang.dto.Duration;
import com.doan.webbanhang.dto.Elements;
import com.doan.webbanhang.dto.GoogleAPI;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@RestController
public class API {

    @Value(value = "${key}")
    private String key;

    @GetMapping(path = "/geocode")
    public GoogleAPI getGeocode() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        String[] t = new String[]{"Yên Thường Gia Lâm Hà Nội", "Khương Đình Thanh Xuân Hà Nội"};
        String origin = URLEncoder.encode("Đinh Công Hạ Hoàng Mai Hà Nội", "UTF-8");
        StringBuilder sb = new StringBuilder();
        sb.append(URLEncoder.encode(t[0], "UTF-8"));
        for (int i = 1; i < t.length; i++) {
            String des = URLEncoder.encode(t[i], "UTF-8");
            sb.append("%7C").append(des);
        }
        Request request = new Request.Builder()
                .url("https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + origin +"&destinations=" + sb.toString() + "&key=" + key)
                .method("GET", null)
                .build();
        ResponseBody responseBody = client.newCall(request).execute().body();
        ObjectMapper objectMapper = new ObjectMapper();
        GoogleAPI googleAPI = objectMapper.readValue(responseBody.string(), GoogleAPI.class);
//        List<Elements> kcClientToWareHouse = googleAPI.getRows().get(0).getElements();
//        int min = Integer.MAX_VALUE;
//        int im = 0;
//        for (int i = 0; i < kcClientToWareHouse.size(); i++) {
//            Duration duration = kcClientToWareHouse.get(i).getDuration();
//            String[] sequence = duration.getText().split(" ");
//            int time = Integer.parseInt(sequence[0]);
//            if (time < min) {
//                min = time;
//                im = i;
//            }
//        }
        return googleAPI;
    }
}
