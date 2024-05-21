package miniNetty;// package miniNetty;
//
// import miniNetty.server.ServerBootstrap;
//
// public class Main {
//    public static void main(String[] args) throws InterruptedException {
//        int port = 9000;
//        final ServerBootstrap server = new ServerBootstrap();
//        server.bind(port);
//        System.out.println("Server started, listening on port " + port);
//
//        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
//            try {
//                server.close();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }));
//    }
// }
//
