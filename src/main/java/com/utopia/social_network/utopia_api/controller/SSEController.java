package com.utopia.social_network.utopia_api.controller;

import com.utopia.social_network.utopia_api.service.SSEService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import com.utopia.social_network.utopia_api.model.HttpNotification;
import com.utopia.social_network.utopia_api.utils.APIResult;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.annotation.PreDestroy;
import org.springframework.web.bind.annotation.DeleteMapping;

@CrossOrigin
@RestController
@RequestMapping("/api/SSE")
public class SSEController {

    @Autowired
    private SSEService notificationService;
    
    @GetMapping(value = "{token}/callNotification")
    public SseEmitter callNotification(@PathVariable String token) {
        String id = String.valueOf(System.currentTimeMillis());
        SseEmitter emitter = new SseEmitter();
        ExecutorService sseMvcExecutor = Executors.newSingleThreadExecutor();
        sseMvcExecutor.execute(() -> {
            try {
                String tmp = token;
                if (tmp == null || tmp.isEmpty()) {
                    emitter.complete();
                    return;
                }
                HttpNotification httpNotification = new HttpNotification();
                httpNotification.setId(id);
                httpNotification.setToken(token);
                httpNotification.setMessagers(new ArrayList<>());
                notificationService.addNotification(httpNotification);
                
                emitter.send("SSE Notification connected" +  "\r\n", MediaType.TEXT_EVENT_STREAM);
                while (true) {
                    Thread.sleep(2000);

                    HttpNotification notification = notificationService.getHttpNotifications().stream()
                            .filter(s -> s.getId().compareTo(id) == 0)
                            .findFirst()
                            .orElse(null);
                    
                    if (notification != null) {
                        List<String> list = notification.getMessagers();
                        LocalDateTime currentTime = LocalDateTime.now();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                        String formattedTime = currentTime.format(formatter);

                        if (!list.isEmpty()) {
                            for (String s : list) {
                                System.out.println(s);
                                emitter.send(" message: " + s + " | time: " + formattedTime+ "\r\n", MediaType.TEXT_EVENT_STREAM);
                                Thread.sleep(100);
                            }
                            list.clear();
                        }
                        
                    }
                }
            }catch(Exception e) {
                emitter.completeWithError(e);  
                notificationService.removeById(id);
                System.out.println("close connection 1 ");
            }
        });

        emitter.onCompletion(() -> {
            System.out.println("close connection 2");
            sseMvcExecutor.shutdown();
            notificationService.removeById(id);
        });
        
        emitter.onTimeout(() -> {
            System.out.println("close connection (timeout) 3");
            sseMvcExecutor.shutdown();
            notificationService.removeById(id);
        });

        return emitter;
    }
    
    @GetMapping(value = "{token}/sendMessage/{message}")
    private APIResult getListPostForViewer(String token,String message){
        notificationService.addMessageForClient(token, message);
        return new APIResult(200,"Ok",null,null);
    }
    
    @GetMapping(value = "getListConnection")
    private APIResult getListConnection(){
        return new APIResult(200,"Ok",null,notificationService.getHttpNotifications());
    }
    
    @DeleteMapping(value = "removeConnection")
    private APIResult removeConnection(String id){
        notificationService.removeById(id);
        return new APIResult(200,"Ok",null,null);
    }
}

