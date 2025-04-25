package cool.lazy.cat.sublimecodex.example.event.subscribe.service;

import cool.lazy.cat.sublimecodex.example.event.OnUserJoin;
import org.springframework.stereotype.Service;

/**
 * <p>
 *
 * </p>
 *
 * @author : jason.ma
 * @date : 14/4/2025 16:43
 */
@Service
public class MailService implements OnUserJoin {

    @Override
    public void onUserJoin(String id, String name) {
        System.out.println(this.getClass().getName() + " onUserJoin " + id + " " + name);
    }
}
