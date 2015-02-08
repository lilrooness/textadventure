//package server;
//
//import game.Game;
//import game.Messege;
//import game.Player;
//
//import java.io.*;
//import java.net.InetSocketAddress;
//import java.nio.ByteBuffer;
//import java.nio.channels.SelectionKey;
//import java.nio.channels.Selector;
//import java.nio.channels.ServerSocketChannel;
//import java.nio.channels.SocketChannel;
//import java.util.Iterator;
//
///**
// * Created by Joseph Frangoudes on 04/02/2015.
// *
// * @author Joseph Frangoudes
// */
//public class GameServer {
//
//    public static void main(String[] args) {
//
//        Game game = new Game();
//
//        try {
//            ServerSocketChannel server = ServerSocketChannel.open();
//            Selector selector = Selector.open();
//
//            server.configureBlocking(false);
//            server.bind(new InetSocketAddress(7000));
//
//            server.register(selector, SelectionKey.OP_ACCEPT);
//
//            int playersConnected = 0;
//
//            ByteBuffer allocated = ByteBuffer.allocate(100);
//
//            while (true) {
//                selector.select();
//
//                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
//
//                SelectionKey key;
//
//                while (iterator.hasNext()) {
//
//                    key = iterator.next();
//
//                    if (key.isAcceptable()) {
//                        SocketChannel client = ((ServerSocketChannel) key.channel()).accept();
//
//                        if (client != null) {
//                            client.configureBlocking(false);
//                            client.register(selector, SelectionKey.OP_WRITE | SelectionKey.OP_READ, new Player());
//                            System.out.println(String.format("%d clients connected", playersConnected));
//                        }
//                    }
//
//                    if (key.isReadable()) {
//                        SocketChannel client = (SocketChannel) key.channel();
//                        Player player = (Player) key.attachment();
//
//                        int bytesRead = client.read(allocated);
//
//                        if (bytesRead > 0) {
//                            allocated.flip();
//                            ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(allocated.array()));
//                            Messege messege = (Messege) objectInputStream.readObject();
//                            game.processMessege(messege, player);
//                            allocated.clear();
//                        }
//                    }
//
//                    if (key.isWritable()) {
//                        if (game.getPlayerMessege((Player) key.attachment()) != null) {
//
//                            SocketChannel client = (SocketChannel) key.channel();
//
//                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//
//                            new ObjectOutputStream(byteArrayOutputStream)
//                                    .writeObject(game.getPlayerMessege((Player) key.attachment()));
//
//                            allocated.put(byteArrayOutputStream.toByteArray());
//                            client.write(allocated);
//                            allocated.clear();
//                        }
//                    }
////          iterator.remove();
//                }
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//}
