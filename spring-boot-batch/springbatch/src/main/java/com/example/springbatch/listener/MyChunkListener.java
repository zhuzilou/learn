package com.example.springbatch.listener;

import org.springframework.batch.core.annotation.AfterChunk;
import org.springframework.batch.core.annotation.BeforeChunk;
import org.springframework.batch.core.scope.context.ChunkContext;

/**
 * 注解方式创建Listener
 * Created by endless on 3/19/2020.
 */
public class MyChunkListener {

    @BeforeChunk
    public void beforeChunk(ChunkContext chunkContext) {
        System.out.println(chunkContext.getStepContext().getStepName() + " before...");
    }

    @AfterChunk
    public void afterChunk(ChunkContext chunkContext) {
        System.out.println(chunkContext.getStepContext().getStepName() + " after...");
    }
}
