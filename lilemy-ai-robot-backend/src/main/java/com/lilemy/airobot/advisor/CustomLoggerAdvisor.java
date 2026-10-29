package com.lilemy.airobot.advisor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClientRequest;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.ai.chat.client.ChatClientMessageAggregator;
import org.springframework.ai.chat.client.advisor.api.CallAdvisor;
import org.springframework.ai.chat.client.advisor.api.CallAdvisorChain;
import org.springframework.ai.chat.client.advisor.api.StreamAdvisor;
import org.springframework.ai.chat.client.advisor.api.StreamAdvisorChain;
import reactor.core.publisher.Flux;

/**
 * 自定义日志记录 Advisor
 *
 * @author lilemy
 * @date 2026-10-29
 */
@Slf4j
public class CustomLoggerAdvisor implements CallAdvisor, StreamAdvisor {
    @Override
    public ChatClientResponse adviseCall(ChatClientRequest chatClientRequest, CallAdvisorChain callAdvisorChain) {
        log.info("普通模型请求入参: {}", chatClientRequest);
        ChatClientResponse chatClientResponse = callAdvisorChain.nextCall(chatClientRequest);
        log.info("普通模型请求出参: {}", chatClientResponse);
        return chatClientResponse;
    }

    @Override
    public Flux<ChatClientResponse> adviseStream(ChatClientRequest chatClientRequest, StreamAdvisorChain streamAdvisorChain) {
        return Flux.defer(() -> {
            log.info("流式模型请求入参: {}", chatClientRequest);
            return new ChatClientMessageAggregator().aggregateChatClientResponse(
                    streamAdvisorChain.nextStream(chatClientRequest),
                    chatClientResponse -> log.info("流式模型请求出参: {}", chatClientResponse));
        });
    }

    @Override
    public String getName() {
        // 获取类名称
        return this.getClass().getSimpleName();
    }

    @Override
    public int getOrder() {
        // order 值越小，越先执行
        return 1;
    }
}
