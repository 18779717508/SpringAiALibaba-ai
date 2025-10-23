package com.atguigu.study.controller;

import jakarta.annotation.Resource;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class StreamOutputController {

    @Resource(name = "deepseek")
    private ChatModel deepseekChatModel;
    @Resource(name = "qwen")
    private ChatModel qwenChatModel;

    /**  V1
     * http://localhost:8004/stream/chatflux1
     * @param msg
     * @return
     */
    @GetMapping("/stream/chatflux1")
    public Flux<String> stream(@RequestParam(name = "msg",defaultValue = "你是谁") String msg)
    {
        return deepseekChatModel.stream(msg);
    }
    @GetMapping("/stream/chatflux2")
    public Flux<String> stream2(@RequestParam(name = "msg",defaultValue = "你是谁") String msg)
    {
        return qwenChatModel.stream(msg);
    }

    /**  V2
     * http://localhost:8004/stream/chatflux3
     * @param msg
     * @return
     */

    //V2 通过ChatClient实现stream实现流式输出
    @Resource(name = "deepseekChatClient")
    private ChatClient deepseekChatClient;
    @Resource(name = "qwenChatClient")
    private ChatClient qwenChatClient;

    @GetMapping("/stream/chatflux3")
    public Flux<String> stream3(@RequestParam(name = "msg",defaultValue = "你是谁") String msg)
    {
        return deepseekChatClient.prompt(msg).stream().content();
    }

    @GetMapping("/stream/chatflux4")
    public Flux<String> stream4(@RequestParam(name = "msg",defaultValue = "你是谁") String msg){
        return qwenChatClient.prompt(msg).stream().content();
    }

}
