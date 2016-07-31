package me.wonwoo.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpContentCompressor;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import me.wonwoo.servlet.NettyContext;

/**
 * Created by wonwoo on 2016. 7. 18..
 */
public class ServerInitializer extends ChannelInitializer<SocketChannel> {

  @Override
  protected void initChannel(SocketChannel socketChannel) throws Exception {
    ChannelPipeline p = socketChannel.pipeline();
    p.addLast(new HttpRequestDecoder());
    p.addLast(new HttpObjectAggregator(65536));
    p.addLast(new HttpResponseEncoder());
    p.addLast(new HttpContentCompressor());
    p.addLast(new ChannelServerHandler());
  }
}
