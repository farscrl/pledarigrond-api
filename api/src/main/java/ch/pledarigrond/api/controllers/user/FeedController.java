package ch.pledarigrond.api.controllers.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("user/feed")
public class FeedController {

    @GetMapping("")
    ResponseEntity<?> listFeeds() throws IOException {
        // "http://liarumantscha.ch/?ctrl=feed&type=3"
        String[] urls = { "http://liarumantscha.ch/?ctrl=feed&type=1", "http://liarumantscha.ch/?ctrl=feed&type=2" };

        List<String> toReturn = new ArrayList<>();
        for (String url : urls) {
            URL connect = new URL(url);
            BufferedInputStream bis = new BufferedInputStream(connect.openStream());
            int i;
            StringBuilder sb = new StringBuilder();
            while ((i = bis.read()) != -1) {
                sb.append((char) i);
            }
            bis.close();
            toReturn.add(sb.toString());
        }
        return ResponseEntity.ok(toReturn);
    }
}
