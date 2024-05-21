package miniNetty.server;//package miniNetty.server;
//
//import miniNetty.handler.EchoServerHandler;
//
//import java.nio.channels.Channel;
//import java.nio.channels.SocketChannel;
//
//public class ServerBootstrap {
//    private EventLoopGroup bossGroup;
//    private EventLoopGroup workerGroup;
//    private Channel channel;
//
//    public ServerBootstrap() {
//        this.bossGroup = new NioEventLoopGroup();
//        this.workerGroup = new NioEventLoopGroup();
//    }
//
//    public void bind(int port) throws InterruptedException {
//        ServerBootstrap bootstrap = new ServerBootstrap();
//        bootstrap.group(bossGroup, workerGroup)
//                .channel(NioServerSocketChannel.class)
//                .childHandler(new ChannelInitializer<SocketChannel>() {
//                    @Override
//                    protected void initChannel(SocketChannel ch) throws Exception {
//                        // 添加自定义处理器
//                        ch.pipeline().addLast(new EchoServerHandler());
//                    }
//                });
//        ChannelFuture future = bootstrap.bind(port).sync();
//        this.channel = future.channel();
//    }
//
//    public void close() throws InterruptedException {
//        if (this.channel != null) {
//            this.channel.close().sync();
//        }
//        this.bossGroup.shutdownGracefully().sync();
//        this.workerGroup.shutdownGracefully().sync();
//    }
//}