package io.github.kimmking.gateway.filter;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;

@ChannelHandler.Sharable
public class HttpRequestFilterImpl extends SimpleChannelInboundHandler<FullHttpRequest> implements HttpRequestFilter {
    @Override
    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        fullRequest.headers().set("nio", "yangwangwang");
        ctx.read();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, FullHttpRequest fullHttpRequest) throws Exception {
        this.filter(fullHttpRequest, channelHandlerContext);
    }
}
