package com.example.user.handler;

import com.alibaba.fastjson.JSONObject;
import com.example.user.entity.Result;
import com.example.user.entity.User;
import com.example.user.service.UserService;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.multipart.Attribute;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.ReferenceCounted;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
 * @classNmae UserServiceHandler
 */
@ChannelHandler.Sharable
@Component
@Slf4j
public class UserServiceHandler extends ChannelInboundHandlerAdapter {

    public final String URI_USER="/user";

    @Autowired
    public UserService service;

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.channel().flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            long startTime = System.currentTimeMillis();

            FullHttpRequest fullRequest = (FullHttpRequest)msg;

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
            resultJson.put("Head",head);
            Result result = doService(fullRequest);
            resultJson.put("Body",result);
            response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(resultJson.toJSONString().getBytes("UTF-8")));

            response.headers().set("Content-Type", "application/json");
            response.headers().setInt("Content-Length", response.content().readableBytes());
            response.headers().set("globalSeqNo", requestHeader.get("globalSeqNo","null"));
        } catch (Exception e) {
            log.error("user Service 处理出错", e);
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

    private Result doService(FullHttpRequest fullRequest) throws IOException {
        String uri = fullRequest.uri();
        HttpMethod requestMethod = fullRequest.method();
        Result<User> result =null;
        if(uri.startsWith(URI_USER)){
           //---------------test coder start-------------
//            printRequestRaw(fullRequest);
            //---------------test coder end-------------
            //post or put
            if(uri.equals(URI_USER)){

//                ReferenceCountUtil.retain(fullRequest);
                User user = extractUser(fullRequest);
                if(user==null){
                    return null;
                }
                if(requestMethod.equals(HttpMethod.PUT)){
                    service.update(user);
                    return Result.buildSuccess("更新成功",String.valueOf(user.getUserId()));
                }
                if(requestMethod.equals(HttpMethod.POST)){
                    service.insert(user);
                    return Result.buildSuccess("新增成功",String.valueOf(user.getUserUuid()));
                }
            }
            //  /user/{id}
            String id = uri.substring(URI_USER.length()+1);
            if(requestMethod.equals(HttpMethod.GET)){

                User user = service.select(Integer.valueOf(id).intValue());
                return Result.buildSuccess(user);
            }
            if(requestMethod.equals(HttpMethod.DELETE)){

                service.delete(Integer.valueOf(id).intValue());
                return Result.buildSuccess("删除成功",id);
            }

        }
        return null;
    }

    private void printRequestRaw(FullHttpRequest fullRequest) throws IOException {
        ByteBuf byteBuf = fullRequest.copy().content();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while(byteBuf.readableBytes()>0){
            byteBuf.readBytes(bos,byteBuf.readableBytes());
        }
        System.out.println(bos.toString("UTF-8"));
    }


    private User extractUser(FullHttpRequest fullRequest){
        HttpPostRequestDecoder decoder = new HttpPostRequestDecoder(fullRequest);
        List<InterfaceHttpData> httpPostData = decoder.getBodyHttpDatas();
//        decoder.offer(fullRequest);
        List<InterfaceHttpData> httpData = decoder.getBodyHttpDatas();
        Map<String,String> requestParams = new HashMap<>();
        httpData.forEach(param->{
            Attribute  attr=(Attribute)param;
            try {
                requestParams.put(attr.getName(),attr.getValue());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        if(!requestParams.isEmpty()){

            User user = new User();
            user.setUserId(Integer.valueOf(requestParams.get("userId")));
            user.setUserName(requestParams.get("userName"));
            return user;
        }
        return null;
    }
}
