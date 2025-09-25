package com.example.mspl_connect.PayslipController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.example.mspl_connect.PayslipService.ConnectivityService;


@RestController
public class ConnectivityController {

    @Autowired
    private ConnectivityService connectivityService;

    @GetMapping("/connectivity-status")
    public SseEmitter getConnectivityStatus() {
        SseEmitter emitter = new SseEmitter();
        new Thread(() -> {
            try {
                while (true) {
                    emitter.send(connectivityService.isConnected());
                    Thread.sleep(1000); // Send status every 5 seconds
                }
            } catch (Exception e) {
                emitter.completeWithError(e);
            }
        }).start();
        return emitter;
    }
}
