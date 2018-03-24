package heartbeat.handler;

import com.simon.heartbeat.common.RequestVo;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.net.InetAddress;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ClientHeartBeatHandler extends ChannelHandlerAdapter{

    private ScheduledExecutorService service = Executors.newScheduledThreadPool(1);

    private ScheduledFuture<?> heartBeat;

    private InetAddress address;

    private static final String SUCCESS_KEY = "OK";

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof String) {
            String result = (String)msg;
            if (SUCCESS_KEY.equals(result)) {
                this.heartBeat = service.scheduleWithFixedDelay(new HeartBeatTask(ctx),0,2, TimeUnit.SECONDS);
                System.out.println(this.heartBeat.toString());
                System.out.println(result);
            } else {
                System.out.println(result);
            }
        } else {
            System.out.println(msg.toString());
        }
    }

    /**
     * 给服务端发送心跳信息前的身份验证
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        address = InetAddress.getLocalHost();
        String ip = address.getHostAddress();
        String key = "123";

        String auth = ip + "," + key;
        System.out.println(auth);
        ctx.writeAndFlush(auth);
    }


    private class HeartBeatTask implements Runnable {

        private final ChannelHandlerContext ctx;

        public HeartBeatTask(final ChannelHandlerContext ctx) {
            this.ctx = ctx;
        }

        public void run() {
            try {
                RequestVo vo = new RequestVo();
                vo.setId(UUID.randomUUID().toString().replace("-",""));
                vo.setRequestMessage("i am OK!!!!!!!!!!");
                ctx.writeAndFlush(vo);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
