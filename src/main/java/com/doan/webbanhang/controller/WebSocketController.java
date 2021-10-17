package com.doan.webbanhang.controller;

import com.doan.webbanhang.dto.NotificationDTO;
import com.doan.webbanhang.dto.Result;
import com.doan.webbanhang.entity.Bill;
import com.doan.webbanhang.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class WebSocketController {

    @MessageMapping("/hello")
    @SendTo("/topic/hi")
    public NotificationDTO greeting(NotificationDTO notificationDTO) throws Exception {
        return new NotificationDTO(notificationDTO.getNotification());
    }
}
