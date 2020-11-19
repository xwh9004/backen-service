package com.example.hello;

import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;

import static io.netty.handler.codec.http.HttpHeaderNames.CONNECTION;
import static io.netty.handler.codec.http.HttpHeaderValues.KEEP_ALIVE;
import static io.netty.handler.codec.http.HttpResponseStatus.NO_CONTENT;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * <p><b>Description:</b>
 *
 * <p><b>Company:</b>
 *
 * @author created by Jesse Hsu at 16:49 on 2020/11/2
 * @version V0.1
 * @classNmae HelloServiceHandler
 */
@Slf4j
public class HelloServiceHandler extends ChannelInboundHandlerAdapter {

    public HelloService service;

    public HelloServiceHandler(HelloService service){
        this.service =service;
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.channel().flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            long startTime = System.currentTimeMillis();

            FullHttpRequest fullRequest = (FullHttpRequest)msg;
//            log.info("HelloServiceHandler channelRead流量接口请求开始，时间为:{}", startTime);
            doHandler(ctx,fullRequest);

        } finally {
            ReferenceCountUtil.release(msg);
        }

    }

    private void doHandler(ChannelHandlerContext ctx,FullHttpRequest fullRequest){

        FullHttpResponse response = null;
        try {
            HttpHeaders requestHeader = fullRequest.headers();
            JSONObject resultJson = new JSONObject();
            JSONObject head = new JSONObject();
            head.put("globalSeqNo",requestHeader.get("globalSeqNo",null));
            head.put("nio",requestHeader.get("nio",null));
            resultJson.put("Head",head);
            JSONObject body = new JSONObject();
            body.put("msg",service.doService());
            resultJson.put("Body",body);
            response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(resultJson.toJSONString().getBytes("UTF-8")));

            response.headers().set("Content-Type", "application/json");
            response.headers().setInt("Content-Length", response.content().readableBytes());
            response.headers().set("globalSeqNo", requestHeader.get("globalSeqNo","null"));
        } catch (Exception e) {
            log.error("Hello Service 处理测试接口出错", e);
            response = new DefaultFullHttpResponse(HTTP_1_1, NO_CONTENT);
        } finally {
            if (fullRequest != null) {
                if (!HttpUtil.isKeepAlive(fullRequest)) {
                    ctx.write(response).addListener(ChannelFutureListener.CLOSE);
                } else {
                    response.headers().set(CONNECTION, KEEP_ALIVE);
                    ctx.write(response);
                }

            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
